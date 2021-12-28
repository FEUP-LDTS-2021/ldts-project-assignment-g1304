package control;

import control.input.InputListenner;
import model.GameModel;
import view.Game.GameView;
import view.ScreenView;
import java.io.IOException;

public class Controller {

    private final ScreenView screenView;
    private final GameModel gameModel;
    private InputListenner inputListenner;
    private Thread inputThread;

    public Controller(){
        gameModel = new GameModel();
        screenView = new ScreenView(new GameView(gameModel));
    }

    public void run() throws IOException {

        screenView.initScreen();

        inputListenner = new InputListenner(screenView.getScreen());
        inputThread = new Thread(inputListenner);

        inputListenner.addInputObserver(gameModel.getPlayer());

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
