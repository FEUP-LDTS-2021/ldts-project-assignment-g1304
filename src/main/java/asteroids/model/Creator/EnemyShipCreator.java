package asteroids.model.Creator;

import asteroids.Constants;
import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Entities.Sizes;
import asteroids.model.Position;
import asteroids.model.Vector2d;

import java.util.List;
import java.util.Random;

public class EnemyShipCreator extends Creator {
    private static final int WIDTH = 38;
    private static final int HEIGHT = 22;
    private static final int MIN_VELOCITY = 30;
    private static final int MAX_VELOCITY = 70;
    private final Random rand;
    private final Player player;
    private final List<MovingObject> entities;

    public EnemyShipCreator(Random rand, Player player, List<MovingObject> entities) {
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
        int locationChoice = rand.nextInt(4);
        Vector2d velocity = chooseVelocity(locationChoice);
        velocity.resize(MIN_VELOCITY + (MAX_VELOCITY - MIN_VELOCITY) * rand.nextDouble());


        EnemyShip enemyShip = new EnemyShip(choosePosition(locationChoice), velocity, WIDTH, HEIGHT);

        enemyShip.setLaserBeamCreator(chooseLaserBeamCreator(enemyShip));

        return enemyShip;
    }

    // random Position close to screen borders
    @SuppressWarnings("UnnecessaryParentheses")
    private Position choosePosition(int choice) {
        return switch (choice) {
            case 0 -> new Position(Constants.WIDTH * rand.nextDouble(), 0.0);       // BORDER UP
            case 1 -> new Position(0.0, Constants.HEIGHT * rand.nextDouble());      // BORDER LEFT
            case 2 -> new Position(Constants.WIDTH * rand.nextDouble(), Constants.HEIGHT); // BORDER RIGHT
            case 3 -> new Position(Constants.WIDTH, Constants.HEIGHT * rand.nextDouble()); // BORDER DOWN
            default -> null;
        };
    }


    // random Position close to screen borders
    @SuppressWarnings("UnnecessaryParentheses")
    private Vector2d chooseVelocity(int choice) {
        return switch (choice) {
            case 0 -> new Vector2d(0.0, 1.0);
            case 1 -> new Vector2d(1.0, 0.0);
            case 2 -> new Vector2d(0.0, -1.0);
            case 3 -> new Vector2d(-1.0, 0.0);
            default -> null;
        };
    }

    private LaserBeamCreator chooseLaserBeamCreator(EnemyShip enemyShip) {
        LaserBeamCreator laserBeamCreator = null;
        switch (rand.nextInt(2)) {
            case 0 -> {
                laserBeamCreator = new TargetLaserBeamCreator(player, enemyShip, entities);
                enemyShip.setSize(Sizes.SMALL);
            }
            case 1 -> {
                laserBeamCreator = new DumbLaserBeamCreator(enemyShip, entities);
                enemyShip.setSize(Sizes.MEDIUM);
            }
        }
        ;
        return laserBeamCreator;
    }
}