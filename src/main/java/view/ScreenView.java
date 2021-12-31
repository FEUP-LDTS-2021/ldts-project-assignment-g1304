package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;

public abstract class ScreenView extends View{

    private Screen screen;


    public void initScreen() throws IOException {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.NOTHING, getFont());
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(getSize())
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        setGraphics(screen.newTextGraphics());
    }

    public Screen getScreen() {
        return screen;
    }

    public abstract TerminalSize getSize();

    protected void refresh() throws IOException {
        screen.refresh(Screen.RefreshType.AUTOMATIC);
    }

    public void close() throws IOException {
        screen.close();
    }

    protected void clear() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), getSize(), ' ');
    }
}
