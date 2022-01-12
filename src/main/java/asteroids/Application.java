package asteroids;


import asteroids.control.Controller;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            Controller controller = Controller.getInstance();
            controller.run();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
