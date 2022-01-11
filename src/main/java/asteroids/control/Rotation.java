package asteroids.control;

public enum Rotation{
    Left(-1),
    Right(1),
    None(0);

    private final int value;

    Rotation(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}