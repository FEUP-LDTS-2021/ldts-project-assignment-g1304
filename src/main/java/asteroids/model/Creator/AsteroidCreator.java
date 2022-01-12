
package asteroids.model.Creator;

import asteroids.model.Constraints;
import asteroids.model.Entities.Asteroid;
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
        int rangeMin = 0;
        int rangeMax = Constraints.WIDTH;
        Position randomPosition = new Position(0.0,0.0);
        switch (rand.nextInt(4)){
            case 0:
                randomPosition.setX(0.0);
                randomPosition.setY(rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
                break;
            case 1:
                randomPosition.setX(rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
                randomPosition.setY(0.0);
                break;
            case 2:
                randomPosition.setX(rangeMax);
                randomPosition.setY(rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
                break;
            case 3:
                randomPosition.setX(rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
                randomPosition.setY(rangeMax);
                break;
        }
        rangeMax = 20;
        rangeMin = -20;
        Vector2d randomVelocity = new Vector2d(rangeMin + (rangeMax - rangeMin) * rand.nextDouble(),
                rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
        randomVelocity.resize(30 + 50 * rand.nextDouble());
        return new Asteroid(randomPosition,randomVelocity,40);
    }
}