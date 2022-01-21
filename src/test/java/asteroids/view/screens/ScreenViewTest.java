package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ScreenViewTest extends Assertions {

    ScreenView screenView ;
    Font font;
    @BeforeEach
    void prepareScreen(){
        screenView = Mockito.mock(ScreenView.class, Mockito.CALLS_REAL_METHODS);
        font = new Font(Font.MONOSPACED,Font.PLAIN, 4);
        screenView.setFont(font);
        Mockito.when(screenView.getSize()).thenReturn(new TerminalSize(50,50));
    }

    @Test
    void TestInitScreen() {
        try{
            //when
            screenView.initScreen();

            // then
            assertEquals(screenView.getFont(), font);
            assertNotNull(screenView.getGraphics());
            // final
            screenView.close();
        } catch (IOException e) {
            assert  false;
        }

    }

    @Test
    void refresh() throws IOException {
        //given
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.refresh();

        //then
        Mockito.verify(screen, times(1)).refresh(Screen.RefreshType.AUTOMATIC);
    }

    @Test
    void close() throws IOException {
        //given
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);

        // when
        screenView.close();

        //then
        Mockito.verify(screen, times(1)).close();
    }

    @Test
    void clear() {

        //given
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenView.getScreen()).thenReturn(screen);
        Mockito.when(screenView.getGraphics()).thenReturn(graphics);

        //Alterado do BeforeEach para aqui
        Mockito.when(screenView.getSize()).thenReturn(new TerminalSize(50,50));

        // when
        screenView.clear();

        //then
        Mockito.verify(graphics, times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, times(1)).fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(50,50),' ');
    }

    @Test
    void addKeyListenner(){
        // given
        AWTTerminalFrame awtTerminalFrame = Mockito.mock(AWTTerminalFrame.class);
        Component component = Mockito.mock(Component.class);
        KeyListener keyListener = Mockito.mock(KeyListener.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        Mockito.when(screenView.getScreen()).thenReturn(screen);
        Mockito.when(screen.getTerminal()).thenReturn(awtTerminalFrame);
        Mockito.when(awtTerminalFrame.getComponent(0)).thenReturn(component);


        // when
        screenView.addKeyListenner(keyListener);

        // then
        verify(component).addKeyListener(keyListener);
    }

    @Test
    void removeKeyListenner(){
        // given
        AWTTerminalFrame awtTerminalFrame = Mockito.mock(AWTTerminalFrame.class);
        Component component = Mockito.mock(Component.class);
        KeyListener keyListener = Mockito.mock(KeyListener.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        Mockito.when(screenView.getScreen()).thenReturn(screen);
        Mockito.when(screen.getTerminal()).thenReturn(awtTerminalFrame);
        Mockito.when(awtTerminalFrame.getComponent(0)).thenReturn(component);


        // when
        screenView.removeKeyListenner(keyListener);

        // then
        verify(component).removeKeyListener(keyListener);
    }

}
