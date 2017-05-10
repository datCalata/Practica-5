package es.upm.dit.adsw.ej5;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Atiende a un clic del dedo en la pantalla, orientando a la serpiente.
 *
 * @author jose a. manas
 * @version 26-Mar-17.
 */
public class TouchPadListener1
        implements MouseListener {
    @Override
    /**
     * Hacer clic.
     */
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    /**
     * Tocar en la pantalla.
     */
    public void mousePressed(MouseEvent e) {
        Serpent serpent = Game.getSerpent();
        if (serpent == null)
            return;
        XY head = serpent.getHead();
        int dx = e.getX() - head.getX();
        int dy = e.getY() - head.getY();
        serpent.move(Math.atan2(dy, dx));
    }

    @Override
    /**
     * Levantar el dedo de la pantalla.
     */
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    /**
     * Cuando el raton entra en el componente.
     */
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    /**
     * Cuando el raton sale del componente.
     */
    public void mouseExited(MouseEvent e) {
    }

}
