package asteroids.model.Entities;

import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;
import java.util.List;


public class EnemyShip extends MovingObject {

    private static final List<Integer> pointsListX = List.of(8, 11, 15, 19, 15, 4, 0, 4, 6);
    private static final List<Integer> pointsListY = List.of(0, 0, 6, 9, 12, 12, 9, 6,  1);
    private LaserBeamCreator laserBeamCreator;
    private Sizes size;
    private static final int points = 50;
    long lastTime;

    public EnemyShip(Position position, Vector2d velocity, double width, double height) {
        super(position, velocity, width, height);
        this.laserBeamCreator = null;
        this.lastTime = 0;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
        setWidth(getWidth()*size.getSize());
        setHeight(getHeight()*size.getSize());
    }

    public int getPoints() {
        return points;
    }

    public LaserBeamCreator getLaserBeamCreator() {
        return laserBeamCreator;
    }

    public void setLaserBeamCreator(LaserBeamCreator laserBeamCreator) {
        this.laserBeamCreator = laserBeamCreator;
    }

    public void shooting(long dt) {
        if (isShootingTime(dt))
            laserBeamCreator.addLaserBeam((LaserBeam) laserBeamCreator.create());
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
            polygon.addPoint((int)x+pointsListX.get(i)*size.getSize(), (int)y+pointsListY.get(i)*size.getSize());

        return polygon;
    }

}