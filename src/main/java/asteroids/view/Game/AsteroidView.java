package asteroids.view.Game;

import asteroids.model.Entities.Asteroid;

public class AsteroidView extends View {
    private final Asteroid asteroid;

    private static final String[] asteroidDraw=new String[]{
            "     CCCCCCC",
            "    CPPCCCCCCC",
            "   CCCcCCCCCCCC",
            "  CCCccCCCCCCCC",
            "  CCPPCCCCCccCCC",
            " CCCCCCCCCCcPCCC",
            " PCCCPcCCCCPcCCC",
            "PPCCCPCcCCCCcCCC",
            "PCCcCCPccCCCCCCCC",
            "CCCcCCCPccCCCCCCC",
            "CCCCCCCPcCCCccCP",
            "PCccPCCPccCCCcPP",
            "  PPccPCCCPcCcPP",
            "   CCCPPCCCCPPP",
            "      PPCCCPP"
    };

    public AsteroidView(Asteroid asteroid){
        super(asteroid.getAsteroidSize().getSize(),asteroid.getAsteroidSize().getSize());
        this.asteroid = asteroid;
    }


    @Override
    public void draw(){
        setCharWidth(asteroid.getAsteroidSize().getSize());
        setCharHeight(asteroid.getAsteroidSize().getSize());
        int x = (int)asteroid.getPosition().getX();
        int y = (int)asteroid.getPosition().getY();
        drawImage(asteroidDraw, x, y);
    }

    public Asteroid getAsteroid() {
        return asteroid;
    }
}

