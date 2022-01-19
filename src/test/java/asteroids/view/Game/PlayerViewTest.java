package asteroids.view.Game;

import asteroids.model.Entities.Player;
import asteroids.model.Position;
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
        positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(15.0);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        playerView = Mockito.spy(new PlayerView(playerMock));
        graphicsMock = Mockito.mock(TextGraphics.class);
        playerView.setGraphics(graphicsMock);
    }

    @Test
    void draw(){
        //given
        Mockito.doNothing().when(playerView).drawImage(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        //when
        playerView.draw();

        //then
        Mockito.verify(playerView, Mockito.times(1)).drawImage(PlayerView.playerDraw, 10, 15);
    }
}
