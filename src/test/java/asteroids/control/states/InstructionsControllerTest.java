package asteroids.control.states;

import asteroids.control.Controller;
import asteroids.input.InputListenner;
import asteroids.states.ApplicationState;
import asteroids.states.InstructionsController;
import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InstructionsControllerTest {
    Controller context;
    ScreenView screenViewMock;
    Screen screenMock;
    InputListenner inputListenner;
    InstructionsController instructionControllerSpy;


    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(Controller.class);
        inputListenner = Mockito.mock(InputListenner.class);
        Mockito.when(context.getInputListenner()).thenReturn(inputListenner);

        // create InstructionController
        instructionControllerSpy = Mockito.spy(new InstructionsController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(instructionControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }
    @Test
    void processKeyEscape(){
        // when
        instructionControllerSpy.processKey(new KeyStroke(KeyType.Escape));

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Menu);
    }
    @Test
    void startRun() throws IOException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(screenMock);

        Mockito.verify(inputListenner, Mockito.times(1)).addInputObserver(instructionControllerSpy);
    }

    @Test
    void endRun() throws IOException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(inputListenner, Mockito.times(1)).setScreen(null);
        Mockito.verify(inputListenner, Mockito.times(1)).removeInputObserver(instructionControllerSpy);
    }

}
