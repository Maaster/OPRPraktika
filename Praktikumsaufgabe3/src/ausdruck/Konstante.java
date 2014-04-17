/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

/**
 *
 * @author Maaster
 */
public class Konstante extends Ausdruck {
    
    /**
     * Erstellt eine neue Konstante.
     * @param wert Wert der Konstanten.
     */
    public Konstante(int wert) {
        
    }

    /**
     * Gibt den Wert dieser Variable.
     * @param belegung Belegung der Variable.
     * @return Den Wert
     */
    @Override
    public int gibWert(Variablenbelegung belegung) {
        return 0;
    }
    
    /**
     * Überprüft, ob das übergeben Objekt das gleiche ist, wie das aktuelle.
     * @param obj das zu prüfende Objekt
     * @return True genau dann, wenn das übergeben Otjekt das gleiche ist,
     * wie das aktuelle.
     */
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
