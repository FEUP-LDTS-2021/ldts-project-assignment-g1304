package model.Menu;

public enum MenuItem {
    Play("Play"),
    LeaderBoard("LeaderBoard"),
    Exit("Exit");

    String name;
    MenuItem(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}