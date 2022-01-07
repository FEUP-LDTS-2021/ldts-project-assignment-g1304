package asteroids.control.states;

import asteroids.states.ApplicationState;
import asteroids.states.MenuController;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import asteroids.control.Controller;
import asteroids.input.InputListenner;
import asteroids.model.Menu.Menu;
import asteroids.model.Menu.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import asteroids.view.screens.ScreenView;

import java.io.IOException;

public class MenuControllerTest extends Assertions {

    MenuController menuControllerSpy;
    Controller context;
    ScreenView screenViewMock;
    Screen screenMock;
    InputListenner inputListenner;
    Menu menu;

    @BeforeEach
    void initGameController(){
        // create context
        context = Mockito.mock(Controller.class);
        inputListenner = Mockito.mock(InputListenner.class);
        Mockito.when(context.getInputListenner()).thenReturn(inputListenner);

        // create MenuController
        menuControllerSpy = Mockito.spy(new MenuController(context));

        // create menuMoCK
        menu = Mockito.mock(Menu.class);
        Mockito.when(menuControllerSpy.getMenu()).thenReturn(menu);

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(menuControllerSpy.getScreenView()).thenReturn(screenViewMock);

    }

    @Test
    void processKeyArrowDown(){
        // when
        menuControllerSpy.processKey(new KeyStroke(KeyType.ArrowDown));

        // then
        Mockito.verify(menu, Mockito.times(1)).selectNext();
    }

    @Test
    void processKeyArrowUp(){
        // when
        menuControllerSpy.processKey(new KeyStroke(KeyType.ArrowUp));

        // then
        Mockito.verify(menu, Mockito.times(1)).selectprevious();
    }

    @Test
    void processKeyEnter(){
        // when
        menuControllerSpy.processKey(new KeyStroke(KeyType.Enter));

        // then
        Mockito.verify(menu, Mockito.times(1)).choose();
    }

    @Test
    void processKeyEscape(){
        // when
        menuControllerSpy.processKey(new KeyStroke(KeyType.Escape));

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void processKeyEOF(){
        // when
        menuControllerSpy.processKey(new KeyStroke(KeyType.EOF));

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void startRun() throws IOException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(screenMock);

        Mockito.verify(inputListenner, Mockito.times(1)).addInputObserver(menuControllerSpy);

    }

    @Test
    void endRun() throws IOException {
        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(null);
        Mockito.verify(inputListenner, Mockito.times(1)).removeInputObserver(menuControllerSpy);

    }

    @Test
    void testRunWhileStopGame() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Game);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(2)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileStopExit() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Exit);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(1)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }

    @Test
    void testRunWhileStopLeaderboard() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Game);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(2)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(2)).draw();

    }

    @Test
    void testRunWhileChoosed_1true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(1)).nextState();
    }

    @Test
    void testRunWhileChoosed_2true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,true,true);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(2)).nextState();
    }

    @Test
    void testRunWhileChoosed_0true() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Menu, ApplicationState.Menu, ApplicationState.Menu,ApplicationState.Exit);
        Mockito.when(menu.isChoosed()).thenReturn(false,false,false);
        Mockito.doNothing().when(menuControllerSpy).nextState();

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menuControllerSpy, Mockito.times(0)).nextState();
    }

    @Test
    void choosePlay(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Play);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Game);
    }

    @Test
    void chooseLeaderBoard(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.LeaderBoard);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.LeaderBoard);
    }

    @Test
    void chooseExit(){
        // given
        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Exit);

        // when
        menuControllerSpy.nextState();

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Exit);
    }

    @Test
    void testRunStop() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Exit);

        // when
        menuControllerSpy.run();

        // then
        Mockito.verify(menu, Mockito.times(0)).isChoosed();
        Mockito.verify(screenViewMock, Mockito.times(0)).draw();

    }
}
