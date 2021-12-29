package model;

public interface LaserBeam {

    Position getPosition();

    double getAngle();

    void setPosition(Position position);

    void setAngle(double angle);

    void update(long dt);
}
