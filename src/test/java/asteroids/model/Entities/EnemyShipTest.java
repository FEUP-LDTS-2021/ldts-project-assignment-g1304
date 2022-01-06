package asteroids.model.Entities;

import asteroids.model.Position;
import asteroids.model.physics.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class EnemyShipTest extends Assertions {
    @Test
    void isShootingTime(){
        //given
        Player p = new Player(new Position(100.0,100.0));
        EnemyShip enemyShip =(EnemyShip) new EnemyShipCreator(new Random(),p).create(); // note: this part is already tested in
                                                                                        // EnemyShipCreatorTest.java
        //when
        boolean result1 = enemyShip.isShootingTime(999);
        boolean result2 = enemyShip.isShootingTime(2);

        //then
        assertTrue(result2);
        assertFalse(result1);
        assertEquals(1,enemyShip.getLastTime());
    }

    @Test
    void shooting(){
        //given
        Player p = new Player(new Position(100.0,100.0));
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);
        EnemyShip enemyShip =(EnemyShip) new EnemyShipCreator(randMock,p).create();

        //when
        enemyShip.shooting(1001);
        LaserBeam laserBeam = enemyShip.getLaserBeams().get(0);

        //then
        assertEquals(1,enemyShip.getLaserBeams().size());

        assertEquals(1.1071487177940904,laserBeam.getAngle());

        assertEquals(50.0,laserBeam.getPosition().getX());
        assertEquals(0.0,laserBeam.getPosition().getY());

        assertEquals(250*Math.cos(1.1071487177940904),laserBeam.getVelocity().getX());
        assertEquals(250*Math.sin(1.1071487177940904),laserBeam.getVelocity().getY());

    }
    @Test
    void update(){
        Player player = Mockito.mock(Player.class);
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getY()).thenReturn(10.0);
        Mockito.when(position.getX()).thenReturn(10.0);

        Vector2d vel = Mockito.mock(Vector2d.class);
        Mockito.when(vel.getY()).thenReturn(11.0);
        Mockito.when(vel.getX()).thenReturn(11.0);

        Mockito.when(player.getPosition()).thenReturn(new Position(12.2,12.2));

        EnemyShip enemyShip1 = new EnemyShip(player,position,vel,20,20);
        EnemyShip enemyShip = Mockito.spy(enemyShip1);

        //when
        enemyShip.update(1001);
        //then
        Mockito.verify(enemyShip,Mockito.times(1)).shooting(1001);

    }
}
