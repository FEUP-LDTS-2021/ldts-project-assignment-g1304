package asteroids.control;

import asteroids.model.Entities.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayerControllerTest extends Assertions {

    PlayerController playerController;
    Player player;
    List<KeyEvent> keyEventList;
    KeyEvent right;
    KeyEvent left;
    KeyEvent up;
    KeyEvent space;

    @BeforeEach
    void initPlayerController(){
        player= Mockito.mock(Player.class);
        playerController = new PlayerController(player);
        keyEventList = new ArrayList<>();

        for(int k = 0; k < 256; k++){
            KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0,
                    k, KeyEvent.CHAR_UNDEFINED);
            keyEventList.add(e);
        }

        right = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0,
                KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        left = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0,
                KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        up = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0,
                KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        space = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0,
                KeyEvent.VK_SPACE, ' ');
    }

    @Test
    void changeDirectionLeftInput() {
        // when
        playerController.keyPressed(left);
        Mockito.when(player.getRotation()).thenReturn(Rotation.Left);

        // then
        Mockito.verify(player, Mockito.times(1)).setRotation(Rotation.Left);

        // when
        playerController.keyReleased(left);

        // then
        Mockito.verify(player, Mockito.times(1)).setRotation(Rotation.None);
    }

    @Test
    void changeDirectionRightInput() {
        // when
        playerController.keyPressed(right);
        Mockito.when(player.getRotation()).thenReturn(Rotation.Right);

        // then
        Mockito.verify(player, Mockito.times(1)).setRotation(Rotation.Right);

        // when
        playerController.keyReleased(right);

        // thhen
        Mockito.verify(player, Mockito.times(1)).setRotation(Rotation.None);
    }

    @Test
    void changeDirectionLeftThenRightInput() {

        // when
        Mockito.when(player.getRotation()).thenReturn(Rotation.Right);
        playerController.keyReleased(left);

        // then
        Mockito.verify(player, Mockito.times(0)).setRotation(Rotation.None);

        playerController.keyReleased(right);
        Mockito.verify(player, Mockito.times(1)).setRotation(Rotation.None);
    }

    @Test
    void notChangeDirection(){
        for(KeyEvent keyEvent : keyEventList) {
            if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT
                    || keyEvent.getKeyCode()==KeyEvent.VK_LEFT
                    || keyEvent.getKeyCode()==KeyEvent.VK_UP
                    || keyEvent.getKeyCode()==KeyEvent.VK_SPACE)
                continue;
            playerController.keyPressed(keyEvent);
            Mockito.verify(player, Mockito.never()).setRotation(Mockito.any());
            Mockito.verify(player, Mockito.never()).setShoot(Mockito.anyBoolean());
            Mockito.verify(player, Mockito.never()).setAcelerate(Mockito.anyBoolean());
        }
    }

    @Test
    void acelerationInput(){
        playerController.keyPressed(up);
        Mockito.verify(player, Mockito.times(1)).setAcelerate(true);

        playerController.keyReleased(up);
        Mockito.verify(player, Mockito.times(1)).setAcelerate(false);
    }

    @Test
    void shootInput(){
        playerController.keyPressed(space);
        Mockito.verify(player, Mockito.times(1)).setShoot(true);

        playerController.keyReleased(space);
        Mockito.verify(player, Mockito.times(1)).setShoot(false);
    }
}
