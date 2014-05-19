/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bytefolge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
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
     * Erstellt einen neuen Textfinder.
     * @param datenquelle Datenquelle, aus der gelesen werden soll
     * @param laenge Mindestlänge der Wörter
     */
    public Textfinder(InputStream datenquelle, int laenge) {
        mindestlaenge = laenge;
        this.datenquelle = new BufferedReader(
                            new InputStreamReader(datenquelle));
    }
    
    /**
     * Gibt die Wörter wieder, die mind. so lang wie die angegebene Mindest-
     * länge ist.
     * @return Wörter als HashSet, die länger oder gleich lang wie Mindestlaenge
     * sind
     * @throws IOException InputOutput-Exception
     */
    public Set gibWoerter() throws IOException {
        HashSet<String> woerter = new HashSet();
        
        String wort = "";
        
        char aktuellerChar;
        
        if (mindestlaenge > 0) {
            
            aktuellesZeichen = datenquelle.read();
            
            while ((aktuellesZeichen = datenquelle.read()) != -1) {
                
                aktuellerChar = (char) aktuellesZeichen;
                
                if (Character.isLetter(aktuellerChar)) {
                    
                    wort = wort + aktuellerChar;
                    
                    aktuellerChar = (char) datenquelle.read();
                    
                    while (Character.isLetter(aktuellerChar) 
                            || Character.isDigit(aktuellerChar)) {
                        
                        wort = wort + aktuellerChar;
                        
                        aktuellerChar = (char) datenquelle.read();
                    }
                    
                    if (wort.length() >= mindestlaenge) {
                        woerter.add(wort);
                    }
                        
                    wort = "";
                }
            
                aktuellesZeichen = datenquelle.read();
            }
        }
        
        return woerter;
    }
    
    /**
     * Gibt die Häufigkeit des Übergebenen Wortes wieder.
     * @param wort Zu suchendes Wort
     * @return Anzahl, wie oft das gesucht Wort vorkommt.
     * @throws IOException InputOutput-Exception
     */
    public int gibHaeufigkeit(String wort) throws IOException {
        int ergebnis = 0;
        
        String aktuellesWort = "";
        
        char aktuellerChar;
        
        aktuellesZeichen = datenquelle.read();
            
        while (aktuellesZeichen != -1) {

            aktuellerChar = (char) aktuellesZeichen;

            if (Character.isLetter(aktuellerChar)) {

                aktuellesWort = aktuellesWort + aktuellerChar;

                aktuellerChar = (char) datenquelle.read();

                while (Character.isLetter(aktuellerChar) 
                        || Character.isDigit(aktuellerChar)) {

                    aktuellesWort = aktuellesWort + aktuellerChar;

                    aktuellerChar = (char) datenquelle.read();
                }

                if (wort.equals(aktuellesWort)) {
                    ergebnis++;
                }
                
                aktuellesWort = "";
            }
            aktuellesZeichen = datenquelle.read();
        }
        
        return ergebnis;
    }
}
