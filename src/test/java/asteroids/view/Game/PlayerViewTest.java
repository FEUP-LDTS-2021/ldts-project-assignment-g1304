package asteroids.view.Game;

import asteroids.model.Entities.Player;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewTest {

    Player playerMock;
    Position positionMock;
    PlayerView playerView;
    TextGraphics graphicsMock;

    @BeforeEach
    void init() {
        playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getAngle()).thenReturn(25.0);
        positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(15.0);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        playerView = Mockito.spy(new PlayerView(playerMock));
        graphicsMock = Mockito.mock(TextGraphics.class);
        playerView.setGraphics(graphicsMock);
    }

    @Test
    void drawWithoutFlames(){
        //given
        Mockito.doNothing().when(playerView).drawShadow(Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.doNothing().when(playerView).drawPlayer(Mockito.any(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.when(playerMock.isAccelerating()).thenReturn(false);
        //when
        playerView.draw();

        //then
        Mockito.verify(playerView, Mockito.times(1)).drawShadow(25.0+Math.PI/2.0, 10, 15);
        Mockito.verify(playerView, Mockito.times(1)).drawPlayer(PlayerView.playerDraw,25.0+Math.PI/2.0, 10, 15);

    }

    @Test
    void drawWithFlames(){
        //given
        Mockito.doNothing().when(playerView).drawShadow(Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.doNothing().when(playerView).drawPlayer(Mockito.any(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt());
        Mockito.when(playerMock.isAccelerating()).thenReturn(true);
        //when
        playerView.draw();

        //then
        Mockito.verify(playerView, Mockito.times(1)).drawShadow(25.0+Math.PI/2.0, 10, 15);
        Mockito.verify(playerView, Mockito.times(1)).drawPlayer(PlayerView.playerFlamesDraw,25.0+Math.PI/2.0, 10, 15);

    }

    @Test
    void drawShadow(){
        // given
        Mockito.doNothing().when(playerView).setBackgroundColor(Mockito.any());

        double angle = 10;
        int x = 16;
        int y = 31;

        TerminalPosition point1 = new TerminalPosition(-6+x, 7+y);
        TerminalPosition point2 = new TerminalPosition(16+x, 2+y);
        TerminalPosition point3 = new TerminalPosition(-8+x, -13+y);

        // when
        playerView.drawShadow(angle, x, y);

        // then
        Mockito.verify(playerView).setBackgroundColor(Color.White);
        Mockito.verify(graphicsMock, Mockito.times(1)).fillTriangle(point1, point2, point3, ' ');

    }

    @Test
    public void drawPlayer(){
        // given
        String[] playerDraw = PlayerView.playerDraw;
        double angle = 10;
        int playerX = 16;
        int playerY = 31;
        int midX = playerDraw[0].length()/2;
        int midY = playerDraw.length/2;
        System.out.println(midX + " " + midY);
        // when
        playerView.drawPlayer(PlayerView.playerDraw, angle, playerX, playerY);

        // then

        Mockito.verify(playerView, Mockito.times(45)).setColor(Color.MediumGray.getChar());
        Mockito.verify(playerView, Mockito.times(71)).setColor(Color.White.getChar());
        Mockito.verify(playerView, Mockito.times(9)).setColor(Color.LightBlue.getChar());
        Mockito.verify(playerView, Mockito.times(2)).setColor(Color.Red.getChar());

        for(int y = 0; y < playerDraw.length; y++){
            for(int x = 0; x < playerDraw[0].length(); x++){
                char c = playerDraw[y].charAt(x);
                if(c!=' '){

                    Vector2d point = new Vector2d(x-midX, y-midY);
                    Vector2d newPoint = point.rotatePoint(angle);

                    Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                            new TerminalPosition((int)(playerX+newPoint.getX()*2),
                                    (int)(playerY+newPoint.getY()*2)),
                            new TerminalSize(2, 2), ' ');

                }
            }
        }


    }
}
