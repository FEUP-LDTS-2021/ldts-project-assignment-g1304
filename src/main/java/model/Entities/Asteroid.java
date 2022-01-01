package model.Entities;
import com.googlecode.lanterna.TerminalPosition;
import model.Position;
import model.physics.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Asteroid extends MovingObject{

    public Asteroid(Position position, Vector2d velocity,Integer size){
        super(position,velocity, size, size);
    }
}
