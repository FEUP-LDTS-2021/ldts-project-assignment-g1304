package model.Entities;

import java.util.Random;

public class AsteroidCreator extends Creator{

    public AsteroidCreator(Random rand);

    @Override
    public MovingObject create();
}
