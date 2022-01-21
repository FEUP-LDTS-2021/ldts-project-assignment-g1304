package asteroids.model.Menu;

public enum MenuItem {
    Play("Play"),
    LeaderBoard("LeaderBoard"),
    Instructions("Instructions"),
    Exit("Exit");

    private final String name;
    MenuItem(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}