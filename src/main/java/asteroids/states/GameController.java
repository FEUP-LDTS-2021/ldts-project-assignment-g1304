
package asteroids.states;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import asteroids.control.Controller;
import asteroids.control.PlayerController;
import asteroids.input.InputObserver;
import asteroids.model.GameModel;
import asteroids.view.screens.GameScreen;
import asteroids.view.ScreenView;

import java.io.IOException;

public class GameController implements StateController, InputObserver {

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
        context.getInputListenner().setScreen(getScreenView().getScreen());
        context.getInputListenner().addInputObserver(this);
        context.getInputListenner().addInputObserver(getPlayerController());

        long pastTime =  System.currentTimeMillis();
        while (context.getApplicationState() == ApplicationState.Game){
            long now = System.currentTimeMillis();
            getGameModel().update(now-pastTime);
            getScreenView().draw();

            pastTime=now;
        }

        context.getInputListenner().removeInputObserver(this);
        context.getInputListenner().removeInputObserver(getPlayerController());
        context.getInputListenner().setScreen(null);
        getScreenView().close();
        nextState();

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
    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.EOF || key.getKeyType() == KeyType.Escape) {
            nextState();
        }
    }
}