package asteroids.model.Entities;

import asteroids.model.Creator.EnemyLaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.physics.Vector2d;

public class EnemyShip extends MovingObject {
    private EnemyLaserBeamCreator laserBeamCreator;
    long lastTime;

    public EnemyShip(Position position, Vector2d velocity, double width, double height) {
        super(position, velocity, width, height);
        this.laserBeamCreator = null;
        this.lastTime = 0;
    }

    public void setLaserBeamCreator(EnemyLaserBeamCreator laserBeamCreator) {
        this.laserBeamCreator = laserBeamCreator;
    }

    public void shooting(long dt) {
        if (isShootingTime(dt))
            laserBeamCreator.addLaserBeam(laserBeamCreator.create());
    }

    public boolean isShootingTime(long dt) {
        lastTime += dt;
        if (lastTime > 1000) {
            lastTime -= 1000;
            return true;
        }
        return false;
    }

    public long getLastTime() {
        return lastTime;
    }

    @Override
    public void update(long dt){
        super.update(dt);
        shooting(dt);
    }
}