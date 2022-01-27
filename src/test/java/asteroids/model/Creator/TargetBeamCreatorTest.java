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
}
