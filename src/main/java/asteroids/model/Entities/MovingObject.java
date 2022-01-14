package asteroids.model.Entities;

import asteroids.model.Collider.CollidableObject;
import asteroids.Constants;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.geom.Rectangle2D;

public abstract class MovingObject implements CollidableObject {

    private Vector2d velocity;
    private Position position;
    private double width;
    private double height;
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void dies() {
        this.alive = false;
    }

    public MovingObject(Position position, Vector2d velocity, double width, double height){
        this.velocity = velocity;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    void goFoward(long dt){
        getPosition().setX(getPosition().getX() + velocity.getX()*dt/1000);
        getPosition().setY(getPosition().getY() + velocity.getY()*dt/1000);
    }

    public void update(long dt){
        goFoward(dt);
        fixPassScreenBorder();
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public final void fixPassScreenBorder(){
        if(getPosition().getX() > Constants.WIDTH)
            getPosition().setX(-getWidth());
        else if(getPosition().getX() < -getWidth())
            getPosition().setX(Constants.WIDTH);

        if(getPosition().getY() > Constants.HEIGHT)
            getPosition().setY(-getHeight());
        else if(getPosition().getY() < -getHeight())
            getPosition().setY(Constants.HEIGHT);
    }

    public Rectangle2D.Double getCollider() {
        return new Rectangle2D.Double(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}
