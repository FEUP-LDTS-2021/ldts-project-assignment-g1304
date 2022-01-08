package asteroids.model.Entities;

import java.util.List;
import java.util.Random;

public class EnemyShipSpawner {
    private long timePassed;
    private final Player player;
    private final List<MovingObject> entities;
    private final EnemyShipCreator enemyShipCreator;

    public EnemyShipSpawner(Player player, List<MovingObject> entities){
        this.timePassed = 0;
        this.player = player;
        this.entities = entities;
        this.enemyShipCreator = new EnemyShipCreator(new Random(),player, entities);
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
            entities.add(enemyShipCreator.create());
    }

    public Player getPlayer() {
        return player;
    }
}