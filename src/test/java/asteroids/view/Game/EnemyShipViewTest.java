package asteroids.view.Game;

import asteroids.model.Entities.EnemyShip;
import asteroids.model.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyShipViewTest {
    EnemyShip enemyShipMock;
    EnemyShipView enemyShipView;
    TextGraphics graphicsMock;
    @BeforeEach
    void init(){
        enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(10.0,15.0));
        enemyShipView = Mockito.spy(new EnemyShipView(enemyShipMock));
        graphicsMock = Mockito.mock(TextGraphics.class);
        enemyShipView.setGraphics(graphicsMock);
    }

    @Test
    void draw(){
        //given
        Mockito.doNothing().when(enemyShipView).drawImage(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        //when
        enemyShipView.draw();

        //then
        Mockito.verify(enemyShipView, Mockito.times(1)).drawImage(EnemyShipView.enemyShipDraw, 10, 15);
    }
}
