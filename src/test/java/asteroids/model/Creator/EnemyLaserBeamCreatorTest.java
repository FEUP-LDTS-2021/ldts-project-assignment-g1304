package asteroids.model.Creator;

import asteroids.model.Entities.EnemyShip;
import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyLaserBeamCreatorTest extends Assertions {
    @Test
    void createEnemyLaserBeam(){
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);

        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(20.0, 30.0));
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock, enemyShipMock));

        EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(playerMock, enemyShipMock, entities);

        //when
        LaserBeam laserBeam = (LaserBeam) enemyLaserBeamCreator.create();
        enemyLaserBeamCreator.addLaserBeam(laserBeam);

        //then
        assertEquals(3, enemyLaserBeamCreator.getEntities().size());
        assertEquals(17.17157287525381,laserBeam.getPosition().getX());
        assertEquals(27.17157287525381, laserBeam.getPosition().getY());
        assertEquals(-176.77669529663692, laserBeam.getVelocity().getX());
        assertEquals(-176.77669529663686, laserBeam.getVelocity().getY());
        assertEquals(3.9269908169872414, laserBeam.getAngle());
        assertEquals(3, laserBeam.getHeight());
        assertEquals(3,laserBeam.getWidth());
    }
}
