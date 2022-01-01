package view.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import model.Entities.Asteroid;

import view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidView extends View {
    private final Asteroid asteroid;

    public AsteroidView(Asteroid asteroid){
        super();
        this.asteroid = asteroid;
    }


    @Override
    public void draw(){
        getGraphics().setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        getGraphics().drawRectangle(new TerminalPosition((int)asteroid.getPosition().getX(),
                        (int)asteroid.getPosition().getY()),
                new TerminalSize((int)asteroid.getWidth(), (int)asteroid.getHeight()),' ');
    }
}
