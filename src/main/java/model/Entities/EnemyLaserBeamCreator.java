package model.Entities;

import model.physics.Vector2d;

public class EnemyLaserBeamCreator extends Creator{
    private final Player player;
    private final EnemyShip enemyShip;

    public EnemyLaserBeamCreator(Player player, EnemyShip enemyShip){
        this.player = player;
        this.enemyShip = enemyShip;
    }

    @Override
    public MovingObject create() {
        Vector2d velocity = new Vector2d(player.getPosition().getX() - enemyShip.getPosition().getX(),
                player.getPosition().getY() - enemyShip.getPosition().getY());
        double angle = Math.acos(velocity.dotProduct(new Vector2d(1,0))/velocity.module());
        if(velocity.getY() < 0)
            angle = Math.PI*2 - angle;
        return new LaserBeam(enemyShip.getPosition().clone(), angle,3,3);
    }
}