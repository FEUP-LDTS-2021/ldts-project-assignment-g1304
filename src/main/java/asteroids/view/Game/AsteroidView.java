package asteroids.view.Game;

import asteroids.Color;
import asteroids.Constants;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Entities.Asteroid;

public class AsteroidView extends View {
    private final Asteroid asteroid;

    public AsteroidView(Asteroid asteroid){
        this.asteroid = asteroid;
    }


    @Override
    public void draw(){
        getGraphics().setBackgroundColor(Color.White.getColor());
        getGraphics().drawRectangle(new TerminalPosition((int)asteroid.getPosition().getX(),
                        (int)asteroid.getPosition().getY()),
                new TerminalSize((int)asteroid.getWidth(), (int)asteroid.getHeight()),' ');
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }
}

