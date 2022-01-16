package asteroids.model.Creator;

import asteroids.model.Entities.Asteroid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class AsteroidCreatorTest extends Assertions {
    @Test
    void create(){
        //given
       Random randMock = Mockito.mock(Random.class);
       Mockito.when(randMock.nextDouble()).thenReturn(0.1);
       Mockito.when(randMock.nextInt(4)).thenReturn(0);

       Random randMock1 = Mockito.mock(Random.class);
       Mockito.when(randMock1.nextDouble()).thenReturn(0.2);
       Mockito.when(randMock1.nextInt(4)).thenReturn(1);

       Random randMock2 = Mockito.mock(Random.class);
       Mockito.when(randMock2.nextDouble()).thenReturn(0.3);
       Mockito.when(randMock2.nextInt(4)).thenReturn(2);

       Random randMock3 = Mockito.mock(Random.class);
       Mockito.when(randMock3.nextDouble()).thenReturn(0.4);
       Mockito.when(randMock3.nextInt(4)).thenReturn(3);

       //when
       Asteroid a = new AsteroidCreator(randMock).create();
       Asteroid a1 = new AsteroidCreator(randMock1).create();
       Asteroid a2 = new AsteroidCreator(randMock2).create();
       Asteroid a3 = new AsteroidCreator(randMock3).create();

       //then
       assertEquals(0.0,a.getPosition().getX());
       assertEquals(50,a.getPosition().getY());
       assertEquals(-24.74873734152916,a.getVelocity().getY());
       assertEquals(-24.74873734152916,a.getVelocity().getX());

       assertEquals(100,a1.getPosition().getX());
       assertEquals(0,a1.getPosition().getY());
       assertEquals(-28.284271247461902,a1.getVelocity().getY());
       assertEquals(-28.284271247461902,a1.getVelocity().getX());

       assertEquals(500.0,a2.getPosition().getX());
       assertEquals(150,a2.getPosition().getY());
       assertEquals(-31.819805153394636,a2.getVelocity().getY());
       assertEquals(-31.819805153394636,a2.getVelocity().getX());

       assertEquals(200.0,a3.getPosition().getX());
       assertEquals(500,a3.getPosition().getY());
       assertEquals(-35.35533905932737,a3.getVelocity().getY());
       assertEquals(-35.35533905932737,a3.getVelocity().getX());
    }
}
