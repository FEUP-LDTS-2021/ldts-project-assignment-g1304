package asteroids.states;

import asteroids.control.Controller;
import asteroids.view.screens.InstructionScreen;
import asteroids.view.screens.ScreenView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class InstructionsController implements StateController, KeyListener {
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
    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);


        while (context.getApplicationState() == ApplicationState.Instructions) {
            getScreenView().draw();
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().close();
    }

    @Override
    public void nextState() {
        context.changeState(ApplicationState.Menu);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nextState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
