package es.upm.dit.adsw.ej5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Mantiene y protege la lista de manzanitas.
 *
 * @author jose a. manas
 * @version 04-Apr-17.
 */
public class AppleListMonitor {
    private final List<Apple> appleList = new ArrayList<>();    // Concurrent modification
    private RW_Monitor_0 monitor = new RW_Monitor();
    /**
     * Tenemos una manzana mas.
     *
     * @param apple
     */

    // Preguntar ¿Puede haber duplicados por superposiciones?
    public void add(Apple apple) {
        monitor.openWriting();
        appleList.add(apple);
        System.out.print("Manzana Aniadida \n");
        monitor.closeWriting();
    }

    /**
     * Tenemos una manzana menos.
     *
     * @param apple
     */

    public void remove(Apple apple) {
        monitor.openReading();
        if(!appleList.contains(apple)){
            System.err.print("NO EXISTE MANZANA A BORRAR");
            monitor.closeReading();
            return;
        }
        monitor.closeReading();
        monitor.openWriting();
        appleList.remove(apple);
        System.out.println("La manzana ha sido eliminada");
        monitor.closeReading();
    }

    /**
       * Informa de si hay alguna manzana cerca del segmento P1-P2.
   *
     * @param P1
     * @param P2
     * @return la manzana cercana, si la hubiera; null si no.
     */
    public Apple getCloseApple(XY P1, XY P2) {
        monitor.openWriting();
        Iterator<Apple> iter = appleList.iterator();
        while(iter.hasNext()){
            Apple current = iter.next();
            if(current.getXY().isCloseTo(P1,P2)){
                monitor.closeWriting();
                return current;
            }
        }
        monitor.closeWriting();
        return null;
    }

    /**
     * Actua sobre una manzana cerca del segmento P1-P2.
     *
     * @param P1
     * @param P2
     * @return la manzana cercana, si la hubiera; null si no.
     */
    //¿Actuar es borrar?
    public Apple hitCloseApple(XY P1, XY P2) {
        Apple manzana = getCloseApple(P1,P2);
        if(manzana == null){
            return null;
        }
        monitor.openWriting();
        appleList.remove(manzana);
        monitor.closeWriting();
        return manzana;
    }

}
