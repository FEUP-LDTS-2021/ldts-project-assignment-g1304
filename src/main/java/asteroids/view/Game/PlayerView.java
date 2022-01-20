package asteroids.view.Game;

import asteroids.model.Entities.Player;
import asteroids.model.Vector2d;
import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import static java.lang.Math.signum;


public class PlayerView extends View {

    private final Player player;

    public static final String[] playerDraw = new String[]{
            "        C        ",
            "       CWC       ",
            "       CWC       ",
            "      CCWCC      ",
            "      CWCWC      ",
            "     WCWbWCW     ",
            "    WWCWbWCWW    ",
            "   WWWCWbWCWWW   ",
            "  WWWWCWbWCWWWW  ",
            " WWWRWCWbWCWRWWW ",
            "WWWWWWCWbWCWWWWWW",
            "WWCWCWCWbWCWCWCWW",
            " CCCC CWbWC CCCC ",
            "      CWbWC      ",
            "       CCC       "
    };

    public PlayerView(Player player){
        super(CHAR_WIDTH,CHAR_HEIGHT);
        this.player = player;
    }

    @Override
    public void draw();

    public void drawShadow(double angle, int x, int y);

    public void drawPlayer(double angle, int playerX, int playerY);



    public Player getPlayer() {
        return player;
    }
}
