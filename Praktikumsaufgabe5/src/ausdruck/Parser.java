package ausdruck;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Maaster
 */
public class Parser {
    
    /**
     * Der Splitter, der den Term splittet.
     */
    private StringTokenizer splitter;
    
    /**
     * Liste der Tokens.
     */
    private ArrayList<String> tokens;
    
    /**
     * Erzeugt einen neuen Parser.
     */
    public Parser() {
//        splitter = new StringTokenizer();
    }
    
    /**
     * Parsed den angegeben Term in einen Baum.
     * @param term Term, der zu parsen ist
     * @return Ausdruck, in Baum-Form.
     */
    public Ausdruck parse(String term) throws ParseException {
        
        splitter = new StringTokenizer(term, "+-*/()");
        tokens = new ArrayList();
        Ausdruck ergebnis;
        
        while (splitter.hasMoreTokens()) {
            tokens.add(splitter.nextToken().trim());
        }
        
        ergebnis = parseAusdruck();
        
        if (tokens.isEmpty()) {
            throw new ParseException("Unerwartetes Token" + " " 
                                                    + tokens.get(0), 0);
        }
        
        return ergebnis;
    }
    
    /**
     * Parsed den Ausdruck.
     * @return Ausdruck
     * @throws java.text.ParseException
     */
    public Ausdruck parseAusdruck() throws ParseException {
        Ausdruck links;
        char operator;
        Ausdruck rechts;
        
        links = parseSummand();
        
        if (!tokens.isEmpty() && (tokens.get(0).charAt(0) == '+' 
                || tokens.get(0).charAt(0) == '-')) {
            operator = tokens.remove(0).charAt(0);
        } else {
            throw new ParseException("Unerwartetes Ende", 0);
        }
            
        
        rechts = parseSummand();
        
        OperatorAusdruck ergebnis = new OperatorAusdruck(links,
                                                    operator, rechts);
        
        return ergebnis;
    }
    
    /**
     * Parsed den Summand.
     * @return Ausdruck
     * @throws java.text.ParseException
     */
    public Ausdruck parseSummand() throws ParseException {
        Ausdruck links;
        char operator;
        Ausdruck rechts;
        
        links = parseFaktor();
        
        if (!tokens.isEmpty() && (tokens.get(0).charAt(0) == '+' 
                || tokens.get(0).charAt(0) == '-')) {
            operator = tokens.remove(0).charAt(0);
        } else {
            throw new ParseException("Unerwartetes Ende", 0);
        }
        
        rechts = parseFaktor();
        
        OperatorAusdruck ergebnis = new OperatorAusdruck(links,
                                                operator, rechts);
        
        return ergebnis;
    }
    
    /**
     * Parsed den Faktor.
     * @return Ausdruck
     * @throws java.text.ParseException
     */
    public Ausdruck parseFaktor() throws ParseException {
        Ausdruck ergebnis = null;
        
        if (!tokens.isEmpty()) {
            String aktuellesToken = tokens.remove(0);
            
            if (aktuellesToken.matches("[0-9]")) {
                ergebnis = new Konstante(Integer.parseInt(aktuellesToken));
            } else if (aktuellesToken.matches("[a-zA-Z][a-zA-Z0-9]")) {
                ergebnis = new Variable(aktuellesToken);
            } else if (aktuellesToken.charAt(0) == '(') {
                ergebnis = parseAusdruck();
                
                if (!tokens.isEmpty()) {
                    if (tokens.get(0).charAt(0) == ')') {
                        tokens.remove(0);
                    } else {
                        throw new ParseException("Ungültiges Token"
                                        + " " + aktuellesToken, 0);
                    }
                } else {
                    throw new ParseException("Unerwartetes Ende", 0);
                }
            }
            
        } else {
            throw new ParseException("Unerwartetes Ende", 0);
        }
        
        return ergebnis;
    }
}
