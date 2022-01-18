package asteroids.control;

import asteroids.states.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ControllerTests  extends Assertions {
    Controller controllerSpy;
    @BeforeEach
    void initController(){
        Controller controller = new Controller();
        controllerSpy = Mockito.spy(controller);
    }
    @Test
    void runState() throws IOException {
        // given
        StateController stateController = Mockito.mock(StateController.class);
        StateController stateController2 = Mockito.mock(StateController.class);

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


    @Test
    void changeStateLeaderboard(){
        //when
        controllerSpy.changeState(ApplicationState.LeaderBoard);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof LeaderboardController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.LeaderBoard);
    }
    @Test
    void changeStateGameOver(){
        //when
        controllerSpy.changeState(ApplicationState.GameOver);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof GameOverController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.GameOver);
    }

    @Test
    void changeStateInstructions(){
        //when
        controllerSpy.changeState(ApplicationState.Instructions);

        // then
        assertTrue(controllerSpy.getStateControler() instanceof InstructionsController);
        assertEquals(controllerSpy.getApplicationState(), ApplicationState.Instructions);
    }

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