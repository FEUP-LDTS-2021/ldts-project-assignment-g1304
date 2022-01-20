package asteroids.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MusicManagerTest extends Assertions {

    MusicManager musicManagerSpy;
    Music soundTrackMock = Mockito.mock(Music.class);
    Music shootMock = Mockito.mock(Music.class);
    Music rocketMock = Mockito.mock(Music.class);
    Music destructionMock = Mockito.mock(Music.class);
    Music gameOverMock = Mockito.mock(Music.class);
    Music enemyShootMock = Mockito.mock(Music.class);

    @BeforeEach
    void initMusicManager() {
        MusicManager musicManager = MusicManager.getInstance();
        MusicManager.setSoundTrack(soundTrackMock);
        MusicManager.setShoot(shootMock);
        MusicManager.setRocket(rocketMock);
        MusicManager.setDestruction(destructionMock);
        MusicManager.setGameOver(gameOverMock);
        MusicManager.setEnemyShoot(enemyShootMock);
        musicManagerSpy = Mockito.spy(musicManager);
    }

    @Test
    void startSoundTrack() {
        musicManagerSpy.start(Sounds.SOUNDTRACK);
        Mockito.verify(soundTrackMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startShoot() {
        musicManagerSpy.start(Sounds.SHOOT);
        Mockito.verify(shootMock, Mockito.times(1)).start();
    }

    @Test
    void startRocket() {
        musicManagerSpy.start(Sounds.ROCKET);
        Mockito.verify(rocketMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startDestruction() {
        musicManagerSpy.start(Sounds.DESTRUCTION);
        Mockito.verify(destructionMock, Mockito.times(1)).start();
    }

    @Test
    void startGameOver() {
        musicManagerSpy.start(Sounds.GAMEOVER);
        Mockito.verify(gameOverMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startEnemyShoot() {
        musicManagerSpy.start(Sounds.ENEMYSHOOT);
        Mockito.verify(enemyShootMock, Mockito.times(1)).start();
    }

    @Test
    void stopSoundTrack() {
        musicManagerSpy.stop(Sounds.SOUNDTRACK);
        Mockito.verify(soundTrackMock, Mockito.times(1)).stop();
    }

    @Test
    void stopRocket() {
        musicManagerSpy.stop(Sounds.ROCKET);
        Mockito.verify(rocketMock, Mockito.times(1)).stop();
    }

    @Test
    void stopGameOver() {
        musicManagerSpy.stop(Sounds.GAMEOVER);
        Mockito.verify(gameOverMock, Mockito.times(1)).stop();
    }

    @Test
    void isPlayingSoundTrackTrue() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertTrue(playing);
    }

    @Test
    void isPlayingSoundTrackFalse() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertFalse(playing);
    }

    @Test
    void isPlayingShootTrue() {
        Mockito.when(shootMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SHOOT);
        assertTrue(playing);
    }

    @Test
    void isPlayingShootFalse() {
        Mockito.when(shootMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SHOOT);
        assertFalse(playing);
    }

    @Test
    void isPlayingRocketTrue() {
        Mockito.when(rocketMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.ROCKET);
        assertTrue(playing);
    }

    @Test
    void isPlayingRocketFalse() {
        Mockito.when(rocketMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.ROCKET);
        assertFalse(playing);
    }

    @Test
    void isPlayingDestructionTrue() {
        Mockito.when(destructionMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.DESTRUCTION);
        assertTrue(playing);
    }

    @Test
    void isPlayingDestructionFalse() {
        Mockito.when(destructionMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.DESTRUCTION);
        assertFalse(playing);
    }

    @Test
    void isPlayingGameOverTrue() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertTrue(playing);
    }

    @Test
    void isPlayingGameOverFalse() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertFalse(playing);
    }

    @Test
    void isPlayingEnemyShootTrue() {
        Mockito.when(enemyShootMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.ENEMYSHOOT);
        assertTrue(playing);
    }

    @Test
    void isPlayingEnemyShootFalse() {
        Mockito.when(enemyShootMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.ENEMYSHOOT);
        assertFalse(playing);
    }

    @Test
    void stopAll() {
        musicManagerSpy.stopAll();
        Mockito.verify(soundTrackMock, Mockito.times(1)).stop();
        Mockito.verify(shootMock, Mockito.times(1)).stop();
        Mockito.verify(rocketMock, Mockito.times(1)).stop();
        Mockito.verify(destructionMock, Mockito.times(1)).stop();
        Mockito.verify(gameOverMock, Mockito.times(1)).stop();
        Mockito.verify(enemyShootMock, Mockito.times(1)).stop();
    }

}
