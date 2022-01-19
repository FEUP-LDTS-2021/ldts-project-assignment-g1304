package asteroids.model.Entities;

import asteroids.model.Creator.EnemyLaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;
import java.util.List;


public class EnemyShip extends MovingObject {

    private static final List<Integer> pointsListX = List.of(16, 22, 30, 38, 30, 8, 0, 8, 12);
    private static final List<Integer> pointsListY = List.of(0, 0, 12, 18, 24, 24, 18, 12,  2);
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

    public EnemyLaserBeamCreator getLaserBeamCreator() {
        return laserBeamCreator;
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
        if (lastTime >= 1001) {
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

        for(int i = 0; i < pointsListX.size(); i++)
            polygon.addPoint((int)x+pointsListX.get(i), (int)y+pointsListY.get(i));

        return polygon;
    }

}