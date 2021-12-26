package model;

public class GameModel {

    private final Player player;

    public GameModel(){
        player = new Player(new Position(100, 100));
    }


    public Player getPlayer() {
        return player;
    }

    public void update(){

    }

}
