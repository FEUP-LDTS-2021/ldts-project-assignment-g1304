package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;

import java.util.List;

public class DumbLaserBeamCreator extends LaserBeamCreator{

    private final MovingObject shooter;
    private static final List<Double> directions = List.of(Math.PI/2.0, -Math.PI/2.0, 0.0,
                        Math.PI, Math.PI/4.0, -Math.PI/4.0, Math.PI*3.0/4.0, -Math.PI*3.0/4.0);

    private int directionId;

    public DumbLaserBeamCreator(MovingObject shooter, List<MovingObject> entities){
        super(entities);
        this.shooter = shooter;
        this.directionId = 0;
    }

    public MovingObject getShooter() {
        return shooter;
    }

    @Override
    public LaserBeam create() {
        double angle = directions.get(directionId);
        Position laserPosition = ajustPosition(angle, shooter);
        LaserBeam laserBeam = new LaserBeam(laserPosition, angle, 3, 3);

        directionId = ++directionId % directions.size();
        return laserBeam;
    }
}
