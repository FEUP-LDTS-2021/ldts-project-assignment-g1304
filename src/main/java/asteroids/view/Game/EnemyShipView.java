package asteroids.view.Game;

import asteroids.model.Entities.EnemyShip;

public class EnemyShipView extends View {
    private final EnemyShip enemyShip;

    private static final String[] enemyShipDraw=new String[]{
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
        super(enemyShip.getSize().getSize(), enemyShip.getSize().getSize());
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