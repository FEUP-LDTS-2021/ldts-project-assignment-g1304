package model;

import model.Entities.LaserBeam;
import model.Entities.Player;

public class GameModel {

    private final Player player;

    public GameModel(){
        player = new Player(new Position(100, 100));
    }

    public Player getPlayer() {
        return player;
    }

    public void update(long dt){
            player.update(dt);
        for (LaserBeam laserBeam : player.getLaserBeams()) {
            laserBeam.update(dt);
        }
    }
}
