package control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import control.input.InputListenner;
import control.input.InputObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class InputListennerTest extends Assertions {
    Screen screenMock;
    InputListenner inputListenner;
    @BeforeEach
    public void initTests(){
        screenMock = Mockito.mock(Screen.class);
        inputListenner = Mockito.spy(InputListenner.class);
        inputListenner.setScreen(screenMock);
    }

    @Test
    public void stopInputListenner(){

        // when
        Mockito.when(inputListenner.isRunning()).thenReturn(true, false, true, false);
        inputListenner.run();

        // then
        Mockito.verify(inputListenner, Mockito.times(2)).isRunning();

    }

    @Test
    public void testInputs() throws IOException {
        // given
        InputObserver observer = Mockito.mock(InputObserver.class);

        // when
        inputListenner.addInputObserver(observer);
        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"),  new KeyStroke(KeyType.EOF));
        Mockito.when(inputListenner.isRunning()).thenReturn(true, true, true, false);

        inputListenner.run();

        // then
        Mockito.verify(observer, Mockito.times(3)).processKey(Mockito.any());

    }

    @Test
    public void testInputs2Observers() throws IOException, InterruptedException {

        //given
        InputObserver observer1 = Mockito.mock(InputObserver.class);
        InputObserver observer2 = Mockito.mock(InputObserver.class);

        //when

        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"), new KeyStroke(KeyType.EOF));
        Mockito.when(inputListenner.isRunning()).thenReturn(true, true, true, false);

        inputListenner.addInputObserver(observer1);
        inputListenner.addInputObserver(observer2);

        inputListenner.run();

        //then
        Mockito.verify(observer1, Mockito.times(3)).processKey(Mockito.any());
        Mockito.verify(observer2, Mockito.times(3)).processKey(Mockito.any());

    }

    @Test
    public void testRemoveObserver() throws IOException {
        // given

        InputObserver observer1 = Mockito.mock(InputObserver.class);
        InputObserver observer2 = Mockito.mock(InputObserver.class);

        // when
        inputListenner.addInputObserver(observer1);
        inputListenner.addInputObserver(observer2);
        inputListenner.removeInputObserver(observer1);

        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"), new KeyStroke(KeyType.EOF));
        Mockito.when(inputListenner.isRunning()).thenReturn(true, true, true, false);
        inputListenner.run();

        // then
        Mockito.verify(observer1, Mockito.never()).processKey(Mockito.any());
        Mockito.verify(observer2, Mockito.times(3)).processKey(Mockito.any());

    }

    @Test
    public void whenScreenNull() throws IOException {

        InputObserver observer = Mockito.mock(InputObserver.class);
        inputListenner.setScreen(null);
        // when
        inputListenner.addInputObserver(observer);
        Mockito.when(inputListenner.isRunning()).thenReturn(true, true, true, false);

        inputListenner.run();

        //then
        Mockito.verify(screenMock, Mockito.never()).readInput();
        Mockito.verify(observer, Mockito.never()).processKey(Mockito.any());
    }

    @Test
    public void whenScreenBecomesNotNull() throws IOException {
        //given

        // when
        Mockito.when(inputListenner.isRunning()).thenReturn(true, true, true, false);
        Mockito.when(inputListenner.getScreen()).thenReturn(null, screenMock, screenMock);
        Mockito.when(screenMock.readInput()).thenReturn(KeyStroke.fromString("A"), KeyStroke.fromString("A"));

        inputListenner.run();

        //then
        Mockito.verify(screenMock, Mockito.times(2)).readInput();
    }

}
