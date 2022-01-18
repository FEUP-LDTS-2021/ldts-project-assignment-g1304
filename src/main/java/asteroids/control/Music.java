package asteroids.control;

import javax.sound.sampled.Clip;

public interface Music {

    boolean isPlaying();

    Clip getSound();

    void setSound(Clip sound);

    Clip loadSound(String sound);

    void startLoop();

    void start();

    void stop();
}
