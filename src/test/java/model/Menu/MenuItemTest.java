package model.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuItemTest extends Assertions {
    @Test
    public void toStringTest (){

        //when
        String play = MenuItem.Play.toString();
        String leaderboard = MenuItem.LeaderBoard.toString();
        String exit = MenuItem.Exit.toString();

        //then
        assertEquals(play, "Play");
        assertEquals(leaderboard, "LeaderBoard");
        assertEquals(exit, "Exit");
    }

}
