package asteroids.view.screens;

import asteroids.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public abstract class ScreenView{

    protected TextGraphics graphics;
    private TerminalScreen screen;
    private Font font;

    public void initScreen() throws IOException {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.NOTHING, getFont());
        Terminal terminal = new DefaultTerminalFactory()
                .setForceAWTOverSwing(true)
                .setInitialTerminalSize(getSize())
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();

        screen = new TerminalScreen(terminal);
        getScreen().setCursorPosition(null);
        getScreen().startScreen();
        getScreen().doResizeIfNecessary();

        setGraphics(screen.newTextGraphics());
    }

    public void addKeyListenner(KeyListener keyListener){
        ((AWTTerminalFrame)getScreen().getTerminal()).getComponent(0).addKeyListener(keyListener);
    }

    public void removeKeyListenner(KeyListener keyListener){
        ((AWTTerminalFrame)getScreen().getTerminal()).getComponent(0).removeKeyListener(keyListener);
    }

    public abstract void draw() throws IOException;

    public TerminalScreen getScreen() {
        return screen;
    }

    public abstract TerminalSize getSize();

    public void refresh() throws IOException {
        getScreen().refresh(Screen.RefreshType.AUTOMATIC);
    }

    public void close() throws IOException {
        getScreen().close();
    }

    public void setForegroundColor(Color color){
        getGraphics().setForegroundColor(color.getColor());
    }

    public void setBackgroundColor(Color color){
        getGraphics().setBackgroundColor(color.getColor());
    }

    public void clear() {
        setBackgroundColor(Color.Black);
        getGraphics().fillRectangle(new TerminalPosition(0, 0), getSize(), ' ');
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
