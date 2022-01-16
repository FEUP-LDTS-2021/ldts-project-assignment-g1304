package asteroids.model.Collider;


import java.awt.Polygon;

public interface CollidableObject {
    Polygon getCollider();

    default boolean collide(CollidableObject object){
        return false;
    }
}
