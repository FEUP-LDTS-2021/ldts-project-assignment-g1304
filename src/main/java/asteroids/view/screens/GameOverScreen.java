package asteroids.view.screens;

import asteroids.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;

public class GameOverScreen extends ScreenView {
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    private String scoreValue;
    private String nickNameSpaces;

    public GameOverScreen() {
        setFont(font);
        scoreValue = "";
        nickNameSpaces = "_ _ _ _ _ _ _ _ _ _ _";
    }

    @Override
    public void draw() throws IOException {
        clear();
        String gameOver = "G A M E   O V E R";
        String score = "Score : " + scoreValue;
        String outputName = "Enter your name:";
        setColor(Color.Red);
        getGraphics().putString(getTerminalPosition(0.3, gameOver.length()), gameOver);
        getGraphics().putString(getTerminalPosition(0.4, score.length()), score);

        setColor(Color.White);
        getGraphics().putString(getTerminalPosition(0.6, outputName.length()), outputName);
        getGraphics().putString(getTerminalPosition(0.7, nickNameSpaces.length()), nickNameSpaces);

        refresh();
    }

    @Override
    public TerminalSize getSize() {
        return new TerminalSize(35, 15);
    }

    public TerminalPosition getTerminalPosition(double percentageRows, int stringLen) {
        return new TerminalPosition(getSize().getColumns() / 2 - stringLen / 2, (int) (getSize().getRows() * percentageRows));
    }

    public void setScore(int score) {
        scoreValue = Integer.toString(score);
    }

    public String getScoreValue() {
        return scoreValue;
    }

    public void setNickNameSpaces(String nickNameSpaces) {
        this.nickNameSpaces = nickNameSpaces;
    }

    public String getNickNameSpaces() {
        return nickNameSpaces;
    }
}