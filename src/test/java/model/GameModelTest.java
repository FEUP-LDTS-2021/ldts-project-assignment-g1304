package model;

import model.Entities.Asteroid;
import model.Entities.LaserBeam;
import model.Entities.MovingObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GameModelTest extends Assertions {

    @Test
    void updateEntities() {
        GameModel gameModel = new GameModel();
        gameModel.updateEntities();
        assertEquals(gameModel.getEntities().size(), 5);
    }

    @Test
    void checkCollisions() {
        //given
        GameModel gameModelMock = Mockito.spy(new GameModel());

        Asteroid c1 = Mockito.mock(Asteroid.class);
        Asteroid c2 = Mockito.mock(Asteroid.class);
        LaserBeam c3 = Mockito.mock(LaserBeam.class);

        List<MovingObject> movingObjects = new ArrayList<>();
        movingObjects.add((MovingObject) c1);
        movingObjects.add((MovingObject) c2);
        movingObjects.add(c3);

        Mockito.when(gameModelMock.getEntities()).thenReturn(movingObjects);

        Rectangle2D.Double r1 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r2 = Mockito.mock(Rectangle2D.Double.class);
        Rectangle2D.Double r3 = Mockito.mock(Rectangle2D.Double.class);

        Mockito.when(c1.getCollider()).thenReturn(r1);
        Mockito.when(c2.getCollider()).thenReturn(r2);
        Mockito.when(c3.getCollider()).thenReturn(r3);

        Mockito.when(r1.intersects(r2)).thenReturn(true);
        Mockito.when(r1.intersects(r3)).thenReturn(false);
        Mockito.when(r2.intersects(r3)).thenReturn(true);

        List<MovingObject> movingObjectsMock = Mockito.mock(List.class);

        Mockito.when(gameModelMock.getAsteroids()).thenReturn(movingObjectsMock);

        //when
        gameModelMock.checkCollisions();

        //then
        Mockito.verify(movingObjectsMock, Mockito.never()).remove(c1);
        Mockito.verify(movingObjectsMock, Mockito.times(1)).remove(c2);
    }
}