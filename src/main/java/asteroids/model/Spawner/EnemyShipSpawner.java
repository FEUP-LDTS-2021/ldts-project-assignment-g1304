package asteroids.model.Spawner;

import asteroids.model.Creator.EnemyShipCreator;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;

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

    public List<MovingObject> getEntities() {
        return entities;
    }

    public boolean isSpawnTime(long dt){
        timePassed += dt;
        if(timePassed >= 9000){
            timePassed -= 9000;
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