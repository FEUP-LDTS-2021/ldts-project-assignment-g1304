package asteroids.model.Entities;

public enum AsteroidSizes {
    SMALL(15), MEDIUM(30), LARGE(50);
    int size;

    AsteroidSizes(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
