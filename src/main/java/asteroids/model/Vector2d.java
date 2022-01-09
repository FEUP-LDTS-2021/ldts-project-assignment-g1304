package asteroids.model;

public class Vector2d extends Position {
    public Vector2d(double x, double y) {
        super(x, y);
    }

    public double module(){
        return Math.sqrt(getX()*getX() + getY()*getY());
    }

    public void resize(double newSize){
        if(module()<0.01)
            return;     // cant resize null vector
        scale(newSize/module());
    }

    public void addX(double x){
        setX(getX()+x);
    }

    public void addY(double y){
        setY(getY()+y);
    }

    public void scale(double m){
        setX(getX()*m);
        setY(getY()*m);
    }

    public double dotProduct(Vector2d vec){
        return vec.getX()*getX() + vec.getY()*getY();
    }
}
