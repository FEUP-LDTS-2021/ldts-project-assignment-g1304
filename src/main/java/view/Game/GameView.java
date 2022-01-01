package view.Game;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Entities.Asteroid;
import model.Entities.EnemyShip;
import model.Entities.MovingObject;
import model.GameModel;
import model.Entities.LaserBeam;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class GameView extends View {

    private final PlayerView playerView;
    private final GameModel model;

    public GameView(GameModel model) {
        super();
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));
        playerView = new PlayerView(model.getPlayer());
    }

    @Override
    public void draw() throws IOException {
        playerView.draw();
        for (LaserBeam laserBeam : model.getLaserCreator().getLaserBeamList()) {
            LaserView laserView = new LaserView(laserBeam);
            laserView.setGraphics(graphics);
            laserView.draw();
        }

        for(MovingObject asteroid : model.getAsteroids()) {
            AsteroidView asteroidView = new AsteroidView((Asteroid) asteroid);
            asteroidView.setGraphics(graphics);
            asteroidView.draw();
        }

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


    }


    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
        playerView.setGraphics(graphics);
    }
}
