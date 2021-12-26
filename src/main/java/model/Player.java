package model;

public class Player {
    private Position position;
    private double angle;
    private final double raio;

    public Player(Position position){
        this.position = position;
        this.angle = 0;
        this.raio = 10;
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
}
