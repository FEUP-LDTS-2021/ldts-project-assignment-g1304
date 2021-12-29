package view.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import model.Entities.LaserBeam;
import view.View;

import java.io.IOException;

public class LaserView extends View {

    private LaserBeam laserBeam;

    public LaserView(LaserBeam laserBeam) {
        super();
        this.laserBeam = laserBeam;
    }

    @Override
    public void draw() throws IOException {
        getGraphics().setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        TerminalPosition terminalPosition = new TerminalPosition((int)(laserBeam.getPosition().getX()-1.5), (int)(laserBeam.getPosition().getY()-1.5));
        TerminalSize size = new TerminalSize(3, 3);
        getGraphics().fillRectangle(terminalPosition, size, ' ');
    }
}