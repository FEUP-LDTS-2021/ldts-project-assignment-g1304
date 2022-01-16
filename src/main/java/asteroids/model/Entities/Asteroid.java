package asteroids.model.Entities;
import asteroids.model.Position;
import asteroids.model.Vector2d;


public class Asteroid extends MovingObject{

    private AsteroidSizes size;

    public Asteroid(Position position, Vector2d velocity, AsteroidSizes size){
        super(position,velocity, size.getSize(), size.getSize());
        this.size = size;
    }

    public int getPoints() {
        if (size == AsteroidSizes.LARGE) {
            return 20;
        }
        else if (size == AsteroidSizes.MEDIUM) {
            return 50;
        }
        else return 100;
    }

    public void decreaseSize() {
        if (size == AsteroidSizes.LARGE) {
            setSize(AsteroidSizes.MEDIUM);
        }
        else if (size == AsteroidSizes.MEDIUM) {
            setSize(AsteroidSizes.SMALL);
        }
    }

    public AsteroidSizes getSize() {
        return size;
    }

    public void setSize(AsteroidSizes size) {
        this.size = size;
        setHeight(size.size);
        setWidth(size.size);
    }

    @Override
    public void dies() {
        if (getSize() == AsteroidSizes.SMALL)
            super.dies();
    }
}