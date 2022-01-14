package asteroids.states;


import asteroids.control.Controller;
import asteroids.view.screens.ScreenView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LeaderboardController implements StateController, KeyListener {
    private final Controller context;
    private final ScreenView screenView;

    public LeaderboardController(Controller context);

    public ScreenView getScreenView() ;

    @Override
    public void run() throws IOException;

    @Override
    public void nextState();

    @Override
    public void keyTyped(KeyEvent e);

    @Override
    public void keyPressed(KeyEvent e);

    @Override
    public void keyReleased(KeyEvent e);
}