/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

/**
 *
 * @author Maaster
 */
public abstract class Ausdruck {
    
    /**
     * Gibt den Wert dieser Variable.
     * @param belegung Belegung der Variable.
     * @return Den Wert
     */
    public abstract int gibWert(Variablenbelegung belegung);
}
