package asteroids.states;

import com.googlecode.lanterna.input.KeyStroke;
import asteroids.control.Controller;
import asteroids.input.InputObserver;
import asteroids.model.Menu.Menu;
import asteroids.view.screens.ScreenView;
import asteroids.view.screens.MenuScreen;

import java.io.IOException;

public class MenuController implements StateController, InputObserver {

    private final Controller context;
    private final ScreenView screenView;
    private final Menu menu;

    public MenuController(Controller context){
        this.context = context;
        this.menu = new Menu();
        this.screenView = new MenuScreen(menu);
    }

    @Override
    public void run() throws IOException {
        getScreenView().initScreen();
        context.getInputListenner().setScreen(getScreenView().getScreen());
        context.getInputListenner().addInputObserver(this);

        while (context.getApplicationState() == ApplicationState.Menu) {
            getScreenView().draw();

            if(getMenu().isChoosed())
                nextState();
        }


        context.getInputListenner().removeInputObserver(this);
        context.getInputListenner().setScreen(null);
        getScreenView().close();
    }

    public ScreenView getScreenView() {
        return screenView;
    }

    public void nextState(){
        switch (getMenu().getSelected()){
            case Play -> context.changeState(ApplicationState.Game);
            case LeaderBoard -> context.changeState(ApplicationState.LeaderBoard);
            case Exit -> context.changeState(ApplicationState.Exit);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowDown -> getMenu().selectNext();
            case ArrowUp -> getMenu().selectprevious();
            case Enter -> getMenu().choose();
            case Escape, EOF -> context.changeState(ApplicationState.Exit);
        }
    }
}
