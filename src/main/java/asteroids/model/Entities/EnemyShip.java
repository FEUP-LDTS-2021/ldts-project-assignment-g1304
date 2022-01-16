package asteroids.model.Entities;

import asteroids.model.Creator.EnemyLaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;

public class EnemyShip extends MovingObject {
    private EnemyLaserBeamCreator laserBeamCreator;
    private static final int points = 50;

    long lastTime;

    public EnemyShip(Position position, Vector2d velocity, double width, double height) {
        super(position, velocity, width, height);
        this.laserBeamCreator = null;
        this.lastTime = 0;
    }

    public int getPoints() {
        return points;
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

    @Override
    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        double x = getPosition().getX();
        double y = getPosition().getY();

        polygon.addPoint((int)x, (int)y);
        polygon.addPoint((int)x, (int)(y +getHeight()));
        polygon.addPoint((int)(x +getWidth()), (int)(y +getHeight()));
        polygon.addPoint((int)(x +getWidth()), (int)y);

        return polygon;
    }

}