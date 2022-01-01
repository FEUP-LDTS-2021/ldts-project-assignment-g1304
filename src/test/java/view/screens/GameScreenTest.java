package view.screens;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Constraints;
import model.Entities.Player;
import model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.Game.LaserView;
import view.Game.PlayerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreenTest extends Assertions {

    GameModel model;
    GameScreen screen;
    List<LaserView> laserBeams;
    PlayerView playerView;
    Player player;

    @BeforeEach
    void init() throws IOException {
        model = Mockito.mock(GameModel.class);
        player = Mockito.mock(Player.class);
        screen = Mockito.spy(new GameScreen(model));

        Mockito.doNothing().when(screen).clear();
        Mockito.doNothing().when(screen).refresh();

        Mockito.doReturn(Mockito.mock(Screen.class)).when(screen).getScreen();
        Mockito.when(model.getPlayer()).thenReturn(player);

        playerView = Mockito.mock(PlayerView.class);
        Mockito.doReturn(playerView).when(screen).getPlayerView();

        laserBeams = new ArrayList<>();
    }

    @Test
    void testCreatePlayerView(){
        // given
        Mockito.when(model.getPlayer()).thenReturn(player);
        GameScreen gameScreen = new GameScreen(model);

        // when
        PlayerView playerView = gameScreen.getPlayerView();

        // then
        assertEquals(player, playerView.getPlayer());
    }
/*
    @Test
    void testCreateAsteroidsView(){
        //given
        List<MovingObject> asteroids = List.of(Mockito.mock(Asteroid.class), Mockito.mock(Asteroid.class), Mockito.mock(Asteroid.class));
        Mockito.when(model.getAsteroids()).thenReturn(asteroids);

        // when
        GameScreen gameScreen = new GameScreen(model);
        List<AsteroidView> asteroidViews = gameScreen.getAsteroidsView();

        //then
        for (var asteroidView : asteroidViews)
            assertTrue(asteroids.contains(asteroidView.getAsteroid()));

        for (var asteroid : asteroids){
            boolean found = false;
            for (var asteroidView : asteroidViews) {
                if(asteroidView.getAsteroid() == asteroid){
                    found=true;
                    break;
                }
            }

            assertTrue(found);
        }

    }


    @Test
    void testGetLaserViews(){
        //given
        List<LaserView> lasers = List.of(Mockito.mock(LaserView.class), Mockito.mock(LaserView.class), Mockito.mock(LaserView.class));
        Mockito.when(model.getLaserCreator().getLaserBeamList()).thenReturn(lasers);

        // when
        List<LaserView> laserViews = screen.getLaserViews();

        //then
        for (var laserView : laserViews)
            assertTrue(lasers.contains(laserView.getLaserBeam()));

        for (var laser : lasers){
            boolean found = false;
            for (var laserView : laserViews) {
                if(laserView.getLaserBeam() == laser){
                    found=true;
                    break;
                }
            }

            assertTrue(found);
        }

    }*/

    @Test
    void getSize(){
        // when
        TerminalSize size = screen.getSize();

        //then
        assertEquals(size, new TerminalSize(Constraints.WIDTH, Constraints.HEIGHT));
    }
/*
    @Test
    void draw() throws IOException {
        // given
        Mockito.doReturn(laserBeams).when(screen).getLaserViews();

        //when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).clear();

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void drawPlayer() throws IOException {
        // given
        Mockito.doReturn(laserBeams).when(screen).getLaserViews();

        //when
        screen.draw();

        // then
        Mockito.verify(playerView, Mockito.times(1)).draw();
    }

    @Test
    void drawLaserBeam() throws IOException {
        //given
        laserBeams.addAll(List.of(Mockito.mock(LaserView.class), Mockito.mock(LaserView.class), Mockito.mock(LaserView.class)));
        Mockito.doReturn(laserBeams).when(screen).getLaserViews();
        //when
        screen.draw();

        // then
        for(var laser: laserBeams) {
            Mockito.verify(laser, Mockito.times(1)).draw();
        }
    }

    @Test
    void drawAstroids() throws IOException {
        //given
        List<LaserView> asteroids = List.of(Mockito.mock(LaserView.class), Mockito.mock(LaserView.class),
                                        Mockito.mock(LaserView.class));
        Mockito.doReturn(asteroids).when(screen).getLaserViews();
        //when
        screen.draw();

        // then
        for(var asteroid: asteroids) {
            Mockito.verify(asteroid, Mockito.times(1)).draw();
        }
    }*/

    @Test
    void setGraphics(){
        // given
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        // when

        screen.setGraphics(graphics);

        //then
        assertEquals(screen.getGraphics(), graphics);
        Mockito.verify(playerView, Mockito.times(1)).setGraphics(graphics);
    }

}
