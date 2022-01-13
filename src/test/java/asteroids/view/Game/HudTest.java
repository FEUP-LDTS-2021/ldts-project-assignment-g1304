package asteroids.view.Game;

import asteroids.model.Constraints;
import asteroids.model.Entities.Player;
import asteroids.model.GameModel;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class HudTest extends Assertions {

    private Hud hud;
    private TextGraphics textGraphics;
    private GameModel model;
    private Player player;


    @BeforeEach
    void init(){
        model = Mockito.mock(GameModel.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        player = Mockito.mock(Player.class);

        Mockito.when(model.getPlayer()).thenReturn(player);

        hud = Mockito.spy(new Hud(model));

        hud.setGraphics(textGraphics);

    }

    private void compareArrays(String[] arr1, String[] arr2){
        assertEquals(arr1.length, arr2.length);
        for (int i = 0; i < arr1.length; i++)
            assertEquals(arr1[i], arr2[i]);

    }
    @Test
    void loadNumbers(){
        // given
        String [] n0 = new String[]{
                " ######",
                "##  ####",
                "## ## ##",
                "####  ##",
                " ######"};
        String [] n1 = new String[]{
                " ## ",
                "###",
                " ##",
                " ##",
                " ##",};
        String [] n2 = new String[]{
                "###### ",
                "     ##",
                " ##### ",
                "##     ",
                "#######",};
        String [] n3 = new String[]{
                "###### ",
                "     ##",
                " ##### ",
                "     ##",
                "######"};
        String [] n4 = new String[]{
                "##   ##",
                "##   ##",
                "#######",
                "     ##",
                "     ##"};
        String [] n5 = new String[]{
                "#######",
                "##     ",
                "#######",
                "     ##",
                "#######"};
        String [] n6 = new String[]{
                " ###### ",
                "##      ",
                "####### ",
                "##    ##",
                " ######"};
        String [] n7 = new String[]{
                " #######",
                "      ##",
                "     ## ",
                "    ##  ",
                "    ##"};
        String [] n8 = new String[]{
                " ##### ",
                "##   ##",
                " ##### ",
                "##   ##",
                " #####"};
        String [] n9 = new String[]{
                " ##### ",
                "##   ##",
                " ######",
                "     ##",
                " #####"};
        // when
        List<String[]> numbers = hud.getNumbers();

        // then

        assertEquals(10 , numbers.size());
        compareArrays(n0, numbers.get(0));
        compareArrays(n1, numbers.get(1));
        compareArrays(n2, numbers.get(2));
        compareArrays(n3, numbers.get(3));
        compareArrays(n4, numbers.get(4));
        compareArrays(n5, numbers.get(5));
        compareArrays(n6, numbers.get(6));
        compareArrays(n7, numbers.get(7));
        compareArrays(n8, numbers.get(8));
        compareArrays(n9, numbers.get(9));
    }

    @Test
    void draw() throws IOException {
        // given
        Mockito.when(player.getScore()).thenReturn(100);
        Mockito.when(player.getLives()).thenReturn(3);

        // when
        hud.draw();

        // then
        Mockito.verify(hud, Mockito.times(1)).drawScore(100);
        Mockito.verify(hud, Mockito.times(1)).drawLives(3);
    }

    @Test
    void drawScore12345(){
        // given
        List<String[]> numbers = List.of(
                new String[]{"##########"},
                new String[]{"#"},
                new String[]{"##"},
                new String[]{"###"},
                new String[]{"####"},
                new String[]{"#####"});
        Mockito.doReturn(numbers).when(hud).getNumbers();
        Mockito.doNothing().when(hud).draw(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        // when
        hud.drawScore(12345);

        // then
        Mockito.verify(hud).draw(hud.getScoreString(), 10, 10);


        int x = 46 + 10;
        for(int n = 1; n <= 5; n++){
            Mockito.verify(hud).draw(numbers.get(n), x , 10);
            x += numbers.get(n)[0].length() + 2;
        }
    }

    @Test
    void drawScore0(){
        // given
        List<String[]> numbers = List.of(
                new String[]{"##########"},
                new String[]{"#"},
                new String[]{"##"},
                new String[]{"###"},
                new String[]{"####"},
                new String[]{"#####"});
        Mockito.doReturn(numbers).when(hud).getNumbers();
        Mockito.doNothing().when(hud).draw(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        // when
        hud.drawScore(0);

        // then
        Mockito.verify(hud).draw(hud.getScoreString(), 10, 10);


        int x = 46 + 10;
        for(int n = 1; n <= 5; n++){
            Mockito.verify(hud).draw(numbers.get(0), x , 10);
            x += numbers.get(0)[0].length() + 2;
        }
    }

    @Test
    void drawScore1(){
        // given
        List<String[]> numbers = List.of(
                new String[]{"##########"},
                new String[]{"#"},
                new String[]{"##"},
                new String[]{"###"},
                new String[]{"####"},
                new String[]{"#####"});
        Mockito.doReturn(numbers).when(hud).getNumbers();
        Mockito.doNothing().when(hud).draw(Mockito.any(), Mockito.anyInt(), Mockito.anyInt());

        // when
        hud.drawScore(1);

        // then
        Mockito.verify(hud).draw(hud.getScoreString(), 10, 10);


        int x = 46 + 10;
        for(int n = 1; n <= 4; n++){
            Mockito.verify(hud).draw(numbers.get(0), x , 10);
            x += numbers.get(0)[0].length() + 2;
        }

        Mockito.verify(hud).draw(numbers.get(1), x , 10);
        x += numbers.get(1)[0].length() + 2;
    }

    @Test
    void drawString(){

        // given
        String[] strings = new String[]{"# #", " ##"};

        // when
        hud.draw(strings, 10, 10);

        // then

        // draw linha 1 de strings
        Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                new TerminalPosition(0*1 + 10,
                        0*2+ 10),
                new TerminalSize(1, 2), ' ');
        Mockito.verify(textGraphics, Mockito.never()).fillRectangle(
                new TerminalPosition(1*1 + 10,
                        0*2+ 10),
                new TerminalSize(1, 2), ' ');

        Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                new TerminalPosition(2 + 10,
                        0*2+ 10),
                new TerminalSize(1, 2), ' ');

        // draw linha 1 de strings
        Mockito.verify(textGraphics, Mockito.never()).fillRectangle(
                new TerminalPosition(0 + 10,
                        2+ 10),
                new TerminalSize(1, 2), ' ');
        Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                new TerminalPosition(1 + 10,
                        2+ 10),
                new TerminalSize(1, 2), ' ');

        Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                new TerminalPosition(2 + 10,
                        2+ 10),
                new TerminalSize(1, 2), ' ');
    }

    @Test
    void drawLives(){
        // given

        int x = Constraints.HEIGHT - 10;
        Position position1 = new Position(x - Player.raio*0, Player.raio + 5);
        Position position2 = new Position(x - Player.raio*2, Player.raio + 5);
        Position position3 = new Position(x - Player.raio*4, Player.raio + 5);

        // when
        hud.drawLives(3);

        // then
        Mockito.verify(hud, Mockito.times(1)).drawPlayer(position1);
        Mockito.verify(hud, Mockito.times(1)).drawPlayer(position2);
        Mockito.verify(hud, Mockito.times(1)).drawPlayer(position3);

    }

    @Test
    void drawPlayer(){

    }
}
