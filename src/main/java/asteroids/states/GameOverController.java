package asteroids.states;

import asteroids.control.Controller;
import asteroids.view.screens.GameOverScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOverController implements StateController, KeyListener {

    public GameOverController(Controller context);
    public GameOverScreen getScreenView();
    @Override
    public void run();

    @Override
    public void nextState();

    @Override
    public void keyTyped(KeyEvent e);

    @Override
    public void keyPressed(KeyEvent e);

    public void updateLeaderboard(String path);

    public void writeLeaderboard(String path) ;

    public String formatScore(Integer scr);

    public void writeName(char c);

    @Override
    public void keyReleased(KeyEvent e) ;
    public void setScore(int score);

    public String getNickName();

    public String getNickNameSpaces();

    public List<Integer> getScore();

    public List<String> getName();

    public void setName(List<String> name);

    public void setScoreList(List<Integer> score);

    public void setScreenView(GameOverScreen screenView);
}
