package asteroids.model.Creator;

import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class LaserBeamCreatorTest extends Assertions {
    LaserBeamCreator creator;
    List<MovingObject> entities;

    @BeforeEach
    void init() {
        entities = Mockito.mock(List.class);
        creator = new LaserBeamCreator(entities) {
            @Override
            public MovingObject create() {
                return null;
            }
        };

    }

    @Test
    void getEntities() {

        // when
        List<MovingObject> returned = creator.getEntities();

        // then
        assertEquals(returned, entities);
    }

    @Test
    void add() {

        // given
        MovingObject object = Mockito.mock(MovingObject.class);

        // when
        creator.addLaserBeam(object);

        // then
        Mockito.verify(entities, Mockito.times(1)).add(object);
    }

    @Test
    public void ajustPosition() {
        // given
        MovingObject shooter = Mockito.mock(MovingObject.class);
        Mockito.when(shooter.getWidth()).thenReturn(10.0);
        Mockito.when(shooter.getHeight()).thenReturn(15.0);

        Position shooterPosition = Mockito.mock(Position.class);
        Position pClone = Mockito.mock(Position.class);
        Mockito.when(shooter.getPosition()).thenReturn(shooterPosition);
        Mockito.when(shooterPosition.clone()).thenReturn(pClone);


        Mockito.when(pClone.getX()).thenReturn(6.0);
        Mockito.when(pClone.getY()).thenReturn(7.0);

        double angle = 10;
        int laserWidth = 3;
        int laserHeight = 3;

        double x = 6.0 + Math.cos(angle) * (shooter.getWidth() + laserWidth + 1) + shooter.getWidth() / 2;
        double y = 7.0 + Math.sin(angle) * (shooter.getHeight() + laserHeight + 1) + shooter.getHeight() / 2;

        // when
        Position result = creator.ajustPosition(angle, shooter);
        // then
        assertEquals(pClone, result);
        Mockito.verify(pClone).setX(x);
        Mockito.verify(pClone).setY(y);
    }
}
    /*


    @Test
    void createLaserBeam() {
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(positionMock);
        Mockito.when(playerMock.getAngle()).thenReturn(40.0);
        Mockito.when(playerMock.getRaio()).thenReturn(5.0);

        List<MovingObject> entities = List.of(playerMock);

        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock, entities);

        double x = Math.cos(playerMock.getAngle())* (playerMock.getRaio()+10) + playerMock.getPosition().getX();
        double y = Math.sin(playerMock.getAngle())* (playerMock.getRaio()+10) + playerMock.getPosition().getY();

        //when
        LaserBeam laserBeam = laserBeamCreator.create();

        //then
        assertEquals((int)(x-laserBeam.getWidth()/2), laserBeam.getPosition().getX());
        assertEquals((int)(y-laserBeam.getHeight()/2), laserBeam.getPosition().getY());
        assertTrue(laserBeam.isPlayerBeam());

    }*/