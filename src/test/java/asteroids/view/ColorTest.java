package asteroids.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    void getColor() {

        for (Color color : Color.values()) {
            // when
            Color result = Color.getColor(color.getChar());

            // then
            Assertions.assertEquals(color, result);
        }

    }

    @Test
    void getColorNull(){
        // when
        Color result = Color.getColor('!');

        // then
        Assertions.assertNull(result);
    }
}
