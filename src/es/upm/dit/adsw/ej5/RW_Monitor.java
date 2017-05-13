package es.upm.dit.adsw.ej5;

/**
 * Monitor de lecturas-escrituras.
 *
 * @author jose a. manas
 * @version 20-Mar-16.
 */

//PREGUNTAR DIFERENCIA ENTRE PADRE E HIJO
public class RW_Monitor extends RW_Monitor_0 {
    private int cola;
    private int lectores;
    private int escritores;
    /**
     * Getter.
     *
     * @return numero de lectores autorizados en este momento.
     */
    public int getNReadersIn() {

        return lectores;
    }

    /**
     * Getter.
     *
     * @return numero de escritores autorizados en este momento.
     */
    public int getNWritersIn() {
        return escritores;
    }

    /**
     * Solicitud de permiso para hacer una lectura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public synchronized void openReading() {
        //PREGUNTAR SI DEBE HABER SOLO UN LECTOR
        System.out.printf("OpenReading PRE : Escritor : %d Cola: %d Lectores: %d \n",escritores,cola,lectores);
        while(escritores > 0 || cola > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lectores++;
        System.out.printf("OpenReading POST : Escritor : %d Cola: %d Lectores: %d \n",escritores,cola,lectores);

    }

    /**
     * Devolucion del permiso de lectura.
     *
     * @throws IllegalMonitorStateException si no hay algun lector dentro.
     */
    public synchronized void closeReading() throws IllegalMonitorStateException {
        System.out.println("closeReading()");
        if(lectores == 0){
            throw  new IllegalArgumentException();
        }
        lectores--;
        System.out.printf("closeReading(): Escritor : %d Cola: %d Lectores: %d \n", escritores, cola, lectores);
        //¿DEBO NOTIFICAR?
    }

    /**
     * Solicitud de permiso para hacer una escritura.
     * La thread que llama se queda esperando hasta que pueda entrar.
     */
    public synchronized void openWriting() {
        System.out.println("OpenWritting");
        System.out.printf("PREO Escritor : %d Cola: %d \n",escritores,cola);

        if(escritores != 0){
            cola++;
        }

        System.out.printf("POSTO Escritor : %d Cola: %d \n",escritores,cola);

        while(cola != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        escritores++;
    }

    /**
     * Devolucion del permiso de escritura.
     *
     * @throws IllegalMonitorStateException si no hay un escritor.
     */
    public synchronized void closeWriting() throws IllegalMonitorStateException {
        if(escritores == 0){
            throw new IllegalArgumentException();
        }
        System.out.println("closeWriting()");
        System.out.printf("CloseWri: PRE Escritor : %d Cola: %d \n",escritores,cola);

        if(cola != 0){
            cola--;
        }else{
            escritores--;
        }
        System.out.printf("CloseWri: POST Escritor : %d Cola: %d \n",escritores,cola);

        notifyAll();

    }
}
