package asteroids.view.screens;

import asteroids.Color;
import asteroids.Constants;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class InformationScreenTest extends Assertions {
    InformationScreen informationScreen;
    TextGraphics graphics;
    String path = "\\src\\test\\resources\\InformationTest.txt";
    @BeforeEach
    void init(){
        List<Integer> redLines = List.of(5, 6);

        informationScreen = Mockito.spy(new InformationScreen(redLines, path, 3, 2));
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.doReturn(screen).when(informationScreen).getScreen();
        Mockito.doReturn(graphics).when(informationScreen).getGraphics();
    }

    @Test
    void draw(){
        // given

        try {
            // when
            informationScreen.draw();

            // then
            Mockito.verify(informationScreen).clear();

            Mockito.verify(informationScreen, Mockito.times(7)).setForegroundColor(Color.White);
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 2),
                    "|||||| Test ||||||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 3),
                    "||              ||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 4),
                    "||     OLA      ||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 5),
                    "||     TEST     ||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 6),
                    "||    Rouge     ||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 7),
                    "||              ||");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(3, 8),
                    "||||||||||||||||||");

            // red Lines
            Mockito.verify(informationScreen, Mockito.times(2)).setForegroundColor(Color.Red);
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(5, 5),
                    "     TEST     ");
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(5, 6),
                    "    Rouge     ");

            Mockito.verify(informationScreen).refresh();

        }catch (IOException exception){
            assert false;
        }
    }

    @Test
    public void getSize() {

        assertEquals(new TerminalSize(41, 24), informationScreen.getSize());
    }
}
