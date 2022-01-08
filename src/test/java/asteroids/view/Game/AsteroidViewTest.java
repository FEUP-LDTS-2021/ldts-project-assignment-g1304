package asteroids.view.Game;

import asteroids.model.Entities.Asteroid;

import asteroids.model.Position;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AsteroidViewTest {
    @Test
    void draw(){
        //given
        Asteroid asteroidMock = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidMock.getPosition()).thenReturn(new Position(10.0,10.0));
        AsteroidView asteroidView = Mockito.spy(new AsteroidView(asteroidMock));
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        asteroidView.setGraphics(graphicsMock);

        //when
        asteroidView.draw();

        //then
        Mockito.verify(asteroidView,Mockito.times(2)).getGraphics();
        Mockito.verify(graphicsMock,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));

    }
}
