package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import java.awt.*;


public class LeaderboardScreen extends ScreenView {
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    public LeaderboardScreen();
    @Override
    public void draw() ;
    public TerminalPosition getTerminalPosition(int row, int stringLen);
    @Override
    public TerminalSize getSize();
}