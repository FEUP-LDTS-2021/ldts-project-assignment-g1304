package model.Entities;

import model.Position;
import model.physics.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import utils.DoubleComparables;

public class MovingObjectsTest extends Assertions {

    MovingObject object;
    Position position;
    Vector2d velocity;
    @BeforeEach
    void create(){
        object = Mockito.mock(MovingObject.class, Mockito.CALLS_REAL_METHODS);
        position = Mockito.mock(Position.class);
        velocity = Mockito.mock(Vector2d.class);
        object.setPosition(position);
        object.setVelocity(velocity);

    }

    @Test
    void goForward(){

        Mockito.when(velocity.getX()).thenReturn(-10.0);
        Mockito.when(velocity.getY()).thenReturn(5.0);

        Mockito.when(position.getX()).thenReturn(10.0);
        Mockito.when(position.getY()).thenReturn(20.0);


        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], position.getX() + velocity.getX() ));
            return null;
        }).when(position).setX(Mockito.anyDouble());

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Object[] arguments = invocation.getArguments();
            assertTrue(DoubleComparables.equalDouble ((double)arguments[0], position.getY() + velocity.getY() ));
            return null;
        }).when(position).setY(Mockito.anyDouble());


        object.goFoward(1000);


    }


    @Test
    void update(){
        Mockito.verify(object, Mockito.never()).goFoward(Mockito.anyLong());

        object.update(1000);

        Mockito.verify(object, Mockito.times(1)).goFoward(1000);

    }

}
