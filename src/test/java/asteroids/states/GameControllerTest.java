package asteroids.states;

import asteroids.model.Entities.Player;
import asteroids.control.Controller;
import asteroids.control.PlayerController;
import asteroids.model.GameModel;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import asteroids.view.screens.ScreenView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameControllerTest extends Assertions {
    GameController gameControllerSpy;
    Controller context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;
    PlayerController playerController;
    Player player;
    GameModel model;

    @BeforeEach
    void initGameController(){
        // create context
        context = Mockito.mock(Controller.class);
        Mockito.when(context.getStateControler()).thenReturn(Mockito.mock(GameOverController.class));

        // create GameController
        GameController gameController = new GameController(context);
        gameControllerSpy = Mockito.spy(gameController);

        // create PlayerController
        playerController = Mockito.mock(PlayerController.class);
        Mockito.when(gameControllerSpy.getPlayerController()).thenReturn(playerController);

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(gameControllerSpy.getScreenView()).thenReturn(screenViewMock);

        // create model Mocks
        model = Mockito.mock(GameModel.class);
        player = Mockito.mock(Player.class);
        Mockito.when(gameControllerSpy.getGameModel()).thenReturn(model);
        Mockito.when(model.getPlayer()).thenReturn(player);
        Mockito.when(player.isAlive()).thenReturn(true);
        Mockito.when(player.getScore()).thenReturn(200);


    }

    @Test
    void exitGameWhenPlayerDies() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game,ApplicationState.Game, ApplicationState.Menu);
        Mockito.when(player.isAlive()).thenReturn(true, true,false);
        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(2)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();
        Mockito.verify(player, Mockito.times(3)).isAlive();
    }

    @Test
    void exitGameEscape(){
        // given
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);

        // when
        gameControllerSpy.keyPressed(e);
        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void nextStatetoGameOver(){
        // when
        gameControllerSpy.nextState();

        //then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.GameOver);
        Mockito.verify((GameOverController)context.getStateControler(), Mockito.times(1)).setScore(200);
    }

    @Test
    void startRun() throws IOException {
        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();

        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(gameControllerSpy);
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(playerController);

    }

    @Test
    void endRun() throws IOException {
        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).nextState();
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(gameControllerSpy);
        Mockito.verify(screenViewMock, Mockito.times(1)).removeKeyListenner(playerController);

    }

    @Test
    void testRunWhileStopMenu() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game, ApplicationState.Menu);

        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(2)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Exit);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopLeaderboard() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game , ApplicationState.LeaderBoard);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(2)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStop() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(0)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(0)).draw();

    }

    @Test
    void updateLessThanFrameRate(){
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);


        // when
        long lastUpdate = gameControllerSpy.update(10);

        // then
        assertEquals(10, lastUpdate);
        Mockito.verify(model, Mockito.times(0)).update(Mockito.anyLong());
    }

    @Test
    void updateWithEqualFrameRate(){
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);


        // when
        long lastUpdate = gameControllerSpy.update(GameController.FRAME_TIME);

        // then
        assertEquals(0, lastUpdate);
        Mockito.verify(model, Mockito.times(1)).update(GameController.FRAME_TIME);
    }

    @Test
    void updateMoreThanFrameRate(){
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);


        // when
        long lastUpdate = gameControllerSpy.update(GameController.FRAME_TIME*3 + 20);

        // then
        assertEquals(20, lastUpdate);
        Mockito.verify(model, Mockito.times(3)).update(GameController.FRAME_TIME);
    }
}