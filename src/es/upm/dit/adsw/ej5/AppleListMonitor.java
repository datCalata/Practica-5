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
            System.err.println("No se aniadio manzana");
            monitor.closeWriting();
        }
        appleList.add(apple);
        System.out.println("Manzana Aniadida \n");
        monitor.closeWriting();
    }

    /**
     * Tenemos una manzana menos.
     *
     * @param apple
     */

    public void remove(Apple apple) {
        /*monitor.openReading();
        if(!appleList.contains(apple)){
            System.err.print("NO EXISTE MANZANA A BORRAR");
            monitor.closeReading();
            return;
        }
        monitor.closeReading();*/
        monitor.openWriting();
        if(!appleList.contains(apple)){
            System.err.println("NO EXISTE MANZANA A BORRAR");
            monitor.closeWriting();
            return;
        }
        appleList.remove(apple);
        System.out.println("La manzana ha sido eliminada");
        monitor.closeWriting();
    }

    /**
       * Informa de si hay alguna manzana cerca del segmento P1-P2.
   *
     * @param p1
     * @param p2
     * @return la manzana cercana, si la hubiera; null si no.
     * Estamos teniendo un problema con la concurrencia, si usamos un foreach en lugar
     * de un iterator, o bien copiamos la lista como en el codigo comentado y bien hay un error
     * de concurrencia.
     */
    public Apple getCloseApple(XY p1, XY p2) {
        monitor.openReading();

        /*for(Apple appleL : new ArrayList<>(appleList)){
            if(appleL.getXY().isCloseTo(P1,P2)){
                monitor.closeReading();
                return appleL;
            }
        }*/

        Iterator<Apple> iter = appleList.iterator();
        while(iter.hasNext()){
            Apple cu = iter.next();
            /*Apple cu2 = iter.next();
            if(!cu.equals(cu2)){
                System.err.println("ERRRORO BUEENO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }*/
            if(cu.getXY().isCloseTo(p1,p2)){
                monitor.closeReading();
                return cu;
            }
        }
        monitor.closeReading();
        return null;
    }

    /**
     * Actua sobre una manzana cerca del segmento p1-p2.
     *
     * @param p1
     * @param p2
     * @return la manzana cercana, si la hubiera; null si no.
     */
    public Apple hitCloseApple(XY p1, XY p2) {
        Apple manzana = getCloseApple(p1,p2);
        if(manzana == null){
            return null;
        }
        remove(manzana);
        return manzana;
    }

}
