package model.Entities;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyShipSpawnerTest extends Assertions {

    @Test
    void createShipSpawner(){
        //given
        Player playerMock = Mockito.mock(Player.class);

        //when
        EnemyShipSpawner enemyShipSpawner = new EnemyShipSpawner(playerMock);

        //then
        assertEquals(0,enemyShipSpawner.getEnemyShips().size());
        assertEquals(0,enemyShipSpawner.getTimePassed());
        assertEquals(playerMock,enemyShipSpawner.getPlayer());

    }
    @Test
    void isSpawnTime(){
        //given
        Player playerMock = Mockito.mock(Player.class);
        EnemyShipSpawner enemyShipSpawner = new EnemyShipSpawner(playerMock);

        //when
        boolean firstTime = enemyShipSpawner.isSpawnTime(4999);
        boolean secondTime = enemyShipSpawner.isSpawnTime(1);

        //then
        assertTrue(secondTime);
        assertFalse(firstTime);
        assertEquals(0,enemyShipSpawner.getTimePassed());
    }

    @Test
    void update(){
        //given
        Player playerMock = Mockito.mock(Player.class);


        Mockito.when(playerMock.getPosition()).thenReturn(new Position(12.2,12.2));
        EnemyShipSpawner realEnemyShipSpawner = new EnemyShipSpawner(playerMock);
        EnemyShipSpawner spawner = Mockito.spy(realEnemyShipSpawner);
        //when

        realEnemyShipSpawner.update(6000);


        //then
        assertEquals(1,realEnemyShipSpawner.getEnemyShips().size());
        Mockito.verify(spawner,Mockito.times(1)).getEnemyShips().get(0).update(6000);
    }

    //TODO update not working!!
}
