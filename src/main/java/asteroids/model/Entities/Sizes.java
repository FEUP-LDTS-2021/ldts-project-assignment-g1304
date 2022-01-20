package asteroids.model.Entities;

public enum Sizes {
    SMALL(1), MEDIUM(2), LARGE(3);
    int size;

    Sizes(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
