package model.Entities;

import model.Position;
import model.physics.Vector2d;

public abstract class MovingObject {

    private Vector2d velocity;
    private Position position;

    public MovingObject(Position position, Vector2d velocity){
        this.velocity = velocity;
        this.position = position;
    }

    public void goFoward(long dt){
        position.setX(position.getX() + velocity.getX()*dt/1000);
        position.setY(position.getY() + velocity.getY()*dt/1000);
    }

    public void update(long dt){
        goFoward(dt);
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
