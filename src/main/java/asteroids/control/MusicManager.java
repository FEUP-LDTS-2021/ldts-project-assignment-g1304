package asteroids.control;

public interface MusicManager {

    MusicManager getInstance();

    void setSoundTrack(Music soundTrack);

    void setShoot(Music shoot);

    void setRocket(Music rocket);

    void setDestruction(Music destruction);

    void setGameOver(Music gameOver);

    void start(Sounds sound);

    void stop(Sounds sound);

    boolean isPlaying(Sounds sound);

    void stopAll();
}
