package asteroids.model.Entities;

import asteroids.control.Rotation;
import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;
import java.util.List;


public class Player extends MovingObject {

    public static final double WIDTH = 16*2;
    public static final double HEIGHT = 16*2;
    public static final double accelaration = 250.0;
    public static final double MAX_VELOCITY = 175.0;
    public static final int MAX_SCORE = 99990;
    public static final double angularVelocity = Math.PI*1.5;
    public final Position beginPosition;

    private List<Integer> pointsListX = List.of(8, 7, 7, 6, 6, 5, 0, 0, 1, 6, 7, 10, 11, 16, 17, 17, 12, 11, 11, 10, 10, 9);
    private List<Integer> pointsListY = List.of(0, 1, 2, 3, 4, 5, 10, 11, 12, 13, 14, 14, 13, 12, 11, 10, 5, 4, 3, 2, 1, 0);

    private double angle;
    private Rotation rotation;
    private boolean acelerate;
    private boolean shoot;
    private LaserBeamCreator laserBeamCreator;
    private int score;
    private int scoreLife;
    private int lives;

    public Player(Position position){
        super(position, new Vector2d(0,0), WIDTH, HEIGHT);
        beginPosition = position.clone();
        this.angle = -Math.PI/2;
        this.acelerate = false;
        setRotation(Rotation.None);
        this.shoot = false;
        this.laserBeamCreator = null;
        this.score = 0;
        this.scoreLife = 0;
        this.lives = 3;
    }

    public LaserBeamCreator getLaserBeamCreator() {
        return laserBeamCreator;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void update(long dt){
        angle += rotation.getValue()*angularVelocity*dt/1000;

        if(isAccelerating()) {
            getVelocity().addX(accelaration * dt / 1000 * Math.cos(angle));
            getVelocity().addY(accelaration * dt / 1000 * Math.sin(angle));
            if(getVelocity().module() > MAX_VELOCITY)
                getVelocity().resize(MAX_VELOCITY);
        }else {
            getVelocity().resize(getVelocity().module()*0.95);
        }

        super.update(dt);       // andar para a frente
        if (isShooting()) {
            getLaserBeamCreator().addLaserBeam(getLaserBeamCreator().create());
            shoot = false;
        }
    }

    @Override
    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        double x = getPosition().getX();
        double y = getPosition().getY();

        for (int i = 0; i < pointsListX.size(); i++) {
            polygon.addPoint((int)x+pointsListX.get(i),
                    (int)y+pointsListY.get(i));
        }

        return polygon;
    }

    public void setLaserBeamCreator(LaserBeamCreator laserBeamCreator) {
        this.laserBeamCreator = laserBeamCreator;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public boolean isAccelerating() {
        return acelerate;
    }

    public void setAcelerate(boolean acelerate) {
        this.acelerate = acelerate;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public boolean isShooting() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score){
        if(this.score + score > MAX_SCORE)
            this.score = MAX_SCORE;
        else
            this.score += score;
        this.scoreLife += score;
        extraLife();
    }

    @Override
    public void dies() {
        lives--;
        setPosition(beginPosition.clone());

        if(lives<=0)
            super.dies(); // kill player
    }

    public int getLives() {
        return lives;
    }
    public void extraLife(){
        if(scoreLife >= 10000){
            lives++;
            scoreLife-= 10000;
        }
    }

    public int getScoreLife() {
        return scoreLife;
    }

}
