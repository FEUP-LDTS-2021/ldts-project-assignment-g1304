import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.Game.GameView;
import view.View;

import java.awt.*;

public class GameViewTest extends Assertions {

    @Test
    void testFont(){

        // given
        View view = new GameView();
        Font font = new Font(Font.DIALOG, Font.BOLD, 100);

        // when

        view.setFont(font);
        // then
        assertEquals(view.getFont(), font);


    }

    @Test
    void drawTest(){
        // TODO
    }

}
