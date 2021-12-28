package model;

import com.googlecode.lanterna.input.KeyStroke;
import model.input.InputObserver;
import model.physics.Vector2d;

public class Player implements InputObserver {
    private Position position;
    private double angle;
    private final double raio;
    public static final double acelaration = 200.0;
    private static final double MAX_VELOCITY = 80.0;
    public static final double angularVelocity = Math.PI*1.5;
    private Vector2d velocity;
    private int rotation = 0;
    private boolean acelerate = false;

    public Player(Position position){
        this.position = position;
        this.angle = 0;
        this.raio = 10;
        this.velocity=new Vector2d(0,0);
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
        angle += rotation*angularVelocity*dt/1000;
        rotation = 0;

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

    public boolean isAcelerate() {
        return acelerate;
    }

    public void setAcelerate(boolean acelerate) {
        this.acelerate = acelerate;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    @Override
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> rotation = -1;
            case ArrowRight -> rotation = 1;
            case ArrowUp -> acelerate = true;
        }
    }
}
