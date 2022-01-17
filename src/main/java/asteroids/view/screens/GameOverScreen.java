package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;


public class GameOverScreen extends ScreenView {

    public GameOverScreen();

    @Override
    public void draw();

    @Override
    public TerminalSize getSize();

    public TerminalPosition getTerminalPosition(double percentageRows, int stringLen) ;

    public void setScore(int score);

    public String getScoreValue();

    public void setNickNameSpaces(String nickNameSpaces);

    public String getNickNameSpaces();
}
