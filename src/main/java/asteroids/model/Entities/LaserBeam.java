package asteroids.model.Entities;

import asteroids.model.Position;
import asteroids.model.Vector2d;

public class LaserBeam extends MovingObject {
    private final double angle;
    public final static double VELOCITY = 300.0;
    private boolean control = false;

    public LaserBeam(Position position, double angle, double width, double height) {
        super(position, new Vector2d(VELOCITY*Math.cos(angle), VELOCITY*Math.sin(angle)), width, height);
        this.angle = angle;
    }

    public double getAngle() {return angle;}

    public void setPlayerBeam(boolean playerBeam) {
    }

    public boolean isPlayerBeam() {
    }

    @Override
    public void update(long dt) {
        if (control)
            super.update(dt);
        control = true;
    }
}