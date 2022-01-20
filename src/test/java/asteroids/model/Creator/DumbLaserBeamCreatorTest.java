package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class DumbLaserBeamCreatorTest extends Assertions {
    DumbLaserBeamCreator creator;
    MovingObject target;
    MovingObject shooter;
    List<MovingObject> entities;

    @BeforeEach
    void init(){
        target = Mockito.mock(MovingObject.class);
        shooter = Mockito.mock(MovingObject.class);

        entities = Mockito.mock(List.class);
        creator = Mockito.spy(new DumbLaserBeamCreator(shooter, entities));
    }

    @Test
    void createCreator(){
        // then
        assertEquals(shooter, creator.getShooter());
        assertEquals(entities, creator.getEntities());
    }

    @Test
    public void create() {
        // given
        Position position = Mockito.mock(Position.class);
        Mockito.doReturn(position).when(creator).ajustPosition(Mockito.anyDouble(), Mockito.any());

        List<Double> directions = List.of(Math.PI / 2.0, -Math.PI / 2.0, 0.0,
                Math.PI, Math.PI / 4.0, -Math.PI / 4.0, Math.PI * 3.0 / 4.0, -Math.PI * 3.0 / 4.0);

        for(int repeticao = 1 ; repeticao <= 2; repeticao++) {
            for (Double angle : directions) {
                // when
                LaserBeam result = creator.create();

                // then
                Mockito.verify(creator, Mockito.times(repeticao)).ajustPosition(angle, shooter);

                assertEquals(position, result.getPosition());
                assertEquals(angle, result.getAngle());
                assertEquals(3, result.getWidth());
                assertEquals(3, result.getHeight());
                assertFalse(result.isPlayerBeam());
            }
        }
    }
}
