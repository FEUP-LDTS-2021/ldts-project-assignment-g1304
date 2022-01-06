package asteroids.model.Entities;

import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class EnemyLaserBeamCreatorTest extends Assertions {
    @Test
    void createEnemyLaserBeam(){
        //given
        Player player = new Player(new Position(150.0,150.0));
        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);
        EnemyShipCreator enemyShipCreator = new EnemyShipCreator(randMock,player);
        EnemyShip enemyShip = (EnemyShip) enemyShipCreator.create();
        EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(player,enemyShip);

        //when
        LaserBeam laserBeam = (LaserBeam) enemyLaserBeamCreator.create();

        //then
        assertEquals(50.0,laserBeam.getPosition().getX());
        assertEquals(0.0, laserBeam.getPosition().getY());
        assertEquals(250.0*Math.cos(0.982793723247329), laserBeam.getVelocity().getX());
        assertEquals(250.0*Math.sin(0.982793723247329), laserBeam.getVelocity().getY());
        assertEquals(0.982793723247329, laserBeam.getAngle());
        assertEquals(3, laserBeam.getHeight());
        assertEquals(3,laserBeam.getWidth());
    }
}
