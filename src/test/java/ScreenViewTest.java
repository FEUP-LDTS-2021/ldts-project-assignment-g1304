import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import view.Game.GameView;
import view.ScreenView;
import view.View;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ScreenViewTest extends Assertions{

    ScreenView screenView ;
    GameView gameViewMock;
    @BeforeEach
    void prepareScreen(){
        gameViewMock = Mockito.mock(GameView.class);

        Mockito.when(gameViewMock.getFont()).thenReturn(new Font(Font.MONOSPACED,Font.PLAIN, 4));
        screenView = new ScreenView(gameViewMock);
    }

    @Test
    void TestInitScreen() throws IOException {

        //when
        screenView.initScreen();

        // then
        assertEquals(screenView.getFont(), gameViewMock.getFont());
        assertNotNull(screenView.getGraphics());

        // final
        screenView.close();

    }

    @Test
    void TestDrawScreen() throws IOException {

        //when
        screenView.initScreen();
        screenView.draw();

        // then
        verify(gameViewMock, times(1)).draw();

        // final
        screenView.close();
    }

    @Test
    void testSetView(){

        // given
        View view = gameViewMock = Mockito.mock(GameView.class);

        // when
        screenView.setView(view);

        // then
        assertEquals(screenView.getView(), view);

    }

}
