package asteroids.control;

import asteroids.states.ApplicationState;
import asteroids.states.GameController;
import asteroids.states.MenuController;
import asteroids.states.StateController;

import java.io.IOException;

public class Controller {

    private static Controller controller;

    private StateController stateControler;
    private ApplicationState applicationState;

    private Controller(){
        changeState(ApplicationState.Menu);
    }

    public void run() throws IOException {
        while (getStateControler() != null)
            getStateControler().run();
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

    public StateController getStateControler() {
        return stateControler;
    }


    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();

        return controller;
    }
}