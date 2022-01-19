package asteroids.control;

import asteroids.model.Entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private Player player;
    private boolean canShoot = true;

    public PlayerController(Player player){
        this.player = player;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.setRotation(Rotation.Left);
                break;
            case KeyEvent.VK_RIGHT:
                player.setRotation(Rotation.Right);
                break;
            case KeyEvent.VK_UP:
                MusicManager.getInstance().start(Sounds.ROCKET);
                player.setAcelerate(true);
                break;
            case KeyEvent.VK_SPACE:
                if(isCanShoot()){
                    MusicManager.getInstance().start(Sounds.SHOOT);
                    player.setShoot(true);
                    canShoot = false;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(player.getRotation()==Rotation.Left)
                    player.setRotation(Rotation.None);
                break;
            case KeyEvent.VK_RIGHT:
                if(player.getRotation()==Rotation.Right)
                    player.setRotation(Rotation.None);
                break;
            case KeyEvent.VK_UP:
                MusicManager.getInstance().stop(Sounds.ROCKET);
                player.setAcelerate(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setShoot(false);
                canShoot = true;
                break;
        }
    }
}
