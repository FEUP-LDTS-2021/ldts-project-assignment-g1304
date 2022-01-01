package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.times;

public class ScreenViewTest extends Assertions {

    ScreenView screenView ;
    Font font;
    @BeforeEach
    void prepareScreen(){
        screenView = Mockito.mock(ScreenView.class, Mockito.CALLS_REAL_METHODS);
        font = new Font(Font.MONOSPACED,Font.PLAIN, 4);
        Mockito.when(screenView.getFont()).thenReturn(font);
        Mockito.when(screenView.getSize()).thenReturn(new TerminalSize(50,50));
    }

    @Test
    void TestInitScreen() throws IOException {

        //when
        screenView.initScreen();

        // then
        assertEquals(screenView.getFont(), font);
        assertNotNull(screenView.getGraphics());

        // final
        screenView.close();

    }

    @Test
    void refresh() throws IOException {
        //given
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.refresh();

        //then
        Mockito.verify(screen, times(1)).refresh(Screen.RefreshType.AUTOMATIC);
    }

    @Test
    void close() throws IOException {
        //given
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.close();

        //then
        Mockito.verify(screen, times(1)).close();
    }

    @Test
    void clear(){

        //given
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.clear();

        //then
        //Mockito.verify(graphics, times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        //Mockito.verify(graphics, times(1)).fillRectangle(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(screen, times(1)).clear();
    }

}
