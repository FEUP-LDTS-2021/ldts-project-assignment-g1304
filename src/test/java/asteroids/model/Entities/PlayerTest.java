package asteroids.model.Entities;

import asteroids.control.Rotation;
import asteroids.model.Creator.LaserBeamCreator;
import asteroids.model.Position;
import asteroids.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import asteroids.utils.DoubleComparables;

import java.awt.*;

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

    @Test
    void addScore(){
        // given
        Player player = Mockito.spy(new Player(Mockito.mock(Position.class)));

        // when
        int score1 = player.getScore();
        int scoreLife1 = player.getScoreLife();
        player.addScore(100);
        int score2 = player.getScore();
        int scoreLife2 = player.getScoreLife();
        player.addScore(50);
        int score3 = player.getScore();
        int scoreLife3 = player.getScoreLife();
        player.addScore(99841);

        // then
        assertEquals(0, score1);
        assertEquals(0,scoreLife1);
        assertEquals(100, score2);
        assertEquals(100,scoreLife2);
        assertEquals(150, score3);
        assertEquals(150,scoreLife3);
        assertEquals(99990,player.getScore());
        Mockito.verify(player,Mockito.times(3)).extraLife();
    }
    @Test
    void extraLife(){
        // given
        Player player = new Player(Mockito.mock(Position.class));

        //when
        player.addScore(10001);
        player.extraLife();
        int scoreLife1 = player.getScoreLife();
        player.addScore(9999);
        player.extraLife();
        int scoreLife2 = player.getScoreLife();

        //then
        assertEquals(5,player.getLives());
        assertEquals(1,scoreLife1);
        assertEquals(0,scoreLife2);
    }
    @Test
    void dies(){
        // given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.doReturn(positionMock).when(positionMock).clone();
        Player p = Mockito.spy(new Player(positionMock));

        // when
        int lives0 = p.getLives();
        boolean isalive0 = p.isAlive();

        p.dies();
        int lives1 = p.getLives();
        boolean isalive1 = p.isAlive();

        p.dies();
        int lives2 = p.getLives();
        boolean isalive2 = p.isAlive();

        p.dies();
        int lives3 = p.getLives();
        boolean isalive3 = p.isAlive();

        // then
        assertEquals(3, lives0);
        assertTrue(isalive0);

        assertEquals(2, lives1);
        assertTrue(isalive1);

        assertEquals(1, lives2);
        assertTrue(isalive2);

        assertEquals(0, lives3);
        assertFalse(isalive3);

        // go to begin position
        Mockito.verify(p, Mockito.atLeast(3)).setPosition(positionMock);
    }

    @Test
    void collider(){
        // Given
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(10.0);
        Mockito.when(position.getY()).thenReturn(15.0);
        Player player = new Player(position);
        player.setAngle(15);

        Polygon polygon = new Polygon();
        double anglePontaNave = 15;
        double anglePontaEsq = anglePontaNave + Math.PI*0.8333;   // 5/6
        double anglePontaDir = anglePontaNave + Math.PI*1.1666;   // 7/6
        polygon.addPoint((int) ((Math.cos(anglePontaNave)+1)*Player.raio + 10),
                (int)((Math.sin(anglePontaNave)+1)*Player.raio + 15));
        polygon.addPoint((int) ((Math.cos(anglePontaEsq)+1)*Player.raio + 10),
                (int) ((Math.sin(anglePontaEsq)+1)*Player.raio + 15));
        polygon.addPoint((int) ((Math.cos(anglePontaDir)+1)*Player.raio + 10),
                (int) ((Math.sin(anglePontaDir)+1)*Player.raio + 15));


        // when
        Polygon playerCollider = player.getCollider();

        // then
        assertEquals(polygon.npoints, playerCollider.npoints);
        assertArrayEquals(polygon.xpoints, playerCollider.xpoints);
        assertArrayEquals(polygon.ypoints, playerCollider.ypoints);

    }
}
