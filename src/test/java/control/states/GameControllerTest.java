package control.states;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import control.Controller;
import control.PlayerController;
import control.input.InputListenner;
import model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.ScreenView;

import java.io.IOException;

public class GameControllerTest extends Assertions {
    GameController gameControllerSpy;
    Controller context;
    ScreenView screenViewMock;
    Screen screenMock;
    InputListenner inputListenner;
    PlayerController playerController;

    @BeforeEach
    void initGameController(){
        // create context
        context = Mockito.mock(Controller.class);
        inputListenner = Mockito.mock(InputListenner.class);
        Mockito.when(context.getInputListenner()).thenReturn(inputListenner);

        // create GameController
        GameController gameController = new GameController(context);
        gameControllerSpy = Mockito.spy(gameController);

        // create PlayerController
        playerController = Mockito.mock(PlayerController.class);
        Mockito.when(gameControllerSpy.getPlayerController()).thenReturn(playerController);

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(gameControllerSpy.getScreenView()).thenReturn(screenViewMock);

    }

    @Test
    void exitGameEOF(){
        // when
        gameControllerSpy.processKey(new KeyStroke(KeyType.EOF));

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void exitGameEscape(){
        // when
        gameControllerSpy.processKey(new KeyStroke(KeyType.Escape));

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void nextStatetoMenu(){
        // when
        gameControllerSpy.nextState();

        //then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Menu);
    }

    @Test
    void startRun() throws IOException {
        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(screenMock);

        Mockito.verify(inputListenner, Mockito.times(1)).addInputObserver(gameControllerSpy);
        Mockito.verify(inputListenner, Mockito.times(1)).addInputObserver(playerController);

    }

    @Test
    void endRun() throws IOException {
        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(gameControllerSpy, Mockito.times(1)).nextState();
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(null);
        Mockito.verify(inputListenner, Mockito.times(1)).removeInputObserver(gameControllerSpy);
        Mockito.verify(inputListenner, Mockito.times(1)).removeInputObserver(playerController);

    }

    @Test
    void testRunWhileStopMenu() throws IOException {
        // given
        GameModel model = Mockito.mock(GameModel.class);
        Mockito.when(gameControllerSpy.getGameModel()).thenReturn(model);
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game, ApplicationState.Menu);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(model, Mockito.times(2)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException {
        // given
        GameModel model = Mockito.mock(GameModel.class);
        Mockito.when(gameControllerSpy.getGameModel()).thenReturn(model);
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Exit);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(model, Mockito.times(1)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopLeaderboard() throws IOException {
        // given
        GameModel model = Mockito.mock(GameModel.class);
        Mockito.when(gameControllerSpy.getGameModel()).thenReturn(model);
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Game, ApplicationState.Game , ApplicationState.LeaderBoard);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(model, Mockito.times(2)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStop() throws IOException {
        // given
        GameModel model = Mockito.mock(GameModel.class);
        Mockito.when(gameControllerSpy.getGameModel()).thenReturn(model);
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);


        // when
        gameControllerSpy.run();

        // then
        Mockito.verify(model, Mockito.times(0)).update(Mockito.anyLong());
        Mockito.verify(screenViewMock, Mockito.times(0)).draw();

    }
}