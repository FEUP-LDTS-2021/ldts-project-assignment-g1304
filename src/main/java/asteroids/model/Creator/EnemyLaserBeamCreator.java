package asteroids.model.Creator;

import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.util.List;

public class EnemyLaserBeamCreator extends Creator {
    private final Player player;
    private final EnemyShip enemyShip;
    private final List<MovingObject> entities;

    public EnemyLaserBeamCreator(Player player, EnemyShip enemyShip, List<MovingObject> entities){
        this.player = player;
        this.enemyShip = enemyShip;
        this.entities = entities;
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    @Override
    public MovingObject create() {
        Vector2d velocity = new Vector2d(player.getPosition().getX() - enemyShip.getPosition().getX(),
                player.getPosition().getY() - enemyShip.getPosition().getY());
        double angle = Math.acos(velocity.dotProduct(new Vector2d(1,0))/velocity.module());
        if(velocity.getY() < 0)
            angle = Math.PI*2 - angle;

        int laserWidth = 3;
        int laserHeight = 3;

        Position laserPosition = enemyShip.getPosition().clone();
        laserPosition.setX(laserPosition.getX() + Math.cos(angle)*(enemyShip.getWidth() + laserWidth+1) + enemyShip.getWidth()/2);
        laserPosition.setY(laserPosition.getY() + Math.sin(angle)*(enemyShip.getHeight() + laserHeight+1) + enemyShip.getHeight()/2);

        return new LaserBeam(laserPosition, angle,laserWidth,laserHeight);
    }

    public void addLaserBeam(MovingObject laserBeam) {
        entities.add(laserBeam);
    }

}