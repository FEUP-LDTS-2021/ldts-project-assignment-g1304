package asteroids.states;

import asteroids.control.Controller;
import asteroids.states.ApplicationState;
import asteroids.states.GameOverController;
import asteroids.view.screens.GameOverScreen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameOverControllerTest extends Assertions {
    Controller context;
    GameOverScreen screenViewMock;
    TerminalScreen screenMock;

    GameOverController gameOverControllerSpy;


    @BeforeEach
    void init(){
        // create context
        context = Mockito.mock(Controller.class);

        // create GameOverController
        gameOverControllerSpy = Mockito.spy(new GameOverController(context));

        // create screens Mocks
        screenViewMock = Mockito.mock(GameOverScreen.class);
        screenMock = Mockito.mock(TerminalScreen.class);
        Mockito.when(screenViewMock.getScreen()).thenReturn(screenMock);
        gameOverControllerSpy.setScreenView(screenViewMock);
    }
    @Test
    void processKeyEscape(){
        // when
        KeyEvent e = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ESCAPE, '\n');
        gameOverControllerSpy.keyPressed(e);

        // then
        Mockito.verify(context, Mockito.times(1)).changeState(ApplicationState.Menu);
    }

    @Test
    void processWritingKey(){
        //given
        KeyEvent e_letter_number = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_A, 'a');
        //when
        gameOverControllerSpy.keyPressed(e_letter_number);

        //then
        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).writeName(Mockito.anyChar());
    }
    @Test
    void processEnterKey(){
        //given
        KeyEvent enter = new KeyEvent(Mockito.mock(Component.class), 1, 20, 0, KeyEvent.VK_ENTER, '\n');
        //when
        gameOverControllerSpy.keyPressed(enter);

        //then
        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).updateLeaderboard(Mockito.anyString());
    }
    @Test
    void startRun() throws IOException {
        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).initScreen();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(gameOverControllerSpy);
    }

    @Test
    void endRun() throws IOException {
        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).close();
        Mockito.verify(screenViewMock, Mockito.times(1)).addKeyListenner(gameOverControllerSpy);
    }
    @Test
    void testRun() throws IOException {
        // given
        Mockito.when(context.getApplicationState()).thenReturn(ApplicationState.GameOver,ApplicationState.Menu);


        // when
        gameOverControllerSpy.run();

        // then
        Mockito.verify(screenViewMock, Mockito.times(1)).draw();

    }
    @Test
    void writeName(){
        //given
        GameOverController gameOverController = Mockito.spy(new GameOverController(context));
        GameOverController gameOverController1 = new GameOverController(context);
        gameOverController.setScreenView(screenViewMock);
        //when
        gameOverController.writeName('p');

        gameOverController1.writeName('a');
        gameOverController1.writeName('b');

        //then
        assertEquals("p",gameOverController.getNickName());
        assertEquals("p _ _ _ _ _ _ _ _ _ _ ",gameOverController.getNickNameSpaces());
        assertEquals("ab",gameOverController1.getNickName());
        assertEquals("a b _ _ _ _ _ _ _ _ _ ",gameOverController1.getNickNameSpaces());
        Mockito.verify(screenViewMock,Mockito.times(1)).setNickNameSpaces("p _ _ _ _ _ _ _ _ _ _ ");
    }

    @Test
    void formatScore(){
        //given
        GameOverController gameOverController = new GameOverController(context);

        //when
        String one_digit = gameOverController.formatScore(1);
        String two_digit = gameOverController.formatScore(11);
        String three_digit = gameOverController.formatScore(111);
        String four_digit = gameOverController.formatScore(1111);
        String five_digit = gameOverController.formatScore(11111);

        //then
        assertEquals("00001",one_digit);
        assertEquals("00011",two_digit);
        assertEquals("00111",three_digit);
        assertEquals("01111",four_digit);
        assertEquals("11111",five_digit);
    }

    @Test
    void updateLeaderboard(){
        //given
        Mockito.doNothing().when(gameOverControllerSpy).writeLeaderboard("src/main/resources/leaderboardTest.txt");
        List<Integer> expectedScore = new ArrayList<>(List.of(99990,50012,20321,14975,9005,2001,2000,1696,220,100));
        List<String> expectedNick = new ArrayList<>(List.of("Asian5yoBoy       ","JonasGameplays    ","Mr.Batolas        ",
                "Rui_Silva         ","MVCforTheWin      ","                  ","Destroyer         ","ErenJagger        ","yellena           ",
                "ZeNabo            "));

        //when
        gameOverControllerSpy.setScore(2001);
        gameOverControllerSpy.updateLeaderboard("leaderboardTest.txt");

        //then
        for(int i = 0; i < expectedNick.size();i++){
            assertEquals(expectedScore.get(i),gameOverControllerSpy.getScore().get(i));
            assertEquals(expectedNick.get(i),gameOverControllerSpy.getName().get(i));
        }
        Mockito.verify(gameOverControllerSpy, Mockito.times(1)).writeLeaderboard(Mockito.anyString());
    }
    @Test
    void writeLeaderboard(){
        //given
        GameOverController gameOverController = new GameOverController(context);
        List<Integer> expectedScore = new ArrayList<>(List.of(99990,50012,20321,14975,9005,2001,2000,1696,220,100));
        List<String> expectedNick = new ArrayList<>(List.of("Asian5yoBoy       ","JonasGameplays    ","Mr.Batolas        ",
                "Rui_Silva         ","MVCforTheWin      ","                  ","Destroyer         ","ErenJagger        ","yellena           ",
                "ZeNabo            "));

        //when
        gameOverController.setScoreList(expectedScore);
        gameOverController.setName(expectedNick);
        gameOverController.writeLeaderboard("src/main/resources/FilesForTests/leaderboardTest2.txt");

        //then
        try {
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String filePath= rootPath + "/src/main/resources/FilesForTests/leaderboardTest1.txt";
        File myObj1 = new File(filePath);
        Scanner myReader1 = new Scanner(myObj1);
        String rootPath2 = new File(System.getProperty("user.dir")).getPath();
        String filePath2= rootPath2 + "/src/main/resources/FilesForTests/leaderboardTest2.txt";
        File myObj2 = new File(filePath2);
        Scanner myReader2 = new Scanner(myObj2);

        while (myReader1.hasNextLine()) {
            String data1 = myReader1.nextLine();
            String data2 = myReader2.nextLine();
            assertEquals(data1,data2);      //comparing the expected file leaderboardTest1.txt
        }                                   //with the file leaderboardTest2.txt written by the
        myReader1.close();                  //function that's being tested
        myReader2.close();

        File file = new File("src/main/resources/FilesForTests/leaderboardTest2.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.print("");                              //cleaning the file to ensure
        writer.close();                                //the proper functioning of the test
        } catch (FileNotFoundException e) {            //for the next time running it
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    @Test
    void setScore(){

        //when
        gameOverControllerSpy.setScore(200);

        //then
        Mockito.verify(screenViewMock,Mockito.times(1)).setScore(200);
    }
}
