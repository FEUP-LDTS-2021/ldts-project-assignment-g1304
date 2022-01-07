package asteroids.view.screens;

import asteroids.view.Game.AsteroidView;
import asteroids.view.Game.EnemyShipView;
import asteroids.view.Game.LaserView;
import asteroids.view.Game.PlayerView;
import asteroids.model.Constraints;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import asteroids.model.Entities.Asteroid;
import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.MovingObject;
import asteroids.model.GameModel;
import asteroids.model.Entities.LaserBeam;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class GameScreen extends ScreenView {

    private final PlayerView playerView;
    private final GameModel model;

    public GameScreen(GameModel model) {
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));

        playerView = new PlayerView(model.getPlayer());
    }

    @Override
    public void draw() throws IOException {
        clear();

        getPlayerView().draw();

        for (LaserView laserView : getLaserViews())
            laserView.draw();

        for(AsteroidView asteroidView :getAsteroidViews())
            asteroidView.draw();

        for(EnemyShip enemyShip : model.getEnemyShipSpawner().getEnemyShips()) {
            EnemyShipView enemyShipView = new EnemyShipView(enemyShip);
            enemyShipView.setGraphics(graphics);
            enemyShipView.draw();
            for(LaserBeam laserBeam : enemyShip.getLaserBeams()){
                LaserView laserView = new LaserView(laserBeam);
                laserView.setGraphics(graphics);
                laserView.draw();
            }
        }

        refresh();
    }

    public List<AsteroidView> getAsteroidViews(){
        List<AsteroidView> asteroidViews = new ArrayList<>();
        for(MovingObject asteroid : model.getAsteroids()) {
            AsteroidView asteroidView = new AsteroidView((Asteroid) asteroid);
            asteroidView.setGraphics(getGraphics());
            asteroidViews.add(asteroidView);
        }

        return asteroidViews;
    }

    public List<LaserView> getLaserViews(){
        List<LaserView> laserViews = new ArrayList<>();

        for (LaserBeam laserBeam : model.getLaserCreator().getLaserBeamList()) {
            LaserView laserView = new LaserView(laserBeam);
            laserView.setGraphics(graphics);
            laserViews.add(laserView);
        }

        return laserViews;
    }


    public GameModel getModel() {
        return model;
    }

    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
        getPlayerView().setGraphics(graphics);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT);
    }

}
