package asteroids.view.screens;

import asteroids.model.Entities.*;
import asteroids.view.Game.AsteroidView;
import asteroids.view.Game.EnemyShipView;
import asteroids.view.Game.LaserView;
import asteroids.view.Game.PlayerView;
import asteroids.view.ScreenView;
import asteroids.model.Constraints;
import asteroids.view.View;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import asteroids.model.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class GameScreen extends ScreenView {

    private final GameModel model;

    public GameScreen(GameModel model) {
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));
    }

    @Override
    public void draw() throws IOException {
        clear();

        for(MovingObject movingObject : model.getEntities()){
            if(movingObject instanceof Player){
                PlayerView playerView = new PlayerView((Player) movingObject);
                playerView.setGraphics(getGraphics());
                playerView.draw();
            }
            else if(movingObject instanceof  Asteroid) {
                AsteroidView asteroidView = new AsteroidView((Asteroid) movingObject);
                asteroidView.setGraphics(getGraphics());
                asteroidView.draw();
            }
            else if (movingObject instanceof LaserBeam) {
                LaserView laserView = new LaserView((LaserBeam) movingObject);
                laserView.setGraphics(getGraphics());
                laserView.draw();
            }
            else if (movingObject instanceof EnemyShip) {
                EnemyShipView enemyShipView = new EnemyShipView((EnemyShip) movingObject);
                enemyShipView.setGraphics(getGraphics());
                enemyShipView.draw();
            }
        }

        refresh();
    }

    public GameModel getModel() {
        return model;
    }

    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
    }

    public TerminalSize getSize(){
        return new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT);
    }

}
