/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bytefolge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Maaster
 */
public class Textfinder {
    
    /**
    * Datenquelle, aus der zu lesen ist.
    */
    private BufferedReader datenquelle;
     
    /**
     * Mindestlänge der Wörter.
     */
    private int mindestlaenge;
     
    /**
     * Aktuelles Zeichen, das zu merken ist.
     */
    private int aktuellesZeichen;
    
    /**
     * HashMap der Wörter.
     */
    private HashMap<String, Integer> woerter;
    
     
     
    /**
     * Erstellt einen neuen Textfinder.
     * @param datenquelle Datenquelle, aus der gelesen werden soll
     * @param laenge Mindestlänge der Wörter
     * @throws java.io.IOException IO-Exception.
     */
    public Textfinder(InputStream datenquelle, int laenge) throws IOException {
        mindestlaenge = laenge;
        this.datenquelle = new BufferedReader(
                            new InputStreamReader(datenquelle));
        
        woerter = new HashMap();
        
        String wort = "";
        
        int anzahlWoerter = 0;
        
        char aktuellerChar;
        
        if (mindestlaenge > 0) {
            
            aktuellesZeichen = datenquelle.read();
            
            while (aktuellesZeichen != -1) {
                
                aktuellerChar = (char) aktuellesZeichen;
                
                if (Character.isLetter(aktuellerChar)) {
                    
                    wort = wort + aktuellerChar;
                    
                    aktuellerChar = (char) datenquelle.read();
                    
                    while (Character.isLetterOrDigit(aktuellerChar)) {
                        
                        wort = wort + aktuellerChar;
                        
                        aktuellerChar = (char) datenquelle.read();
                    }
                    
                    if (wort.length() >= mindestlaenge) {
                        if (woerter.containsKey(wort)) {
                            anzahlWoerter = woerter.get(wort);
                            woerter.put(wort, anzahlWoerter + 1);
                        } else {
                            woerter.put(wort, 1);
                        }
                    }
                        
                    wort = "";
                }
            
                aktuellesZeichen = datenquelle.read();
            }
        }
    }
    
    /**
     * Gibt die Wörter wieder, die mind. so lang wie die angegebene Mindest-
     * länge ist.
     * @return Wörter als HashSet, die länger oder gleich lang wie Mindestlaenge
     * sind
     * @throws IOException InputOutput-Exception
     */
    public Set gibWoerter() throws IOException {
        
        return woerter.keySet();
    }
    
    /**
     * Gibt die Häufigkeit des Übergebenen Wortes wieder.
     * @param wort Zu suchendes Wort
     * @return Anzahl, wie oft das gesucht Wort vorkommt.
     * @throws IOException InputOutput-Exception
     */
    public int gibHaeufigkeit(String wort) throws IOException {
        int ergebnis = 0;
        
        if(woerter.containsKey(wort)) {
            ergebnis = woerter.get(wort);
        }
        
        return ergebnis;
    }
}

