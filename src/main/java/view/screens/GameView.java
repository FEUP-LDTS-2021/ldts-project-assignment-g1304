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

public class GameView extends ScreenView {

    private final PlayerView playerView;
    private final List<AsteroidView> asteroidsView;
    private final GameModel model;

    public GameView(GameModel model) {
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

        try {
            clear();

            playerView.draw();
            for (LaserBeam laserBeam : model.getPlayer().getLaserBeams()) {
                LaserView laserView = new LaserView(laserBeam);
                laserView.setGraphics(graphics);
                laserView.draw();
            }
            for(AsteroidView SingleAsteroid : asteroidsView)
                SingleAsteroid.draw();

            Thread.sleep(100);
            refresh();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
        playerView.setGraphics(graphics);
        for(AsteroidView SingleAsteroid : asteroidsView)
            SingleAsteroid.setGraphics(graphics);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT);
    }

}
