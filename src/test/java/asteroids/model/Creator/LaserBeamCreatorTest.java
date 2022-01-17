package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class LaserBeamCreatorTest extends Assertions {

    @Test
    void createLaserBeamCreator() {
        //given
        Player playerMock = Mockito.mock(Player.class);
        List<MovingObject> entities = List.of(playerMock);
        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock, entities);

        //when
        Player player2 = laserBeamCreator.getPlayer();

        //then
        assertEquals(playerMock, player2);
        assertEquals(entities, laserBeamCreator.getEntities());
    }

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

    }

    @Test
    void addLaserBeam() {
        //given
        Player playerMock = Mockito.mock(Player.class);
        List<MovingObject> entities = new ArrayList<>(List.of(playerMock));
        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock, entities);
        LaserBeam laserBeamMock = Mockito.mock(LaserBeam.class);

        //when
        laserBeamCreator.addLaserBeam(laserBeamMock);

        //then
        for (MovingObject movingObject : laserBeamCreator.getEntities()) {
            if (movingObject instanceof LaserBeam)
                assertEquals(movingObject, laserBeamMock);
        }
    }
}
