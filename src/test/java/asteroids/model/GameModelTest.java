package asteroids.model;

import asteroids.model.Creator.AsteroidCreator;
import asteroids.model.Entities.*;
import asteroids.model.Spawner.AsteroidSpawner;
import asteroids.model.Spawner.EnemyShipSpawner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameModelTest extends Assertions {

    private GameModel gameModel;
    private Player player;
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

    @Test
    void checkCollisions(){
        //given
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

        Mockito.when(c0.isAlive()).thenReturn(true);
        Mockito.when(c1.isAlive()).thenReturn(true);
        Mockito.when(c2.isAlive()).thenReturn(true);
        Mockito.when(c3.isAlive()).thenReturn(true);
        Mockito.when(c4.isAlive()).thenReturn(true);
        Mockito.when(c5.isAlive()).thenReturn(true);
        Mockito.when(c6.isAlive()).thenReturn(true);

        Position position = Mockito.mock(Position.class);

        Mockito.when(position.clone()).thenReturn(position);

        Mockito.when(c0.getPosition()).thenReturn(position);
        Mockito.when(c1.getPosition()).thenReturn(position);
        Mockito.when(c2.getPosition()).thenReturn(position);

        Vector2d v = Mockito.mock(Vector2d.class);

        Mockito.when(v.clone()).thenReturn(v);

        Mockito.when(c0.getVelocity()).thenReturn(v);
        Mockito.when(c1.getVelocity()).thenReturn(v);
        Mockito.when(c2.getVelocity()).thenReturn(v);

        Mockito.when(c0.collide(c1)).thenReturn(false);
        Mockito.when(c0.collide(c2)).thenReturn(true);
        Mockito.when(c0.collide(c3)).thenReturn(false);
        Mockito.when(c0.collide(c4)).thenReturn(false);
        Mockito.when(c0.collide(c5)).thenReturn(false);
        Mockito.when(c0.collide(c6)).thenReturn(false);

        Mockito.when(c1.collide(c2)).thenReturn(true);
        Mockito.when(c1.collide(c3)).thenReturn(false);
        Mockito.when(c1.collide(c4)).thenReturn(false);
        Mockito.when(c1.collide(c5)).thenReturn(false);
        Mockito.when(c1.collide(c6)).thenReturn(false);

        Mockito.when(c2.collide(c3)).thenReturn(true);
        Mockito.when(c2.collide(c4)).thenReturn(false);
        Mockito.when(c2.collide(c5)).thenReturn(false);
        Mockito.when(c2.collide(c6)).thenReturn(false);

        Mockito.when(c3.collide(c4)).thenReturn(true);
        Mockito.when(c3.collide(c5)).thenReturn(false);
        Mockito.when(c3.collide(c6)).thenReturn(false);

        Mockito.when(c4.collide(c5)).thenReturn(true);
        Mockito.when(c4.collide(c6)).thenReturn(false);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        //when
        gameModel.checkCollisions();

        //then
        Mockito.verify(c0, Mockito.never()).dies();
        Mockito.verify(c1, Mockito.never()).dies();
        Mockito.verify(c2, Mockito.times(1)).dies();
        Mockito.verify(c3, Mockito.times(2)).dies();
        Mockito.verify(c4, Mockito.times(2)).dies();
        Mockito.verify(c5, Mockito.times(1)).dies();
        Mockito.verify(c6, Mockito.never()).dies();
    }

    @Test
    void getScore(){
        // given

        // create moving objects
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);

        LaserBeam laserPlayer = Mockito.mock(LaserBeam.class);
        Mockito.when(laserPlayer.isPlayerBeam()).thenReturn(true);

        LaserBeam laserEnemy= Mockito.mock(LaserBeam.class);
        Mockito.when(laserEnemy.isPlayerBeam()).thenReturn(false);
        EnemyShip enemyShip = Mockito.mock(EnemyShip.class);


        Mockito.when(asteroid1.getPoints()).thenReturn(20);
        Mockito.when(asteroid2.getPoints()).thenReturn(20);
        Mockito.when(enemyShip.getPoints()).thenReturn(50);

        Mockito.when(asteroid1.isAlive()).thenReturn(true);
        Mockito.when(asteroid2.isAlive()).thenReturn(true);
        Mockito.when(laserPlayer.isAlive()).thenReturn(true);
        Mockito.when(laserEnemy.isAlive()).thenReturn(true);
        Mockito.when(enemyShip.isAlive()).thenReturn(true);

        Position position = Mockito.mock(Position.class);

        Mockito.when(position.clone()).thenReturn(position);

        Mockito.when(asteroid1.getPosition()).thenReturn(position);
        Mockito.when(asteroid2.getPosition()).thenReturn(position);

        Vector2d v = Mockito.mock(Vector2d.class);

        Mockito.when(v.clone()).thenReturn(v);

        Mockito.when(asteroid1.getVelocity()).thenReturn(v);
        Mockito.when(asteroid2.getVelocity()).thenReturn(v);

        // add mock of player
        Player playermock = Mockito.mock(Player.class);
        Mockito.doReturn(playermock).when(gameModel).getPlayer();
        Mockito.when(playermock.isAlive()).thenReturn(true);

        // add moving objects to entities
        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(asteroid1);
        movingObjects.add(asteroid2);
        movingObjects.add(laserPlayer);
        movingObjects.add(laserEnemy);
        movingObjects.add(enemyShip);
        movingObjects.add(playermock);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        // set collisions
        Mockito.when(asteroid1.collide(asteroid2)).thenReturn(false);  // asteroid  - asteroide
        Mockito.when(asteroid1.collide(laserPlayer)).thenReturn(true);   // asteroid  - laserbeam player
        Mockito.when(asteroid1.collide(laserEnemy)).thenReturn(true);   // asteroid  - laserbeam enenmy
        Mockito.when(asteroid1.collide(enemyShip)).thenReturn(false);  // asteroid  - enemyShip
        Mockito.when(asteroid1.collide(playermock)).thenReturn(true);   // asteroid  - player

        Mockito.when(asteroid2.collide(laserPlayer)).thenReturn(false);  // asteroid  - laserbeam player
        Mockito.when(asteroid2.collide(laserEnemy)).thenReturn(false);  // asteroid  - laserbeam enenmy
        Mockito.when(asteroid2.collide(enemyShip)).thenReturn(true);   // asteroid  - enemyShip
        Mockito.when(asteroid2.collide(playermock)).thenReturn(false);   // asteroid  - player

        Mockito.when(laserPlayer.collide(laserEnemy)).thenReturn(true);   // laserbeam player  - laserbeam enenmy
        Mockito.when(laserPlayer.collide(enemyShip)).thenReturn(true);   // laserbeam player  - enemyShip
        Mockito.when(laserPlayer.collide(playermock)).thenReturn(true);   // laserbeam player  - player

        Mockito.when(laserEnemy.collide(enemyShip)).thenReturn(true);   // laserbeam enenmy  - enemyShip
        Mockito.when(laserEnemy.collide(playermock)).thenReturn(true);   // laserbeam enenmy  - player

        Mockito.when(enemyShip.collide(playermock)).thenReturn(true);   // enenmy  - player


        // when
        gameModel.checkCollisions();


        // then
        Mockito.verify(playermock, Mockito.times(2)).addScore(20);
        Mockito.verify(playermock, Mockito.times(2)).addScore(50);


    }

    @Test
    void dontCountScoreWhenDead(){
        // given

        // create moving objects
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);

        LaserBeam laserPlayer = Mockito.mock(LaserBeam.class);
        Mockito.when(laserPlayer.isPlayerBeam()).thenReturn(true);

        LaserBeam laserEnemy= Mockito.mock(LaserBeam.class);
        Mockito.when(laserEnemy.isPlayerBeam()).thenReturn(false);
        EnemyShip enemyShip = Mockito.mock(EnemyShip.class);


        Mockito.when(asteroid1.getPoints()).thenReturn(20);
        Mockito.when(asteroid2.getPoints()).thenReturn(20);
        Mockito.when(enemyShip.getPoints()).thenReturn(50);

        Mockito.when(asteroid1.isAlive()).thenReturn(false);
        Mockito.when(asteroid2.isAlive()).thenReturn(false);
        Mockito.when(laserPlayer.isAlive()).thenReturn(false);
        Mockito.when(laserEnemy.isAlive()).thenReturn(false);
        Mockito.when(enemyShip.isAlive()).thenReturn(false);

        // add mock of player
        Player playermock = Mockito.mock(Player.class);
        Mockito.doReturn(playermock).when(gameModel).getPlayer();
        Mockito.when(playermock.isAlive()).thenReturn(false);


        // add moving objects to entities
        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add(asteroid1);
        movingObjects.add(asteroid2);
        movingObjects.add(laserPlayer);
        movingObjects.add(laserEnemy);
        movingObjects.add(enemyShip);
        movingObjects.add(playermock);

        Mockito.when(gameModel.getEntities()).thenReturn(movingObjects);

        // set collisions
        Mockito.when(asteroid1.collide(asteroid2)).thenReturn(false);  // asteroid  - asteroide
        Mockito.when(asteroid1.collide(laserPlayer)).thenReturn(true);   // asteroid  - laserbeam player
        Mockito.when(asteroid1.collide(laserEnemy)).thenReturn(true);   // asteroid  - laserbeam enenmy
        Mockito.when(asteroid1.collide(enemyShip)).thenReturn(false);  // asteroid  - enemyShip
        Mockito.when(asteroid1.collide(playermock)).thenReturn(true);   // asteroid  - player

        Mockito.when(asteroid2.collide(laserPlayer)).thenReturn(false);  // asteroid  - laserbeam player
        Mockito.when(asteroid2.collide(laserEnemy)).thenReturn(false);  // asteroid  - laserbeam enenmy
        Mockito.when(asteroid2.collide(enemyShip)).thenReturn(true);   // asteroid  - enemyShip
        Mockito.when(asteroid2.collide(playermock)).thenReturn(false);   // asteroid  - player

        Mockito.when(laserPlayer.collide(laserEnemy)).thenReturn(true);   // laserbeam player  - laserbeam enenmy
        Mockito.when(laserPlayer.collide(enemyShip)).thenReturn(true);   // laserbeam player  - enemyShip
        Mockito.when(laserPlayer.collide(playermock)).thenReturn(true);   // laserbeam player  - player

        Mockito.when(laserEnemy.collide(enemyShip)).thenReturn(true);   // laserbeam enenmy  - enemyShip
        Mockito.when(laserEnemy.collide(playermock)).thenReturn(true);   // laserbeam enenmy  - player

        Mockito.when(enemyShip.collide(playermock)).thenReturn(true);   // enenmy  - player


        // when
        gameModel.checkCollisions();


        // then
        Mockito.verify(playermock, Mockito.never()).addScore(Mockito.anyInt());


    }

}