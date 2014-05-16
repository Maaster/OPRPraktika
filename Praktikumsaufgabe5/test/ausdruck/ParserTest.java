/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

import java.text.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



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
     * Die sollMessage.
     */
    private String sollMessage;
    
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
    public void testParse() throws ParseException {
        
        parser = new Parser();
        
        // c + 25
        sollAusdruck = new OperatorAusdruck(
                new Variable("c"),
                '+', 
                new Konstante(25));

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
        
        // (a + 1
        
        sollMessage = "Unerwartetes Ende";
        
        try {
            parser.parse("(a + 1");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
        }
        
        
        // ( )
        
        sollMessage = "Ungültiges Token )";
        
        try {
            parser.parse("( )");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(2, e.getErrorOffset());
        }
        
        
        // ( + )
        
        sollMessage = "Ungültiges Token +";
        
        try {
            parser.parse("( + )");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(2, e.getErrorOffset());
        }
        
        // (( 10) 10)
        
        sollMessage = "Ungültiges Token 10";
        
        try {
            parser.parse("(( 10) 10)");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(5, e.getErrorOffset());
        }
        
        
        // 1a
        
        sollMessage = "Ungültiges Token 1a";
        
        try {
            parser.parse("1a");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(1, e.getErrorOffset());
        }
        
        // a 1
        
        sollMessage = "Ungültiges Token 1";
        
        try {
            parser.parse("a 1");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(2, e.getErrorOffset());
        }
        
        // 2 - )
        
        sollMessage = "Ungültiges Token )";
        
        try {
            parser.parse("2 - )");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(3, e.getErrorOffset());
        }
        
        // 2 * (s - t * a_b)
        
        sollMessage = "Ungültiges Token a_b";
        
        try {
            parser.parse("2 * (s - t * a_b)");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(8, e.getErrorOffset());
        }
        
        // 2 * (s - )
        
        sollMessage = "Ungültiges Token )";
        
        try {
            parser.parse("2 * (s - )");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(6, e.getErrorOffset());
        }
        
        // 2 * (s - t) (
        
        sollMessage = "Ungültiges Token (";
        
        try {
            parser.parse("2 * (s - t) (");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
            Assert.assertEquals(8, e.getErrorOffset());
        }
        
        // ""
        
        sollMessage = "Unerwartetes Ende";
        
        try {
            parser.parse("");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
        }
        
        // 2 * (s - t * 5
        
        sollMessage = "Unerwartetes Ende";
        
        try {
            parser.parse("2 * (s - t * 5");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
        }
        
        // 2 * (3 * (i + 4)
        
        sollMessage = "Unerwartetes Ende";
        
        try {
            parser.parse("2 * (3 * (i + 4)");
            Assert.fail();
        } catch (ParseException e) {
            Assert.assertEquals(sollMessage, e.getMessage());
        }
    }
}
