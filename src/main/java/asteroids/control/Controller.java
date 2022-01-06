package asteroids.control;

import asteroids.input.InputListenner;
import asteroids.states.ApplicationState;
import asteroids.states.GameController;
import asteroids.states.MenuController;
import asteroids.states.StateController;

import java.io.IOException;

public class Controller {

    private StateController stateControler;
    private ApplicationState applicationState;
    private final Thread inputThread;
    private final InputListenner inputListenner;

    public Controller(){
        inputListenner = new InputListenner();
        inputThread = new Thread(inputListenner);
        changeState(ApplicationState.Menu);
    }

    public void run() throws IOException {
        getInputThread().start();

        while (getStateControler() != null)
            getStateControler().run();

        getInputListenner().stop();
        getInputThread().interrupt();
    }


    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void changeState(ApplicationState state){
        applicationState = state;
        switch (state){
            case Game -> stateControler=new GameController(this);
            case Menu -> stateControler = new MenuController(this);
            case Exit, LeaderBoard -> stateControler=null;
        }
    }

    public InputListenner getInputListenner() {
        return inputListenner;
    }

    public Thread getInputThread() {
        return inputThread;
    }

    public StateController getStateControler() {
        return stateControler;
    }
}