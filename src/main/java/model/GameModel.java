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

    private final List<MovingObject> entities;
    private final LaserBeamCreator laserCreator;

    public GameModel(){
        this.asteroidCreator = new AsteroidCreator(new Random());
        asteroids = initAsteroids(4);
        entities = new ArrayList<>();
        player = new Player(new Position(100, 100));
        this.laserCreator = new LaserBeamCreator(player);
        player.setLaserBeamCreator(laserCreator);
        this.enemyShipSpawner = new EnemyShipSpawner(player);
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

    public EnemyShipSpawner getEnemyShipSpawner() {
        return enemyShipSpawner;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(long dt){

        getPlayer().update(dt);
        for (LaserBeam laserBeam : getLaserCreator().getLaserBeamList())
            laserBeam.update(dt);

        enemyShipSpawner.update(dt);

        for(MovingObject asteroid : getAsteroids())
            asteroid.update(dt);

        updateEntities();
        checkCollisions();
    }

    public void checkCollisions() {
        List<MovingObject> toRemove = new ArrayList<>();
        for (int i = 0; i < getEntities().size() - 1; i++) {
            for (int j = i+1; j < getEntities().size(); j++) {
                MovingObject c1 = getEntities().get(i);
                MovingObject c2 = getEntities().get(j);
                if (c1 instanceof Asteroid && c2 instanceof Asteroid) {
                    continue;
                }
                if (c1.getCollider().intersects(c2.getCollider())) {
                    toRemove.add(c1);
                    toRemove.add(c2);
                }
            }
        }
        for (MovingObject c : toRemove) {
            if (c instanceof Asteroid) {
                getAsteroids().remove(c);
            }
            else if (c instanceof LaserBeam) {
                getLaserCreator().getLaserBeamList().remove(c);
            }
        }
    }


    public void updateEntities() {
        getEntities().clear();
        getEntities().add(getPlayer());
        getEntities().addAll(getAsteroids());
        getEntities().addAll(getLaserCreator().getLaserBeamList());
    }
}
