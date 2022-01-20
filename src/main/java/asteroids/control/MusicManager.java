package asteroids.control;

public class MusicManager {

    private static Music soundTrack;
    private static Music shoot;
    private static Music enemyShoot;
    private static Music rocket;
    private static Music destruction;
    private static Music gameOver;
    private static MusicManager musicManager;

    private MusicManager() {
        soundTrack = new Music("/src/main/resources/Sounds/soundTrack.wav");
        shoot = new Music("/src/main/resources/Sounds/shoot.wav");
        enemyShoot = new Music("/src/main/resources/Sounds/enemyShoot.wav");
        rocket = new Music("/src/main/resources/Sounds/rocket.wav");
        destruction = new Music("/src/main/resources/Sounds/destruction.wav");
        gameOver = new Music("/src/main/resources/Sounds/gameOver.wav");
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    public static void setSoundTrack(Music soundTrack) {
        MusicManager.soundTrack = soundTrack;
    }

    public static void setShoot(Music shoot) {
        MusicManager.shoot = shoot;
    }

    public static void setRocket(Music rocket) {
        MusicManager.rocket = rocket;
    }

    public static void setDestruction(Music destruction) {
        MusicManager.destruction = destruction;
    }

    public static void setGameOver(Music gameOver) {
        MusicManager.gameOver = gameOver;
    }

    public static void setEnemyShoot(Music enemyShoot) {
        MusicManager.enemyShoot = enemyShoot;
    }

    public void start(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.startLoop();
            case SHOOT -> shoot.start();
            case ROCKET -> rocket.startLoop();
            case DESTRUCTION -> destruction.start();
            case GAMEOVER -> gameOver.start();
            case ENEMYSHOOT -> enemyShoot.start();
        }
    }

    public void stop(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.stop();
            case ROCKET -> rocket.stop();
            case GAMEOVER -> gameOver.stop();
        }
    }

    public boolean isPlaying(Sounds sound) {
        return switch (sound) {
            case SOUNDTRACK -> soundTrack.isPlaying();
            case SHOOT -> shoot.isPlaying();
            case ROCKET -> rocket.isPlaying();
            case DESTRUCTION -> destruction.isPlaying();
            case GAMEOVER -> gameOver.isPlaying();
            case ENEMYSHOOT -> enemyShoot.isPlaying();
        };
    }

    public void stopAll() {
        soundTrack.stop();
        shoot.stop();
        enemyShoot.stop();
        rocket.stop();
        destruction.stop();
        gameOver.stop();
    }
}
