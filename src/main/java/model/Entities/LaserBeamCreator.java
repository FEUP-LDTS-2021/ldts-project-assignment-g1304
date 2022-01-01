package model.Entities;

import model.Position;

import java.util.ArrayList;
import java.util.List;

public class LaserBeamCreator {

    private final Player player;
    private final List<LaserBeam> laserBeamList;

    public LaserBeamCreator(Player player) {

        this.player = player;
        laserBeamList = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public List<LaserBeam> getLaserBeamList() {
        return laserBeamList;
    }

    public LaserBeam createLaserBeam() {
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
        laserBeamList.add(laserBeam);
    }
}