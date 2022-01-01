package model;

import model.Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameModel {

    private final Player player;
    private final List<MovingObject> asteroids;
    private final AsteroidCreator asteroidCreator;
    private final List<MovingObject> entities;
    private final LaserBeamCreator laserCreator;

    public GameModel(){
        this.asteroidCreator = new AsteroidCreator(new Random());
        asteroids = initAsteroids(4);
        entities = new ArrayList<>();
        player = new Player(new Position(100, 100));
        this.laserCreator = new LaserBeamCreator(player);
        player.setLaserBeamCreator(laserCreator);
    }

    public LaserBeamCreator getLaserCreator() {
        return laserCreator;
    }

    public List<MovingObject> initAsteroids(Integer numberAsteroids){
        List<MovingObject> asteroidsConstructor = new ArrayList<>();
        for(int i = 0; i < numberAsteroids;i++){
            asteroidsConstructor.add(asteroidCreator.create());
        }
        return asteroidsConstructor;
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public List<MovingObject> getAsteroids() {return asteroids;}

    public Player getPlayer() {
        return player;
    }

    public void update(long dt){
        getPlayer().update(dt);
        for (LaserBeam laserBeam : getLaserCreator().getLaserBeamList())
            laserBeam.update(dt);

        for(MovingObject asteroid : getAsteroids())
            asteroid.update(dt);
        updateEntities();
        checkCollisions();
    }

    public void checkCollisions();

    public void updateEntities();
}
