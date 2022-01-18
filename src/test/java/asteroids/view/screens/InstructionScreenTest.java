package asteroids.view.screens;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InstructionScreenTest extends Assertions {

    InstructionScreen instructionScreen;

    @BeforeEach
    void helper(){
        instructionScreen = Mockito.spy(new InstructionScreen());
    }

    @Test
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        instructionScreen.setGraphics(graphics);

        Mockito.when(instructionScreen.getScreen()).thenReturn(Mockito.mock(TerminalScreen.class));
        // when
        instructionScreen.draw();

        // then
        Mockito.verify(instructionScreen, Mockito.times(1)).clear();
        Mockito.verify(instructionScreen, Mockito.times(52)).getGraphics();
        Mockito.verify(instructionScreen, Mockito.times(1)).refresh();
    }
}
