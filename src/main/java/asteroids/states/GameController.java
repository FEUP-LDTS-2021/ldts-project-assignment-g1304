
package asteroids.states;

import asteroids.control.Controller;
import asteroids.control.PlayerController;
import asteroids.model.GameModel;
import asteroids.view.Game.Hud;
import asteroids.view.screens.GameOverScreen;
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
    public static final int FRAME_TIME = 50;

    public GameController(Controller context){
        this.context = context;
        gameModel = new GameModel();
        screenView = new GameScreen(gameModel, new Hud(gameModel));
        playerController = new PlayerController(gameModel.getPlayer());
    }

    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        getScreenView().addKeyListenner(this);
        getScreenView().addKeyListenner(getPlayerController());

        long pastTime =  System.currentTimeMillis();
        long lastUpdate = 0L;

        while (context.getApplicationState() == ApplicationState.Game && playerAlive()){
            long now = System.currentTimeMillis();
            long deltaTime = now - pastTime;

            lastUpdate += deltaTime;
            lastUpdate = update(lastUpdate);

            getScreenView().draw();

            pastTime=now;
        }

        getScreenView().removeKeyListenner(this);
        getScreenView().removeKeyListenner(getPlayerController());
        getScreenView().close();
        nextState();

    }

    public long update(long lastUpdate){
        while(lastUpdate >= FRAME_TIME) {
            getGameModel().update(FRAME_TIME);
            lastUpdate -= FRAME_TIME;
        }

        return  lastUpdate;
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
        context.changeState(ApplicationState.GameOver);
        ((GameOverController)context.getStateControler()).setScore(getGameModel().getPlayer().getScore());
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