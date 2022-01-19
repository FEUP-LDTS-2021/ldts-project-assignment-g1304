package asteroids.view.Game;

import asteroids.model.Entities.Player;


public class PlayerView extends View {

    private final Player player;
    private static final int CHAR_WIDTH = 2;
    private static final int CHAR_HEIGHT = 2;

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
    public void draw(){
        int x = (int)player.getPosition().getX();
        int y = (int)player.getPosition().getY();
        drawImage(playerDraw, x, y);
    }

    public Player getPlayer() {
        return player;
    }
}
