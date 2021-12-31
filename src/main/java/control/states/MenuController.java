package control.states;

import com.googlecode.lanterna.input.KeyStroke;
import control.input.InputObserver;

public class MenuController implements StateController, InputObserver {

    @Override
    public void run() ;

    public void nextState();

    @Override
    public void processKey(KeyStroke key);
