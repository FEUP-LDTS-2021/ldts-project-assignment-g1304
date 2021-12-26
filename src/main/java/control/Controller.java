package control;

import model.GameModel;
import view.Game.GameView;
import view.ScreenView;
import java.io.IOException;

public class Controller {

    ScreenView screenView;
    GameModel gameModel;

    public Controller(){
        gameModel = new GameModel();
        screenView = new ScreenView(new GameView(gameModel));
    }

    public void run() throws IOException {

        screenView.initScreen();

        while (true){
            screenView.draw();

        }


        //screenView.close();
    }

}
