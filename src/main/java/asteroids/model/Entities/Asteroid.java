package asteroids.model.Entities;
import asteroids.model.Position;
import asteroids.model.physics.Vector2d;


public class Asteroid extends MovingObject{

    public Asteroid(Position position, Vector2d velocity, Integer size){
        super(position,velocity, size, size);
    }
}
