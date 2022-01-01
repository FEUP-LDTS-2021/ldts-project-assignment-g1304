package model.Entities;

import model.Position;
import model.physics.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import utils.DoubleComparables;


public class PlayerTest extends Assertions {

    @Test
    void createPlayer(){
        // given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10.0);
        Mockito.when(positionMock.getY()).thenReturn(20.0);

        Player p = new Player(positionMock);

        // when
        Position position = p.getPosition();

        // then
        assertEquals(position.getX(), positionMock.getX());
        assertEquals(position.getY(), positionMock.getY());
        assertEquals(p.getRaio(), Player.raio);
    }

    @Test
    void angle(){
        Player player = new Player(new Position(10, 10));

        assertEquals(0, player.getAngle());

        player.setAngle(10);
        assertEquals(10, player.getAngle());
    }


    @Test
    void changeDirectionWithTime(){
        Player player = new Player(new Position(10, 10));
        player.rotateRight();
        player.update(500);
        assertTrue( DoubleComparables.equalDouble(Player.angularVelocity/2, player.getAngle()));

        player.rotateLeft();
        player.update(500);
        assertTrue( DoubleComparables.equalDouble(0.0, player.getAngle()));

        player.rotateLeft();
        player.update(500);
        assertTrue( DoubleComparables.equalDouble(-Player.angularVelocity/2, player.getAngle()));
    }


    @Test
    void updateWithoutAceleration(){
        //given
        Player player = new Player(new Position(10, 10));

        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);

        //when
        player.update(1000);

        //then

        Mockito.verify(velocity, Mockito.never()).addX(Mockito.anyDouble());
        Mockito.verify(velocity, Mockito.never()).addY(Mockito.anyDouble());

        assertFalse(player.isAcelerate());
    }

    @Test
    void updateWithMaxVelocity(){
        //given
        Player player = new Player(new Position(10, 10));
        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);


        //when
        Mockito.when(velocity.module()).thenReturn(Player.MAX_VELOCITY);
        player.acelerate();
        player.update(1000);

        //then
        Mockito.verify(velocity, Mockito.times(0)).resize(Mockito.anyDouble());
    }

    @Test
    void updateaGreaterThanMaxVelocity(){
        //given
        Player player = new Player(new Position(10, 10));
        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);


        //when
        Mockito.when(velocity.module()).thenReturn(Player.MAX_VELOCITY+1);
        player.acelerate();
        player.update(1000);

        //then
        Mockito.verify(velocity, Mockito.times(1)).resize(Player.MAX_VELOCITY);
    }

    @Test
    void updateWithAceleration(){
        //given
        Player player = new Player(new Position(10, 10));

        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], Player.acelaration));
            return null;
        }).when(velocity).addX(Mockito.anyDouble());

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], 0));
            return null;
        }).when(velocity).addY(Mockito.anyDouble());


        //when
        player.acelerate();
        player.update(1000);

        //then

        Mockito.verify(velocity, Mockito.times(1)).addX(Mockito.anyDouble());
        Mockito.verify(velocity, Mockito.times(1)).addY(Mockito.anyDouble());

        assertFalse(player.isAcelerate());
    }


    @Test
    void updateWithAcelerationWithAngle(){
        //given
        Player player = new Player(new Position(10, 10));
        double angle = Math.PI/2;
        player.setAngle(angle);

        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], Player.acelaration*Math.cos(angle)));
            return null;
        }).when(velocity).addX(Mockito.anyDouble());

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], Player.acelaration*Math.sin(angle)));
            return null;
        }).when(velocity).addY(Mockito.anyDouble());


        //when
        player.acelerate();
        player.update(1000);

        //then

        Mockito.verify(velocity, Mockito.times(1)).addX(Mockito.anyDouble());
        Mockito.verify(velocity, Mockito.times(1)).addY(Mockito.anyDouble());

        assertFalse(player.isAcelerate());
    }
    @Test
    void updateShot() {
        //given
        Player player = new Player(new Position(10, 10));
        LaserBeamCreator laserBeamCreatorMock = Mockito.mock(LaserBeamCreator.class);
        player.setLaserBeamCreator(laserBeamCreatorMock);

        //when
        player.addLaserBeams();
        player.update(10);

        //then
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).addLaserBeam(Mockito.any());
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).create();
    }
}
