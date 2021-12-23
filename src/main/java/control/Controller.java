package control;

import view.Game.GameView;
import view.ScreenView;

import java.io.IOException;

public class Controller {

    ScreenView screenView;

    public Controller(){
        screenView = new ScreenView(new GameView());
    }

    public void run() throws IOException {

        screenView.initScreen();

        while (true){

            screenView.draw();

        }


        //screenView.close();
    }

}
