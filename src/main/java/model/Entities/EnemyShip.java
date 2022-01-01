package model.Entities;

import model.Position;
import model.physics.Vector2d;
import java.util.List;

public class EnemyShip extends MovingObject{


    public EnemyShip(Player player,Position position, Vector2d velocity, double width, double height);

    public void shooting(long dt);
    public boolean isShootingTime(long dt);
    public List<LaserBeam> getLaserBeams();

    public Player getPlayer();

    public long getLastTime();

    @Override
    public void update(long dt);
}
