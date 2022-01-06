package asteroids.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest extends Assertions {

    @Test
    void testPosition(){
        Position position = new Position(10, 20);

        assertEquals(10, position.getX());
        assertEquals(20, position.getY());

    }

    @Test
    void testSets(){
        Position position = new Position(10, 20);

        assertEquals(10, position.getX());
        assertEquals(20, position.getY());

        position.setX(20);
        position.setY(40);

        assertEquals(20, position.getX());
        assertEquals(40, position.getY());
    }

    @Test
    void cloneTest(){
        //given
        Position pos = new Position(20.0,23.1);

        //when
        Position cloned = pos.clone();

        //then
        assertEquals(pos.getX(),cloned.getX());
        assertEquals(pos.getY(),cloned.getY());
    }



}
