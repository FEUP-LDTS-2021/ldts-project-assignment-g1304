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

        Asteroid a = new Asteroid(positionMock,velocityMock, Sizes.SMALL);

        // when
        Position position = a.getPosition();
        Vector2d velocity = a.getVelocity();

        // then
        assertEquals(position.getX(), positionMock.getX());  //checks if position is okay
        assertEquals(position.getY(), positionMock.getY());

        assertEquals(velocity.getX(),velocityMock.getX());   //checks if velocity is okay
        assertEquals(velocity.getY(),velocityMock.getY());

        assertEquals(a.getAsteroidSize(), Sizes.SMALL);              //checks if size is okay
    }

    @Test
    void update(){
        //given
        Vector2d velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(10.0);
        Mockito.when(velocityMock.getY()).thenReturn(-5.0);

        Asteroid a = new Asteroid(new Position(50.0, 50.0), velocityMock, Sizes.SMALL);

        //when
        a.update(1000);

        //then
        assertEquals(60, a.getPosition().getX());
        assertEquals(45,a.getPosition().getY());

    }

    @Test
    void getPoints(){
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.LARGE);

        // when
        int points = asteroid.getPoints();

        // then
        assertEquals(20, points);
    }

    @Test
    void decreaseSizeToMedium() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.LARGE);

        //when
        asteroid.decreaseSize();

        //then
        assertEquals(asteroid.getAsteroidSize(), Sizes.MEDIUM);
    }

    @Test
    void decreaseSizeToSmall() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.MEDIUM);

        //when
        asteroid.decreaseSize();

        //then
        assertEquals(asteroid.getAsteroidSize(), Sizes.SMALL);
    }

    @Test
    void setSize() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.MEDIUM);

        //when
        asteroid.setSize(Sizes.LARGE);

        //then
        assertEquals(asteroid.getAsteroidSize(), Sizes.LARGE);
    }

    @Test
    void setHeightWidth() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.MEDIUM);
        Asteroid asteroid1 = Mockito.spy(asteroid);

        //when
        asteroid1.setSize(Sizes.LARGE);

        //then
        Mockito.verify(asteroid1, Mockito.times(1)).setHeight(Sizes.LARGE.getSize()*15);
        Mockito.verify(asteroid1, Mockito.times(1)).setWidth(Sizes.LARGE.getSize()*17);
    }

    @Test
    void smallDies() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.SMALL);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), false);
    }

    @Test
    void mediumLives() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.MEDIUM);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), true);
    }

    @Test
    void largeLives() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.LARGE);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), true);
    }

    @Test
    void getSmallPoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.SMALL);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(100, points);
    }

    @Test
    void getMediumPoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.MEDIUM);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(50, points);
    }

    @Test
    void getLargePoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class), Sizes.LARGE);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(20, points);
    }

    @Test
    void getColliderSmall() {
        // given

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Vector2d velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(10.0);
        Mockito.when(velocityMock.getY()).thenReturn(-5.0);

        Asteroid asteroid = new Asteroid(positionMock,velocityMock, Sizes.SMALL);

        int[] pointsListX = new int []{5, 2, 2, 1, 1, 0, 0, 2, 3, 6, 13, 16, 17, 17, 16, 16, 15, 14, 12};
        int[] pointsListY = new int []{0, 3, 4, 5, 6, 7, 11, 12, 13, 14, 14, 12, 9, 8, 7, 4, 2, 1, 0};


        // when
        Polygon returned = asteroid.getCollider();

        // then

        assertEquals(returned.npoints, 19);
        for(int i = 0 ; i<returned.npoints; i++){
            assertEquals(10+pointsListX[i]*asteroid.getAsteroidSize().getSize(), returned.xpoints[i]);
            assertEquals(20+pointsListY[i]*asteroid.getAsteroidSize().getSize(), returned.ypoints[i]);
        }

    }

    @Test
    void getColliderMedium() {
        // given

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Vector2d velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(10.0);
        Mockito.when(velocityMock.getY()).thenReturn(-5.0);

        Asteroid asteroid = new Asteroid(positionMock, velocityMock, Sizes.MEDIUM);

        int[] pointsListX = new int[]{5, 2, 2, 1, 1, 0, 0, 2, 3, 6, 13, 16, 17, 17, 16, 16, 15, 14, 12};
        int[] pointsListY = new int[]{0, 3, 4, 5, 6, 7, 11, 12, 13, 14, 14, 12, 9, 8, 7, 4, 2, 1, 0};


        // when
        Polygon returned = asteroid.getCollider();

        // then

        assertEquals(returned.npoints, 19);
        for (int i = 0; i < returned.npoints; i++) {
            assertEquals(10 + pointsListX[i]*asteroid.getAsteroidSize().getSize(), returned.xpoints[i]);
            assertEquals(20 + pointsListY[i]*asteroid.getAsteroidSize().getSize(), returned.ypoints[i]);
        }
    }

    @Test
    void getColliderLarge() {
        // given

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Vector2d velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(10.0);
        Mockito.when(velocityMock.getY()).thenReturn(-5.0);

        Asteroid asteroid = new Asteroid(positionMock, velocityMock, Sizes.LARGE);

        int[] pointsListX = new int[]{5, 2, 2, 1, 1, 0, 0, 2, 3, 6, 13, 16, 17, 17, 16, 16, 15, 14, 12};
        int[] pointsListY = new int[]{0, 3, 4, 5, 6, 7, 11, 12, 13, 14, 14, 12, 9, 8, 7, 4, 2, 1, 0};


        // when
        Polygon returned = asteroid.getCollider();

        // then

        assertEquals(returned.npoints, 19);
        for (int i = 0; i < returned.npoints; i++) {
            assertEquals(10 + pointsListX[i]*asteroid.getAsteroidSize().getSize(), returned.xpoints[i]);
            assertEquals(20 + pointsListY[i]*asteroid.getAsteroidSize().getSize(), returned.ypoints[i]);
        }
    }
}
