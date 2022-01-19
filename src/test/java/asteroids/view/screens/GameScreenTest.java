package asteroids.view.screens;

import asteroids.model.Entities.*;
import asteroids.view.Game.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import asteroids.Constants;
import asteroids.model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreenTest extends Assertions {

    GameModel model;
    GameScreen screen;
    List<MovingObject> entities;
    TextGraphics graphics;
    Hud hud;

    @BeforeEach
    void init() throws IOException {
        model = Mockito.mock(GameModel.class);
        hud = Mockito.mock(Hud.class);
        screen = Mockito.spy(new GameScreen(model, hud));
        entities = new ArrayList<>();
        graphics = Mockito.mock(TextGraphics.class);

        screen.setGraphics(graphics);

        Mockito.doNothing().when(screen).clear();
        Mockito.doNothing().when(screen).refresh();
        Mockito.when(model.getEntities()).thenReturn(entities);

    }

    @Test
    void drawClearandRefresh() throws IOException {
        // when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void drawPlayer() throws IOException {
        // given
        Player player = Mockito.mock(Player.class);
        PlayerView view = Mockito.mock(PlayerView.class);
        entities.add(player);
        Mockito.when(screen.getView(player)).thenReturn(view);

        // when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).getView(player);
        Mockito.verify(view, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view, Mockito.times(1)).draw();
    }

    @Test
    void drawAsteroids() throws IOException {
        // given
        Asteroid asteroid1 = Mockito.mock(Asteroid.class);
        Mockito.when(asteroid1.getAsteroidSize()).thenReturn(AsteroidSizes.LARGE);
        AsteroidView view1 = Mockito.mock(AsteroidView.class);
        entities.add(asteroid1);
        Mockito.doReturn(view1).when(screen).getView(asteroid1);
        Mockito.when(screen.getView(asteroid1)).thenReturn(view1);
        Asteroid asteroid2 = Mockito.mock(Asteroid.class);
        Mockito.when(asteroid2.getAsteroidSize()).thenReturn(AsteroidSizes.LARGE);
        AsteroidView view2 = Mockito.mock(AsteroidView.class);
        entities.add(asteroid2);
        Mockito.doReturn(view2).when(screen).getView(asteroid2);
        Mockito.when(screen.getView(asteroid2)).thenReturn(view2);

        // when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).getView(asteroid1);
        Mockito.verify(view1, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view1, Mockito.times(1)).draw();

        Mockito.verify(screen, Mockito.times(1)).getView(asteroid2);
        Mockito.verify(view2, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view2, Mockito.times(1)).draw();
    }

    @Test
    void drawLaseBeam() throws IOException {
        // given
        LaserBeam laserBeam1 = Mockito.mock(LaserBeam.class);
        LaserView view1 = Mockito.mock(LaserView.class);
        entities.add(laserBeam1);
        Mockito.when(screen.getView(laserBeam1)).thenReturn(view1);

        LaserBeam laserBeam2 = Mockito.mock(LaserBeam.class);
        LaserView view2 = Mockito.mock(LaserView.class);
        entities.add(laserBeam2);
        Mockito.when(screen.getView(laserBeam2)).thenReturn(view2);

        // when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).getView(laserBeam1);
        Mockito.verify(view1, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view1, Mockito.times(1)).draw();

        Mockito.verify(screen, Mockito.times(1)).getView(laserBeam2);
        Mockito.verify(view2, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view2, Mockito.times(1)).draw();
    }

    @Test
    void drawEnemyShip() throws IOException {
        // given
        EnemyShip enemyShip1 = Mockito.mock(EnemyShip.class);
        EnemyShipView view1 = Mockito.mock(EnemyShipView.class);
        entities.add(enemyShip1);
        Mockito.when(screen.getView(enemyShip1)).thenReturn(view1);

        EnemyShip enemyShip2 = Mockito.mock(EnemyShip.class);
        EnemyShipView view2 = Mockito.mock(EnemyShipView.class);
        entities.add(enemyShip2);
        Mockito.when(screen.getView(enemyShip2)).thenReturn(view2);

        // when
        screen.draw();

        // then
        Mockito.verify(screen, Mockito.times(1)).getView(enemyShip2);
        Mockito.verify(view1, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view1, Mockito.times(1)).draw();

        Mockito.verify(screen, Mockito.times(1)).getView(enemyShip2);
        Mockito.verify(view2, Mockito.times(1)).setGraphics(graphics);
        Mockito.verify(view2, Mockito.times(1)).draw();
    }

    @Test
    void drawHud() throws IOException {

        // when
        screen.draw();

        // then
        Mockito.verify(hud, Mockito.times(1)).draw();
    }

    @Test
    void getModel(){
        // when
        GameModel modelReturned = screen.getModel();

        // then
        assertEquals(model, modelReturned);
    }

    @Test
    void getPlayerView(){
        // given
        Player player = Mockito.mock(Player.class);

        // when
        View view = screen.getView(player);

        // then
        assertTrue(view instanceof PlayerView);
        assertEquals(player, ((PlayerView) view).getPlayer());
    }

    @Test
    void getAsteroidView(){
        // given
        Asteroid asteroid = Mockito.mock(Asteroid.class);
        Mockito.when(asteroid.getAsteroidSize()).thenReturn(AsteroidSizes.LARGE);
        //Mockito.when(asteroid.getAsteroidSize().getSize()).thenReturn(3);

        // when
        View view = screen.getView(asteroid);

        // then
        assertTrue(view instanceof AsteroidView);
        assertEquals(asteroid, ((AsteroidView) view).getAsteroid());
    }

    @Test
    void getLaserView(){
        // given
        LaserBeam laserBeam = Mockito.mock(LaserBeam.class);

        // when
        View view = screen.getView(laserBeam);

        // then
        assertTrue(view instanceof LaserView);
        assertEquals(laserBeam, ((LaserView) view).getLaserBeam());
    }


    @Test
    void getEnemyShipsView(){
        // given
        EnemyShip enemyShip = Mockito.mock(EnemyShip.class);

        // when
        View view = screen.getView(enemyShip);

        // then
        assertTrue(view instanceof EnemyShipView);
        assertEquals(enemyShip, ((EnemyShipView) view).getEnemyShip());
    }


    @Test
    void getSize(){
        // when
        TerminalSize size = screen.getSize();

        //then
        assertEquals(size, new TerminalSize(Constants.WIDTH, Constants.HEIGHT));
    }

    @Test
    void setGraphics(){
        // given
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        // when
        screen.setGraphics(graphics);

        //then
        assertEquals(screen.getGraphics(), graphics);
        Mockito.verify(hud, Mockito.times(1)).setGraphics(graphics);
    }

}
