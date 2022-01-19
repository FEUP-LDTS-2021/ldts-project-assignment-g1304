package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.util.List;

import static java.lang.Math.signum;

public class TargetLaserBeamCreator extends LaserBeamCreator{
    private final MovingObject target;
    private final MovingObject shooter;

    public TargetLaserBeamCreator(MovingObject target, MovingObject shooter, List<MovingObject> entities){
        super(entities);
        this.target = target;
        this.shooter = shooter;
    }

    public MovingObject getShooter() {
        return shooter;
    }

    public MovingObject getTarget() {
        return target;
    }

    @Override
    public LaserBeam create() {
        Vector2d velocity = new Vector2d(target.getPosition().getX() - shooter.getPosition().getX(),
                target.getPosition().getY() - shooter.getPosition().getY());
        double angle = Math.acos(velocity.dotProduct(new Vector2d(1,0))/velocity.module());
        if(signum(velocity.getY()) == -1)
            angle = Math.PI*2 - angle;

        Position laserPosition = ajustPosition(angle, shooter);
        return new LaserBeam(laserPosition, angle,laserWidth,laserHeight);
    }
}
