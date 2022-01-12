package asteroids.control;


import asteroids.states.*;

import java.io.IOException;

public class Controller {

    private StateController stateControler;
    private ApplicationState applicationState;

    public Controller(){
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
            case Instructions -> stateControler = new InstructionsController(this);
            case Exit, LeaderBoard -> stateControler=null;
        }
    }

    public StateController getStateControler() {
        return stateControler;
    }
}