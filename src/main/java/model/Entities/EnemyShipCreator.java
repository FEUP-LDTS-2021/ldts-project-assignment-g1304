package model.Entities;


import java.util.Random;

public class EnemyShipCreator extends Creator{

    public EnemyShipCreator(Random rand,Player player);

    @Override
    public MovingObject create();
}