package asteroids.model.Creator;

import asteroids.Constants;
import asteroids.model.Entities.Asteroid;
import asteroids.model.Entities.Sizes;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import java.util.Random;

public class AsteroidCreator extends Creator {
    private final Random rand;

    public AsteroidCreator(Random rand){
        this.rand = rand;
    }

    @Override
    public Asteroid create() {
        int rangeMax = Constants.WIDTH;
        Position randomPosition = new Position(0.0,0.0);
        switch (rand.nextInt(4)){
            case 0:
                randomPosition.setY(rangeMax * rand.nextDouble());
                break;
            case 1:
                randomPosition.setX(rangeMax * rand.nextDouble());
                break;
            case 2:
                randomPosition.setX(rangeMax);
                randomPosition.setY(rangeMax * rand.nextDouble());
                break;
            case 3:
                randomPosition.setX(rangeMax * rand.nextDouble());
                randomPosition.setY(rangeMax);
                break;
        }
        rangeMax = 20;
        int rangeMin = -20;
        Vector2d randomVelocity = new Vector2d(rangeMin + (rangeMax - rangeMin) * rand.nextDouble(),
                rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
        randomVelocity.resize(30 + 50 * rand.nextDouble());
        return new Asteroid(randomPosition,randomVelocity, Sizes.LARGE);
    }
}