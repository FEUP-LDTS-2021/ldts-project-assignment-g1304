package model.Entities;

import model.Position;

public class LaserBeamCreator {

    private final Player player;

    public LaserBeamCreator (Player player) {

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public LaserBeam createLaserBeam() {
        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        Position laserPos = new Position((int) (Math.cos(player.getAngle())* (player.getRaio()) + x),
                (int) (Math.sin(player.getAngle())* (player.getRaio()) + y));
        return new LaserBeam(laserPos, player.getAngle());
    }
}