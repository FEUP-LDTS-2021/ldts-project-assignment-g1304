package asteroids.view.Game;

import asteroids.view.Color;
import asteroids.Constants;
import asteroids.model.Entities.Player;
import asteroids.model.GameModel;
import asteroids.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Hud extends View{
    private final GameModel model;

    private final String[] scoreString = {
            "#######  ######  ######  ######  #######      ",
            "##      ##      ##    ## ##   ## ##      ##   ",
            "####### ##      ##    ## ######  #####        ",
            "     ## ##      ##    ## ##   ## ##      ##   ",
            "#######  ######  ######  ##   ## #######      "};

    private final List<String[]> numbers;

    public Hud(GameModel model){
        super(1, 2);
        this.model = model;
        numbers = new ArrayList<>();
        try {
            loadFonts("Font/numbers.font");
        } catch (URISyntaxException | IOException e) {
            System.out.println("Nao foi possivel ler o ficheiro");
        }
    }

    private void loadFonts(String path) throws URISyntaxException, IOException {

        InputStreamReader file = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path));
        BufferedReader bufferedReader = new BufferedReader(file);

        String n = bufferedReader.readLine();
        int numberHeight = Integer.parseInt(n);

        int fileLine = 0;
        while (bufferedReader.ready()){
            int number = fileLine/numberHeight;
            if(numbers.size() <= number)
                numbers.add(new String[numberHeight]);

            int numberLine = fileLine % numberHeight;
            numbers.get(number)[numberLine] = bufferedReader.readLine();
            fileLine++;
        }
    }

    @Override
    public void draw() throws IOException {
        drawScore(model.getPlayer().getScore());

        drawLives(model.getPlayer().getLives());
    }

    void drawLives(int nLives){
        int x = (int)(Constants.HEIGHT - Player.WIDTH - 5);
        for (int i = 0; i <= nLives-1 ; i++)
            drawPlayer(new Position(x - Player.WIDTH*i, Player.HEIGHT/2+ 5));
    }

    void drawPlayer(Position position){
        Player player = new Player(position);
        player.setAngle(-Math.PI/2);

        PlayerView playerView = new PlayerView(player);
        playerView.setGraphics(getGraphics());
        playerView.draw();
    }

    void drawScore(int score){
        setBackgroundColor(Color.White);

        // draw "Score" string
        draw(getScoreString(), 10, 10);

        // draw score Number
        int x = 46*charWidth + 10;
        String number = String.format("%05d", score);
        for(char c : number.toCharArray()){
            int digit = Integer.parseInt(""+c);
            draw(getNumbers().get(digit), x, 10);
            x+=getNumbers().get(digit)[0].length()*charWidth+2;
        }

    }

    void draw(String [] asciiArt, int paddingX, int paddingY){
        int y = 0;
        for (String line : asciiArt) {
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x)=='#')
                    getGraphics().fillRectangle(new TerminalPosition(x*charWidth + paddingX,
                                                                        y*charHeight+ paddingY),
                                            new TerminalSize(charWidth, charHeight), ' ');
            }
            y++;
        }
    }

    public List<String[]> getNumbers() {
        return numbers;
    }

    public String[] getScoreString() {
        return scoreString;
    }
}
