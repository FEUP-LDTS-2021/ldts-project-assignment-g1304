package asteroids.view.screens;

import java.util.List;

public class LeaderboardScreen extends InformationScreen {
    private static final int PADDING_X = 3;
    private static final int PADDING_Y = 4;
    private static final String leaderboardPath = "leaderboardDraw.txt";

    public LeaderboardScreen(){
        super(List.of(17), leaderboardPath, PADDING_X, PADDING_Y);
    }
}