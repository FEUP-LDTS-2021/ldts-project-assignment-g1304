package asteroids.model.Entities;

import asteroids.control.Rotation;
import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.awt.*;


public class Player extends MovingObject {

    public static final double raio = 10;
    public static final double acelaration = 250.0;
    public static final double MAX_VELOCITY = 175.0;
    public static final double angularVelocity = Math.PI*1.5;
    public final Position beginPosition;

    private double angle;
    private Rotation rotation;
    private boolean acelerate;
    private boolean shoot;
    private LaserBeamCreator laserBeamCreator;
    private int score;
    private int lives;

    public Player(Position position){
        super(position, new Vector2d(0,0), raio, raio);
        beginPosition = position.clone();
        this.angle = 0;
        this.acelerate = false;
        setRotation(Rotation.None);
        this.shoot = false;
        this.laserBeamCreator = null;
        this.score = 0;
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

    public double getRaio() {
        return raio;
    }

    public void update(long dt){
        angle += rotation.getValue()*angularVelocity*dt/1000;

        if(isAccelerating()) {
            getVelocity().addX(acelaration * dt / 1000 * Math.cos(angle));
            getVelocity().addY(acelaration * dt / 1000 * Math.sin(angle));
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
        double anglePontaNave = angle;
        double anglePontaEsq = anglePontaNave + Math.PI*0.8333;   // 5/6
        double anglePontaDir = anglePontaNave + Math.PI*1.1666;   // 7/6
        addPoint(polygon, anglePontaNave);
        addPoint(polygon, anglePontaEsq);
        addPoint(polygon, anglePontaDir);
        return polygon;
    }

    private void addPoint(Polygon polygon, double angle){
        polygon.addPoint((int) (Math.cos(angle)*raio + getPosition().getX()),
                (int) (Math.sin(angle)*raio + getPosition().getY()));
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
        this.score += score;
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
}
