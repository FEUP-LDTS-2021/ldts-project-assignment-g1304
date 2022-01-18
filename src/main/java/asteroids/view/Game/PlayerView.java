package asteroids.view.Game;

import asteroids.Color;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Entities.Player;

import java.io.IOException;


public class PlayerView extends View {

    private final Player player;

    public PlayerView(Player player){
        this.player = player;
    }

    private static TerminalPosition toTerminalPosition(Position position, double angle, double raio){
        return new TerminalPosition((int) (Math.cos(angle)*raio + position.getX()),
                (int) (Math.sin(angle)*raio + position.getY()));
    }

    @Override
    public void draw(){
        setBackgroundColor(Color.White);
        Position position = new Position(player.getPosition().getX(), player.getPosition().getY());

        double anglePontaNave = player.getAngle();
        double anglePontaEsq = anglePontaNave + Math.PI*0.8333;   // 5/6
        double anglePontaDir = anglePontaNave + Math.PI*1.1666;   // 7/6

        getGraphics().drawTriangle(toTerminalPosition(position, anglePontaNave, player.getRaio()),
                                    toTerminalPosition(position, anglePontaEsq, player.getRaio()),
                                    toTerminalPosition(position, anglePontaDir, player.getRaio()), ' ');
    }

    public Player getPlayer() {
        return player;
    }
}
