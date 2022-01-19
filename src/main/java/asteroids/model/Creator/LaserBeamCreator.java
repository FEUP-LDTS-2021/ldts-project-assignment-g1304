package asteroids.model.Creator;

import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;

import java.util.List;

public abstract class LaserBeamCreator extends Creator {

    public List<MovingObject> getEntities() ;

    public void addLaserBeam(MovingObject object);

    public Position ajustPosition(double angle, MovingObject shooter);


}