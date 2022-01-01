package model.Entities;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class EnemyShipCreatorTest extends Assertions {

    @Test
    void createEnemyShip(){
        //given
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(1);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        Player p = new Player(positionMock);
        //when
        EnemyShip enemyShip = (EnemyShip) new EnemyShipCreator(randMock,p).create();

        //then
        assertEquals(p,enemyShip.getPlayer());
        assertEquals(0.0 ,enemyShip.getPosition().getX());
        assertEquals(50.0,enemyShip.getPosition().getY());
        assertEquals(35.0,enemyShip.getVelocity().getX());
        assertEquals(0,enemyShip.getVelocity().getY());
        assertEquals(0,enemyShip.getLaserBeams().size());


    }
}
