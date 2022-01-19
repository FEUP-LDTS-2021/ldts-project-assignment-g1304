package asteroids.model.Creator;

import asteroids.model.Entities.LaserBeam;
import asteroids.model.Entities.MovingObject;
import asteroids.model.Entities.Player;
import asteroids.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class PlayerLaserBeamCreatorTest extends Assertions {

    PlayerLaserBeamCreator creator;
    Player player;
    List<MovingObject> entities;

    @BeforeEach
    void init(){
        player = Mockito.mock(Player.class);

        entities = Mockito.mock(List.class);
        creator = Mockito.spy(new PlayerLaserBeamCreator(player, entities));

    }

    @Test
    void createCreator(){
        // then
        assertEquals(player, creator.getPlayer());
        assertEquals(entities, creator.getEntities());
    }

    @Test
    public void create() {
        // given
        Mockito.when(player.getAngle()).thenReturn(10.0);
        Position position = Mockito.mock(Position.class);
        Mockito.doReturn(position).when(creator).ajustPosition(Mockito.anyDouble(), Mockito.any());

        //when
        LaserBeam result = creator.create();

        // then
        Mockito.verify(creator).ajustPosition(10, player);
        assertEquals(position, result.getPosition());
        assertEquals(10.0, result.getAngle());
        assertEquals(3, result.getWidth());
        assertEquals(3, result.getHeight());
        assertTrue(result.isPlayerBeam());
    }


}
