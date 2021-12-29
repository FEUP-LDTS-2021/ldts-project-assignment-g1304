package model.Entities;

import model.Position;

public class LaserBeamCreator {

    private final Player player;

    public LaserBeamCreator(Player player) {

        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public LaserBeam createLaserBeam() {
        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        double laserWidth = 3;
        double laserHeight = 3;

        Position laserPos = new Position((int) (
                Math.cos(player.getAngle()) * (player.getRaio()) + x - laserWidth / 2),
                (int) (Math.sin(player.getAngle()) * (player.getRaio()) + y - laserHeight / 2));

        return new LaserBeam(laserPos, player.getAngle(), laserWidth, laserHeight);
    }
}