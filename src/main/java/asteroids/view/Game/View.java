package asteroids.view.Game;

import asteroids.view.Color;
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

    public void setForegroundColor(Color color){
        getGraphics().setForegroundColor(color.getColor());
    }

    public void setBackgroundColor(Color color){
        getGraphics().setBackgroundColor(color.getColor());
    }

    public void drawLine(String line, int startX, int startY);

    public void drawImage(String[] image, int imageX, int imageY);

    public void setColor(char color);

    public void setCharHeight(int charHeight);
    public void setCharWidth(int charWidth);
}
