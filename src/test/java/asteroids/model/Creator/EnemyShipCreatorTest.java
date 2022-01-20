package asteroids.model.Creator;

import asteroids.Constants;
import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;

public class EnemyShipCreatorTest extends Assertions {

    @Test
    void createEnemyShip0(){
        //given
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock));

        //when
        EnemyShipCreator enemyShipCreator = new EnemyShipCreator(randMock, playerMock, entities);
        EnemyShip enemyShip = (EnemyShip) enemyShipCreator.create();
        entities.add(enemyShip);

        //then
        assertEquals(playerMock,enemyShipCreator.getPlayer());
        assertEquals(50.0 ,enemyShip.getPosition().getX());
        assertEquals(0.0,enemyShip.getPosition().getY());
        assertEquals(0.0,enemyShip.getVelocity().getX());
        assertEquals(34.0,enemyShip.getVelocity().getY());
        assertEquals(2,enemyShipCreator.getEntities().size());
        assertEquals(entities, enemyShip.getLaserBeamCreator().getEntities());
    }

    @Test
    void createEnemyShip1(){
        //given
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(1);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock));

        //when
        EnemyShipCreator enemyShipCreator = new EnemyShipCreator(randMock, playerMock, entities);
        EnemyShip enemyShip = (EnemyShip) enemyShipCreator.create();
        entities.add(enemyShip);

        //then
        assertEquals(playerMock,enemyShipCreator.getPlayer());
        assertEquals(0.0 ,enemyShip.getPosition().getX());
        assertEquals(50.0,enemyShip.getPosition().getY());
        assertEquals(34.0,enemyShip.getVelocity().getX());
        assertEquals(0,enemyShip.getVelocity().getY());
        assertEquals(2,enemyShipCreator.getEntities().size());
        assertEquals(entities, enemyShip.getLaserBeamCreator().getEntities());
    }

    @Test
    void createEnemyShip2(){
        //given
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(2);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock));

        //when
        EnemyShipCreator enemyShipCreator = new EnemyShipCreator(randMock, playerMock, entities);
        EnemyShip enemyShip = (EnemyShip) enemyShipCreator.create();
        entities.add(enemyShip);

        //then
        assertEquals(playerMock,enemyShipCreator.getPlayer());
        assertEquals(50.0 ,enemyShip.getPosition().getX());
        assertEquals(Constants.WIDTH,enemyShip.getPosition().getY());
        assertEquals(0.0,enemyShip.getVelocity().getX());
        assertEquals(-34.0,enemyShip.getVelocity().getY());
        assertEquals(2,enemyShipCreator.getEntities().size());
        assertEquals(entities, enemyShip.getLaserBeamCreator().getEntities());
    }

    @Test
    void createEnemyShip3(){
        //given
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(3);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock));

        //when
        EnemyShipCreator enemyShipCreator = new EnemyShipCreator(randMock, playerMock, entities);
        EnemyShip enemyShip = (EnemyShip) enemyShipCreator.create();
        entities.add(enemyShip);

        //then
        assertEquals(playerMock,enemyShipCreator.getPlayer());
        assertEquals(Constants.WIDTH,enemyShip.getPosition().getX());
        assertEquals(50.0,enemyShip.getPosition().getY());
        assertEquals(-34.0,enemyShip.getVelocity().getX());
        assertEquals(0,enemyShip.getVelocity().getY());
        assertEquals(2,enemyShipCreator.getEntities().size());
        assertEquals(entities, enemyShip.getLaserBeamCreator().getEntities());
    }
}
