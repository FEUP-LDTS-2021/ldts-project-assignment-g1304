package asteroids.view.Game;

import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class View {
    protected int charWidth;
    protected int charHeight;

    protected TextGraphics graphics;
    public View(int charWidth ,int charHeight){
        this.charWidth = charWidth;
        this.charHeight = charHeight;
    }

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

    public void drawLine(String line, int startX, int startY){
        int x = 0;
        for (char c : line.toCharArray()){
            if (c!=' '){
                setColor(c);
                graphics.fillRectangle(new TerminalPosition(startX+x, startY),
                        new TerminalSize(charWidth, charHeight), ' ');
            }
            x+=charWidth;
        }
    }

    public void drawImage(String[] image, int imageX, int imageY) {
        int y = imageY;
        for (String line : image){
            drawLine(line, imageX, y);
            y+=charHeight;
        }
    }

    public void setColor(char color){
        Color c = Color.getColor(color);
        if (c!=null)
            setBackgroundColor(c);
    }

    public void setCharHeight(int charHeight) {
        this.charHeight = charHeight;
    }

    public void setCharWidth(int charWidth) {
        this.charWidth = charWidth;
    }
}
