package asteroids.model.Entities;

import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.physics.Vector2d;


public class Player extends MovingObject {
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
    public static final double acelaration = 1000.0;
    public static final double MAX_VELOCITY = 200.0;
    public static final double angularVelocity = Math.PI*3;
    private Rotation rotation;
    private boolean acelerate;
    private boolean shoot;
    private LaserBeamCreator laserBeamCreator;

    public Player(Position position){
        super(position, new Vector2d(0,0), raio, raio);
        this.angle = 0;
        this.acelerate = false;
        this.rotation = Rotation.None;
        this.shoot = false;
        this.laserBeamCreator = null;
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
        rotation = Rotation.None;

        if(acelerate) {
            getVelocity().addX(acelaration * dt / 1000 * Math.cos(angle));
            getVelocity().addY(acelaration * dt / 1000 * Math.sin(angle));
            if(getVelocity().module() > MAX_VELOCITY)
                getVelocity().resize(MAX_VELOCITY);
        }else {
            getVelocity().resize(getVelocity().module()*0.98);
        }

        acelerate=false;
        super.update(dt);       // andar para a frente
        if (shoot)
            getLaserBeamCreator().addLaserBeam(getLaserBeamCreator().create());
        shoot = false;
    }

    public void setLaserBeamCreator(LaserBeamCreator laserBeamCreator) {
        this.laserBeamCreator = laserBeamCreator;
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

    public void addLaserBeams() {
        shoot = true;
    }

}
