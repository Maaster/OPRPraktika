/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

import java.util.HashMap;

/**
 *
 * @author Maaster
 */
public class Variablenbelegung {

    /**
     * Die Belegungen in dieser Variablenbelegung.
     */
    private HashMap<String, Integer> belegungen;
    
    /**
     * Erstellt eine neue Variablenbelegung.
     */
    public Variablenbelegung() {
        this.belegungen = new HashMap();
        
    }
    
    /**
     * Belegt eine Variable mit einem Wert.
     * @param var Veriable, die zu belegen ist.
     * @param wert Wert, mit dem die Variable zu belegen ist.
     */
    public void belege(String var, int wert) {
        belegungen.put(var, wert);
    }
    
    /**
     * Gibt den Wert für die Variable wieder.
     * @param var Variable, für den der Wert gegeben werden soll
     * @return Wert der Variable.
     */
    public int gibWert(String var) {
        return belegungen.get(var);
    }
}
