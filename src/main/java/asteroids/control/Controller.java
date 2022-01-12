package asteroids.control;

import asteroids.input.InputListenner;
import asteroids.states.*;

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
            case Instructions -> stateControler = new InstructionsController(this);
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