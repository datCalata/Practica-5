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
        if(appleList.contains(apple)){
            System.out.println("No se aniadio manzana");
            monitor.closeWriting();
        }
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
        monitor.closeWriting();
    }

    /**
       * Informa de si hay alguna manzana cerca del segmento P1-P2.
   *
     * @param P1
     * @param P2
     * @return la manzana cercana, si la hubiera; null si no.
     */
    public Apple getCloseApple(XY P1, XY P2) {
        monitor.openReading();
        for(Apple appleL : new ArrayList<>(appleList)){
            if(appleL.getXY().isCloseTo(P1,P2)){
                monitor.closeReading();
                return appleL;
            }
        }

        /*Iterator<Apple> iter = appleList.iterator();
        while(iter.hasNext()){
            XY current = iter.next().getXY();
            if(current.isCloseTo(P1,P2)){
                monitor.closeReading();
                return iter.next();
            }
        }*/
        monitor.closeReading();
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
        remove(manzana);
        return manzana;
    }

}
