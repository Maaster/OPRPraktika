/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Maaster
 */
public class ParserTest {
    
    /**
     * Das SollErgebnis.
     */
    private Ausdruck sollAusdruck;
    
    /**
     * Der Parser.
     */
    private Parser parser;
    
    /**
     * Erstellt einen neues ParserTest-Objekt.
     */
    public ParserTest() {
    }

    /**
     * Methode vor dem Test.
     * @throws Exception Fehler
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Methode nach dem Test.
     * @throws Exception Fehler
      */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * Methode vor dem Test.
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Methode nach dem Test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class Parser.
     */
    @Test
    public void testParse() {
        
        parser = new Parser();
        
        // c + 25
        sollAusdruck = new OperatorAusdruck(
                new Variable("c"),
                '+', 
                new Konstante(10));

        Assert.assertEquals(sollAusdruck, parser.parse("c + 25"));

        // d + 250 - 300

        sollAusdruck = new OperatorAusdruck(
                            new OperatorAusdruck(
                                new Variable("d"), 
                                '+',
                                new Konstante(250)),
                            '-', 
                            new Konstante(300));

        Assert.assertEquals(sollAusdruck, parser.parse("d + 250 - 300"));
        
        // (20 + c) - (10 * 5)
        
        sollAusdruck = new OperatorAusdruck(
                new OperatorAusdruck(
                        new Konstante(20), 
                        '+',
                        new Variable("c")), 
                '-' , 
                new OperatorAusdruck(
                        new Konstante(10), 
                        '*',
                        new Konstante(5)));
        
        Assert.assertEquals(sollAusdruck, parser.parse("(20 + c) - (10 * 5)"));
        
        // c + dz
        
        sollAusdruck = new OperatorAusdruck(
                new Variable("c"),
                '+', 
                new Variable("dz"));
        
        Assert.assertEquals(sollAusdruck, parser.parse("c + dz"));
        
        // ((2 * 5) - a) + 3
        
        
        sollAusdruck = new OperatorAusdruck(
                new OperatorAusdruck(
                        new OperatorAusdruck(
                                new Konstante(2),
                                '*', 
                                new Konstante(5))
                        , '-',
                        new Variable("a"))
                , '+',
                new Konstante(3));
        
        Assert.assertEquals(sollAusdruck, parser.parse("((2 * 5) - a) + 3"));
        
        // a + b * c - d
        
        sollAusdruck = new OperatorAusdruck(
                new OperatorAusdruck(
                        new Variable("a"),
                        '+',
                        new OperatorAusdruck(
                                new Variable("b"),
                                '*', 
                                new Variable("c")))
                , '-',
                new Variable("d"));
        
        Assert.assertEquals(sollAusdruck, parser.parse("a + b * c - d"));
    }
}
