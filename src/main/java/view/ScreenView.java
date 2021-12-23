package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class ScreenView extends View{
    private View view;
    private Screen screen;

    public ScreenView(View view){
        this.view = view;

    }

    public void initScreen() throws IOException {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.NOTHING, getFont());
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(WIDTH, HEIGHT))
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        setGraphics(screen.newTextGraphics());
        view.setGraphics(graphics);
    }

    @Override
    public void draw() throws IOException {
        clear();
        view.draw();
        refresh();
    }


    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    @Override
    public Font getFont() {
        return view.getFont();
    }


    protected void refresh() throws IOException {
        screen.refresh();
    }

    public void close() throws IOException {
        screen.close();
    }

    protected void clear() {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), getSize(), ' ');
    }
}