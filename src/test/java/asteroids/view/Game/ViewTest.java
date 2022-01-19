package asteroids.view.Game;

import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ViewTest extends Assertions {
    View view;
    TextGraphics textGraphics;
    @BeforeEach
    void init(){
        view = Mockito.mock(View.class, Mockito.CALLS_REAL_METHODS);
        textGraphics = Mockito.mock(TextGraphics.class);
        view.setGraphics(textGraphics);
        view.setCharWidth(2);
        view.setCharHeight(3);
    }

    @Test
    void setForegroundColor(){

        // when
        view.setForegroundColor(Color.Red);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(Color.Red.getColor());
    }

    @Test
    void setBackgroundColor(){

        // when
        view.setBackgroundColor(Color.White);

        // then
        Mockito.verify(textGraphics, Mockito.times(1)).setBackgroundColor(Color.White.getColor());
    }

    @Test
    void drawImage(){
        // given
        int imageX = 10;
        int imageY = 15;
        String [] image = new String[]{"abcd","efg"};

        Mockito.doNothing().when(view).drawLine(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        // when
        view.drawImage(image, imageX, imageY);

        // then
        int y = 0;
        for (String line : image){
            Mockito.verify(view).drawLine(line, imageX, imageY+y);
            y+=3;
        }
    }

    @Test
    public void drawLine(){
        // given
        String line = " BbcCpP ";
        int startX = 10;
        int startY = 20;
        Mockito.doNothing().when(view).setColor(Mockito.anyChar());
        //when
        view.drawLine(line, startX, startY);

        // then
        int x = 0;
        for (char c : line.toCharArray()){
            if (c!=' '){
                Mockito.verify(view, Mockito.times(1)).setColor(c);
                Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                        new TerminalPosition(startX+x, startY),
                        new TerminalSize(2, 3), ' ');
            }else{
                Mockito.verify(textGraphics, Mockito.never()).fillRectangle(
                        new TerminalPosition(startX+x, startY),
                        new TerminalSize(2, 3), ' ');
            }
            x+=2;
        }
    }

    @Test
    public void setColorNULL(){

        // when
        view.setColor(' ');

        // then
        Mockito.verify(textGraphics, Mockito.never()).setBackgroundColor(Mockito.any());
    }

    @Test
    public void setColor(){

        for(Color color : Color.values()) {
            // when
            view.setColor(color.getChar());

            // then
            Mockito.verify(view, Mockito.times(1)).setBackgroundColor(color);
        }
        }
}
