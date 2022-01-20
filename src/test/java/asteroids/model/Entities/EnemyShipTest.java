package asteroids.model.Entities;

import asteroids.control.MusicManager;
import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
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
        Mockito.when(positionMock.getY()).thenReturn(120.0);

        velocityMock = Mockito.mock(Vector2d.class);
        Mockito.when(velocityMock.getX()).thenReturn(1.0);
        Mockito.when(velocityMock.getY()).thenReturn(1.0);

        width = 3;
        height = 3;

        enemyShip = new EnemyShip(positionMock, velocityMock, width, height);
    }

    @Test
    void isShootingTime() {

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //when
            boolean result1 = enemyShip.isShootingTime(999);
            boolean result2 = enemyShip.isShootingTime(2);

            //then
            assertTrue(result2);
            assertFalse(result1);
            assertEquals(1, enemyShip.getLastTime());
        }
    }

    @Test
    void shooting(){

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            //given
            LaserBeam laserBeam = new LaserBeam(new Position(100.0, 100.0), 0.69, 1.0, 1.0);
            LaserBeamCreator laserBeamCreatorMock = Mockito.mock(LaserBeamCreator.class);
            Mockito.when(laserBeamCreatorMock.create()).thenReturn(laserBeam);

            //when
            enemyShip.setLaserBeamCreator(laserBeamCreatorMock);
            enemyShip.shooting(1001);

            //then
            Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).addLaserBeam(laserBeam);
            Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).create();
        }
    }
    @Test
    void update(){
        //given

        MusicManager manager= Mockito.mock(MusicManager.class);
        try(MockedStatic<MusicManager > configurationMockedStatic=Mockito.mockStatic(MusicManager.class)) {
            configurationMockedStatic.when(MusicManager::getInstance).thenReturn(manager);

            LaserBeamCreator laserBeamCreatorMock = Mockito.mock(LaserBeamCreator.class);
            EnemyShip enemyShip1 = Mockito.spy(enemyShip);

            //when
            enemyShip1.setLaserBeamCreator(laserBeamCreatorMock);
            enemyShip1.update(1001);

            //then
            Mockito.verify(enemyShip1, Mockito.times(1)).shooting(1001);
        }
    }

    @Test
    void getPoints(){

        // when
        int points = enemyShip.getPoints();

        // then
        assertEquals(50, points);
    }

    @Test
    void getCollider() {
        // given
        int[] pointsListX = new int []{16, 22, 30, 38, 30, 8, 0, 8, 12};
        int[] pointsListY = new int []{0, 0, 12, 18, 24, 24, 18, 12, 2};
        enemyShip.setSize(Sizes.MEDIUM);

        // when
        Polygon returned = enemyShip.getCollider();

        // then

        assertEquals(returned.npoints, 9);
        for(int i = 0 ; i<returned.npoints; i++){
            assertEquals(100+pointsListX[i], returned.xpoints[i]);
            assertEquals(120+pointsListY[i], returned.ypoints[i]);
        }
    }

    @Test
    void getSize() {
        enemyShip.setSize(Sizes.MEDIUM);
        assertNotNull(enemyShip.getSize());
    }
}
