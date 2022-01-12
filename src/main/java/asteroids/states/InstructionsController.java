package asteroids.states;

import asteroids.control.Controller;
import asteroids.input.InputObserver;
import asteroids.view.screens.InstructionScreen;
import asteroids.view.screens.ScreenView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class InstructionsController implements StateController, InputObserver {
    private final Controller context;
    private final ScreenView screenView;

    public InstructionsController(Controller context){
        this.context = context;
        screenView = new InstructionScreen();
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    @Override
    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Escape) {
            nextState();
        }
    }

    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        context.getInputListenner().setScreen(getScreenView().getScreen());
        context.getInputListenner().addInputObserver(this);

        while (context.getApplicationState() == ApplicationState.Instructions) {
            getScreenView().draw();
        }

        context.getInputListenner().removeInputObserver(this);
        context.getInputListenner().setScreen(null);
        getScreenView().close();
    }

    @Override
    public void nextState() {
        context.changeState(ApplicationState.Menu);
    }
}
