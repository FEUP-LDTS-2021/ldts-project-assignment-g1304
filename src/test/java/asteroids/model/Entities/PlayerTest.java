package asteroids.model.Entities;

import asteroids.control.Rotation;
import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import asteroids.utils.DoubleComparables;


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
        player.setRotation(Rotation.Right);
        player.update(500);
        assertTrue( DoubleComparables.equalDouble(Player.angularVelocity/2, player.getAngle()));

        player.setRotation(Rotation.Left);
        player.update(500);
        assertTrue( DoubleComparables.equalDouble(0.0, player.getAngle()));

        player.setRotation(Rotation.Left);
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
    }

    @Test
    void updateWithMaxVelocity(){
        //given
        Player player = new Player(new Position(10, 10));
        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);


        //when
        Mockito.when(velocity.module()).thenReturn(Player.MAX_VELOCITY);
        player.setAcelerate(true);
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
        player.setAcelerate(true);
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

        Mockito.doNothing().when(velocity).addX(Mockito.anyDouble());

        Mockito.doNothing().when(velocity).addY(Mockito.anyDouble());


        //when
        player.setAcelerate(true);
        player.update(1000);

        //then

        Mockito.verify(velocity, Mockito.times(1)).addX(Player.acelaration);
        Mockito.verify(velocity, Mockito.times(1)).addY(0);
    }


    @Test
    void updateWithAcelerationWithAngle(){
        //given
        Player player = new Player(new Position(10, 10));
        double angle = Math.PI/2;
        player.setAngle(angle);

        Vector2d velocity = Mockito.mock(Vector2d.class);
        player.setVelocity(velocity);

        Mockito.doNothing().when(velocity).addX(Mockito.anyDouble());

        Mockito.doNothing().when(velocity).addY(Mockito.anyDouble());


        //when
        player.setAcelerate(true);
        player.update(1000);

        //then

        Mockito.verify(velocity, Mockito.times(1)).addX(Player.acelaration*Math.cos(angle));
        Mockito.verify(velocity, Mockito.times(1)).addY(Player.acelaration*Math.sin(angle));
    }
    @Test
    void updateShot() {
        //given
        Player player = new Player(new Position(10, 10));
        LaserBeamCreator laserBeamCreatorMock = Mockito.mock(LaserBeamCreator.class);
        player.setLaserBeamCreator(laserBeamCreatorMock);

        //when
        player.setShoot(true);
        player.update(10);

        //then
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).addLaserBeam(Mockito.any());
        Mockito.verify(laserBeamCreatorMock, Mockito.times(1)).create();
    }

    @Test
    void rotate() {
        //given
        Position position = Mockito.mock(Position.class);
        Player player = new Player(position);

        //when
        Rotation r1 = player.getRotation();

        player.setRotation(Rotation.Left);
        Rotation r2 = player.getRotation();


        player.setRotation(Rotation.Right);
        Rotation r3 = player.getRotation();


        player.setRotation(Rotation.None);
        Rotation r4 = player.getRotation();

        //then
        assertEquals(Rotation.None, r1);
        assertEquals(Rotation.Left, r2);
        assertEquals(Rotation.Right, r3);
        assertEquals(Rotation.None, r4);
    }
}
