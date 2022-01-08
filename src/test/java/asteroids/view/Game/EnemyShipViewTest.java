package asteroids.view.Game;

import asteroids.model.Entities.EnemyShip;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyShipViewTest {
    @Test
    void draw(){
        //given
        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(10.0,10.0));
        EnemyShipView enemyShipView = Mockito.spy(new EnemyShipView(enemyShipMock));
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        enemyShipView.setGraphics(graphicsMock);

        //when
        enemyShipView.draw();

        //then
        Mockito.verify(enemyShipView,Mockito.times(2)).getGraphics();
        Mockito.verify(graphicsMock,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(graphicsMock,Mockito.times(1)).drawRectangle(new TerminalPosition((int)enemyShipMock.getPosition().getX(),
                        (int)enemyShipMock.getPosition().getY()),
                new TerminalSize((int)enemyShipMock.getWidth(), (int)enemyShipMock.getHeight()),' ');
    }
}
