package asteroids.control.states;

import asteroids.control.Controller;
import asteroids.states.ApplicationState;
import asteroids.states.InstructionsController;
import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class InstructionsControllerTest {
    Controller context;
    ScreenView screenViewMock;
    TerminalScreen screenMock;

    InstructionsController instructionControllerSpy;


    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(Controller.class);

        // create InstructionController
        instructionControllerSpy = Mockito.spy(new InstructionsController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(ScreenView.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        Mockito.when(instructionControllerSpy.getScreenView()).thenReturn(screenViewMock);
    }
    @Test
    void processKeyEscape(){
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        instructionControllerSpy.keyPressed(e);

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Menu);
    }
    @Test
    void startRun() throws IOException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(instructionControllerSpy);
    }

    @Test
    void endRun() throws IOException {
        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(instructionControllerSpy);
    }
    @Test
    void testRun() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.Instructions,ApplicationState.Menu);


        // when
        instructionControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }
}
