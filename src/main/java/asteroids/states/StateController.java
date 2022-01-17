package asteroids.states;

import java.io.IOException;

public interface StateController {
    void run() throws IOException;

    void nextState();

}
