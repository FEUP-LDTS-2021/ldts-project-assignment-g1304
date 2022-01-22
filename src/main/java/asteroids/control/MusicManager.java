package asteroids.control;

public class MusicManager {

    private Music soundTrack;
    private Music shoot;
    private Music enemyShoot;
    private Music rocket;
    private Music destruction;
    private Music gameOver;
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

    public void setSoundTrack(Music soundTrack) {
        this.soundTrack = soundTrack;
    }

    public void setShoot(Music shoot) {
        this.shoot = shoot;
    }

    public void setRocket(Music rocket) {
        this.rocket = rocket;
    }

    public void setDestruction(Music destruction) {
        this.destruction = destruction;
    }

    public void setGameOver(Music gameOver) {
        this.gameOver = gameOver;
    }

    public void setEnemyShoot(Music enemyShoot) {
        this.enemyShoot = enemyShoot;
    }

    public void start(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.startLoop();
            case SHOOT -> shoot.start();
            case ROCKET -> rocket.startLoop();
            case DESTRUCTION -> destruction.start();
            case GAMEOVER -> gameOver.startLoop();
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

    @SuppressWarnings("UnnecessaryParentheses")
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
