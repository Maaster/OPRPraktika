/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

/**
 *
 * @author Maaster
 */
public class OperatorAusdruck extends Ausdruck {
    
    /**
     * Erstellt einen neuen OperatorAusdruck.
     * @param links Linke Seite des Ausdrucks.
     * @param mitte Mitte des Ausdrucks.
     * @param rechts Rechte Seite des Ausdrucks.
     */
    public OperatorAusdruck(Ausdruck links, char mitte, Ausdruck rechts) {
        
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
