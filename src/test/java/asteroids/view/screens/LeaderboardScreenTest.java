package asteroids.view.screens;

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
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        leaderboardScreen.setGraphics(graphics);

        Mockito.when(leaderboardScreen.getScreen()).thenReturn(Mockito.mock(TerminalScreen.class));
        // when
        leaderboardScreen.draw();

        // then
        Mockito.verify(leaderboardScreen, Mockito.times(1)).clear();
        Mockito.verify(leaderboardScreen, Mockito.times(34)).getGraphics();
        Mockito.verify(leaderboardScreen, Mockito.times(1)).refresh();
    }
}