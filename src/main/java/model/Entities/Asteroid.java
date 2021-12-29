package model.Entities;


import model.Position;
import model.physics.Vector2d;

public class Asteroid extends MovingObject{

    public Asteroid(Position position, Vector2d velocity, Integer size);

    public Integer getSize();

    public void setSize(Integer size);
}
