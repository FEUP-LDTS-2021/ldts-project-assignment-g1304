package model.Entities;

import java.util.List;


public class EnemyShipSpawner {

    public EnemyShipSpawner(Player player);
    public List<EnemyShip> getEnemyShips() ;

    public boolean isSpawnTime(long dt);

    public long getTimePassed() ;
    public void update(long dt) ;

    public Player getPlayer();
}
