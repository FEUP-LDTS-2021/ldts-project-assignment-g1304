package asteroids.view.screens;

import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import asteroids.model.Menu.MenuItem;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuItemViewTest extends Assertions {
    MenuItemView menuItemView;
    TextGraphics graphics;
    TerminalPosition position;

    @BeforeEach
    void init(){
        graphics = Mockito.mock(TextGraphics.class);
        position = Mockito.mock(TerminalPosition.class);

        menuItemView = Mockito.spy(new MenuItemView(MenuItem.Play));
        menuItemView.setGraphics(graphics);
        menuItemView.setPosition(position);
    }

    @Test
    void drawCallNotSelected(){
        // given
        Mockito.doReturn(false).when(menuItemView).isSelected();
        //when
        menuItemView.draw();

        //then
        Mockito.verify(menuItemView, Mockito.times(0)).drawSelected();
        Mockito.verify(menuItemView, Mockito.times(1)).drawNotSelected();
    }

    @Test
    void drawCallSelected(){
        // given
        Mockito.doReturn(true).when(menuItemView).isSelected();
        //when
        menuItemView.draw();

        //then
        Mockito.verify(menuItemView, Mockito.times(1)).drawSelected();
        Mockito.verify(menuItemView, Mockito.times(0)).drawNotSelected();
    }

    @Test
    public void drawSelected(){
        Mockito.doReturn(position).when(menuItemView).getPosition();
        // when
        menuItemView.drawSelected();

        //then
        Mockito.verify(graphics).setForegroundColor(MenuItemView.selectedColor);
        Mockito.verify(graphics).putString(position, menuItemView.getMenuItem().toString());
    }

    @Test
    public void drawNotSelected(){
        Mockito.doReturn(position).when(menuItemView).getPosition();
        // when
        menuItemView.drawNotSelected();

        //then
        Mockito.verify(graphics).setForegroundColor(MenuItemView.notSelectedColor);
        Mockito.verify(graphics).putString(position, menuItemView.getMenuItem().toString());
    }

    @Test
    void isSelected(){
        // when
        boolean selected1 = menuItemView.isSelected();
        menuItemView.setSelected(true);
        boolean selected2 = menuItemView.isSelected();
        menuItemView.setSelected(false);
        boolean selected3 = menuItemView.isSelected();

        // then

        assertFalse(selected1);
        assertTrue(selected2);
        assertFalse(selected3);

    }

    @Test
    void getMenuItem(){
        // given
        MenuItemView play = new MenuItemView(MenuItem.Play);
        MenuItemView leaderboard = new MenuItemView(MenuItem.LeaderBoard);
        MenuItemView exit = new MenuItemView(MenuItem.Exit);

        // when
        MenuItem p = play.getMenuItem();
        MenuItem l = leaderboard.getMenuItem();
        MenuItem e = exit.getMenuItem();

        //then
        assertEquals(p, MenuItem.Play);
        assertEquals(l, MenuItem.LeaderBoard);
        assertEquals(e, MenuItem.Exit);

    }

    @Test
    void getTerminalPosition() {
        //given
        TerminalPosition terminalPositionMock = Mockito.mock(TerminalPosition.class);
        MenuItemView play = new MenuItemView(MenuItem.Play);

        //when
        play.setPosition(terminalPositionMock);

        //then
        assertEquals(terminalPositionMock, play.getPosition());
    }

}
