package asteroids.view.Game;

import asteroids.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Entities.EnemyShip;

public class EnemyShipView extends View {
    private final EnemyShip enemyShip;
    private final int CHAR_WIDTH = 2;
    private final int CHAR_HEIGHT = 2;

    public static final String[] enemyShipDraw=new String[]{
            "        BBB",
            "      BBBBbbB",
            "     BBBBBBbbB",
            "    BBBBBBBBbBB",
            "    BBBBBBBBBBB",
            "    BBBBBBBBBBB",
            "  ccccccccccccccc",
            " CCCCCCCCCCCCCCCCC",
            "pGpGpppGpppGpppGpGp",
            " PpCcccccccccccCpP",
            "  PpCcccccccccCpP",
            "    CcccccccccC",
    };

    private static final char DarkBlue = 'B';
    private static final char LightBlue = 'b';
    private static final char LightGray = 'c';
    private static final char MediumGray = 'C';
    private static final char DarkGray = 'p';
    private static final char LightBlack = 'P';
    private static final char Red = 'G';

    public EnemyShipView(EnemyShip enemyShip){
        this.enemyShip = enemyShip;
    }
    @Override
    public void draw() {

        int x = (int)enemyShip.getPosition().getX();
        int y = (int)enemyShip.getPosition().getY();
        for (String line : enemyShipDraw){
            drawLine(line, x, y);
            y+=CHAR_HEIGHT;
        }
    }

    public void drawLine(String line, int startX, int startY){
        int x = 0;
        for (char c : line.toCharArray()){
            if (c!=' '){
                setColor(c);
                graphics.fillRectangle(new TerminalPosition(startX+x, startY),
                                        new TerminalSize(CHAR_WIDTH, CHAR_HEIGHT), ' ');
            }
            x+=CHAR_WIDTH;
        }
    }

    public void setColor(char color){
        switch (color){
            case DarkBlue -> setBackgroundColor(Color.DarkBlue);
            case LightBlue -> setBackgroundColor(Color.LightBlue);
            case LightGray -> setBackgroundColor(Color.LightGray);
            case MediumGray -> setBackgroundColor(Color.MediumGray);
            case DarkGray -> setBackgroundColor(Color.DarkGray);
            case LightBlack -> setBackgroundColor(Color.LightBlack);
            case Red -> setBackgroundColor(Color.Red);
        }
    }

    public EnemyShip getEnemyShip() {
        return enemyShip;
    }
}