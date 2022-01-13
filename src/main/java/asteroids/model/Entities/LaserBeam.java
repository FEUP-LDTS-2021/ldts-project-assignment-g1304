package asteroids.model.Entities;

import asteroids.model.Position;
import asteroids.model.Vector2d;

public class LaserBeam extends MovingObject {

    public final static double VELOCITY = 300.0;
    private long timePassed;
    private final double angle;
    private boolean firstRun = true;
    private boolean playerBeam = false;

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

    public void setPlayerBeam(boolean playerBeam) {
        this.playerBeam = playerBeam;
    }

    public boolean isPlayerBeam() {
        return playerBeam;
    }

    @Override
    public void update(long dt) {
        if (isKillTime(dt))
            dies();

        if (!firstRun)
            super.update(dt);
        firstRun = false;
    }
}