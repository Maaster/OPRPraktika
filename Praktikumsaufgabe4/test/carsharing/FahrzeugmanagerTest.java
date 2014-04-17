/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carsharing;

import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maaster
 */
public class FahrzeugmanagerTest {
    
    /**
     * Der Fahrzeugmanager.
     */
    private Fahrzeugmanager manager;
    
    /**
     * Das Sollergebnis.
     */
    private ArrayList<String> sollErgebnis;
    
    /**
     * Erzeugt eine neue Test-Klasse für den Fahrzeugmanager.
     */
    public FahrzeugmanagerTest() {
    }
    
    /**
     * Daten initialisieren für den Test.
     */
    @Before
    public void setUp() {
        sollErgebnis = new ArrayList();
        
        manager = new Fahrzeugmanager();
        
        manager.fuegeFahrzeugHinzu("Rathaus 1", "Rathaus");
        manager.fuegeFahrzeugHinzu("Bahnhof 1", "Bahnhof");
        manager.fuegeFahrzeugHinzu("Bahnhof 2", "Bahnhof");
        manager.fuegeFahrzeugHinzu("Bahnhof 3", "Bahnhof");
        
        manager.bucheFahrzeug("Bahnhof 1", "2005/04/14 20:00",
                "2005/04/15 08:00");
        
        manager.bucheFahrzeug("Bahnhof 1", "2005/04/15 18:00",
                "2005/04/16 00:00");
        
        manager.bucheFahrzeug("Bahnhof 2", "2005/04/14 11:00",
                "2005/04/15 12:00");
        
        manager.bucheFahrzeug("Bahnhof 3", "2005/04/15 10:00",
                "2005/04/15 19:00");
    }
    
    /**
     * Test of gibFahrzeugnamen method, of class Fahrzeugmanager.
     */
    @Test
    public void testGibFahrzeugnamen_0args() {
        sollErgebnis.clear();
        
        sollErgebnis.add("Bahnhof 1");
        sollErgebnis.add("Bahnhof 2");
        sollErgebnis.add("Bahnhof 3");
        sollErgebnis.add("Rathaus 1");
        
        Assert.assertEquals(sollErgebnis, manager.gibFahrzeugnamen());
    }

    /**
     * Test of gibFahrzeugnamen method, of class Fahrzeugmanager.
     */
    @Test
    public void testGibFahrzeugnamen_String() {
        sollErgebnis.clear();
        
        sollErgebnis.add("Bahnhof 1");
        sollErgebnis.add("Bahnhof 2");
        sollErgebnis.add("Bahnhof 3");
        
        Assert.assertEquals(sollErgebnis, manager.gibFahrzeugnamen("Bahnhof"));
        
        sollErgebnis.clear();
        
        sollErgebnis.add("Rathaus 1");
        
        Assert.assertEquals(sollErgebnis, manager.gibFahrzeugnamen("Rathaus"));
    }

    /**
     * Test of bucheFahrzeug method, of class Fahrzeugmanager.
     */
    @Test
    public void testBucheFahrzeug() {
        
        Assert.assertEquals(true , manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 09:00", "2005/04/15 10:00"));
        
        Assert.assertEquals(false, manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 09:00", "2005/04/15 11:00"));
        
        Assert.assertEquals(false, manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 11:00", "2005/04/15 18:00"));
        
        Assert.assertEquals(false, manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 18:00", "2005/04/15 20:00"));
        
        Assert.assertEquals(true, manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 19:00", "2005/04/15 20:00"));
        
        Assert.assertEquals(false, manager.bucheFahrzeug(
                        "Bahnhof 3", "2005/04/15 09:00", "2005/04/15 20:00"));
        
    }

    /**
     * Test of gibVerfuegbareFahrzeuge method, of class Fahrzeugmanager.
     */
    @Test
    public void testGibVerfuegbareFahrzeuge() {
        sollErgebnis.clear();
        
        Assert.assertEquals(sollErgebnis, manager.gibVerfuegbareFahrzeuge(
                        "Bahnhof", "2005/04/15 11:30", "2005/04/15 19:00"));
        
        sollErgebnis.clear();
        
        sollErgebnis.add("Bahnhof 1");
        sollErgebnis.add("Bahnhof 2");
        
        Assert.assertEquals(sollErgebnis, manager.gibVerfuegbareFahrzeuge(
                        "Bahnhof", "2005/04/15 12:00", "2005/04/15 18:00"));
        
        sollErgebnis.clear();
        
        sollErgebnis.add("Bahnhof 2");
        sollErgebnis.add("Bahnhof 3");
        
        Assert.assertEquals(sollErgebnis, manager.gibVerfuegbareFahrzeuge(
                        "Bahnhof", "2005/04/15 19:15", "2005/04/15 23:00"));
    }
    
}
