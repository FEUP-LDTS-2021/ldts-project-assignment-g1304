package asteroids.view.Game;

import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import asteroids.model.Entities.Asteroid;

public class AsteroidView extends View {
    private final Asteroid asteroid;

    public AsteroidView(Asteroid asteroid){
        super(0,0);
        this.asteroid = asteroid;
    }


    @Override
    public void draw(){
        setBackgroundColor(Color.White);
        getGraphics().drawRectangle(new TerminalPosition((int)asteroid.getPosition().getX(),
                        (int)asteroid.getPosition().getY()),
                new TerminalSize((int)asteroid.getWidth(), (int)asteroid.getHeight()),' ');
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }
}

