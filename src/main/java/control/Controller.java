package control;

import control.input.InputListenner;
import model.GameModel;
import view.Game.GameView;
import view.ScreenView;
import java.io.IOException;

public class Controller {

    private final ScreenView screenView;
    private final GameModel gameModel;
    private final PlayerController playerController;

    public Controller(){
        gameModel = new GameModel();
        screenView = new ScreenView(new GameView(gameModel));
        playerController = new PlayerController(gameModel.getPlayer());
    }

    public void run() throws IOException {

        screenView.initScreen();

        InputListenner inputListenner = new InputListenner(screenView.getScreen());
        Thread inputThread = new Thread(inputListenner);

        inputListenner.addInputObserver(playerController);

        inputThread.start();
        long pastTime =  System.currentTimeMillis();
        while (true){
            long now = System.currentTimeMillis();
            gameModel.update(now-pastTime);
            screenView.draw();

            pastTime=now;

        }

        //inputThread.stop();
        //screenView.close();
    }

}
