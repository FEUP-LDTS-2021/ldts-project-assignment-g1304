package asteroids.view.Game;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LaserViewTest {
    @Test
    void draw(){
        //given
        LaserBeam laserBeamMock = Mockito.mock(LaserBeam.class);
        Mockito.when(laserBeamMock.getPosition()).thenReturn(new Position(12.3,14.5));
        Mockito.when(laserBeamMock.getHeight()).thenReturn(3.0);
        Mockito.when(laserBeamMock.getWidth()).thenReturn(3.0);
        LaserView laserView = Mockito.spy(new LaserView(laserBeamMock));
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        laserView.setGraphics(graphicsMock);

        TerminalPosition terminalPosition = new TerminalPosition((int)(
                laserBeamMock.getPosition().getX()), (int)(laserBeamMock.getPosition().getY()));
        TerminalSize size = new TerminalSize((int)laserBeamMock.getWidth(), (int)laserBeamMock.getHeight());

        //when
        laserView.draw();

        //then
        Mockito.verify(laserView,Mockito.times(2)).getGraphics();
        Mockito.verify(graphicsMock,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock,Mockito.times(1)).fillRectangle(terminalPosition, size, ' ');

    }
}
