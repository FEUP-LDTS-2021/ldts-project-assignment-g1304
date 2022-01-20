package asteroids.model.Spawner;

import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EnemyShipSpawnerTest extends Assertions {

    @Test
    void createShipSpawner(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        List<MovingObject> entities = List.of(playerMock);

        //when
        EnemyShipSpawner enemyShipSpawner = new EnemyShipSpawner(playerMock, entities);

        //then
        assertEquals(entities, enemyShipSpawner.getEntities());
        assertEquals(0,enemyShipSpawner.getTimePassed());
        assertEquals(playerMock,enemyShipSpawner.getPlayer());
    }

    @Test
    void isNotSpawnTime(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        List<MovingObject> entitiesMock = List.of(playerMock);

        //when
        EnemyShipSpawner enemyShipSpawner = new EnemyShipSpawner(playerMock, entitiesMock);
        boolean time = enemyShipSpawner.isSpawnTime(4999);

        //then
        assertFalse(time);
        assertEquals(4999,enemyShipSpawner.getTimePassed());
    }

    @Test
    void isSpawnTime(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        List<MovingObject> entitiesMock = List.of(playerMock);

        //when
        EnemyShipSpawner enemyShipSpawner = new EnemyShipSpawner(playerMock, entitiesMock);
        boolean time = enemyShipSpawner.isSpawnTime(9000);

        //then
        assertTrue(time);
        assertEquals(0,enemyShipSpawner.getTimePassed());
    }

    @Test
    void updateFalse(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(new Position(12.2,12.2));
        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        List<MovingObject> entities = List.of(playerMock, enemyShipMock);
        EnemyShipSpawner enemyShipSpawner = Mockito.spy(new EnemyShipSpawner(playerMock, entities));

        Mockito.when(enemyShipSpawner.getEntities()).thenReturn(entities);
        Mockito.when(enemyShipSpawner.isSpawnTime(Mockito.anyLong())).thenReturn(false);

        //when
        enemyShipSpawner.update(6000);


        //then
        assertEquals(2,enemyShipSpawner.getEntities().size());
        Mockito.verify(enemyShipSpawner, Mockito.times(1)).update(6000);

    }

    @Test
    void updateTrue(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(new Position(12.2,12.2));
        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock, enemyShipMock));
        EnemyShipSpawner enemyShipSpawner = Mockito.spy(new EnemyShipSpawner(playerMock, entities));

        Mockito.when(enemyShipSpawner.getEntities()).thenReturn(entities);
        Mockito.when(enemyShipSpawner.isSpawnTime(Mockito.anyLong())).thenReturn(true);

        //when
        enemyShipSpawner.update(6000);


        //then
        assertEquals(3,enemyShipSpawner.getEntities().size());
        Mockito.verify(enemyShipSpawner, Mockito.times(1)).update(6000);

    }
}
