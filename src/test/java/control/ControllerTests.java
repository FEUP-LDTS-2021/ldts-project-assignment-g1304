package control;

import control.states.ApplicationState;
import control.states.GameController;
import control.states.MenuController;
import control.states.StateController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ControllerTests  extends Assertions {
    Controller controllerSpy;
    Thread thread;
    @BeforeEach
    void initController(){
        thread = Mockito.mock(Thread.class);
        Controller controller = new Controller();
        controllerSpy = Mockito.spy(controller);
        Mockito.when(controllerSpy.getInputThread()).thenReturn(thread);
    }

    @Test
    void startInputThread() throws IOException {
        // given
        Mockito.when(controllerSpy.getStateControler()).thenReturn(null);

        // when
        controllerSpy.run();

        // then
        Mockito.verify(thread, Mockito.times(1)).start();
        Mockito.verify(thread, Mockito.times(1)).interrupt();
    }

    @Test
    void runState() throws IOException {
        // given
        StateController stateController = Mockito.mock(StateController.class);
        StateController stateController2 = Mockito.mock(StateController.class);

        Mockito.when(controllerSpy.getInputThread()).thenReturn(thread);
        Mockito.when(controllerSpy.getStateControler()).thenReturn(stateController, stateController, stateController2 ,stateController2, null);

        // when
        controllerSpy.run();

        // then
        Mockito.verify(stateController, Mockito.times(1)).run();
        Mockito.verify(stateController2, Mockito.only()).run();
        Mockito.verify(controllerSpy, Mockito.times(5)).getStateControler();
    }

    @Test
    void inicialState(){
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
    }

    @Test
    void changeStateGame(){
        //when
        controllerSpy.changeState(ApplicationState.Game);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof GameController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Game);
    }

    /*
    @Test
    void changeStateLeaderboard(){
        //when
        controllerSpy.changeState(ApplicationState.LeaderBoard);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof LeaderboardController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.LeaderBoard);
    }*/

    @Test
    void changeStateExit(){
        //when
        controllerSpy.changeState(ApplicationState.Exit);

        // then
        assertNull(controllerSpy.getStateControler());
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Exit);
    }

    @Test
    void changeStateMenu(){
        //when
        controllerSpy.changeState(ApplicationState.Menu);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof MenuController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Menu);
    }
}