package asteroids.view.Game;

import asteroids.model.Entities.Asteroid;

import asteroids.model.Entities.AsteroidSizes;
import asteroids.model.Position;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class AsteroidViewTest {
    Asteroid asteroidMock;
    Position positionMock;
    AsteroidView asteroidView;
    TextGraphics graphicsMock;
    @BeforeEach
    void init(){
        asteroidMock = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidMock.getAsteroidSize()).thenReturn(AsteroidSizes.LARGE);
        positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(15.0);
        Mockito.when(asteroidMock.getPosition()).thenReturn(positionMock);
        asteroidView = Mockito.spy(new AsteroidView(asteroidMock));
        graphicsMock = Mockito.mock(TextGraphics.class);
        asteroidView.setGraphics(graphicsMock);
    }

    @Test
    void draw(){
        //given
        Mockito.doNothing().when(asteroidView).drawImage(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        //when
        asteroidView.draw();

        //then
        Mockito.verify(asteroidView, Mockito.times(1)).setCharWidth(asteroidMock.getAsteroidSize().getSize());
        Mockito.verify(asteroidView, Mockito.times(1)).setCharHeight(asteroidMock.getAsteroidSize().getSize());
        Mockito.verify(asteroidView, Mockito.times(1)).drawImage(AsteroidView.asteroidDraw, 10, 15);
    }
}
