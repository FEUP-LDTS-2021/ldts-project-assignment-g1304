package asteroids.model.Entities;


import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


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

        Asteroid a = new Asteroid(positionMock,velocityMock,AsteroidSizes.SMALL);

        // when
        Position position = a.getPosition();
        Vector2d velocity = a.getVelocity();

        // then
        assertEquals(position.getX(), positionMock.getX());  //checks if position is okay
        assertEquals(position.getY(), positionMock.getY());

        assertEquals(velocity.getX(),velocityMock.getX());   //checks if velocity is okay
        assertEquals(velocity.getY(),velocityMock.getY());

        assertEquals(a.getSize(), AsteroidSizes.SMALL);              //checks if size is okay
    }

    @Test
    void update(){
        //given
        Asteroid a = new Asteroid(new Position(50,50),new Vector2d(10,-5),AsteroidSizes.SMALL);

        //when
        a.update(1000);

        //then
        assertEquals(60, a.getPosition().getX());
        assertEquals(45,a.getPosition().getY());

    }

    @Test
    void getPoints(){
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.LARGE);

        // when
        int points = asteroid.getPoints();

        // then
        assertEquals(20, points);
    }

    @Test
    void decreaseSizeToMedium() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.LARGE);

        //when
        asteroid.decreaseSize();

        //then
        assertEquals(asteroid.getSize(), AsteroidSizes.MEDIUM);
    }

    @Test
    void decreaseSizeToSmall() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.MEDIUM);

        //when
        asteroid.decreaseSize();

        //then
        assertEquals(asteroid.getSize(), AsteroidSizes.SMALL);
    }

    @Test
    void setSize() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.MEDIUM);

        //when
        asteroid.setSize(AsteroidSizes.LARGE);

        //then
        assertEquals(asteroid.getSize(), AsteroidSizes.LARGE);
    }

    @Test
    void smallDies() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.SMALL);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), false);
    }

    @Test
    void mediumLives() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.MEDIUM);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), true);
    }

    @Test
    void largeLives() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.LARGE);

        //when
        asteroid.dies();

        //then
        assertEquals(asteroid.isAlive(), true);
    }

    @Test
    void getSmallPoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.SMALL);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(100, points);
    }

    @Test
    void getMediumPoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.MEDIUM);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(50, points);
    }

    @Test
    void getLargePoints() {
        //given
        Asteroid asteroid = new Asteroid(Mockito.mock(Position.class), Mockito.mock(Vector2d.class),AsteroidSizes.LARGE);

        //when
        int points = asteroid.getPoints();

        //then
        assertEquals(20, points);
    }
}
