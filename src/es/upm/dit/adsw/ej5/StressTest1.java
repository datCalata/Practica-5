package es.upm.dit.adsw.ej5;

import java.io.IOException;

/**
 * Escenarios que fuerzan el comportamiento del juego.
 *
 * @author jose a. manas
 * @version 26-Mar-17.
 */
public class StressTest1 {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        Game.setState(Game.TESTING);
        Game.init();
        Game.setAppleListMonitor(new AppleListMonitor());

        AppleGenerator appleGenerator = new AppleGenerator(200);
        appleGenerator.start();

        BallGenerator ballGenerator = new BallGenerator(150, 50);
        ballGenerator.start();
    }

}
