package asteroids.model;

import asteroids.Constants;
import asteroids.control.MusicManager;
import asteroids.control.Sounds;
import asteroids.model.Creator.PlayerLaserBeamCreator;
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

    public GameModel() {
        this.entities = new ArrayList<>();
        player = new Player(new Position(Constants.WIDTH / 2.0, Constants.HEIGHT / 2.0));
        player.setLaserBeamCreator(new PlayerLaserBeamCreator(player, getEntities()));
        entities.add(player);
        this.asteroidSpawner = new AsteroidSpawner(getEntities());
        this.enemyShipSpawner = new EnemyShipSpawner(player, getEntities());
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public void update(long dt) {
        getEnemyShipSpawner().update(dt);
        getAsteroidSpawner().update();

        for (int i = 0; i < getEntities().size(); i++)
            getEntities().get(i).update(dt);

        checkCollisions();
    }

    public AsteroidSpawner getAsteroidSpawner() {
        return asteroidSpawner;
    }

    public EnemyShipSpawner getEnemyShipSpawner() {
        return enemyShipSpawner;
    }

    public void checkCollisions() {
        for (int i = 0; i < getEntities().size() - 1; i++) {
            for (int j = i + 1; j < getEntities().size(); j++) {
                MovingObject c1 = getEntities().get(i);
                MovingObject c2 = getEntities().get(j);

                if (c1 instanceof Asteroid && c2 instanceof Asteroid)
                    continue;

                if (c1.collide(c2) && c1.isAlive() && c2.isAlive()) {
                    getScore(c1, c2);
                    getScore(c2, c1);

                    MusicManager.getInstance().start(Sounds.DESTRUCTION);

                    c1.dies();
                    c2.dies();

                    if (c1 instanceof Asteroid)
                        asteroidSplitter((Asteroid) c1);
                    else if (c2 instanceof Asteroid)
                        asteroidSplitter((Asteroid) c2);

                }
            }
        }
        getEntities().removeIf(c -> !c.isAlive());
    }

    private void getScore(MovingObject c1, MovingObject c2) {

        boolean hitByPlayer = c1 instanceof Player;
        boolean hitByLaserPlayer = c1 instanceof LaserBeam && ((LaserBeam) c1).isPlayerBeam();
        if (hitByPlayer || hitByLaserPlayer) {
            if (c2 instanceof Asteroid)
                getPlayer().addScore(((Asteroid) c2).getPoints());
            else if (c2 instanceof EnemyShip)
                getPlayer().addScore(((EnemyShip) c2).getPoints());
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void asteroidSplitter(Asteroid c1) {
        if (c1.getAsteroidSize() == Sizes.SMALL) return;
        c1.decreaseSize();
        Asteroid asteroid = getAsteroidSpawner().getAsteroidCreator().create();
        asteroid.setPosition(c1.getPosition().clone());
        asteroid.setVelocity(c1.getVelocity().clone());
        asteroid.getVelocity().scale(-1);
        asteroid.setSize(c1.getAsteroidSize());
        getEntities().add(asteroid);
    }
}