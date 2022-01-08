package asteroids.view.Game;

import asteroids.model.Entities.Player;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewTest {

    @Test
    void draw(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(new Position(13.2,42.0));
        Mockito.when(playerMock.getAngle()).thenReturn(0.123456789);
        Mockito.when(playerMock.getRaio()).thenReturn(5.69);
        PlayerView playerView = Mockito.spy(new PlayerView(playerMock));
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        playerView.setGraphics(graphicsMock);

        double anglePontaNave = playerMock.getAngle();
        double anglePontaEsq = anglePontaNave + Math.PI*0.8333;   // 5/6
        double anglePontaDir = anglePontaNave + Math.PI*1.1666;   // 7/6

        //when
        playerView.draw();

        //then
        Mockito.verify(playerView,Mockito.times(2)).getGraphics();
        Mockito.verify(graphicsMock,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock,Mockito.times(1)).drawTriangle(
                new TerminalPosition((int) (Math.cos(anglePontaNave)* playerMock.getRaio() + playerMock.getPosition().getX()),
                        (int) (Math.sin(anglePontaNave)*playerMock.getRaio() + playerMock.getPosition().getY())),
                new TerminalPosition((int) (Math.cos(anglePontaEsq)* playerMock.getRaio() + playerMock.getPosition().getX()),
                        (int) (Math.sin(anglePontaEsq)*playerMock.getRaio() + playerMock.getPosition().getY())),
                new TerminalPosition((int) (Math.cos(anglePontaDir)* playerMock.getRaio() + playerMock.getPosition().getX()),
                        (int) (Math.sin(anglePontaDir)*playerMock.getRaio() + playerMock.getPosition().getY())),
                ' ');
    }
}
