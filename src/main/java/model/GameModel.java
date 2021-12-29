package model;

import model.Entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    private final Player player;
    private final List<MovingObject> asteroids;
    private final AsteroidCreator asteroidCreator;
    public GameModel(){
        player = new Player(new Position(100, 100));
        this.asteroidCreator = new AsteroidCreator(new Random());
        asteroids = initAsteroids(4);

    }

    public List<MovingObject> initAsteroids(Integer numberAsteroids){
        List<MovingObject> asteroidsConstructor = new ArrayList<>();
        for(int i = 0; i < numberAsteroids;i++){
            asteroidsConstructor.add(asteroidCreator.create());
        }
        return asteroidsConstructor;
    }

    public List<MovingObject> getAsteroids() {return asteroids;}

    public Player getPlayer() {
        return player;
    }

    public void update(long ms){
        player.update(ms);
        for(MovingObject asteroid : asteroids)
            asteroid.update(ms);
    }

}
