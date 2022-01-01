package model;

import model.Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameModel {

    private final Player player;
    private final List<MovingObject> asteroids;
    private final AsteroidCreator asteroidCreator;
    private final EnemyShipSpawner enemyShipSpawner;

    public GameModel(){
        this.player = new Player(new Position(100, 100));
        this.asteroidCreator = new AsteroidCreator(new Random());
        this.asteroids = initAsteroids(4);
        this.enemyShipSpawner = new EnemyShipSpawner(player);
    }

    public List<MovingObject> initAsteroids(Integer numberAsteroids){
        List<MovingObject> asteroidsConstructor = new ArrayList<>();
        for(int i = 0; i < numberAsteroids;i++){
            asteroidsConstructor.add(asteroidCreator.create());
        }
        return asteroidsConstructor;
    }

    public List<MovingObject> getAsteroids() {return asteroids;}

    public EnemyShipSpawner getEnemyShipSpawner() {
        return enemyShipSpawner;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(long dt){
        player.update(dt);
        for (LaserBeam laserBeam : player.getLaserBeams())
            laserBeam.update(dt);

        for(MovingObject asteroid : asteroids)
            asteroid.update(dt);

        enemyShipSpawner.update(dt);
    }
}
