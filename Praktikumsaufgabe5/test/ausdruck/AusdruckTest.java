package ausdruck;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Maaster
 */
public class AusdruckTest {
    
    /**
     * Das SollErgebnis.
     */
    private Ausdruck sollAusdruck;
    
    /**
     * Die Belegung der Variablen.
     */
    private Variablenbelegung belegung;
    
    /**
     * Erstellt ein neues AusdruckTest-Objekt.
     */
    public AusdruckTest() {
    }

    /**
     * Methode vor dem Test.
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Methode nach dem Test.
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
     * Test of gibWert method, of class Ausdruck.
     */
    @Test
    public void testGibWert() {

        belegung = new Variablenbelegung();

        // c + 25
        belegung.belege("c", 10);

        sollAusdruck = new OperatorAusdruck(
                new Variable("c"),
                '+',
                new Konstante(10));

        Assert.assertEquals(35, sollAusdruck.gibWert(belegung));

        // d + 250 - 300
        belegung.belege("d", 500);

        sollAusdruck = new OperatorAusdruck(
                            new OperatorAusdruck(
                                new Variable("d"), 
                                '+',
                                new Konstante(250)),
                            '-',
                            new Konstante(300));

        Assert.assertEquals(450, sollAusdruck.gibWert(belegung));
        
        // (20 + c) - (10 * 5)
        belegung.belege("c", 2);
        
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
        
        Assert.assertEquals(-28, sollAusdruck.gibWert(belegung));
        
        // c + dz
        belegung.belege("c", 5);
        belegung.belege("dz", 5);
        
        sollAusdruck = new OperatorAusdruck(
                new Variable("c"),
                '+', 
                new Variable("dz"));
        
        Assert.assertEquals(10, sollAusdruck.gibWert(belegung));
        
        // ((2 * 5) - a) + 3
        
        belegung.belege("a", 3);
        
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
        
        Assert.assertEquals(10, sollAusdruck.gibWert(belegung));
        
        // a + b * c - d
        belegung.belege("a", 3);
        belegung.belege("b", 20);
        belegung.belege("c", 10);
        belegung.belege("d", 3);
        
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
        
        Assert.assertEquals(200, sollAusdruck.gibWert(belegung));
    }
}