package view.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import model.Entities.LaserBeam;
import view.View;

import java.io.IOException;

public class LaserView extends View {

    private final LaserBeam laserBeam;

    public LaserView(LaserBeam laserBeam) {
        super();
        this.laserBeam = laserBeam;
    }

    @Override
    public void draw(){
        getGraphics().setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        TerminalPosition terminalPosition = new TerminalPosition((int)(
                laserBeam.getPosition().getX()), (int)(laserBeam.getPosition().getY()));
                TerminalSize size = new TerminalSize((int)laserBeam.getWidth(), (int)laserBeam.getHeight());
                getGraphics().fillRectangle(terminalPosition, size, ' ');
    }
}