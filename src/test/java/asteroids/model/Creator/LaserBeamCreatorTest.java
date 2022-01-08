package asteroids.model.Entities;

import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LaserBeamCreatorTest extends Assertions {

    @Test
    void createLaserBeamCreator() {
        //given
        Player playerMock = Mockito.mock(Player.class);
        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock);

        //when
        Player player2 = laserBeamCreator.getPlayer();

        //then
        assertEquals(playerMock, player2);
    }

    @Test
    void createLaserBeam() {
        //given
        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(playerMock.getPosition()).thenReturn(new Position(10.0, 20.0));
        Mockito.when(playerMock.getAngle()).thenReturn(40.0);
        Mockito.when(playerMock.getRaio()).thenReturn(5.0);
        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock);

        double x = Math.cos(playerMock.getAngle())* (playerMock.getRaio()+5) + playerMock.getPosition().getX();
        double y = Math.sin(playerMock.getAngle())* (playerMock.getRaio()+5) + playerMock.getPosition().getY();

        //when
        LaserBeam laserBeam = laserBeamCreator.create();

        //then
        assertEquals((int)(x-laserBeam.getWidth()/2), laserBeam.getPosition().getX());
        assertEquals((int)(y-laserBeam.getHeight()/2), laserBeam.getPosition().getY());

    }

    @Test
    void addLaserBeam() {
        //given
        Player playerMock = Mockito.mock(Player.class);
        LaserBeamCreator laserBeamCreator = new LaserBeamCreator(playerMock);
        LaserBeam laserBeamMock = Mockito.mock(LaserBeam.class);

        //when
        laserBeamCreator.addLaserBeam(laserBeamMock);

        //then
        for (LaserBeam l : laserBeamCreator.getLaserBeamList()) {
            assertEquals(l, laserBeamMock);
        }
    }
}
