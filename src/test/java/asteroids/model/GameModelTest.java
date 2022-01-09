package asteroids.model;

import asteroids.model.Entities.Asteroid;
import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Spawner.AsteroidSpawner;
import asteroids.model.Spawner.EnemyShipSpawner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GameModelTest extends Assertions {

    private GameModel gameModel;
    private EnemyShipSpawner enemyShipSpawner;
    private AsteroidSpawner asteroidSpawner;
    @BeforeEach
    void init(){
        gameModel = Mockito.spy(new GameModel());

        enemyShipSpawner = Mockito.mock(EnemyShipSpawner.class);
        Mockito.when(gameModel.getEnemyShipSpawner()).thenReturn(enemyShipSpawner);


        asteroidSpawner = Mockito.mock(AsteroidSpawner.class);
        Mockito.when(gameModel.getAsteroidSpawner()).thenReturn(asteroidSpawner);
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

        Rectangle2D.Double r0 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r1 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r2 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r3 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r4 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r5 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r6 = Mockito.mock(Rectangle2D.Double.class);

        Mockito.when(c0.getCollider()).thenReturn(r0);
        Mockito.when(c1.getCollider()).thenReturn(r1);
        Mockito.when(c2.getCollider()).thenReturn(r2);
        Mockito.when(c3.getCollider()).thenReturn(r3);
        Mockito.when(c4.getCollider()).thenReturn(r4);
        Mockito.when(c5.getCollider()).thenReturn(r5);
        Mockito.when(c6.getCollider()).thenReturn(r6);

        Mockito.when(r0.intersects(r1)).thenReturn(false);
        Mockito.when(r0.intersects(r2)).thenReturn(true);
        Mockito.when(r0.intersects(r3)).thenReturn(false);
        Mockito.when(r0.intersects(r4)).thenReturn(false);
        Mockito.when(r0.intersects(r5)).thenReturn(false);
        Mockito.when(r0.intersects(r6)).thenReturn(false);

        Mockito.when(r1.intersects(r2)).thenReturn(true);
        Mockito.when(r1.intersects(r3)).thenReturn(false);
        Mockito.when(r1.intersects(r4)).thenReturn(false);
        Mockito.when(r1.intersects(r5)).thenReturn(false);
        Mockito.when(r1.intersects(r6)).thenReturn(false);

        Mockito.when(r2.intersects(r3)).thenReturn(true);
        Mockito.when(r2.intersects(r4)).thenReturn(false);
        Mockito.when(r2.intersects(r5)).thenReturn(false);
        Mockito.when(r1.intersects(r6)).thenReturn(false);

        Mockito.when(r3.intersects(r4)).thenReturn(true);
        Mockito.when(r3.intersects(r5)).thenReturn(false);
        Mockito.when(r1.intersects(r6)).thenReturn(false);

        Mockito.when(r4.intersects(r5)).thenReturn(true);
        Mockito.when(r1.intersects(r6)).thenReturn(false);

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

}