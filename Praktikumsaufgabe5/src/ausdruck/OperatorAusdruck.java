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
    
    private int ergebnis;
    private Ausdruck linkeSeite;
    private Ausdruck rechteSeite;
    private char operator;
    
    /**
     * Erstellt einen neuen OperatorAusdruck.
     * @param links Linke Seite des Ausdrucks.
     * @param mitte Mitte des Ausdrucks.
     * @param rechts Rechte Seite des Ausdrucks.
     */
    public OperatorAusdruck(Ausdruck links, char mitte, Ausdruck rechts) {
        linkeSeite = links;
        operator = mitte;
        rechteSeite = rechts;
    }

    /**
     * Gibt den Wert dieser Variable.
     * @param belegung Belegung der Variable.
     * @return Den Wert
     */
    @Override
    public int gibWert(Variablenbelegung belegung) {
        
        if (operator == '+') {
            ergebnis = linkeSeite.gibWert(belegung) + rechteSeite.gibWert(belegung);
        } else if (operator == '-') {
            ergebnis = linkeSeite.gibWert(belegung) - rechteSeite.gibWert(belegung);
        } else if (operator == '*') {
            ergebnis = linkeSeite.gibWert(belegung) * rechteSeite.gibWert(belegung);
        } else
            ergebnis = linkeSeite.gibWert(belegung) / rechteSeite.gibWert(belegung);
        
        return ergebnis;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.ergebnis;
        hash = 71 * hash + (this.linkeSeite != null ? this.linkeSeite.hashCode() : 0);
        hash = 71 * hash + this.operator;
        hash = 71 * hash + (this.rechteSeite != null ? this.rechteSeite.hashCode() : 0);
        return hash;
    }
    
    /**
     * Überprüft, ob das übergeben Objekt das gleiche ist, wie das aktuelle.
     * @param obj das zu prüfende Objekt
     * @return True genau dann, wenn das übergeben Otjekt das gleiche ist,
     * wie das aktuelle.
     */
    @Override
    public boolean equals(Object obj) {
        boolean sindGleich = true;
        
        
        if (obj == null) {
            sindGleich = false;
        }
        if (getClass() != obj.getClass()) {
            sindGleich = false;
        }
        
        final OperatorAusdruck other = (OperatorAusdruck) obj;
        
        if (this.ergebnis != other.ergebnis) {
            sindGleich = false;
        }
        if (this.linkeSeite != other.linkeSeite && (this.linkeSeite == null || !this.linkeSeite.equals(other.linkeSeite))) {
            sindGleich = false;
        }
        if (this.operator != other.operator) {
            sindGleich = false;
        }
        if (this.rechteSeite != other.rechteSeite && (this.rechteSeite == null || !this.rechteSeite.equals(other.rechteSeite))) {
            sindGleich = false;
        }
        
        return sindGleich;
    }
}
