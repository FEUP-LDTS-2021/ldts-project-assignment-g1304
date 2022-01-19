package asteroids.view.Game;

import asteroids.view.Color;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import asteroids.model.Entities.Player;


public class PlayerView extends View {

    private final Player player;

    public PlayerView(Player player){
        super(0,0);
        this.player = player;
    }

    private static TerminalPosition toTerminalPosition(Position position, double angle, double raio){
        return new TerminalPosition((int) (position.getX() + (Math.cos(angle)+1)*raio),
                (int) ((Math.sin(angle)+1)*raio + position.getY()));
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
