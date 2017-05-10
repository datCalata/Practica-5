package es.upm.dit.adsw.ej5;

import java.util.Random;

/**
 * Generador de manzanitas.
 *
 * @author jose a. manas
 * @version 26-Mar-17.
 */
public class AppleGenerator
        extends Thread {
    private final Random random = new Random();
    private final int delta;

    /**
     * Constructor.
     *
     * @param delta intervalo de creacion de manzanitas.
     */
    public AppleGenerator(int delta) {
        this.delta = delta;
    }

    @Override
    public void run() {
        Screen screen = Game.getScreen();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        while (Game.getState() != Game.FINISHED) {
            Nap.sleep(delta);
            int x = random.nextInt(screen.getWidth());
            int y = random.nextInt(screen.getHeight());
            Apple apple = new Apple(x, y);
            appleListMonitor.add(apple);
        }
    }

    public void test(int n) {
        Screen screen = Game.getScreen();
        AppleListMonitor appleListMonitor = Game.getAppleListMonitor();
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(screen.getWidth());
            int y = random.nextInt(screen.getHeight());
            Apple apple = new Apple(x, y);
            appleListMonitor.add(apple);
        }
    }
}
