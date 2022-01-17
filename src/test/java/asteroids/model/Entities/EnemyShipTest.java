package asteroids.model.Entities;

import asteroids.model.Creator.EnemyLaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

public class EnemyShipTest extends Assertions {

    Position positionMock;
    Vector2d velocityMock;
    double width;
    double height;
    EnemyShip enemyShip;

    @BeforeEach
    void init() {
        positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(100.0);
        Mockito.when(positionMock.getY()).thenReturn(100.0);

        velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(1.0);
        Mockito.when(velocityMock.getY()).thenReturn(1.0);

        width = 3;
        height = 3;

        enemyShip = new EnemyShip(positionMock, velocityMock, width, height);
    }

    @Test
    void isShootingTime() {

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
        LaserBeam laserBeam = new LaserBeam(new Position(100.0, 100.0), 0.69, 1.0, 1.0);
        EnemyLaserBeamCreator laserBeamCreatorMock = Mockito.mock(EnemyLaserBeamCreator.class);
        Mockito.when(laserBeamCreatorMock.create()).thenReturn(laserBeam);

        //when
        enemyShip.setLaserBeamCreator(laserBeamCreatorMock);
        enemyShip.shooting(1001);

        //then
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).addLaserBeam(laserBeam);
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).create();
    }
    @Test
    void update(){
        //given
        EnemyLaserBeamCreator laserBeamCreatorMock = Mockito.mock(EnemyLaserBeamCreator.class);
        EnemyShip enemyShip1 = Mockito.spy(enemyShip);

        //when
        enemyShip1.setLaserBeamCreator(laserBeamCreatorMock);
        enemyShip1.update(1001);

        //then
        Mockito.verify(enemyShip1,Mockito.times(1)).shooting(1001);
    }

    @Test
    void getPoints(){

        // when
        int points = enemyShip.getPoints();

        // then
        assertEquals(50, enemyShip.getPoints());
    }

    @Test
    void collider(){
        // Given
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10.0);
        Mockito.when(position.getY()).thenReturn(15.0);
        EnemyShip enemyShip = new EnemyShip(position, Mockito.mock(Vector2d.class), 20, 30);

        Polygon polygon = new Polygon();
        double x = 10;
        double y = 15;

        polygon.addPoint((int)x, (int)y);
        polygon.addPoint((int)x , (int)(y + 30));
        polygon.addPoint((int)(x + 20), (int)(y + 30));
        polygon.addPoint((int)(x + 20), (int)y);


        // when
        Polygon enemyShipCollider = enemyShip.getCollider();

        // then
        assertEquals(polygon.npoints, enemyShipCollider.npoints);
        assertArrayEquals(polygon.xpoints, enemyShipCollider.xpoints);
        assertArrayEquals(polygon.ypoints, enemyShipCollider.ypoints);
    }
}
