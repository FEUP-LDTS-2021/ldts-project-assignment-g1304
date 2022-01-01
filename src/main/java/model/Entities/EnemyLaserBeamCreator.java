package model.Entities;


public class EnemyLaserBeamCreator extends Creator{

    public EnemyLaserBeamCreator(Player player, EnemyShip enemyShip);

    @Override
    public MovingObject create();
}
