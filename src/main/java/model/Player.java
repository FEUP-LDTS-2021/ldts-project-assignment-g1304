package model;

import model.physics.Vector2d;


public class Player {
    private enum Rotation{
        Left(-1),
        Right(1),
        None(0);

        private final int value;

        Rotation(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Position position;
    private double angle;
    public static final double raio = 10;
    public static final double acelaration = 200.0;
    public static final double MAX_VELOCITY = 80.0;
    public static final double angularVelocity = Math.PI*1.5;
    private Vector2d velocity;
    private Rotation rotation;
    private boolean acelerate;

    public Player(Position position){
        this.position = position;
        this.angle = 0;
        this.velocity=new Vector2d(0,0);
        this.acelerate = false;
        this.rotation = Rotation.None;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public double getRaio() {
        return raio;
    }

    private void goFoward(long dt){
        position.setX(position.getX() + velocity.getX()*dt/1000);
        position.setY(position.getY() + velocity.getY()*dt/1000);
    }

    public void update(long dt){
        angle += rotation.getValue()*angularVelocity*dt/1000;
        rotation = Rotation.None;

        if(acelerate) {
            velocity.addX(acelaration * dt / 1000 * Math.cos(angle));
            velocity.addY(acelaration * dt / 1000 * Math.sin(angle));
            if(velocity.module() > MAX_VELOCITY)
                velocity.resize(MAX_VELOCITY);
        }else {
            velocity.resize(velocity.module()*0.90);
        }

        goFoward(dt);
        acelerate=false;
    }

    public void rotateLeft(){
        rotation = Rotation.Left;
    }

    public void rotateRight(){
        rotation = Rotation.Right;
    }

    public boolean isAcelerate() {
        return acelerate;
    }

    public void acelerate() {
        this.acelerate=true;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

}
