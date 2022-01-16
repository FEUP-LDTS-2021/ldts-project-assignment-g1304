package asteroids.model.Collider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.security.Policy;

public class CollidableObjectTest extends Assertions {
    CollidableObject collidableObject1;
    CollidableObject collidableObject2;
    Polygon polygon1, polygon2;
    @BeforeEach
    void init(){
        collidableObject1 = Mockito.mock(CollidableObject.class, Mockito.CALLS_REAL_METHODS);
        collidableObject2 = Mockito.mock(CollidableObject.class, Mockito.CALLS_REAL_METHODS);

        polygon1 = new Polygon();
        polygon2 = new Polygon();

        Mockito.when(collidableObject1.getCollider()).thenReturn(polygon1);
        Mockito.when(collidableObject2.getCollider()).thenReturn(polygon2);

    }

    @Test
    void collide(){
        // given
        polygon1.addPoint(0, 0);
        polygon1.addPoint(10, 0);
        polygon1.addPoint(10, 10);
        polygon1.addPoint(0, 10);

        polygon2.addPoint(7, -20);
        polygon2.addPoint(5, 5);
        polygon2.addPoint(20, 20);
        // when
        boolean colide = collidableObject1.collide(collidableObject2);

        // then
        assertTrue(colide);

    }

    @Test
    void DontCollide(){
        // given
        polygon1.addPoint(0, 0);
        polygon1.addPoint(10, 0);
        polygon1.addPoint(10, 10);
        polygon1.addPoint(0, 10);

        polygon2.addPoint(7, -20);
        polygon2.addPoint(15, 5);
        polygon2.addPoint(20, 20);
        // when
        boolean colide = collidableObject1.collide(collidableObject2);

        // then
        assertFalse(colide);
    }

    @Test
    void Collide2(){
        // given
        polygon1.addPoint(0, 0);
        polygon1.addPoint(10, 0);
        polygon1.addPoint(10, 10);
        polygon1.addPoint(0, 10);

        polygon2.addPoint(7, -20);
        polygon2.addPoint(5, 5);
        polygon2.addPoint(20, 20);
        // when
        boolean colide = collidableObject2.collide(collidableObject1);

        // then
        assertTrue(colide);
    }

}
