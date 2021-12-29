package model.Entities;
import model.Position;
import model.physics.Vector2d;


public class Asteroid extends MovingObject{
    private Integer size;

    public Asteroid(Position position, Vector2d velocity,Integer size){
        super(position,velocity);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public void update(long ms){
        super.update(ms);
    }
}
