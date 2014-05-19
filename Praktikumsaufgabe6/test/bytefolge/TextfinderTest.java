/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bytefolge;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
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
    
    FileInputStream bla;
    Textfinder blaa;
    
    public TextfinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of gibWoerter method, of class Textfinder.
     */
    @Test
    public void testGibWoerter() throws Exception {
        File datei = new File("C:\\Users\\Maaster\\Documents\\NetBeansProjects\\OPR6\\src\\bytefolge\\test.txt");
        
        bla = new FileInputStream(datei);
        blaa = new Textfinder(bla, 2);
        
        System.out.println(blaa.gibHaeufigkeit("Hallo"));
        
    }

    /**
     * Test of gibHaeufigkeit method, of class Textfinder.
     */
    @Test
    public void testGibHaeufigkeit() throws Exception {
        
    }
    
}
