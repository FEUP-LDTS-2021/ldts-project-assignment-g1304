package model.Entities;

import model.Position;
import model.physics.Vector2d;

public abstract class MovingObject {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private Vector2d velocity;
    private Position position;
    private double width;
    private double height;


    public MovingObject(Position position, Vector2d velocity, double width, double height){
        this.velocity = velocity;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void goFoward(long dt){
        position.setX(position.getX() + velocity.getX()*dt/1000);
        position.setY(position.getY() + velocity.getY()*dt/1000);
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

    public abstract void fixPassScreenBorder();
}
