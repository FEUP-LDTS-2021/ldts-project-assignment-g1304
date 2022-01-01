package model.Entities;

import model.Position;

import java.util.ArrayList;
import java.util.List;

public class LaserBeamCreator {

    private final Player player;
    private final List<LaserBeam> laserBeamList;

    public LaserBeamCreator (Player player) {

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
        Position laserPos = new Position((int) (Math.cos(player.getAngle())* (player.getRaio()+5) + x),
                (int) (Math.sin(player.getAngle())* (player.getRaio()+5) + y));
        return new LaserBeam(laserPos, player.getAngle());
    }

    public void addLaserBeam(LaserBeam laserBeam) {
        laserBeamList.add(laserBeam);
    }
}