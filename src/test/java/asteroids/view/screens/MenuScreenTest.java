package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import asteroids.model.Menu.Menu;
import asteroids.model.Menu.MenuItem;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuScreenTest extends Assertions {

    Menu menu;
    TextGraphics textGraphics;
    MenuScreen menuScreen;
    MenuItemView play;
    MenuItemView leaderboard;
    MenuItemView exit;

    @BeforeEach
    void init(){

        play = Mockito.mock(MenuItemView.class);
        leaderboard = Mockito.mock(MenuItemView.class);
        exit = Mockito.mock(MenuItemView.class);

        menu = Mockito.mock(Menu.class);

        Mockito.when(menu.getSelected()).thenReturn(MenuItem.Play);

        menuScreen = Mockito.spy(new MenuScreen(menu));

        Mockito.doReturn(play).when(menuScreen).getPlay();
        Mockito.doReturn(leaderboard).when(menuScreen).getLeaderBoard();
        Mockito.doReturn(exit).when(menuScreen).getExit();


        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.doReturn(screen).when(menuScreen).getScreen();
        Mockito.doNothing().when(menuScreen).choose(Mockito.any());

        textGraphics = Mockito.mock(TextGraphics.class);
        menuScreen.setGraphics(textGraphics);

    }

    @Test
    void initScreen()  {
        try{
            //given
            menuScreen.setGraphics(null);
            Mockito.doCallRealMethod().when(menuScreen).getScreen();
            TerminalPosition p1 = Mockito.mock(TerminalPosition.class);
            TerminalPosition p2 = Mockito.mock(TerminalPosition.class);
            TerminalPosition p3 = Mockito.mock(TerminalPosition.class);

            Mockito.doReturn(p1).when(menuScreen).getTerminalPosition(0.5, MenuItem.Play.toString().length());
            Mockito.doReturn(p2).when(menuScreen).getTerminalPosition(0.6, MenuItem.LeaderBoard.toString().length());
            Mockito.doReturn(p3).when(menuScreen).getTerminalPosition(0.7, MenuItem.Exit.toString().length());

            //when
            menuScreen.initScreen();

            //then
            Mockito.verify(play).setGraphics(Mockito.notNull());
            Mockito.verify(leaderboard).setGraphics(Mockito.notNull());
            Mockito.verify(exit).setGraphics(Mockito.notNull());

            Mockito.verify(play).setPosition(p1);
            Mockito.verify(leaderboard).setPosition(p2);
            Mockito.verify(exit).setPosition(p3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPlay(){
        MenuScreen menuS = new MenuScreen(menu);

        // when

        MenuItemView play = menuS.getPlay();
        MenuItemView leaderBoard = menuS.getLeaderBoard();
        MenuItemView exit = menuS.getExit();

        // then
        assertEquals(play.getMenuItem(), MenuItem.Play);
        assertEquals(leaderBoard.getMenuItem(), MenuItem.LeaderBoard);
        assertEquals(exit.getMenuItem(), MenuItem.Exit);
    }

    @Test
    void getTerminalPosition(){

        TerminalSize size = new TerminalSize(50,40);
        double percentage = 0.7;
        int strlen = 20;
        TerminalPosition position = new TerminalPosition(size.getColumns()/2-strlen/2, (int)(size.getRows()*percentage));

        Mockito.doReturn(size).when(menuScreen).getSize();

        // when
        TerminalPosition p = menuScreen.getTerminalPosition(percentage, strlen);


        //then
        assertEquals(p, position);

    }


    @Test
    void choosePlay(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItem.Play);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
        Mockito.verify(leaderboard, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.times(1)).setSelected(true);
        Mockito.verify(leaderboard, Mockito.never()).setSelected(true);
        Mockito.verify(exit,Mockito.never()).setSelected(true);
    }

    @Test
    void chooseLeaderboard(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItem.LeaderBoard);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
        Mockito.verify(leaderboard, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.never()).setSelected(true);
        Mockito.verify(leaderboard, Mockito.times(1)).setSelected(true);
        Mockito.verify(exit,Mockito.never()).setSelected(true);
    }

    @Test
    void chooseExit(){
        // given
        Mockito.doCallRealMethod().when(menuScreen).choose(Mockito.any());

        // when
        menuScreen.choose(MenuItem.Exit);

        //then
        Mockito.verify(play, Mockito.times(1)).setSelected(false);
        Mockito.verify(leaderboard, Mockito.times(1)).setSelected(false);
        Mockito.verify(exit, Mockito.times(1)).setSelected(false);

        Mockito.verify(play, Mockito.never()).setSelected(true);
        Mockito.verify(leaderboard, Mockito.never()).setSelected(true);
        Mockito.verify(exit,Mockito.times(1)).setSelected(true);
    }

    @Test
    void draw() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(menuScreen, Mockito.times(1)).choose(Mockito.any());
        Mockito.verify(menuScreen, Mockito.times(1)).clear();
        Mockito.verify(menuScreen, Mockito.times(1)).refresh();
    }

    @Test
    void drawCallTitle() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(menuScreen, Mockito.times(1)).drawTitle();
    }

    @Test
    void drawPlay() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(play, Mockito.times(1)).draw();
    }

    @Test
    void drawLeaderBoard() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(leaderboard, Mockito.times(1)).draw();
    }

    @Test
    void drawExit() throws IOException {

        // when
        menuScreen.draw();

        // then
        Mockito.verify(exit, Mockito.times(1)).draw();
    }

}
