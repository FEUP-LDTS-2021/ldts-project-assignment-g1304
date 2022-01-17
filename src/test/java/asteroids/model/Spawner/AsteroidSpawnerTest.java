package asteroids.model.Spawner;

import asteroids.model.Entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class AsteroidSpawnerTest extends Assertions {
    @Test
    void createAsteroidSpawner() {
        //given
        List<MovingObject> entities = new ArrayList<>();
        Player playerMock = Mockito.mock(Player.class);
        entities.add(playerMock);

        //when
        AsteroidSpawner asteroidSpawner = new AsteroidSpawner(entities);

        //then
        assertEquals(entities, asteroidSpawner.getEntities());
        assertNotNull(asteroidSpawner.getAsteroidCreator());
    }

    @Test
    void spawnCheckEmpty() {
        //given
        List<MovingObject> entities = new ArrayList<>();

        //when
        AsteroidSpawner asteroidSpawner = new AsteroidSpawner(entities);

        //then
        assertTrue(asteroidSpawner.spawnCheck());
    }

    @Test
    void spawnCheckNonAsteroid() {
        //given
        List<MovingObject> entities = new ArrayList<>();

        //when
        entities.add(Mockito.mock(LaserBeam.class));
        entities.add(Mockito.mock(Player.class));
        entities.add(Mockito.mock(EnemyShip.class));
        AsteroidSpawner asteroidSpawner = new AsteroidSpawner(entities);

        //then
        assertTrue(asteroidSpawner.spawnCheck());
    }

    @Test
    void spawnCheckAsteroid() {
        //given
        List<MovingObject> entities = new ArrayList<>();

        //when
        entities.add(Mockito.mock(LaserBeam.class));
        entities.add(Mockito.mock(Player.class));
        entities.add(Mockito.mock(EnemyShip.class));
        entities.add(Mockito.mock(Asteroid.class));
        AsteroidSpawner asteroidSpawner = new AsteroidSpawner(entities);

        //then
        assertFalse(asteroidSpawner.spawnCheck());
    }

    @Test
    void updateSpawnCheckFalse() {
        //given
        List<MovingObject> entities = new ArrayList<>();
        AsteroidSpawner asteroidSpawner = Mockito.spy(new AsteroidSpawner(entities));

        //when
        Mockito.when(asteroidSpawner.spawnCheck()).thenReturn(false);
        asteroidSpawner.update();

        //then
        assertTrue(entities.isEmpty());
    }
    @Test
    void updateSpawnCheckTrue() {
        //given
        List<MovingObject> entities = new ArrayList<>();
        AsteroidSpawner asteroidSpawner = Mockito.spy(new AsteroidSpawner(entities));

        //when
        Mockito.when(asteroidSpawner.spawnCheck()).thenReturn(true);
        asteroidSpawner.update();

        //then
        assertEquals(4, entities.size());
    }
}
