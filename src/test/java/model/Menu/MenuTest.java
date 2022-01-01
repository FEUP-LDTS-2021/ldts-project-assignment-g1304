package model.Menu;

import model.Menu.Menu;
import model.Menu.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest extends Assertions {
    Menu menu;

    @BeforeEach
    void initMenu(){
        menu = new Menu();
    }

    @Test
    void choose(){
        // when
        boolean choose1 = menu.isChoosed();
        menu.choose();
        boolean choose2 =menu.isChoosed();

        // then
        assertFalse(choose1);
        assertTrue(choose2);
    }

    @Test
    void selectNextChoose(){
        // when
        menu.choose();
        MenuItem select1 = menu.getSelected();
        menu.selectNext();
        MenuItem select2 = menu.getSelected();
        menu.selectNext();
        MenuItem select3 = menu.getSelected();
        menu.selectNext();
        MenuItem select4 = menu.getSelected();
        menu.selectNext();

        // then
        assertEquals(MenuItem.Play, select1);
        assertEquals(MenuItem.Play, select2);
        assertEquals(MenuItem.Play, select3);
        assertEquals(MenuItem.Play, select4);
    }

    @Test
    void selectPreviousChoose(){
        // when
        menu.selectNext();
        menu.selectNext();
        menu.selectNext();

        menu.choose();

        MenuItem select1 = menu.getSelected();
        menu.selectprevious();
        MenuItem select2 = menu.getSelected();
        menu.selectprevious();
        MenuItem select3 = menu.getSelected();
        menu.selectprevious();
        MenuItem select4 = menu.getSelected();
        menu.selectprevious();

        //then
        assertEquals(MenuItem.Exit, select1);
        assertEquals(MenuItem.Exit, select2);
        assertEquals(MenuItem.Exit, select3);
        assertEquals(MenuItem.Exit, select4);
    }

    @Test
    void selectNextNotChoose(){
        // when
        MenuItem select1 = menu.getSelected();
        menu.selectNext();
        MenuItem select2 = menu.getSelected();
        menu.selectNext();
        MenuItem select3 = menu.getSelected();
        menu.selectNext();
        MenuItem select4 = menu.getSelected();
        menu.selectNext();

        // then
        assertEquals(MenuItem.Play, select1);
        assertEquals(MenuItem.LeaderBoard, select2);
        assertEquals(MenuItem.Exit, select3);
        assertEquals(MenuItem.Exit, select4);
    }

    @Test
    void selectPreviousNotChoose(){
        // when
        menu.selectNext();
        menu.selectNext();
        menu.selectNext();

        MenuItem select1 = menu.getSelected();
        menu.selectprevious();
        MenuItem select2 = menu.getSelected();
        menu.selectprevious();
        MenuItem select3 = menu.getSelected();
        menu.selectprevious();
        MenuItem select4 = menu.getSelected();
        menu.selectprevious();

        //then
        assertEquals(MenuItem.Exit, select1);
        assertEquals(MenuItem.LeaderBoard, select2);
        assertEquals(MenuItem.Play, select3);
        assertEquals(MenuItem.Play, select4);
    }


}
