package asteroids.view.Game;

import asteroids.model.Entities.EnemyShip;

public class EnemyShipView extends View {
    private final EnemyShip enemyShip;
    private static final int CHAR_WIDTH = 2;
    private static final int CHAR_HEIGHT = 2;

    public static final String[] enemyShipDraw=new String[]{
            "        AAA",
            "      AAAAbbA",
            "     AAAAAAbbA",
            "    AAAAAAAAbAA",
            "    AAAAAAAAAAA",
            "    AAAAAAAAAAA",
            "  ccccccccccccccc",
            " CCCCCCCCCCCCCCCCC",
            "pRpRpppRpppRpppRpRp",
            " PpCcccccccccccCpP",
            "  PpCcccccccccCpP",
            "    CcccccccccC",
    };

    public EnemyShipView(EnemyShip enemyShip){
        super(CHAR_WIDTH, CHAR_HEIGHT);
        this.enemyShip = enemyShip;
    }

    @Override
    public void draw() {
        int x = (int)enemyShip.getPosition().getX();
        int y = (int)enemyShip.getPosition().getY();
        drawImage(enemyShipDraw, x, y);
    }

    public EnemyShip getEnemyShip() {
        return enemyShip;
    }
}