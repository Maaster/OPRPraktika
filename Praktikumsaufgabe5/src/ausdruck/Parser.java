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
     * Anzahl der Tokens.
     */
    private int anzahlTokens;
    
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
     * @throws java.text.ParseException
     */
    public Ausdruck parse(String term) throws ParseException {
        
        splitter = new StringTokenizer(term, "+-*/() ", true);
        tokens = new ArrayList();
        Ausdruck ergebnis;
        
        while (splitter.hasMoreTokens()) {
            tokens.add(splitter.nextToken().trim());
        }
        
        while (tokens.remove("")) {
            
        }
        
        anzahlTokens = tokens.size();
        
        ergebnis = parseAusdruck();
        
        if (!tokens.isEmpty()) {
            throw new ParseException("Ungültiges Token " + tokens.get(0), 
                                        anzahlTokens - tokens.size() + 1);
        }
        
        return ergebnis;
    }
    
    /**
     * Parsed den Ausdruck.
     * @return Ausdruck
     * @throws java.text.ParseException
     */
    public Ausdruck parseAusdruck() throws ParseException {
        boolean hatOperator;
        char operator;
        Ausdruck rechts;
        
        Ausdruck ergebnis = parseSummand();
        
        if (!tokens.isEmpty()) {
            hatOperator = (tokens.get(0).charAt(0) == '+' 
                        || tokens.get(0).charAt(0) == '-');
        } else {
            hatOperator = false;
        }
        
        while (!tokens.isEmpty() && hatOperator) {
            
            operator = tokens.remove(0).charAt(0);
            rechts = parseSummand();
            ergebnis = new OperatorAusdruck(ergebnis, operator, rechts);
            
            if (!tokens.isEmpty()) {
                hatOperator = (tokens.get(0).charAt(0) == '+' 
                        || tokens.get(0).charAt(0) == '-');
            } else {
                hatOperator = false;
            }
        }
        
        return ergebnis;
    }
    
    /**
     * Parsed den Summand.
     * @return Ausdruck
     * @throws java.text.ParseException
     */
    public Ausdruck parseSummand() throws ParseException {
        boolean hatOperator;
        char operator;
        Ausdruck rechts;
        
        Ausdruck ergebnis = parseFaktor();
        
        if (!tokens.isEmpty()) {
            hatOperator = (tokens.get(0).charAt(0) == '*' 
                        || tokens.get(0).charAt(0) == '/');
        } else {
            hatOperator = false;
        }
        
        while (!tokens.isEmpty() & hatOperator) {
            
            operator = tokens.remove(0).charAt(0);
            rechts = parseFaktor();
            ergebnis = new OperatorAusdruck(ergebnis, operator, rechts);
            
            if (!tokens.isEmpty()) {
                hatOperator = (tokens.get(0).charAt(0) == '*' 
                        || tokens.get(0).charAt(0) == '/');
            } else {
                hatOperator = false;
            }
        }
        
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
            
            if (aktuellesToken.matches("[0-9]+")) {
                ergebnis = new Konstante(Integer.parseInt(aktuellesToken));
            } else if (aktuellesToken.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                ergebnis = new Variable(aktuellesToken);
            } else if (aktuellesToken.charAt(0) == '(') {
                ergebnis = parseAusdruck();
                
                if (!tokens.isEmpty()) {
                    if (tokens.get(0).charAt(0) == ')') {
                        tokens.remove(0);
                    } else {
                        throw new ParseException("Ungültiges Token " 
                                                + tokens.get(0), anzahlTokens 
                                                    - tokens.size() + 1);
                    }
                } else {
                    throw new ParseException("Unerwartetes Ende", 0);
                }
            } else {
                throw new ParseException("Ungültiges Token " 
                        + aktuellesToken, anzahlTokens - this.tokens.size());
            }
        } else {
            throw new ParseException("Unerwartetes Ende", 0);
        }
        
        return ergebnis;
        
        
        
        
        
    }
}
