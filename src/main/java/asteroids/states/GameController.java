
package asteroids.states;

import asteroids.control.Controller;
import asteroids.control.PlayerController;
import asteroids.model.GameModel;
import asteroids.view.screens.GameScreen;
import asteroids.view.screens.ScreenView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameController implements StateController, KeyListener {

    private final ScreenView screenView;
    private final GameModel gameModel;
    private final PlayerController playerController;
    private final Controller context;

    public GameController(Controller context){
        this.context = context;
        gameModel = new GameModel();
        screenView = new GameScreen(gameModel);
        playerController = new PlayerController(gameModel.getPlayer());
    }

    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);
        getScreenView().addKeyListenner(getPlayerController());

        long pastTime =  System.currentTimeMillis();
        while (context.getApplicationState() == ApplicationState.Game && playerAlive()){
            long now = System.currentTimeMillis();
            getGameModel().update(now-pastTime);
            getScreenView().draw();

            pastTime=now;
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().removeKeyListenner(getPlayerController());
        getScreenView().close();
        nextState();

    }

    private boolean playerAlive(){
        return getGameModel().getPlayer().isAlive();
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    @Override
    public void nextState() {
        context.changeState(ApplicationState.Menu);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nextState();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}