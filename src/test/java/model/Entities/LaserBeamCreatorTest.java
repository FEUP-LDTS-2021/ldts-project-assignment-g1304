package model.Entities;

import model.Position;
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

        int x = (int) (Math.cos(playerMock.getAngle())* playerMock.getRaio() + playerMock.getPosition().getX());
        int y = (int) (Math.sin(playerMock.getAngle())* playerMock.getRaio() + playerMock.getPosition().getY());

        //when
        LaserBeam laserBeam = laserBeamCreator.createLaserBeam();

        //then
        assertEquals(x, laserBeam.getPosition().getX());
        assertEquals(y, laserBeam.getPosition().getY());

    }
}
