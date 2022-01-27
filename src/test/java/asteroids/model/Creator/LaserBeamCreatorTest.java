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

        double distance = Math.sqrt(shooter.getWidth()*shooter.getWidth() + shooter.getHeight()*shooter.getHeight());
        double x = 6.0 + Math.cos(angle) * (distance/2 + laserWidth + 1);
        double y = 7.0 + Math.sin(angle) * (distance/2 + laserHeight + 1);

        // when
        Position result = creator.ajustPosition(angle, shooter);
        // then
        assertEquals(pClone, result);
        Mockito.verify(pClone).setX(x);
        Mockito.verify(pClone).setY(y);
    }
}