package asteroids.model.Entities;

import asteroids.model.Constraints;
import asteroids.model.Position;
import asteroids.model.physics.Vector2d;

import java.util.Random;

public class EnemyShipCreator extends Creator{
    private final Random rand;
    private final Player player;

    public EnemyShipCreator(Random rand,Player player){
        this.rand = rand;
        this.player = player;
    }

    @Override
    public MovingObject create() {
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0 -> {
                Position position = new Position(Constraints.WIDTH * rand.nextDouble(), 0.0);
                Vector2d velocity = new Vector2d(0.0, 1.0);
                velocity.resize(30 + 50 * rand.nextDouble());
                return new EnemyShip(player,position, velocity, 20, 20);
            }
            case 1 -> {
                Position position1 = new Position(0.0, Constraints.HEIGHT * rand.nextDouble());
                Vector2d velocity1 = new Vector2d(1.0, 0.0);
                velocity1.resize(30 + 50 * rand.nextDouble());
                return new EnemyShip(player,position1, velocity1, 20, 20);
            }
            case 2 -> {
                Position position2 = new Position(Constraints.WIDTH * rand.nextDouble(), Constraints.HEIGHT);
                Vector2d velocity2 = new Vector2d(0.0, -1.0);
                velocity2.resize(30 + 50 * rand.nextDouble());
                return new EnemyShip(player,position2, velocity2, 20, 20);
            }
            case 3 -> {
                Position position3 = new Position(Constraints.WIDTH, Constraints.HEIGHT * rand.nextDouble());
                Vector2d velocity3 = new Vector2d(-1.0, 0.0);
                velocity3.resize(30 + 50 * rand.nextDouble());
                return new EnemyShip(player,position3, velocity3, 20, 20);
            }
        }
        return null;
    }
}