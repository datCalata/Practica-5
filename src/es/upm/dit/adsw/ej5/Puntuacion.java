package es.upm.dit.adsw.ej5;

import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Contador de puntuacion.
 *
 * @author jose a. manas
 * @version 10-Apr-16.
 */
public class Puntuacion implements Runnable, Screen.Thing {
    private final Font font;
    private int puntos;

    /**
     * Constructor.
     */
    public Puntuacion() {
        font = new Font("SansSerif", Font.BOLD, 18);
        puntos = 100;
        Game.getScreen().add(this);
    }

    /**
     * Suma puntos.
     *
     * @param n a sumar.
     */
    public synchronized void increment(int n) {
        puntos += n;
    }

    /**
     * Resta puntos.
     *
     * @param n a restar.
     */
    public synchronized void decrement(int n) {
        puntos -= n;
    }

    /**
     * Cada segundo resta 1 punto.
     */
    @Override
    public void run(){
        while(true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            decrement(1);
        }
    }

    /**
     * Se imprime en pantalla.
     *
     * @param g pantalla.
     */
    @Override
    public void paint(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("puntos: " + puntos, 10, 20);
    }
}
