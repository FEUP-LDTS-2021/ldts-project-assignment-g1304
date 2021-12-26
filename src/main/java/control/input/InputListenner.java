package control.input;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.input.InputObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputListenner implements Runnable{

    private Screen screen;
    private List<InputObserver> observers;

    public InputListenner(Screen screen){
        this.screen = screen;
        observers = new ArrayList<>();
    }


    @Override
    public void run() {
        try{
            KeyStroke keyStroke = screen.readInput();

            while (keyStroke.getKeyType() != KeyType.EOF){
                for (InputObserver observer : observers)
                    observer.processKey(keyStroke);
                keyStroke = screen.readInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addInputObserver(InputObserver observer){
        observers.add(observer);
    }

    public void removeInputObserver(InputObserver observer){
        observers.remove(observer);
    }
}
