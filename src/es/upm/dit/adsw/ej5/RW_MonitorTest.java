package es.upm.dit.adsw.ej5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jcala on 16/05/2017.
 */
public class RW_MonitorTest {
    RW_Monitor monitor;

    @Before
    public void setUp(){
        monitor = new RW_Monitor();
    }

    @Test
    public void ReadingTest(){
        monitor.openReading();
        assertEquals(1,monitor.getNReadersIn());
        monitor.openReading();
        assertEquals(2,monitor.getNReadersIn());
        monitor.closeReading();
        monitor.closeReading();
        assertEquals(0,monitor.getNReadersIn());
    }

    @Test
    public void WrittingText(){
        monitor.openWriting();
        assertEquals(1,monitor.getNWritersIn());
        monitor.closeWriting();
        assertEquals(0,monitor.getNWritersIn());

    }

    @Test (expected = IllegalMonitorStateException.class)
    public void writtingException(){
        monitor.closeWriting();
    }

    @Test (expected = IllegalMonitorStateException.class)
    public void readingException(){
        monitor.closeReading();
    }




}