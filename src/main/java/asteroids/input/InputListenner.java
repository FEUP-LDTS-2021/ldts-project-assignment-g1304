package asteroids.input;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputListenner implements Runnable{

    volatile private Screen screen;
    volatile private boolean running;
    private final List<InputObserver> observers;

    public InputListenner(){
        this.running = false;
        this.screen = null;
        observers = new ArrayList<>();
    }


    @Override
    public void run() {
        this.running = true;
        try{

            while (isRunning()) {
                if (getScreen() == null) {
                    Thread.sleep(100);
                    continue;
                }

                KeyStroke keyStroke = getScreen().readInput();

                synchronized (observers) {
                    for (InputObserver observer : observers)
                        observer.processKey(keyStroke);
                }
            }
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }

    }

    public Screen getScreen() {
        return screen;
    }

    public boolean isRunning() {
        return running;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void addInputObserver(InputObserver observer){
        observers.add(observer);
    }

    public void removeInputObserver(InputObserver observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }
    public void stop(){
        running = false;
    }
}
