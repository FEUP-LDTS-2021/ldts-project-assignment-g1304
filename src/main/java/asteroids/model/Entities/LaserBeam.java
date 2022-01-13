package asteroids.model.Entities;

import asteroids.model.Position;
import asteroids.model.Vector2d;

public class LaserBeam extends MovingObject {
    private double angle;
    private long timePassed;
    public final static double VELOCITY = 300.0;
    private boolean control = false;

    public LaserBeam(Position position, double angle, double width, double height) {
        super(position, new Vector2d(VELOCITY*Math.cos(angle), VELOCITY*Math.sin(angle)), width, height);
        this.angle = angle;
        this.timePassed = 0;
    }

    public double getAngle() {return angle;}

    public boolean isKillTime(long dt){
        timePassed += dt;
        if(timePassed >= 1200){
            timePassed = 0;
            return true;
        }
        return false;
    }

    @Override
    public void update(long dt) {
        if (isKillTime(dt))
            dies();
        if (control)
            super.update(dt);
        control = true;
    }
}