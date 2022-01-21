package asteroids.model.Entities;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import java.awt.*;
import java.util.List;

public class Asteroid extends MovingObject{

    private static final int WIDTH = 17;
    private static final int HEIGHT = 15;

    private Sizes size;
    private List<Integer> pointsListX = List.of(5, 2, 2, 1, 1, 0, 0, 2, 3, 6, 13, 16, 17, 17, 16, 16, 15, 14, 12);
    private List<Integer> pointsListY = List.of(0, 3, 4, 5, 6, 7, 11, 12, 13, 14, 14, 12, 9, 8, 7, 4, 2, 1, 0);

    public Asteroid(Position position, Vector2d velocity, Sizes size){
        super(position,velocity, size.getSize()*HEIGHT, size.getSize()*WIDTH);
        this.size = size;
    }

    public int getPoints() {
        if (size == Sizes.LARGE) {
            return 20;
        }
        else if (size == Sizes.MEDIUM) {
            return 50;
        }
        else return 100;
    }

    public void decreaseSize() {
        if (size == Sizes.LARGE) {
            setSize(Sizes.MEDIUM);
        }
        else if (size == Sizes.MEDIUM) {
            setSize(Sizes.SMALL);
        }
    }

    public Sizes getAsteroidSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
        setHeight(size.getSize()*HEIGHT);
        setWidth(size.getSize()*WIDTH);
    }

    @Override
    public void dies() {
        if (getAsteroidSize() == Sizes.SMALL)
            super.dies();
    }

    @Override
    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        double x = getPosition().getX();
        double y = getPosition().getY();

        for (int i = 0; i < pointsListX.size(); i++) {
            polygon.addPoint((int)x+pointsListX.get(i)*getAsteroidSize().getSize(),
                    (int)y+pointsListY.get(i)*getAsteroidSize().getSize());
        }

        return polygon;
    }
}