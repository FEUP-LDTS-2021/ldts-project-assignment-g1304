package asteroids.model.Creator;

import asteroids.model.Entities.Asteroid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import asteroids.utils.DoubleComparables;

import java.util.Random;

public class AsteroidCreatorTest extends Assertions {
    @Test
    void create(){
       Random randMock = Mockito.mock(Random.class);
       Mockito.when(randMock.nextDouble()).thenReturn(0.1);

       Asteroid a = new AsteroidCreator(randMock).create();

       assertEquals(50,a.getPosition().getX());
       assertEquals(50,a.getPosition().getY());
       assertTrue(DoubleComparables.equalDouble(-24.74873734152916,a.getVelocity().getY()));
       assertTrue(DoubleComparables.equalDouble(-24.74873734152916,a.getVelocity().getX()));
    }
}
