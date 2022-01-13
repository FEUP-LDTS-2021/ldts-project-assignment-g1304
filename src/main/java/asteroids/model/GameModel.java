package asteroids.model;

import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Entities.*;
import asteroids.model.Spawner.AsteroidSpawner;
import asteroids.model.Spawner.EnemyShipSpawner;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final AsteroidSpawner asteroidSpawner;
    private final EnemyShipSpawner enemyShipSpawner;
    private final Player player;
    private final List<MovingObject> entities;

    public GameModel(){
        this.entities = new ArrayList<>();
        player = new Player(new Position(Constraints.WIDTH/2.0, Constraints.HEIGHT/2.0));
        player.setLaserBeamCreator(new LaserBeamCreator(player, getEntities()));
        entities.add(player);
        this.asteroidSpawner = new AsteroidSpawner(getEntities());
        this.enemyShipSpawner = new EnemyShipSpawner(player, getEntities());
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public void update(long dt){
        getEnemyShipSpawner().update(dt);
        getAsteroidSpawner().update();

        for (int i = 0; i < getEntities().size(); i++)
            getEntities().get(i).update(dt);

        checkCollisions();
        getEntities().removeIf(c -> !c.isAlive());
    }

    public AsteroidSpawner getAsteroidSpawner() {
        return asteroidSpawner;
    }

    public EnemyShipSpawner getEnemyShipSpawner() {
        return enemyShipSpawner;
    }

    public void checkCollisions() {
        for (int i = 0; i < getEntities().size() - 1; i++) {
            for (int j = i+1; j < getEntities().size(); j++) {
                MovingObject c1 = getEntities().get(i);
                MovingObject c2 = getEntities().get(j);
                if (c1 instanceof Asteroid && c2 instanceof Asteroid)
                    continue;


                if (c1.getCollider().intersects(c2.getCollider())) {
                    c1.dies();
                    c2.dies();
                }
            }
        }
    }

    private void calculeScore(MovingObject c1, MovingObject c2){
    }

    public Player getPlayer() {
        return player;
    }

}
