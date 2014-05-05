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
    
    private int wert;
    
    /**
     * Erstellt eine neue Konstante.
     * @param wert Wert der Konstanten.
     */
    public Konstante(int wert) {
        this.wert = wert;
    }

    /**
     * Gibt den Wert dieser Variable.
     * @param belegung Belegung der Variable.
     * @return Den Wert
     */
    @Override
    public int gibWert(Variablenbelegung belegung) {
        return wert;
    }
    
    /**
     * Überprüft, ob das übergeben Objekt das gleiche ist, wie das aktuelle.
     * @param obj das zu prüfende Objekt
     * @return True genau dann, wenn das übergeben Otjekt das gleiche ist,
     * wie das aktuelle.
     */
    @Override
    public boolean equals(Object obj) {
        return this == (Konstante) obj;
    }
}
