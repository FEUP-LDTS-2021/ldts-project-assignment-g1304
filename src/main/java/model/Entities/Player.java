package model;

import model.physics.Vector2d;

import java.util.Random;


public class Player extends MovingObject{
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

    private double angle;
    public static final double raio = 10;
    public static final double acelaration = 200.0;
    public static final double MAX_VELOCITY = 80.0;
    public static final double angularVelocity = Math.PI*1.5;
    private Rotation rotation;
    private boolean acelerate;

    public Player(Position position){
        super(position, new Vector2d(0,0));
        this.angle = 0;
        this.acelerate = false;
        this.rotation = Rotation.None;
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

    public void update(long dt){
        angle += rotation.getValue()*angularVelocity*dt/1000;
        rotation = Rotation.None;

        if(acelerate) {
            getVelocity().addX(acelaration * dt / 1000 * Math.cos(angle));
            getVelocity().addY(acelaration * dt / 1000 * Math.sin(angle));
            if(getVelocity().module() > MAX_VELOCITY)
                getVelocity().resize(MAX_VELOCITY);
        }else {
            getVelocity().resize(getVelocity().module()*0.90);
        }

        acelerate=false;
        super.update(dt);       // andar para a frente
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

}
