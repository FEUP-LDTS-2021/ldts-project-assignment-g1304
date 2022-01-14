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

public class LeaderboardScreenTest extends Assertions {
    LeaderboardScreen leaderboardScreen;

    @BeforeEach
    void helper(){
        leaderboardScreen = Mockito.spy(new LeaderboardScreen());
    }

    @Test
    void getTerminalPosition(){
        //given
        TerminalSize size = new TerminalSize(50,40);
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, size.getRows());

        Mockito.doReturn(size).when(leaderboardScreen).getSize();

        // when
        TerminalPosition p = leaderboardScreen.getTerminalPosition(size.getRows(), strlen);

        //then
        assertEquals(p, position);

    }
    @Test
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        leaderboardScreen.setGraphics(graphics);

        Mockito.when(leaderboardScreen.getScreen()).thenReturn(Mockito.mock(TerminalScreen.class));
        // when
        leaderboardScreen.draw();

        // then
        Mockito.verify(leaderboardScreen, Mockito.times(1)).clear();
        Mockito.verify(leaderboardScreen, Mockito.times(36)).getGraphics();
        Mockito.verify(leaderboardScreen, Mockito.times(1)).refresh();
    }
}