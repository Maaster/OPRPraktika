package carsharing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Maaster
 */
public class Fahrzeug {
    
    /**
     * Name des Fahrzeuges.
     */
    private String name;
    
    /**
     * Standort des Fahrzeuges.
     */
    private String standort;
    
    /**
     * Liste, die alle Buchungszeiträume speichert.
     */
    private ArrayList<Buchungszeitraum> zeitraeume;
    
    /**
     * Erzeugt ein neues Fahruzeug mit dem angegebenen Namen und Standort.
     * @param name Name des Fahrzeuges
     * @param standort Standort des Fahrzeuges
     */
    public Fahrzeug(String name, String standort) {
        this.name = name;
        this.standort = standort;
        zeitraeume = new ArrayList();
    }
    
    /**
     * Gibt den Namen des Fahrzeuges wieder.
     * @return Namen des Fahrzeuges
     */
    public String gibName() {
        return name;
    }
    
    /**
     * Gibt den Standort des Fahrzeuges wieder.
     * @return Standort des Fahrzeuges
     */
    public String gibStandort() {
        return standort;
    }
    
    /**
     * Überprüft, ob das Fahrzeug bis zum angebenen Datum buchbar ist.
     * @param anfangsDatum Anfangsdatum der Buchung
     * @param endDatum Enddatum der Buchung
     * @return true genau dann, wenn das Fahrzeug verfügbar ist in dem 
     * angegebenen Zeitraum
     */
    public boolean istBuchbar(String anfangsDatum, String endDatum) {
        boolean buchbar = true;
        
        //Datumsformat: JJJJ/MM/TT
         
        //Alles entfernen, was keine Zahl ist.
        anfangsDatum = anfangsDatum.replaceAll("\\D", "");
        endDatum = endDatum.replaceAll("\\D", "");

        Iterator<Buchungszeitraum> it = zeitraeume.iterator();

        while (it.hasNext() && buchbar) {

            Buchungszeitraum aktuellerZeitraum = it.next();
            
            if (Double.parseDouble(anfangsDatum) 
                    > aktuellerZeitraum.gibAnfangsDatum()
                && Double.parseDouble(anfangsDatum) 
                    < aktuellerZeitraum.gibEndDatum()) {
                buchbar = false;
            } else if (Double.parseDouble(endDatum) 
                    > aktuellerZeitraum.gibAnfangsDatum()
                && Double.parseDouble(endDatum) 
                    < aktuellerZeitraum.gibEndDatum()) {
                buchbar = false;
            } else if (Double.parseDouble(anfangsDatum) 
                    < aktuellerZeitraum.gibAnfangsDatum()
                && Double.parseDouble(endDatum) 
                    > aktuellerZeitraum.gibEndDatum()) {
                buchbar = false;
            }
            
        }
        
        if (buchbar) {
            double anfang = Double.parseDouble(
                    anfangsDatum.replaceAll("\\D", ""));
            double ende = Double.parseDouble(endDatum.replaceAll("\\D", ""));
            
            zeitraeume.add(new Buchungszeitraum(anfang, ende));
        }
        
        return buchbar;
    }
}
