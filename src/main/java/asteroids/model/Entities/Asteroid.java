package asteroids.model.Entities;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;

public class Asteroid extends MovingObject{

    private static final int points = 20;

    public Asteroid(Position position, Vector2d velocity, Integer size){
        super(position,velocity, size, size);
    }

    public int getPoints() {
        return points;
    }

    @Override
    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        double x = getPosition().getX();
        double y = getPosition().getY();

        polygon.addPoint((int)x, (int)y);
        polygon.addPoint((int)(x +getWidth()), (int)y);
        polygon.addPoint((int)(x +getWidth()), (int)(y +getHeight()));
        polygon.addPoint((int)x, (int)(y +getHeight()));

        return polygon;
    }
}