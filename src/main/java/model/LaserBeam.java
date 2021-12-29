package model;

public class LaserBeam {
    private Position position;
    private double angle;

    public LaserBeam(Position position, double angle) {
        this.position = position;
        this.angle = angle;
    }

    public Position getPosition() {
        return position;
    }

    public double getAngle() {return angle;}

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setAngle(double angle) {this.angle = angle;}

    public void update(long dt) {
        position.setX(position.getX() + 100*Math.cos(angle)*dt/1000);
        position.setY(position.getY() + 100*Math.sin(angle)*dt/1000);
    }
}