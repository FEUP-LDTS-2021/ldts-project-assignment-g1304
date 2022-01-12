package asteroids.states;

import asteroids.control.Controller;
import asteroids.input.InputObserver;
import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.input.KeyStroke;


public class InstructionsController implements StateController, InputObserver {
    private final Controller context;
    private final ScreenView screenView;

    public InstructionsController(Controller context);

    public ScreenView getScreenView();

    @Override
    public void processKey(KeyStroke key);

    @Override
    public void run();

    @Override
    public void nextState();
