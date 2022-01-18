package asteroids.view.screens;

import asteroids.Constants;

import java.util.List;

public class LeaderboardScreen extends InformationScreen {
    private static final int PADDING_X = 3;
    private static final int PADDING_Y = 4;

    public LeaderboardScreen(){
        super(List.of(17), Constants.LEADERBOARD_FILE, PADDING_X, PADDING_Y);
    }
}