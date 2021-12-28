package model;

import model.Entities.Player;

public class GameModel {

    private final Player player;

    public GameModel(){
        player = new Player(new Position(100, 100));
    }


    public Player getPlayer() {
        return player;
    }

    public void update(long ms){
        player.update(ms);
    }

}
