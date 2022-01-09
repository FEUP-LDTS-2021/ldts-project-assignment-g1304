package asteroids.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import asteroids.utils.DoubleComparables;

public class Vector2dTest extends Assertions {

    @Test
    void positive(){
        Vector2d vec = new Vector2d(10,20);

        assertTrue(DoubleComparables.equalDouble(10,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(20,vec.getY()));
    }

    @Test
    void negative(){
        Vector2d vec = new Vector2d(-10,-20);

        assertTrue(DoubleComparables.equalDouble(-10,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(-20,vec.getY()));
    }

    @Test
    void zero(){
        Vector2d vec = new Vector2d(0,0);
        assertTrue(DoubleComparables.equalDouble(0,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getY()));
    }

    @Test
    void addX(){
        Vector2d vec = new Vector2d(0,0);
        vec.addX(10);
        assertTrue(DoubleComparables.equalDouble(10,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getY()));

        vec.addX(-10);
        assertTrue(DoubleComparables.equalDouble(0,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getY()));

        vec.addX(-5);
        assertTrue(DoubleComparables.equalDouble(-5,vec.getX()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getY()));
    }

    @Test
    void addY(){
        Vector2d vec = new Vector2d(0,0);
        vec.addY(10);
        assertTrue(DoubleComparables.equalDouble(10,vec.getY()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getX()));

        vec.addY(-10);
        assertTrue(DoubleComparables.equalDouble(0,vec.getY()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getX()));

        vec.addY(-5);
        assertTrue(DoubleComparables.equalDouble(-5,vec.getY()));
        assertTrue(DoubleComparables.equalDouble(0,vec.getX()));
    }

     @Test
    void moduleNegative(){
         Vector2d vec = new Vector2d(-10,0);
         Vector2d vec1 = new Vector2d(0,-10);
         Vector2d vec2 = new Vector2d(-5,-5);

         assertTrue(DoubleComparables.equalDouble(10,vec.module()));
         assertTrue(DoubleComparables.equalDouble(10,vec1.module()));
         assertTrue(DoubleComparables.equalDouble(Math.sqrt(50),vec2.module()));
     }

     @Test
    void modulePositive(){
         Vector2d vec = new Vector2d(10,0);
         Vector2d vec1 = new Vector2d(0,10);
         Vector2d vec2 = new Vector2d(5,5);

         assertTrue(DoubleComparables.equalDouble(10,vec.module()));
         assertTrue(DoubleComparables.equalDouble(10,vec1.module()));
         assertTrue(DoubleComparables.equalDouble(Math.sqrt(50),vec2.module()));
    }

    @Test
    void modulePositiveNegative(){
        Vector2d vec1 = new Vector2d(-10,10);
        Vector2d vec2 = new Vector2d(10,-10);

        assertTrue(DoubleComparables.equalDouble(Math.sqrt(200),vec1.module()));
        assertTrue(DoubleComparables.equalDouble(Math.sqrt(200),vec2.module()));
    }

    @Test
    void moduleZero(){
        Vector2d vec = new Vector2d(0,0);
        assertTrue(DoubleComparables.equalDouble(0,vec.module()));
    }

    @Test
    void moduleWithChanges(){
        Vector2d vec = new Vector2d(0,0);
        Vector2d vec1 = new Vector2d(0,0);
        Vector2d vec2 = new Vector2d(0,0);
        Vector2d vec3 = new Vector2d(0,0);
        Vector2d vec4 = new Vector2d(10,0);


        vec.addX(10);

        vec1.addY(10);

        vec2.addX(10);
        vec2.addY(10);

        vec3.addX(-5);

        vec4.addY(-10);

        assertTrue(DoubleComparables.equalDouble(10,vec.module()));
        assertTrue(DoubleComparables.equalDouble(10,vec1.module()));
        assertTrue(DoubleComparables.equalDouble(Math.sqrt(200),vec2.module()));
        assertTrue(DoubleComparables.equalDouble(5,vec3.module()));
        assertTrue(DoubleComparables.equalDouble(Math.sqrt(200),vec4.module()));
    }

    @Test
    void scaleBigger(){

        Vector2d vec1 = new Vector2d(10,0);
        Vector2d vec2 = new Vector2d(20,-10);

        vec1.scale(2);
        vec2.scale(2);


        assertTrue(DoubleComparables.equalDouble(20, vec1.getX()));
        assertTrue(DoubleComparables.equalDouble(0, vec1.getY()));

        assertTrue(DoubleComparables.equalDouble(40, vec2.getX()));
        assertTrue(DoubleComparables.equalDouble(-20, vec2.getY()));

    }

    @Test
    void scaleSmaller(){

        Vector2d vec1 = new Vector2d(10,0);
        Vector2d vec2 = new Vector2d(20,-10);

        vec1.scale(-2);
        vec2.scale(0.5);

        assertTrue(DoubleComparables.equalDouble(-20, vec1.getX()));
        assertTrue(DoubleComparables.equalDouble(0, vec1.getY()));

        assertTrue(DoubleComparables.equalDouble(10, vec2.getX()));
        assertTrue(DoubleComparables.equalDouble(-5, vec2.getY()));

    }

    @Test
    void resizeDobro(){

        Vector2d vec1 = Mockito.mock(Vector2d.class);
        Mockito.doCallRealMethod().when(vec1).resize(Mockito.anyDouble());
        Mockito.when(vec1.module()).thenReturn(10.0);

        Mockito.doNothing().when(vec1).scale(Mockito.anyDouble());
        vec1.resize(20);

        Mockito.verify(vec1, Mockito.atLeastOnce()).module();
        Mockito.verify(vec1, Mockito.times(1)).scale(2);

    }
    @Test
    void resizeSmaller(){
        Vector2d vec1 = Mockito.mock(Vector2d.class);
        Mockito.doCallRealMethod().when(vec1).resize(Mockito.anyDouble());
        Mockito.when(vec1.module()).thenReturn(10.0);

        Mockito.doNothing().when(vec1).scale(Mockito.anyDouble());
        vec1.resize(5);

        Mockito.verify(vec1, Mockito.atLeastOnce()).module();
        Mockito.verify(vec1, Mockito.times(1)).scale(0.5);

    }


    @Test
    void resizeNull(){
        Vector2d vec1 = Mockito.mock(Vector2d.class);
        Mockito.doCallRealMethod().when(vec1).resize(Mockito.anyDouble());
        Mockito.when(vec1.module()).thenReturn(0.0);

        Mockito.doNothing().when(vec1).scale(Mockito.anyDouble());
        vec1.resize(5);

        Mockito.verify(vec1, Mockito.never()).scale(Mockito.anyDouble());
    }

    @Test
    void dotProduct(){
        //given
        Vector2d v1 = new Vector2d(12.0,23.0);
        Vector2d v2 = new Vector2d(2.0,3.0);
        Vector2d v3 = new Vector2d(1.2,4.56);

        //when
        Double result1 = v1.dotProduct(v2);
        Double result2 = v2.dotProduct(v3);

        //then
        assertEquals(24.0 + 69.0, result1);
        assertEquals(2.4 + 13.68, result2);
    }

}
