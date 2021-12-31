package control.states;

import com.googlecode.lanterna.input.KeyStroke;
import control.input.InputObserver;
public class GameController implements StateController, InputObserver {


    public void run();

    @Override
    public void nextState();

    @Override
    public void processKey(KeyStroke key);
}
