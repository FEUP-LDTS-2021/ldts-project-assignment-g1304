package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

public class TargetBeamCreatorTest extends Assertions {

    TargetLaserBeamCreator creator;
    MovingObject target;
    MovingObject shooter;
    List<MovingObject> entities;

    @BeforeEach
    void init(){
        target = Mockito.mock(MovingObject.class);
        shooter = Mockito.mock(MovingObject.class);

        entities = Mockito.mock(List.class);
        creator = Mockito.spy(new TargetLaserBeamCreator(target, shooter, entities));
    }

    @Test
    void createCreator(){
        // then
        assertEquals(shooter, creator.getShooter());
        assertEquals(target, creator.getTarget());
        assertEquals(entities, creator.getEntities());
    }

    @Test
    public void createPosVel() {

        // given
        Position position = Mockito.mock(Position.class);
        Mockito.doReturn(position).when(creator).ajustPosition(Mockito.anyDouble(), Mockito.any());

        Position targetPos = Mockito.mock(Position.class);
        Mockito.when(targetPos.getX()).thenReturn(10.0);
        Mockito.when(targetPos.getY()).thenReturn(79.0);

        Position shooterPos = Mockito.mock(Position.class);
        Mockito.when(shooterPos.getX()).thenReturn(60.0);
        Mockito.when(shooterPos.getY()).thenReturn(50.0);

        Mockito.when(shooter.getPosition()).thenReturn(shooterPos);
        Mockito.when(target.getPosition()).thenReturn(targetPos);

        double angle = 2.6160088600381832;

        // when
        LaserBeam result = creator.create();

        //then

        Mockito.verify(creator).ajustPosition(angle, shooter);
        assertEquals(position, result.getPosition());
        assertEquals(angle, result.getAngle());
        assertEquals(3, result.getWidth());
        assertEquals(3, result.getHeight());
        assertFalse(result.isPlayerBeam());
    }

    @Test
    public void createNegVel() {

        // given
        Position position = Mockito.mock(Position.class);
        Mockito.doReturn(position).when(creator).ajustPosition(Mockito.anyDouble(), Mockito.any());

        Position targetPos = Mockito.mock(Position.class);
        Mockito.when(targetPos.getX()).thenReturn(10.0);
        Mockito.when(targetPos.getY()).thenReturn(30.0);

        Position shooterPos = Mockito.mock(Position.class);
        Mockito.when(shooterPos.getX()).thenReturn(60.0);
        Mockito.when(shooterPos.getY()).thenReturn(92.0);

        Mockito.when(shooter.getPosition()).thenReturn(shooterPos);
        Mockito.when(target.getPosition()).thenReturn(targetPos);

        double angle = 4.033726489636377;

        // when
        LaserBeam result = creator.create();

        //then

        Mockito.verify(creator).ajustPosition(angle, shooter);
        assertEquals(position, result.getPosition());
        assertEquals(angle, result.getAngle());
        assertEquals(3, result.getWidth());
        assertEquals(3, result.getHeight());
        assertFalse(result.isPlayerBeam());
    }

    /*
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

        TargetLaserBeamCreator enemyLaserBeamCreator = new TargetLaserBeamCreator(playerMock, enemyShipMock, entities);

        //when
        LaserBeam laserBeam = enemyLaserBeamCreator.create();
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
        //assertEquals(playerMock, enemyLaserBeamCreator.());
        //assertEquals(enemyShipMock, enemyLaserBeamCreator.getEnemyShip());
    }

    @Test
    void createNullVelocity() {
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

        TargetLaserBeamCreator enemyLaserBeamCreator = new TargetLaserBeamCreator(playerMock, enemyShipMock, entities);

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
    }*/
}
