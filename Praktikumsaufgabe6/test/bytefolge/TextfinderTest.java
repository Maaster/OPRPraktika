/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bytefolge;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Maaster
 */
public class TextfinderTest {
    
    /**
     * InputStream.
     */
    private String stream;
    
    /**
     * Texte.
     */
    private Textfinder text3;
    
    /**
     * Texte.
     */
    private Textfinder text4;
    
    /**
     * Texte.
     */
    private Textfinder text5;
    
    /**
     * Woerter, SollErgebnis.
     */
    private HashSet<String> woerter;
 
    
    /**
     * Neuer Textfinder.
     */
    public TextfinderTest() {
    }
    
    /**
     * Test-Setup.
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
        stream = "H>-p>Chd>(9&i@g0P6=X,(cb0E(eh9#Chd";
        text3 = new Textfinder(new ByteArrayInputStream(stream.getBytes()), 3);
        text4 = new Textfinder(new ByteArrayInputStream(stream.getBytes()), 4);
        text5 = new Textfinder(new ByteArrayInputStream(stream.getBytes()), 5);
    }

    /**
     * Test of gibWoerter method, of class Textfinder.
     * @throws java.lang.Exception Ausnahme.
     */
    @Test
    public void testGibWoerter() throws Exception {
        
        woerter = new HashSet<>();
        woerter.add("g0P6");
        woerter.add("Chd");
        woerter.add("cb0E");
        woerter.add("eh9");
       
 
        assertEquals(woerter, text3.gibWoerter());
 
        woerter = new HashSet<>();
        woerter.add("g0P6");
        woerter.add("cb0E");
        assertEquals(woerter, text4.gibWoerter());
 
        woerter = new HashSet<>();
        assertEquals(woerter, text5.gibWoerter());
        
        
    }

    /**
     * Test of gibHaeufigkeit method, of class Textfinder.
     * @throws java.lang.Exception Ausnahme.
     */
    @Test
    public void testGibHaeufigkeit() throws Exception {
//        assertEquals(2, text3.gibHaeufigkeit("Chd"));
//        assertEquals(0, text3.gibHaeufigkeit("hf2z"));
//        assertEquals(1, text3.gibHaeufigkeit("cb0E"));
 
       
        assertEquals(1, text4.gibHaeufigkeit("g0P6"));
        assertEquals(1, text4.gibHaeufigkeit("cb0E"));
        assertEquals(0, text4.gibHaeufigkeit(".,mnadfk4kjb4"));
 
        assertEquals(0, text5.gibHaeufigkeit("hf2z"));
        assertEquals(0, text5.gibHaeufigkeit("cb0E"));
    }
    
}
