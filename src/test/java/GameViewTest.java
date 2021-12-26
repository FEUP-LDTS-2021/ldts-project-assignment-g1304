import model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Game.GameView;
import view.View;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.times;

public class GameViewTest extends Assertions {

    @Test
    void testFont(){
        GameModel model = Mockito.mock(GameModel.class);

        // given
        View view = new GameView(model);
        Font font = new Font(Font.DIALOG, Font.BOLD, 100);

        // when
        view.setFont(font);

        // then
        assertEquals(view.getFont(), font);
    }


    //TODO PERGUNTAR AO STOR
    // Testar se chamamos o draw do player e isso ...
    // Mas nao conseguimos testar isso porque é final (e nao e suposto alterar)
    @Test
    void drawTest() throws IOException {
        GameModel model = Mockito.mock(GameModel.class);


    }

}
