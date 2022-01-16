package asteroids.model.Entities;
import asteroids.model.Position;
import asteroids.model.Vector2d;


public class Asteroid extends MovingObject{

    private static final int points = 20;

    private AsteroidSizes size;

    public Asteroid(Position position, Vector2d velocity, AsteroidSizes size){
        super(position,velocity, size.getSize(), size.getSize());
        this.size = size;
    }

    public int getPoints() {
        return points;
    }

    public void decreaseSize();

    public AsteroidSizes getSize();

    public void setSize(AsteroidSizes size);

    @Override
    public void dies();
}