package asteroids.view.screens;

import asteroids.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class LeaderboardScreen extends ScreenView {
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private static final int PADDING_X = 3;
    private static final int PADDING_Y = 4;

    public LeaderboardScreen(){
        setFont(font);
    }
    @Override
    public void draw() throws IOException {
        clear();

        try {
            File myObj = new File(getClass().getClassLoader().getResource("leaderboardDraw.txt").toURI());
            Scanner myReader = new Scanner(myObj);
            int y = PADDING_Y;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                setForegroundColor(Color.White);
                printLine(line, PADDING_X, y);
                if (y == PADDING_Y+13)
                    drawRedLine(line, y);

                y++;
            }
            myReader.close();
        } catch (FileNotFoundException | URISyntaxException e) {
            System.out.println("An error occurred.");
        }
        refresh();
    }

    private void drawRedLine(String line, int y){
        setForegroundColor(Color.Red);
        int beginBorder = line.indexOf("||") + 2;
        int endBorder = line.indexOf("||", beginBorder);
        printLine(line.substring(beginBorder, endBorder), PADDING_X + beginBorder, y);
    }


    private void printLine(String line, int x, int y){
        getGraphics().putString(new TerminalPosition(x, y), line);
    }

    public TerminalPosition getTerminalPosition(int row, int stringLen){
        return new TerminalPosition(getSize().getColumns()/2-stringLen/2,row);
    }
    @Override
    public TerminalSize getSize() {
        return new TerminalSize(41, 24);
    }
}