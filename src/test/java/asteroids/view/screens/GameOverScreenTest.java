package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class GameOverScreenTest extends Assertions {
    GameOverScreen gameOverScreen;

    @BeforeEach
    void helper(){
        gameOverScreen = Mockito.spy(new GameOverScreen());
    }

    @Test
    void getTerminalPosition(){
        //given
        TerminalSize size = new TerminalSize(50,40);
        double percentage = 0.7;
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, (int)(size.getRows()*percentage));

        Mockito.doReturn(size).when(gameOverScreen).getSize();

        // when
        TerminalPosition p = gameOverScreen.getTerminalPosition(percentage, strlen);


        //then
        assertEquals(p, position);

    }
    @Test
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        gameOverScreen.setGraphics(graphics);

        Mockito.when(gameOverScreen.getScreen()).thenReturn(Mockito.mock(TerminalScreen.class));
        // when
        gameOverScreen.draw();

        // then
        Mockito.verify(gameOverScreen, Mockito.times(1)).clear();
        Mockito.verify(gameOverScreen, Mockito.times(8)).getGraphics();
        Mockito.verify(gameOverScreen, Mockito.times(1)).refresh();
    }
    @Test
    void setScore(){
        //when
        gameOverScreen.setScore(300);

        //then
        assertEquals("300",gameOverScreen.getScoreValue());
    }
    @Test
    void setNickNameSpaces(){
        //when
        gameOverScreen.setNickNameSpaces("a _ _ _ _ _ _ _ _ _ _ ");

        //then
        assertEquals("a _ _ _ _ _ _ _ _ _ _ ",gameOverScreen.getNickNameSpaces());
    }
    @Test
    void getNickNameSpaces(){
        //when
        String nick = "exemplo";
        gameOverScreen.setNickNameSpaces(nick);
        nick = gameOverScreen.getNickNameSpaces();

        //then
        assertEquals("exemplo",nick);
    }
    @Test
    void getScoreValue(){
        //when
        int scr = 123;
        gameOverScreen.setScore(scr);
        String scrSt = gameOverScreen.getScoreValue();

        //then
        assertEquals("123",scrSt);
    }
}

