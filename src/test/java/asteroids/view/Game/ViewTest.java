package asteroids.view.Game;

import asteroids.Color;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ViewTest extends Assertions {
    @Test
    void setForegroundColor(){
        View view = Mockito.mock(View.class, Mockito.CALLS_REAL_METHODS);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        view.setGraphics(textGraphics);

        // when
        view.setForegroundColor(Color.Red);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(Color.Red.getColor());
    }

    @Test
    void setBackgroundColor(){
        View view = Mockito.mock(View.class, Mockito.CALLS_REAL_METHODS);
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        view.setGraphics(textGraphics);

        // when
        view.setBackgroundColor(Color.White);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setBackgroundColor(Color.White.getColor());
    }
}
