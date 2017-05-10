package es.upm.dit.adsw.ej5;

import java.io.IOException;

/**
 * Escenarios que fuerzan el comportamiento del juego.
 *
 * @author jose a. manas
 * @version 26-Mar-17.
 */
public class StressTest2 {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        Game.setState(Game.TESTING);
        Game.init();
        Game.setAppleListMonitor(new AppleListMonitor());

        AppleGenerator appleGenerator = new AppleGenerator(500);
        appleGenerator.start();

        {
            Serpent serpent = new Serpent(10, 10, 20);
            serpent.move(0.2);
            Game.setSerpent(serpent);
            new Thread(serpent).start();
        }
        {
            Serpent serpent = new Serpent(100, 100, 30);
            serpent.move(0.2);
            Game.setSerpent(serpent);
            new Thread(serpent).start();
        }

//        BallGenerator ballGenerator = new BallGenerator(1000, 100);
//        ballGenerator.start();
    }

}
