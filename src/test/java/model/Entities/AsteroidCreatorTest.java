package model.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.DoubleComparables;

import java.util.Random;

public class AsteroidCreatorTest extends Assertions {
    @Test
    void create(){
       Random randMock = Mockito.mock(Random.class);
       Mockito.when(randMock.nextDouble()).thenReturn(0.1);

       Asteroid a =(Asteroid) new AsteroidCreator(randMock).create();

       assertEquals(50,a.getPosition().getX());
       assertEquals(50,a.getPosition().getY());
       assertTrue(DoubleComparables.equalDouble(-24.74873734152916,a.getVelocity().getY()));
       assertTrue(DoubleComparables.equalDouble(-24.74873734152916,a.getVelocity().getX()));
    }
}
