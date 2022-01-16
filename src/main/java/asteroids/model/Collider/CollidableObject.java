package asteroids.model.Collider;


import java.awt.Polygon;

public interface CollidableObject {
    Polygon getCollider();

    default boolean collide(CollidableObject object){
        Polygon polygon = object.getCollider();

        return containsAnyPoints(polygon, getCollider()) || containsAnyPoints(getCollider(), polygon);
    }

    private static boolean containsAnyPoints(Polygon object1, Polygon object2){
        for (int point = 0 ; point < object1.npoints; point++)
            if (object2.contains(object1.xpoints[point], object1.ypoints[point]))
                return true;
        return false;
    }
}
