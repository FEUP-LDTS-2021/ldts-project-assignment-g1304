package model.Entities;

import model.Position;
import model.physics.Vector2d;

public interface MovingObject {

    void goFoward(long dt);

    void update(long dt);

    void setVelocity(Vector2d velocity);

    Vector2d getVelocity();

    Position getPosition();

    void setPosition(Position position);
}
