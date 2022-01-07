package asteroids.view.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Entities.EnemyShip;

import java.io.IOException;

public class EnemyShipView extends View {
    private final EnemyShip enemyShip;

    public EnemyShipView(EnemyShip enemyShip){
        super();
        this.enemyShip = enemyShip;
    }
    @Override
    public void draw() throws IOException {
        getGraphics().setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        getGraphics().drawRectangle(new TerminalPosition((int)enemyShip.getPosition().getX(),
                        (int)enemyShip.getPosition().getY()),
                new TerminalSize((int)enemyShip.getWidth(), (int)enemyShip.getHeight()),' ');
    }
}