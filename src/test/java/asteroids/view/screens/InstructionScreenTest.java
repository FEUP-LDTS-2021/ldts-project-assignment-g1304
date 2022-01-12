package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
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
    void getTerminalPosition(){
        //given
        TerminalSize size = new TerminalSize(50,40);
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, size.getRows());

        Mockito.doReturn(size).when(instructionScreen).getSize();

        // when
        TerminalPosition p = instructionScreen.getTerminalPosition(size.getRows(), strlen);

        //then
        assertEquals(p, position);

    }
    @Test
    void draw() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        instructionScreen.setGraphics(graphics);

        Mockito.when(instructionScreen.getScreen()).thenReturn(Mockito.mock(Screen.class));
        // when
        instructionScreen.draw();

        // then
        Mockito.verify(instructionScreen, Mockito.times(1)).clear();
        Mockito.verify(instructionScreen, Mockito.times(58)).getGraphics();
        Mockito.verify(instructionScreen, Mockito.times(1)).refresh();
    }
}
