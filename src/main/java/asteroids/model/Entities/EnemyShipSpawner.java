package asteroids.model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShipSpawner {
    private long timePassed;
    private final Player player;
    private final List<EnemyShip> enemyShips;
    private final EnemyShipCreator enemyShipCreator;

    public EnemyShipSpawner(Player player){
        this.timePassed = 0;
        this.player = player;
        this.enemyShips = new ArrayList<>();
        this.enemyShipCreator = new EnemyShipCreator(new Random(),player);
    }
    public List<EnemyShip> getEnemyShips() {
        return enemyShips;
    }

    public boolean isSpawnTime(long dt){
        timePassed += dt;
        if(timePassed >= 5000){
            timePassed -= 5000;
            return true;
        }
        return false;
    }

    public long getTimePassed() {
        return timePassed;
    }

    public void update(long dt) {
        if(isSpawnTime(dt))
           getEnemyShips().add((EnemyShip) enemyShipCreator.create());

        for(EnemyShip enemyShip : getEnemyShips()) {
            enemyShip.update(dt);
            for (LaserBeam laserBeam : enemyShip.getLaserBeams())
                laserBeam.update(dt);
        }
    }

    public Player getPlayer() {
        return player;
    }
}