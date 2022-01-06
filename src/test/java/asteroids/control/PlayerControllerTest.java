package asteroids.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import asteroids.model.Entities.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerControllerTest extends Assertions {

    PlayerController playerController;
    Player player;
    List<KeyStroke> keyStrokeList;
    KeyStroke right;
    KeyStroke left;
    KeyStroke up;

    @BeforeEach
    void initPlayerController(){
        player= Mockito.mock(Player.class);
        playerController = new PlayerController(player);
        keyStrokeList = new ArrayList<>();

        for(KeyType keyType : KeyType.values()){
            if (keyType == KeyType.Character)
                continue;
            keyStrokeList.add(new KeyStroke(keyType));
        }

        for(char letter = 1; letter < 256 ; letter++){
            keyStrokeList.add(KeyStroke.fromString(""+letter));
        }

        right = new KeyStroke(KeyType.ArrowRight);
        left = new KeyStroke(KeyType.ArrowLeft);
        up = new KeyStroke(KeyType.ArrowUp);
    }

    @Test
    void changeDirectionInput(){
        playerController.processKey(left);
        Mockito.verify(player, Mockito.times(1)).rotateLeft();
        
        playerController.processKey(left);
        Mockito.verify(player, Mockito.times(2)).rotateLeft();

        playerController.processKey(left);
        Mockito.verify(player, Mockito.times(3)).rotateLeft();

        playerController.processKey(right);
        Mockito.verify(player, Mockito.times(1)).rotateRight();

        playerController.processKey(right);
        Mockito.verify(player, Mockito.times(2)).rotateRight();

    }

    @Test
    void notChangeDirection(){
        for(KeyStroke keyStroke : keyStrokeList) {
            if(keyStroke.equals(right) || keyStroke.equals(left) || keyStroke.equals(up))
                continue;
            playerController.processKey(keyStroke);
            Mockito.verify(player, Mockito.never()).rotateLeft();
            Mockito.verify(player, Mockito.never()).rotateRight();
            Mockito.verify(player, Mockito.never()).acelerate();
        }
    }

    @Test
    void acelerationInput(){

        playerController.processKey(up);
        Mockito.verify(player, Mockito.times(1)).acelerate();

        playerController.processKey(up);
        Mockito.verify(player, Mockito.times(2)).acelerate();
    }
}
