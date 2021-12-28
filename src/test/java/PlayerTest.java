import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Player;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class PlayerTest extends Assertions {

    @Test
    void createPlayer(){
        // given
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(10);
        Mockito.when(positionMock.getY()).thenReturn(20);

        Player p = new Player(positionMock);

        // when
        Position position = p.getPosition();

        // then
        assertEquals(position.getX(), positionMock.getX());
        assertEquals(position.getY(), positionMock.getY());
    }

    @Test
    void angle(){
        Player player = new Player(new Position(10, 10));

        assertEquals(0, player.getAngle());

        player.setAngle(10);
        assertEquals(10, player.getAngle());
    }

    @Test
    void changeDirectionInput(){
        Player player = new Player(new Position(10, 10));
        player.processKey(new KeyStroke(KeyType.ArrowLeft));
        player.update(1000);
        assertEquals(Player.angularVelocity, player.getAngle());

        player.processKey(new KeyStroke(KeyType.ArrowRight));
        player.update(1000);
        assertEquals(0.0f, player.getAngle());

        player.processKey(new KeyStroke(KeyType.ArrowRight));
        player.update(1000);
        assertEquals(-Player.angularVelocity, player.getAngle());
    }
}
