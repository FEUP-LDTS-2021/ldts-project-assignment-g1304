package asteroids.model;

import asteroids.Constants;
import asteroids.model.Creator.AsteroidCreator;
import asteroids.model.Entities.*;
import asteroids.model.Spawner.AsteroidSpawner;
import asteroids.model.Spawner.EnemyShipSpawner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameModelTest extends Assertions {

    private GameModel gameModel;
    private EnemyShipSpawner enemyShipSpawner;
    private AsteroidSpawner asteroidSpawner;
    private AsteroidCreator asteroidCreator;
    @BeforeEach
    void init(){
        gameModel = Mockito.spy(new GameModel());

        enemyShipSpawner = Mockito.mock(EnemyShipSpawner.class);
        Mockito.when(gameModel.getEnemyShipSpawner()).thenReturn(enemyShipSpawner);


        asteroidSpawner = Mockito.mock(AsteroidSpawner.class);
        asteroidCreator = Mockito.mock(AsteroidCreator.class);
        Mockito.when(gameModel.getAsteroidSpawner()).thenReturn(asteroidSpawner);
        Mockito.when(gameModel.getAsteroidSpawner().getAsteroidCreator()).thenReturn(asteroidCreator);

        Asteroid asteroid = Mockito.mock(Asteroid.class);
        Mockito.when(asteroid.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(asteroid.getVelocity()).thenReturn(Mockito.mock(Vector2d.class));

        Mockito.when(asteroidCreator.create()).thenReturn(asteroid);
    }

    @Test
    void createPlayer() {
        // then
        assertNotNull(gameModel.getPlayer());
        assertNotNull(gameModel.getPlayer().getLaserBeamCreator());
        assertFalse(gameModel.getEntities().isEmpty());
        assertTrue(gameModel.getEntities().contains(gameModel.getPlayer()));

        assertNotNull(gameModel.getAsteroidSpawner());
        assertNotNull(gameModel.getEnemyShipSpawner());

        assertEquals(new Position(Constants.WIDTH / 2.0, Constants.HEIGHT / 2.0), gameModel.getPlayer().getPosition());
    }

    @Test
    void createSpawnners(){
        // given
        GameModel gameModel = Mockito.spy(new GameModel());

        // when
        AsteroidSpawner asteroidSpawner = gameModel.getAsteroidSpawner();
        EnemyShipSpawner enemyShipSpawner = gameModel.getEnemyShipSpawner();

        // then
        assertEquals(gameModel.getEntities() ,asteroidSpawner.getEntities());
        assertEquals(gameModel.getEntities() ,enemyShipSpawner.getEntities());
        assertEquals(gameModel.getPlayer() ,enemyShipSpawner.getPlayer());
    }

    @Test
    void updateCallCheckCollisions(){
        Mockito.doNothing().when(gameModel).checkCollisions();

        // when
        gameModel.update(10);

        // then
        Mockito.verify(gameModel).checkCollisions();
    }

    @Test
    void updateEnemyShipSpawner(){
        Mockito.doNothing().when(gameModel).checkCollisions();

        // when
        gameModel.update(10);

        // then
        Mockito.verify(enemyShipSpawner).update(10);
    }

    @Test
    void updateAsteroidSpawner(){
        Mockito.doNothing().when(gameModel).checkCollisions();

        // when
        gameModel.update(10);

        // then
        Mockito.verify(asteroidSpawner).update();
    }

    @Test
    void updateEntities(){
        Mockito.doNothing().when(gameModel).checkCollisions();

        // given
        List<MovingObject> entities = List.of(Mockito.mock(MovingObject.class), Mockito.mock(MovingObject.class) ,Mockito.mock(MovingObject.class));
        Mockito.when(gameModel.getEntities()).thenReturn(entities);

        // when
        gameModel.update(10);

        // then
        for(MovingObject movingObject : entities)
            Mockito.verify(movingObject, Mockito.times(1)).update(10);
    }

    List<MovingObject> getObjects(){
        Asteroid c0 = Mockito.mock(Asteroid.class);
        Asteroid c1 = Mockito.mock(Asteroid.class);
        Asteroid c2 = Mockito.mock(Asteroid.class);
        MovingObject c3 = Mockito.mock(MovingObject.class);
        MovingObject c4 = Mockito.mock(MovingObject.class);
        MovingObject c5 = Mockito.mock(MovingObject.class);
        MovingObject c6 = Mockito.mock(MovingObject.class);

        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(c0);
        movingObjects.add(c1);
        movingObjects.add(c2);
        movingObjects.add(c3);
        movingObjects.add(c4);
        movingObjects.add(c5);
        movingObjects.add(c6);

        for(MovingObject object : movingObjects)
            Mockito.when(object.isAlive()).thenReturn(true);

        return movingObjects;
    }

    @Test
        // Test if Colisions remove Object from entities
    void ifCollisionsRemove(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();

        MovingObject object5 = entities.get(5);
        MovingObject object6 = entities.get(6);
        Mockito.doAnswer(invocation -> {
            Mockito.when(object5.isAlive()).thenReturn(false);
            return null;
        }).when(object5).dies();

        Mockito.doAnswer(invocation -> {
            Mockito.when(object6.isAlive()).thenReturn(false);
            return null;
        }).when(object6).dies();

        // when
        Mockito.when(entities.get(5).collide(entities.get(6))).thenReturn(true);

        gameModel.checkCollisions();

        // then
        assertEquals(5, entities.size());
        assertFalse(entities.contains(object5));
        assertFalse(entities.contains(object6));
        Mockito.verify(object5).dies();
        Mockito.verify(object6).dies();
    }

    @Test
    void colisionAsteroidAsteroid(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();

        // when
        Mockito.when(entities.get(0).collide(entities.get(1))).thenReturn(true);
        Mockito.when(entities.get(0).collide(entities.get(2))).thenReturn(true);
        Mockito.when(entities.get(1).collide(entities.get(0))).thenReturn(true);
        Mockito.when(entities.get(1).collide(entities.get(2))).thenReturn(true);
        Mockito.when(entities.get(2).collide(entities.get(0))).thenReturn(true);
        Mockito.when(entities.get(2).collide(entities.get(1))).thenReturn(true);

        gameModel.checkCollisions();

        // then
        Mockito.verify(entities.get(0), Mockito.never()).dies();
        Mockito.verify(entities.get(1), Mockito.never()).dies();
        Mockito.verify(entities.get(2), Mockito.never()).dies();
    }

    @Test
        // colision asteroid with object
    void colisionCallSplit1(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();

        Asteroid original = (Asteroid) entities.get(0);
        Asteroid split1 = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidCreator.create()).thenReturn(split1);
        Mockito.when(split1.isAlive()).thenReturn(true);

        Position position = Mockito.mock(Position.class);
        Position positionClone = Mockito.mock(Position.class);
        Mockito.when(original.getPosition()).thenReturn(position);
        Mockito.when(position.clone()).thenReturn(positionClone);

        Vector2d velocity = Mockito.mock(Vector2d.class);
        Vector2d velocityClone = Mockito.mock(Vector2d.class);
        Mockito.when(original.getVelocity()).thenReturn(velocity);
        Mockito.when(velocity.clone()).thenReturn(velocityClone);

        Mockito.when(split1.getVelocity()).thenReturn(velocityClone);

        // mock decrezeSize
        Mockito.when(original.getSize()).thenReturn(AsteroidSizes.MEDIUM);
        Mockito.doAnswer(invocation -> {
            Mockito.when(original.getSize()).thenReturn(AsteroidSizes.SMALL);
            return null;
        }).when(original).decreaseSize();

        // when
        Mockito.when(original.collide(entities.get(4))).thenReturn(true);
        gameModel.checkCollisions();

        // then
        Mockito.verify(original, Mockito.times(1)).decreaseSize();
        Mockito.verify(split1).setPosition(positionClone);
        Mockito.verify(split1).setVelocity(velocityClone);
        Mockito.verify(velocityClone).scale(-1);
        Mockito.verify(split1).setSize(AsteroidSizes.SMALL);

        assertTrue(entities.contains(split1));
        assertTrue(entities.contains(original));


    }

    @Test
        // colision object with asteroid
    void colisionCallSplit2(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();

        Asteroid original = Mockito.mock(Asteroid.class); // now asteroid is in the last position
        entities.add(original);
        Mockito.when(original.isAlive()).thenReturn(true);

        Asteroid split1 = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidCreator.create()).thenReturn(split1);
        Mockito.when(split1.isAlive()).thenReturn(true);

        Position position = Mockito.mock(Position.class);
        Position positionClone = Mockito.mock(Position.class);
        Mockito.when(original.getPosition()).thenReturn(position);
        Mockito.when(position.clone()).thenReturn(positionClone);

        Vector2d velocity = Mockito.mock(Vector2d.class);
        Vector2d velocityClone = Mockito.mock(Vector2d.class);
        Mockito.when(original.getVelocity()).thenReturn(velocity);
        Mockito.when(velocity.clone()).thenReturn(velocityClone);

        Mockito.when(split1.getVelocity()).thenReturn(velocityClone);

        // mock decrezeSize
        Mockito.when(original.getSize()).thenReturn(AsteroidSizes.MEDIUM);
        Mockito.doAnswer(invocation -> {
            Mockito.when(original.getSize()).thenReturn(AsteroidSizes.SMALL);
            return null;
        }).when(original).decreaseSize();

        // when
        Mockito.when(entities.get(4).collide(original)).thenReturn(true);
        gameModel.checkCollisions();

        // then
        Mockito.verify(original, Mockito.times(1)).decreaseSize();
        Mockito.verify(split1).setPosition(positionClone);
        Mockito.verify(split1).setVelocity(velocityClone);
        Mockito.verify(velocityClone).scale(-1);
        Mockito.verify(split1).setSize(AsteroidSizes.SMALL);

        assertTrue(entities.contains(split1));
        assertTrue(entities.contains(original));
    }

    @Test
    void dontSplitSmall(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();
        Asteroid original = (Asteroid) entities.get(0);
        Mockito.when(original.getSize()).thenReturn(AsteroidSizes.SMALL);

        int size = entities.size();

        // when
        Mockito.when(original.collide(entities.get(4))).thenReturn(true);
        gameModel.checkCollisions();

        // then
        assertEquals(size, entities.size());
    }

    @Test
    void dontKillIfDead(){
        // given
        List<MovingObject> entities = getObjects();
        Mockito.doReturn(entities).when(gameModel).getEntities();

        MovingObject object4 = entities.get(4);
        MovingObject object5 = entities.get(5);
        MovingObject object6 = entities.get(6);

        // when
        Mockito.when(object4.isAlive()).thenReturn(false);
        Mockito.when(object6.isAlive()).thenReturn(false);
        Mockito.when(object4.collide(object5)).thenReturn(true);
        Mockito.when(object5.collide(object6)).thenReturn(true);
        gameModel.checkCollisions();

        // then
        Mockito.verify(object4, Mockito.never()).dies();
        Mockito.verify(object5, Mockito.never()).dies();
        Mockito.verify(object6, Mockito.never()).dies();
    }

    @Test
        // count score when player hit in asteroid or in a enemy shipt
    void getScoreHitByPlayer(){
        // given
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);
        EnemyShip enemyShip1 = Mockito.mock(EnemyShip.class);
        Player player = Mockito.mock(Player.class);
        EnemyShip enemyShip2 = Mockito.mock(EnemyShip.class);
        Asteroid asteroid3 = Mockito.mock(Asteroid.class);

        Mockito.when(asteroid1.getPoints()).thenReturn(10);
        Mockito.when(asteroid2.getPoints()).thenReturn(20);
        Mockito.when(enemyShip1.getPoints()).thenReturn(30);
        Mockito.when(enemyShip2.getPoints()).thenReturn(40);
        Mockito.when(asteroid3.getPoints()).thenReturn(50);

        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(asteroid1);
        movingObjects.add(asteroid2);
        movingObjects.add(enemyShip1);
        movingObjects.add(player);
        movingObjects.add(enemyShip2);
        movingObjects.add(asteroid3);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        for(MovingObject object : movingObjects) {
            Mockito.when(object.isAlive()).thenReturn(true);

            Position position = Mockito.mock(Position.class);
            Mockito.when(object.getPosition()).thenReturn(position);
            Mockito.when(position.clone()).thenReturn(position);
            Vector2d vector2d = Mockito.mock(Vector2d.class);
            Mockito.when(object.getVelocity()).thenReturn(vector2d);
            Mockito.when(vector2d.clone()).thenReturn(vector2d);
            Mockito.when(object.getPosition()).thenReturn(Mockito.mock(Position.class));
        }

        Mockito.when(gameModel.getPlayer()).thenReturn(player);

        Asteroid split = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidCreator.create()).thenReturn(split);
        Mockito.when(split.getVelocity()).thenReturn(Mockito.mock(Vector2d.class));

        // when
        Mockito.when(asteroid1.collide(player)).thenReturn(true);
        Mockito.when(enemyShip1.collide(player)).thenReturn(true);
        Mockito.when(player.collide(enemyShip2)).thenReturn(true);
        Mockito.when(player.collide(asteroid3)).thenReturn(true);

        gameModel.checkCollisions();

        // then
        Mockito.verify(player, Mockito.times(1)).addScore(10);
        Mockito.verify(player, Mockito.never()).addScore(20);
        Mockito.verify(player, Mockito.times(1)).addScore(30);
        Mockito.verify(player, Mockito.times(1)).addScore(40);
        Mockito.verify(player, Mockito.times(1)).addScore(50);
        Mockito.verify(player, Mockito.times(4)).addScore(Mockito.anyInt());

    }

    @Test
        // count score when Laser hit in asteroid or in a enemy shipt
    void getScoreHitByLaserPlayer(){
        // given
        Player player = Mockito.mock(Player.class);
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);
        EnemyShip enemyShip1 = Mockito.mock(EnemyShip.class);
        LaserBeam laserPlayer = Mockito.mock(LaserBeam.class);
        EnemyShip enemyShip2 = Mockito.mock(EnemyShip.class);
        Asteroid asteroid3 = Mockito.mock(Asteroid.class);

        Mockito.when(laserPlayer.isPlayerBeam()).thenReturn(true);

        Mockito.when(asteroid1.getPoints()).thenReturn(10);
        Mockito.when(asteroid2.getPoints()).thenReturn(20);
        Mockito.when(enemyShip1.getPoints()).thenReturn(30);
        Mockito.when(enemyShip2.getPoints()).thenReturn(40);
        Mockito.when(asteroid3.getPoints()).thenReturn(50);

        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(asteroid1);
        movingObjects.add(asteroid2);
        movingObjects.add(enemyShip1);
        movingObjects.add(laserPlayer);
        movingObjects.add(enemyShip2);
        movingObjects.add(asteroid3);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        for(MovingObject object : movingObjects) {
            Mockito.when(object.isAlive()).thenReturn(true);

            Position position = Mockito.mock(Position.class);
            Mockito.when(object.getPosition()).thenReturn(position);
            Mockito.when(position.clone()).thenReturn(position);
            Vector2d vector2d = Mockito.mock(Vector2d.class);
            Mockito.when(object.getVelocity()).thenReturn(vector2d);
            Mockito.when(vector2d.clone()).thenReturn(vector2d);
            Mockito.when(object.getPosition()).thenReturn(Mockito.mock(Position.class));
        }

        Mockito.when(gameModel.getPlayer()).thenReturn(player);

        Asteroid split = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidCreator.create()).thenReturn(split);
        Mockito.when(split.getVelocity()).thenReturn(Mockito.mock(Vector2d.class));

        // when
        Mockito.when(asteroid1.collide(laserPlayer)).thenReturn(true);
        Mockito.when(enemyShip1.collide(laserPlayer)).thenReturn(true);
        Mockito.when(laserPlayer.collide(enemyShip2)).thenReturn(true);
        Mockito.when(laserPlayer.collide(asteroid3)).thenReturn(true);

        gameModel.checkCollisions();

        // then
        Mockito.verify(player, Mockito.times(1)).addScore(10);
        Mockito.verify(player, Mockito.never()).addScore(20);
        Mockito.verify(player, Mockito.times(1)).addScore(30);
        Mockito.verify(player, Mockito.times(1)).addScore(40);
        Mockito.verify(player, Mockito.times(1)).addScore(50);
        Mockito.verify(player, Mockito.times(4)).addScore(Mockito.anyInt());

    }

    @Test
    void dontCountScoreWhenDead(){
        // given
        Player player = Mockito.mock(Player.class);
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);
        EnemyShip enemyShip1 = Mockito.mock(EnemyShip.class);
        LaserBeam laserPlayer = Mockito.mock(LaserBeam.class);
        EnemyShip enemyShip2 = Mockito.mock(EnemyShip.class);
        Asteroid asteroid3 = Mockito.mock(Asteroid.class);

        Mockito.when(laserPlayer.isPlayerBeam()).thenReturn(true);

        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(asteroid1);
        movingObjects.add(asteroid2);
        movingObjects.add(enemyShip1);
        movingObjects.add(laserPlayer);
        movingObjects.add(enemyShip2);
        movingObjects.add(asteroid3);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        for(MovingObject object : movingObjects) {
            Mockito.when(object.isAlive()).thenReturn(false);

            Position position = Mockito.mock(Position.class);
            Mockito.when(object.getPosition()).thenReturn(position);
            Mockito.when(position.clone()).thenReturn(position);
            Vector2d vector2d = Mockito.mock(Vector2d.class);
            Mockito.when(object.getVelocity()).thenReturn(vector2d);
            Mockito.when(vector2d.clone()).thenReturn(vector2d);
            Mockito.when(object.getPosition()).thenReturn(Mockito.mock(Position.class));
        }

        Mockito.when(gameModel.getPlayer()).thenReturn(player);

        Asteroid split = Mockito.mock(Asteroid.class);
        Mockito.when(asteroidCreator.create()).thenReturn(split);
        Mockito.when(split.getVelocity()).thenReturn(Mockito.mock(Vector2d.class));

        // when
        Mockito.when(asteroid1.collide(laserPlayer)).thenReturn(true);
        Mockito.when(enemyShip1.collide(laserPlayer)).thenReturn(true);
        Mockito.when(laserPlayer.collide(enemyShip2)).thenReturn(true);
        Mockito.when(laserPlayer.collide(asteroid3)).thenReturn(true);

        gameModel.checkCollisions();

        // then
        Mockito.verify(player, Mockito.never()).addScore(Mockito.anyInt());
    }

}