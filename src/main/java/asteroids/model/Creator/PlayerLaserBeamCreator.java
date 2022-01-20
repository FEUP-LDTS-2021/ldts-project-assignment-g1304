package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;

import java.util.List;

public class PlayerLaserBeamCreator extends LaserBeamCreator {
    private final Player player;

    public PlayerLaserBeamCreator(Player player, List<MovingObject> entities) {
        super(entities);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public LaserBeam create() {
        Position laserPos = ajustPosition(player.getAngle(), player);
        LaserBeam laserBeam = new LaserBeam(laserPos, player.getAngle(), laserWidth, laserHeight);
        laserBeam.setPlayerBeam(true);
        return laserBeam;

    }
}
