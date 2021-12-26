package model.input;

import com.googlecode.lanterna.input.KeyStroke;

public interface InputObserver {
    public void processKey(KeyStroke key);

}
