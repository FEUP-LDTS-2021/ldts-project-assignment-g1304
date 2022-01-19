package asteroids.view.Game;

import asteroids.Color;
import asteroids.model.Entities.EnemyShip;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
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
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(10.0,10.0));
        enemyShipView = Mockito.spy(new EnemyShipView(enemyShipMock));
        graphicsMock = Mockito.mock(TextGraphics.class);
        enemyShipView.setGraphics(graphicsMock);
    }

    @Test
    void draw(){
        //given
        Mockito.doNothing().when(enemyShipView).drawLine(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());

        //when
        enemyShipView.draw();

        //then
        int y = 0;
        for (String line : EnemyShipView.enemyShipDraw){
            Mockito.verify(enemyShipView).drawLine(line, 10, 10+y);
            y+=2;
        }
    }

    @Test
    public void drawLine(){
        // given
        String line = " BbcCpP ";
        int startX = 10;
        int startY = 20;
        //when
        enemyShipView.drawLine(line, startX, startY);

        // then
        int x = 0;
        for (char c : line.toCharArray()){
            if (c!=' '){
                Mockito.verify(enemyShipView, Mockito.times(1)).setColor(c);
                Mockito.verify(graphicsMock, Mockito.times(1)).fillRectangle(
                        new TerminalPosition(startX+x, startY),
                        new TerminalSize(2, 2), ' ');
            }else{
                Mockito.verify(graphicsMock, Mockito.never()).fillRectangle(
                        new TerminalPosition(startX+x, startY),
                        new TerminalSize(2, 2), ' ');
            }
            x+=2;
        }
    }

    private static final char DarkBlue = 'B';
    private static final char LightBlue = 'b';
    private static final char LightGray = 'c';
    private static final char MediumGray = 'C';
    private static final char DarkGray = 'p';
    private static final char LightBlack = 'P';
    private static final char Red = 'G';

    @Test
    public void setColorDarkBlue(){
        // when
        enemyShipView.setColor(DarkBlue);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.DarkBlue);
    }

    @Test
    public void setColorLightBlue(){
        // when
        enemyShipView.setColor(LightBlue);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.LightBlue);
    }

    @Test
    public void setColorLightGray(){
        // when
        enemyShipView.setColor(LightGray);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.LightGray);
    }

    @Test
    public void setColorMediumGray(){
        // when
        enemyShipView.setColor(MediumGray);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.MediumGray);
    }

    @Test
    public void setColorDarkGray(){
        // when
        enemyShipView.setColor(DarkGray);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.DarkGray);
    }

    @Test
    public void setColorLightBlack(){
        // when
        enemyShipView.setColor(LightBlack);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.LightBlack);
    }

    @Test
    public void setColorRed(){
        // when
        enemyShipView.setColor(Red);

        // then
        Mockito.verify(enemyShipView, Mockito.times(1)).setBackgroundColor(Color.Red);
    }


}
