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
        EnemyShip enemyShip;
        Position position = null;
        Vector2d velocity = null;
        switch (choice) {
            case 0 -> {
                position = new Position(Constants.WIDTH * rand.nextDouble(), 0.0);
                velocity = new Vector2d(0.0, 1.0);
            }
            case 1 -> {
                position = new Position(0.0, Constants.HEIGHT * rand.nextDouble());
                velocity = new Vector2d(1.0, 0.0);
            }
            case 2 -> {
                position = new Position(Constants.WIDTH * rand.nextDouble(), Constants.HEIGHT);
                velocity = new Vector2d(0.0, -1.0);
            }
            case 3 -> {
                position = new Position(Constants.WIDTH, Constants.HEIGHT * rand.nextDouble());
                velocity = new Vector2d(-1.0, 0.0);
            }
        }

        velocity.resize(30 + 50 * rand.nextDouble());
        enemyShip = new EnemyShip(position, velocity, 38, 22  );
        enemyShip.setLaserBeamCreator(new EnemyLaserBeamCreator(player, enemyShip, entities));
        return enemyShip;
    }
}