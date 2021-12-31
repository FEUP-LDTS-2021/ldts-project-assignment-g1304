package control;

import control.input.InputListenner;
import model.GameModel;
import view.screens.GameView;

import java.io.IOException;

public class Controller {

    private final Thread inputThread;
    private final InputListenner inputListenner;
    private GameView gameView;
    private GameModel gameModel;
    private PlayerController controller;

    public Controller(){
        inputListenner = new InputListenner();
        inputThread = new Thread(inputListenner);
        gameModel = new GameModel();
        controller = new PlayerController(gameModel.getPlayer());
        gameView = new GameView(gameModel);
    }

    public void run() throws IOException {
        gameView.initScreen();


        InputListenner inputListenner = new InputListenner();
        Thread inputThread = new Thread(inputListenner);

        inputListenner.setScreen(gameView.getScreen());

        inputListenner.addInputObserver(controller);

        inputThread.start();

        long pastTime =  System.currentTimeMillis();
        while (true){
            long now = System.currentTimeMillis();
            gameModel.update(now-pastTime);
            gameView.draw();

            pastTime=now;

        }


    }


}
