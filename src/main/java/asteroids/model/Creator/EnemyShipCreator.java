package asteroids.model.Creator;

import asteroids.Constants;
import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.util.List;
import java.util.Random;

public class EnemyShipCreator extends Creator {
    private final Random rand;
    private final Player player;
    private final List<MovingObject> entities;

    public EnemyShipCreator(Random rand, Player player, List<MovingObject> entities){
        this.rand = rand;
        this.player = player;
        this.entities = entities;
    }

    public List<MovingObject> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public MovingObject create() {
        int choice = rand.nextInt(4);
        EnemyShip enemyShip = null;
        switch (choice) {
            case 0 -> {
                Position position = new Position(Constants.WIDTH * rand.nextDouble(), 0.0);
                Vector2d velocity = new Vector2d(0.0, 1.0);
                velocity.resize(30 + 50 * rand.nextDouble());
                enemyShip = new EnemyShip(position, velocity, 20, 20);
            }
            case 1 -> {
                Position position1 = new Position(0.0, Constants.HEIGHT * rand.nextDouble());
                Vector2d velocity1 = new Vector2d(1.0, 0.0);
                velocity1.resize(30 + 50 * rand.nextDouble());
                enemyShip = new EnemyShip(position1, velocity1, 20, 20);
            }
            case 2 -> {
                Position position2 = new Position(Constants.WIDTH * rand.nextDouble(), Constants.HEIGHT);
                Vector2d velocity2 = new Vector2d(0.0, -1.0);
                velocity2.resize(30 + 50 * rand.nextDouble());
                enemyShip = new EnemyShip(position2, velocity2, 20, 20);
            }
            case 3 -> {
                Position position3 = new Position(Constants.WIDTH, Constants.HEIGHT * rand.nextDouble());
                Vector2d velocity3 = new Vector2d(-1.0, 0.0);
                velocity3.resize(30 + 50 * rand.nextDouble());
                enemyShip =  new EnemyShip(position3, velocity3, 20, 20);
            }
        }

        enemyShip.setLaserBeamCreator(new EnemyLaserBeamCreator(player, enemyShip, entities));
        return enemyShip;
    }
}