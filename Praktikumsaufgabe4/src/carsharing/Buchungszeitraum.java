package carsharing;

/**
 *
 * @author Maaster
 */
public class Buchungszeitraum {
    
    /**
     * Anfangsdatum der Buchung.
     */
    private double anfangsDatum;
    
    /**
     * Enddatum der Buchung.
     */
    private double endDatum;
    
    /**
     * Erstellt einen neuen Buchungszeitraum.
     * @param anfang Anfangsdatum
     * @param ende Enddatum
     */
    public Buchungszeitraum(double anfang, double ende) {
        anfangsDatum = anfang;
        endDatum = ende;
    }
    
    /**
     * Gibt das Anfangsdatum zurück.
     * @return Anfangsdatum
     */
    public double gibAnfangsDatum() {
        return anfangsDatum;
    }
    
    /**
     * Gibt das Enddatum zurück.
     * @return Enddatum
     */
    public double gibEndDatum() {
        return endDatum;
    }
}
