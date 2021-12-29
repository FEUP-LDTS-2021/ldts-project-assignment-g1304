package model;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.input.InputObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements InputObserver {
    private Position position;
    private double angle;
    private final double raio;

    public List<LaserBeam> getLaserBeams() {
        return laserBeams;
    }

    private List<LaserBeam> laserBeams;
    private final LaserBeamCreator laserCreator;

    public Player(Position position){
        this.position = position;
        this.angle = 0;
        this.raio = 10;
        laserBeams = new ArrayList<>();
        laserCreator = new LaserBeamCreator(this);
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

    @Override
    public void processKey(KeyStroke key) {
        if (key.getCharacter() == ' ') {
            laserBeams.add(laserCreator.createLaserBeam());
        }
    }
}
