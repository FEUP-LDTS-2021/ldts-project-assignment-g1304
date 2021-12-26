package view.Game;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.GameModel;
import view.View;

import java.awt.*;
import java.io.IOException;

public class GameView extends View {

    private PlayerView playerView;
    private GameModel model;

    public GameView(GameModel model) {
        super();
        this.model = model;
        setFont(new Font(Font.MONOSPACED,Font.PLAIN, 1));
        playerView = new PlayerView(model.getPlayer());
    }

    @Override
    public void draw() throws IOException {
        playerView.draw();
    }


    @Override
    public void setGraphics(TextGraphics graphics) {
        super.setGraphics(graphics);
        playerView.setGraphics(graphics);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

}
