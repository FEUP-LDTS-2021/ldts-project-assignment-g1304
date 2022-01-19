package asteroids.model.Entities;

public enum AsteroidSizes {
    SMALL(1), MEDIUM(2), LARGE(3);
    int size;

    AsteroidSizes(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
