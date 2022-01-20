package asteroids.view.Game;

import asteroids.model.Entities.Player;
import asteroids.model.Vector2d;
import asteroids.view.Color;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;


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

    public static final String[] playerFlamesDraw = new String[]{
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
            " OYO  CWbWC  OYO ",
            " OYO   CCC   OYO "
    };

    public PlayerView(Player player){
        super(CHAR_WIDTH,CHAR_HEIGHT);
        this.player = player;
    }

    @Override
    public void draw(){
        int x = (int)player.getPosition().getX();
        int y = (int)player.getPosition().getY();
        double angle = player.getAngle()+Math.PI/2.0;

        drawShadow(angle, x, y);

        String[] draw = player.isAccelerating()?playerFlamesDraw:playerDraw;
        drawPlayer(draw, angle, x, y);


    }

    public void drawShadow(double angle, int x, int y){
        setBackgroundColor(Color.White);


        double midX = playerDraw[0].length()/2.0;
        double midY = playerDraw.length/2.0;

        Vector2d point1 = new Vector2d(9-midX, 3-midY).rotatePoint(angle);
        Vector2d point2 = new Vector2d(1-midX, 11-midY).rotatePoint(angle);
        Vector2d point3 = new Vector2d(15-midX, 11-midY).rotatePoint(angle);

        getGraphics().fillTriangle(new TerminalPosition((int)(point1.getX()*CHAR_WIDTH+x), (int)(point1.getY()*CHAR_HEIGHT+y)),
                new TerminalPosition((int)(point2.getX()*CHAR_WIDTH+x), (int)(point2.getY()*CHAR_HEIGHT+y)),
                new TerminalPosition((int)(point3.getX()*CHAR_WIDTH+x), (int)(point3.getY()*CHAR_HEIGHT+y)), ' ');
    }

    public void drawPlayer(String[] draw, double angle, int playerX, int playerY){
        int midX = draw[0].length()/2;
        int midY = draw.length/2;

        for(int y = 0; y < draw.length; y++){
            for(int x = 0; x < draw[1].length(); x++){
                char c = draw[y].charAt(x);
                if(c!=' '){
                    Vector2d point = new Vector2d(x-midX, y-midY);
                    Vector2d newPoint = point.rotatePoint(angle);

                    setColor(c);
                    graphics.fillRectangle(new TerminalPosition((int)(playerX+newPoint.getX()*CHAR_WIDTH),
                                                            (int)(playerY+newPoint.getY()*CHAR_HEIGHT)),
                            new TerminalSize(CHAR_WIDTH, CHAR_HEIGHT), ' ');
                }
            }
        }


    }



    public Player getPlayer() {
        return player;
    }
}
