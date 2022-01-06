package asteroids.view;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.io.IOException;

public abstract class View {

    protected TextGraphics graphics;
    protected Font font = null;


    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw() throws IOException;
    public Font getFont(){
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
