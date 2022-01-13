package asteroids.model.Entities;

import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LaserBeamTest extends Assertions {

    @Test
    void createLaserBeam() {
        //given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        LaserBeam l = new LaserBeam(positionMock, 30.0, 3, 3);

        //when
        Position position = l.getPosition();

        //then
        assertEquals(position.getX(), positionMock.getX());
        assertEquals(position.getY(), positionMock.getY());
        assertEquals(l.getAngle(), 30.0);
    }

    @Test
    void update() {
        LaserBeam l1 = new LaserBeam(new Position(10.0, 10.0), 40.0, 3, 3);
        LaserBeam l2 = new LaserBeam(
                            new Position(
                                     10.0 + LaserBeam.VELOCITY*Math.cos(40.0)*10/1000,
                                     10.0 + LaserBeam.VELOCITY*Math.sin(40.0)*10/1000),
                         40.0 , 3, 3);
        l1.update(10);
        l1.update(10);
        assertEquals(l1.getPosition().getX(), l2.getPosition().getX());
        assertEquals(l1.getPosition().getY(), l2.getPosition().getY());
    }

    @Test
    void isLaserBeam(){
        // given
        LaserBeam l1 = new LaserBeam(Mockito.mock(Position.class), 40.0, 3, 3);

        // when
        boolean is1 = l1.isPlayerBeam();
        l1.setPlayerBeam(true);
        boolean is2 = l1.isPlayerBeam();

        // then
        assertFalse(is1);
        assertTrue(is2);

    }
}
