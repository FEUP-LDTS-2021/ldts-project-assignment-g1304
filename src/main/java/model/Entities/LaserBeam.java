package model.Entities;

import model.Position;
import model.physics.Vector2d;

public class LaserBeam extends MovingObject {
    private double angle;
    public final static double VELOCITY = 200.0;
    private boolean control = false;

    public LaserBeam(Position position, double angle, double width, double height) {
        super(position, new Vector2d(VELOCITY*Math.cos(angle), VELOCITY*Math.sin(angle)), width, height);
        this.angle = angle;
    }

    public double getAngle() {return angle;}

    public void setAngle(double angle) {this.angle = angle;}

    @Override
    public void update(long dt) {
        if (control) {
            super.update(dt);
        }
        control = true;
    }
}