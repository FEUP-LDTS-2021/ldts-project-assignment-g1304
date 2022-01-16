package asteroids.model.Entities;


import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;


public class AsteroidTest extends Assertions {
    @Test
    void createAsteroid(){
        // given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Vector2d velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(10.0);
        Mockito.when(velocityMock.getY()).thenReturn(-5.0);

        Asteroid a = new Asteroid(positionMock,velocityMock,15);

        // when
        Position position = a.getPosition();
        Vector2d velocity = a.getVelocity();

        // then
        assertEquals(position.getX(), positionMock.getX());  //checks if position is okay
        assertEquals(position.getY(), positionMock.getY());

        assertEquals(velocity.getX(),velocityMock.getX());   //checks if velocity is okay
        assertEquals(velocity.getY(),velocityMock.getY());

        assertEquals(15,a.getWidth());                //checks if size is okay
        assertEquals(15,a.getHeight());                //checks if size is okay
    }

    @Test
    void update(){
        //given
        Asteroid a = new Asteroid(new Position(50,50),new Vector2d(10,-5),15);

        //when
        a.update(1000);

        //then
        assertEquals(60, a.getPosition().getX());
        assertEquals(45,a.getPosition().getY());

    }

    @Test
    void getPoints(){
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),15);

        // when
        int points = asteroid.getPoints();

        // then
        assertEquals(20, asteroid.getPoints());
    }

    @Test
    void collider(){
        // Given
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10.0);
        Mockito.when(position.getY()).thenReturn(15.0);
        Asteroid asteroid = new Asteroid(position, Mockito.mock(Vector2d.class), 20);

        Polygon polygon = new Polygon();
        double x = 10;
        double y = 15;

        polygon.addPoint((int)x, (int)y);
        polygon.addPoint((int)(x + 20), (int)y);
        polygon.addPoint((int)(x + 20), (int)(y + 20));
        polygon.addPoint((int)x, (int)(y + 20));


        // when
        Polygon asteroidCollider = asteroid.getCollider();

        // then
        assertEquals(polygon.npoints, asteroidCollider.npoints);
        assertArrayEquals(polygon.xpoints, asteroidCollider.xpoints);
        assertArrayEquals(polygon.ypoints, asteroidCollider.ypoints);

    }
}
