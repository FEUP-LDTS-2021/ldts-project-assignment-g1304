package view.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import model.Player;
import model.Position;
import view.View;

import java.io.IOException;


public class PlayerView extends View {

    private final Player player;

    public PlayerView(Player player){
        super();
        this.player = player;
    }

    private static TerminalPosition toTerminalPosition(Position position, double angle, double raio){
        return new TerminalPosition((int) (Math.cos(angle)*raio + position.getX()),
                (int) (Math.sin(angle)*raio + position.getY()));
    }

    @Override
    public void draw() throws IOException {
        getGraphics().setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Position position = new Position(player.getPosition().getX(), player.getPosition().getY());

        double anglePontaNave = player.getAngle();
        double anglePontaEsq = anglePontaNave + Math.PI*0.8333;   // 5/6
        double anglePontaDir = anglePontaNave + Math.PI*1.16666;   // 7/6

        getGraphics().drawTriangle(toTerminalPosition(position, anglePontaNave, player.getRaio()),
                                    toTerminalPosition(position, anglePontaEsq, player.getRaio()),
                                    toTerminalPosition(position, anglePontaDir, player.getRaio()), ' ');
    }


}
