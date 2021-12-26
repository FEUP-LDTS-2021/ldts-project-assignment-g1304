package control;

import control.input.InputListenner;
import model.GameModel;
import view.Game.GameView;
import view.ScreenView;
import java.io.IOException;

public class Controller {

    private ScreenView screenView;
    private GameModel gameModel;
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

        while (true){
            screenView.draw();

        }

        //inputThread.stop();
        //screenView.close();
    }

}
