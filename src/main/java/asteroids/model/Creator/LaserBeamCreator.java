package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;

import java.util.List;


public class LaserBeamCreator extends Creator {

    private final Player player;
    private final List <MovingObject> entities;

    public LaserBeamCreator(Player player, List<MovingObject> entities) {
        this.player = player;
        this.entities = entities;
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    public LaserBeam create() {

        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        double laserWidth = 3;
        double laserHeight = 3;

        Position laserPos = new Position((int) (
                Math.cos(player.getAngle()) * (player.getRaio()+5) + x - laserWidth / 2),
                (int) (Math.sin(player.getAngle()) * (player.getRaio()+5) + y - laserHeight / 2));

        return new LaserBeam(laserPos, player.getAngle(), laserWidth, laserHeight);

    }

    public void addLaserBeam(LaserBeam laserBeam) {
        entities.add(laserBeam);
    }
}