package asteroids.view.Game;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class View {

    protected TextGraphics graphics;


    public TextGraphics getGraphics() {
        return graphics;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw() throws IOException;

}
