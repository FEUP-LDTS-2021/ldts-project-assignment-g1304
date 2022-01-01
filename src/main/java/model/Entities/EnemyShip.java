package model.Entities;

import model.Position;
import model.physics.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class EnemyShip extends MovingObject {
    private final List<LaserBeam> laserBeams;
    long lastTime;
    private final Player player;

    public EnemyShip(Player player, Position position, Vector2d velocity, double width, double height) {
        super(position, velocity, width, height);
        this.laserBeams = new ArrayList<>();
        this.lastTime = 0;
        this.player = player;
    }

    public void shooting(long dt) {

        if (isShootingTime(dt)) {
            EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(player, this);
            laserBeams.add((LaserBeam) enemyLaserBeamCreator.create());
        }
    }

    public boolean isShootingTime(long dt) {
        lastTime += dt;
        if (lastTime > 1000) {
            lastTime -= 1000;
            return true;
        }
        return false;
    }

    public List<LaserBeam> getLaserBeams() {
        return laserBeams;
    }

    public Player getPlayer() {
        return player;
    }

    public long getLastTime() {
        return lastTime;
    }

    @Override
    public void update(long dt){
        shooting(dt);
        super.update(dt);
    }
}