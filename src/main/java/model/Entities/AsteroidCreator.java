package model.Entities;

import model.Position;
import model.physics.Vector2d;

import java.util.Random;

public class AsteroidCreator extends Creator{
    private final Random rand;

    public AsteroidCreator(Random rand){
        this.rand = rand;
    }

    @Override
    public MovingObject create() {
        int rangeMin = 0;
        int rangeMax = 500;
        Position randomPosition = new Position(rangeMin + (rangeMax - rangeMin) * rand.nextDouble(),
                rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
        rangeMax = 20;
        rangeMin = -20;
        Vector2d randomVelocity = new Vector2d(rangeMin + (rangeMax - rangeMin) * rand.nextDouble(),
                rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
        randomVelocity.resize(30 + 50 * rand.nextDouble());
        return new Asteroid(randomPosition,randomVelocity,40);
    }
}