package view.screens;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Constraints;
import model.Entities.Asteroid;
import model.Entities.MovingObject;
import model.GameModel;
import model.Entities.LaserBeam;
import view.Game.AsteroidView;
import view.Game.LaserView;
import view.Game.PlayerView;
import view.ScreenView;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class GameScreen extends ScreenView {

    private final PlayerView playerView;
    private final List<AsteroidView> asteroidsView;
    private final GameModel model;

    public GameScreen(GameModel model) {
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));

        playerView = new PlayerView(model.getPlayer());
        List<MovingObject> asteroids = model.getAsteroids();
        asteroidsView = new ArrayList<>();
        for(MovingObject asteroid : asteroids){
            asteroidsView.add(new AsteroidView((Asteroid) asteroid));
        }
    }

    @Override
    public void draw() throws IOException {
        clear();

        getPlayerView().draw();
        for (LaserView laserView : getLaserViews())
           laserView.draw();

        for(AsteroidView SingleAsteroid : getAsteroidsView())
            SingleAsteroid.draw();

        refresh();
    }


    public GameModel getModel() {
        return model;
    }

    public List<AsteroidView> getAsteroidsView() {
        return asteroidsView;
    }

    public List<LaserView> getLaserViews() {
        List<LaserView> laserBeams = new ArrayList<>();
        for (LaserBeam laserBeam : getModel().getPlayer().getLaserBeams()) {
            LaserView laserView = new LaserView(laserBeam);
            laserView.setGraphics(getGraphics());
            laserBeams.add(laserView);
        }
        return laserBeams;
    }

    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
        getPlayerView().setGraphics(graphics);
        for(AsteroidView asteroidView : getAsteroidsView())
            asteroidView.setGraphics(graphics);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT);
    }

}
