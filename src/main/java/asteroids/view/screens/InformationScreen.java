package asteroids.view.screens;

import asteroids.Color;
import asteroids.Constants;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InformationScreen extends ScreenView{
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private final int PADDING_X;
    private final int PADDING_Y;

    private final List<Integer> redLines;
    private final File informationFile;

    public InformationScreen( List<Integer> redLines, String path, int paddingX, int paddingY) {
        setFont(font);
        PADDING_X = paddingX;
        PADDING_Y = paddingY;
        this.redLines = redLines;
        informationFile = new File(Constants.ROOT+path);
    }

    @Override
    public void draw() throws IOException {
        clear();

        Scanner myReader = new Scanner(informationFile);
        int y = PADDING_Y;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            setForegroundColor(Color.White);
            printLine(line, PADDING_X, y);
            if (redLines.contains(y))
                drawRedLine(line, y);

            y++;
        }
        myReader.close();
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

    @Override
    public TerminalSize getSize() {
        return new TerminalSize(41, 24);
    }
}
