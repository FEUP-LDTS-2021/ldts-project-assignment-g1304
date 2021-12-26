package control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import control.input.InputListenner;
import model.input.InputObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InputListennerTest extends Assertions {

    @Test
    public void testInputs() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"), new KeyStroke(KeyType.EOF));
        InputListenner inputListenner = new InputListenner(screenMock);

        InputObserver observer = Mockito.mock(InputObserver.class);

        inputListenner.addInputObserver(observer);
        inputListenner.run();

        Mockito.verify(observer, Mockito.times(2)).processKey(Mockito.any());

    }

    @Test
    public void testInputs2Observers() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"), new KeyStroke(KeyType.EOF));

        InputListenner inputListenner = new InputListenner(screenMock);

        InputObserver observer1 = Mockito.mock(InputObserver.class);
        InputObserver observer2 = Mockito.mock(InputObserver.class);


        inputListenner.addInputObserver(observer1);
        inputListenner.addInputObserver(observer2);
        inputListenner.run();

        Mockito.verify(observer1, Mockito.times(2)).processKey(Mockito.any());
        Mockito.verify(observer2, Mockito.times(2)).processKey(Mockito.any());

    }

    @Test
    public void testRemoveObserver() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"), new KeyStroke(KeyType.EOF));

        InputListenner inputListenner = new InputListenner(screenMock);

        InputObserver observer1 = Mockito.mock(InputObserver.class);
        InputObserver observer2 = Mockito.mock(InputObserver.class);


        inputListenner.addInputObserver(observer1);
        inputListenner.addInputObserver(observer2);
        inputListenner.removeInputObserver(observer1);
        inputListenner.run();

        Mockito.verify(observer1, Mockito.times(0)).processKey(Mockito.any());
        Mockito.verify(observer2, Mockito.times(2)).processKey(Mockito.any());

    }


}
