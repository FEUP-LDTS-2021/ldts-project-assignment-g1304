package control;

import com.googlecode.lanterna.input.KeyStroke;
import control.input.InputObserver;
import model.Player;

public class PlayerController implements InputObserver {

    public Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    @Override
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> player.rotateLeft();
            case ArrowRight -> player.rotateRight();
            case ArrowUp -> player.acelerate();
        }
    }
}
