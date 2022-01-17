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

import static java.lang.Double.NaN;

public class EnemyLaserBeamCreatorTest extends Assertions {
    @Test
    void createEnemyLaserBeamNegativeVelocity(){
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(25.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);

        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(30.0, 30.0));
        Mockito.when(enemyShipMock.getHeight()).thenReturn(5.0);
        Mockito.when(enemyShipMock.getWidth()).thenReturn(5.0);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock, enemyShipMock));

        EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(playerMock, enemyShipMock, entities);

        //when
        LaserBeam laserBeam = (LaserBeam) enemyLaserBeamCreator.create();
        enemyLaserBeamCreator.addLaserBeam(laserBeam);

        //then
        assertEquals(3, enemyLaserBeamCreator.getEntities().size());
        assertEquals(23.768717498692013,laserBeam.getPosition().getX());
        assertEquals(30.317179374673003, laserBeam.getPosition().getY());
        assertEquals(-291.04275004359954, laserBeam.getVelocity().getX());
        assertEquals(-72.76068751089987, laserBeam.getVelocity().getY());
        assertEquals(3.3865713167166573, laserBeam.getAngle());
        assertEquals(3, laserBeam.getHeight());
        assertEquals(3,laserBeam.getWidth());
        assertEquals(playerMock, enemyLaserBeamCreator.getPlayer());
        assertEquals(enemyShipMock, enemyLaserBeamCreator.getEnemyShip());
    }

    @Test
    void createEnemyLaserBeamPositiveVelocity() {
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(30.0);
        Mockito.when(positionMock.getY()).thenReturn(30.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);

        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(10.0, 25.0));
        Mockito.when(enemyShipMock.getHeight()).thenReturn(5.0);
        Mockito.when(enemyShipMock.getWidth()).thenReturn(5.0);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock, enemyShipMock));

        EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(playerMock, enemyShipMock, entities);

        //when
        LaserBeam laserBeam = (LaserBeam) enemyLaserBeamCreator.create();
        enemyLaserBeamCreator.addLaserBeam(laserBeam);

        //then
        assertEquals(3, enemyLaserBeamCreator.getEntities().size());
        assertEquals(21.231282501307987,laserBeam.getPosition().getX());
        assertEquals(29.682820625326997, laserBeam.getPosition().getY());
        assertEquals(291.04275004359954, laserBeam.getVelocity().getX());
        assertEquals(72.76068751089991, laserBeam.getVelocity().getY());
        assertEquals(0.24497866312686423, laserBeam.getAngle());
        assertEquals(3, laserBeam.getHeight());
        assertEquals(3,laserBeam.getWidth());
        assertEquals(playerMock, enemyLaserBeamCreator.getPlayer());
        assertEquals(enemyShipMock, enemyLaserBeamCreator.getEnemyShip());
    }

    @Test
    void createEnemyLaserBeamNullVelocity() {
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(20.0);
        Mockito.when(positionMock.getY()).thenReturn(30.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);

        Random randMock = Mockito.mock(Random.class);
        Mockito.when(randMock.nextInt(4)).thenReturn(0);
        Mockito.when(randMock.nextDouble()).thenReturn(0.1);

        EnemyShip enemyShipMock = Mockito.mock(EnemyShip.class);
        Mockito.when(enemyShipMock.getPosition()).thenReturn(new Position(20.0, 30.0));
        Mockito.when(enemyShipMock.getHeight()).thenReturn(5.0);
        Mockito.when(enemyShipMock.getWidth()).thenReturn(5.0);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock, enemyShipMock));

        EnemyLaserBeamCreator enemyLaserBeamCreator = new EnemyLaserBeamCreator(playerMock, enemyShipMock, entities);

        //when
        LaserBeam laserBeam = (LaserBeam) enemyLaserBeamCreator.create();
        enemyLaserBeamCreator.addLaserBeam(laserBeam);

        //then
        assertEquals(3, enemyLaserBeamCreator.getEntities().size());
        assertEquals(NaN,laserBeam.getPosition().getX());
        assertEquals(NaN, laserBeam.getPosition().getY());
        assertEquals(NaN, laserBeam.getVelocity().getX());
        assertEquals(NaN, laserBeam.getVelocity().getY());
        assertEquals(NaN, laserBeam.getAngle());
        assertEquals(3, laserBeam.getHeight());
        assertEquals(3,laserBeam.getWidth());
        assertEquals(playerMock, enemyLaserBeamCreator.getPlayer());
        assertEquals(enemyShipMock, enemyLaserBeamCreator.getEnemyShip());
    }
}
