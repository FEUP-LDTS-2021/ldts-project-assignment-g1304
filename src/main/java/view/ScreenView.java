package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class ScreenView extends View{

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
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
        try {
            clear();
            view.draw();

            Thread.sleep(100);
            refresh();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }


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

    public Screen getScreen() {
        return screen;
    }

    public TerminalSize getSize(){
        return new TerminalSize(WIDTH, HEIGHT);
    }

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
