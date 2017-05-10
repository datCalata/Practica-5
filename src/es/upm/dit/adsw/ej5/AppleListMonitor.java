package es.upm.dit.adsw.ej5;

import java.util.ArrayList;
import java.util.List;

/**
 * Mantiene y protege la lista de manzanitas.
 *
 * @author jose a. manas
 * @version 04-Apr-17.
 */
public class AppleListMonitor {
    private final List<Apple> appleList = new ArrayList<>();    // Concurrent modification
    private RW_Monitor_0 monitor = new RW_Monitor_0();
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
        monitor.openReading();
        for(Apple manzana : appleList){
            if(manzana.getXY().isCloseTo(P1,P2)){
                monitor.closeReading();
                return manzana;
            }
        }
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
        }else{
            monitor.openReading();
            appleList.remove(manzana);
            monitor.closeReading();
        }
        return manzana;
    }

}
