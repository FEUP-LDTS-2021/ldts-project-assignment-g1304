
package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Menu.Menu;
import asteroids.model.Menu.MenuItem;

import java.awt.*;
import java.io.IOException;

public class MenuScreen extends ScreenView {
    private Menu menu;
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    private final MenuItemView play;
    private final MenuItemView leaderBoard;
    private final MenuItemView instructions;
    private final MenuItemView exit;

    public MenuScreen(Menu menu){
        this.menu = menu;
        setFont(font);
        play = new MenuItemView(MenuItem.Play);
        leaderBoard = new MenuItemView(MenuItem.LeaderBoard);
        instructions = new MenuItemView(MenuItem.Instructions);
        exit = new MenuItemView(MenuItem.Exit);
    }

    @Override
    public void initScreen() throws IOException {
        super.initScreen();

        getPlay().setGraphics(getGraphics());
        getPlay().setPosition(getTerminalPosition(0.4, play.getMenuItem().toString().length()));

        getLeaderBoard().setGraphics(getGraphics());
        getLeaderBoard().setPosition(getTerminalPosition(0.5, leaderBoard.getMenuItem().toString().length()));

        getInstructions().setGraphics(getGraphics());
        getInstructions().setPosition(getTerminalPosition(0.6, instructions.getMenuItem().toString().length()));

        getExit().setGraphics(getGraphics());
        getExit().setPosition(getTerminalPosition(0.7, exit.getMenuItem().toString().length()));
    }

    @Override
    public void draw() throws IOException {

        choose(menu.getSelected());

        clear();
        drawTitle();

        getPlay().draw();
        getInstructions().draw();
        getLeaderBoard().draw();
        getExit().draw();

        refresh();

    }

    public void choose(MenuItem menuItem){
        getPlay().setSelected(false);
        getLeaderBoard().setSelected(false);
        getInstructions().setSelected(false);
        getExit().setSelected(false);

        switch (menuItem){
            case Play -> getPlay().setSelected(true);
            case LeaderBoard -> getLeaderBoard().setSelected(true);
            case Instructions -> getInstructions().setSelected(true);
            case Exit -> getExit().setSelected(true);
        }
    }

    public MenuItemView getPlay() {
        return play;
    }

    public MenuItemView getLeaderBoard() {
        return leaderBoard;
    }

    public MenuItemView getInstructions() {
        return instructions;
    }

    public MenuItemView getExit() {
        return exit;
    }


    public void drawTitle(){
        String title = "Asteroids";
        getGraphics().setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        getGraphics().putString(getTerminalPosition(0.1, title.length()), title);
    }

    public TerminalPosition getTerminalPosition(double percentageRows, int stringLen){
        return new TerminalPosition(getSize().getColumns()/2-stringLen/2, (int)(getSize().getRows()*percentageRows));
    }


    @Override
    public TerminalSize getSize() {
        return new TerminalSize(30, 20);
    }
}