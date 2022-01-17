package asteroids.control.states;

import asteroids.control.Controller;
import asteroids.states.ApplicationState;
import asteroids.states.LeaderboardController;
import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LeaderboardControllerTest {
    Controller context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;

    LeaderboardController leaderboardControllerSpy;


    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(Controller.class);

        // create LeaderboardController
        leaderboardControllerSpy = Mockito.spy(new LeaderboardController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(leaderboardControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }
    @Test
    void processKeyEscape(){
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        leaderboardControllerSpy.keyPressed(e);

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Menu);
    }
    @Test
    void startRun() throws IOException {
        // when
        leaderboardControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(leaderboardControllerSpy);
    }

    @Test
    void endRun() throws IOException {
        // when
        leaderboardControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(leaderboardControllerSpy);
    }
    @Test
    void testRun() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.LeaderBoard,ApplicationState.Menu);


        // when
        leaderboardControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }
}