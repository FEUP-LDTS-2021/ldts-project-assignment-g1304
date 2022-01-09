package asteroids.model.Spawner;

import asteroids.model.Creator.AsteroidCreator;
import asteroids.model.Entities.Asteroid;
import asteroids.model.Entities.MovingObject;

import java.util.List;
import java.util.Random;

public class AsteroidSpawner {

    private final List<MovingObject> entities;
    private final AsteroidCreator asteroidCreator;

    public AsteroidSpawner(List<MovingObject> entities) {
        this.entities = entities;
        this.asteroidCreator = new AsteroidCreator(new Random());
    }

    public boolean spawnCheck() {
        boolean check = true;
        for (MovingObject movingObject : entities) {
            if (movingObject instanceof Asteroid) {
                check = false;
                break;
            }
        }
        return check;
    }

    public void update(){
        if (spawnCheck()) {
            for(int i = 0; i < 4;i++){
                Asteroid asteroid = asteroidCreator.create();
                entities.add(asteroid);
            }
        }
    }
}
