package carsharing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Maaster
 */
public class Fahrzeugmanager {
    
    /**
     * ArrayList aller Fahrzeuge.
     */
    private ArrayList<Fahrzeug> fahrzeugListe;
    
    
    /**
     * Erstellt einen neuen Fahrzeugmanager.
     * Enthält am Anfang keine Fahrzeuge
     */
    public Fahrzeugmanager() {
        fahrzeugListe = new ArrayList();
    }
    
    /**
     * Fügt neues Fahrzeug in den Manager hinzu.
     * @param name Name des Fahrzeuges
     * @param standort Standort des Fahrzeuges
     */
    public void fuegeFahrzeugHinzu(String name, String standort) {
        
        if (fahrzeugListe.isEmpty()) {
            fahrzeugListe.add(new Fahrzeug(name, standort));
            
        } else {
            boolean nichtImManager = true;
            
            for (Iterator<Fahrzeug> it = fahrzeugListe.iterator();
                    it.hasNext();) {
                
                if (name.equals(it.next().gibName())) {
                    nichtImManager = false;
                }
            }
            if (nichtImManager) {
                fahrzeugListe.add(new Fahrzeug(name, standort));
            }
        }
    }
    
    /**
     * Gibt alle Fahrzeugnamen alphabetisch zurück.
     * @return ArrayList mit alphabetisch sortierten Fahrzeugnamen
     */
    public ArrayList<String> gibFahrzeugnamen() {
        ArrayList<String> namen = new ArrayList();
        
        Iterator<Fahrzeug> it = fahrzeugListe.iterator();
        
        while (it.hasNext()) {
            Fahrzeug aktuellesFahrzeug = it.next();
            namen.add(aktuellesFahrzeug.gibName());
        }
        
        Collections.sort(namen);
        
        return namen;
    }
    
    /**
     * Gibt alle Fahrzeugnamen am übergebenen Standort zurück.
     * @param standort Standort der Fahrzeuge
     * @return ArrayList mit allen Fahrzeugnamen am gewählten Standort
     */
    public ArrayList<String> gibFahrzeugnamen(String standort) {
        ArrayList<String> namen = new ArrayList();
        
        Iterator<Fahrzeug> it = fahrzeugListe.iterator();
        
        while (it.hasNext()) {
            Fahrzeug aktuellesFahrzeug = it.next();
            
            if (aktuellesFahrzeug.gibStandort().equals(standort)) {
                namen.add(aktuellesFahrzeug.gibName());
            }
        }
        
        Collections.sort(namen);
        
        return namen;
    }
    
    /**
     * Bucht Fahrzeug mit angegebenen Namen für den angegebenen Zeitraum.
     * @param name Name des Fahrzeuges
     * @param anfang Anfangsdatum
     * @param ende Enddatum
     * @return true genau dann, wenn Fahrzeug buchbar ist
     */
    public boolean bucheFahrzeug(String name, String anfang, String ende) {
        Iterator<Fahrzeug> it = fahrzeugListe.iterator();
        boolean fahrzeugGefunden = false;
        boolean istBuchbar = false;
        
        while (it.hasNext() && !fahrzeugGefunden) {
            Fahrzeug aktuellesFahrzeug = it.next();
            
            if (aktuellesFahrzeug.gibName().equals(name)) {
                fahrzeugGefunden = true;
                istBuchbar = aktuellesFahrzeug.istBuchbar(anfang, ende);
            }
        }
        
        return istBuchbar;
    }
    
    /**
     * Liefert alle Namen der Fahrzeuge am angegebenen Standort, die verfügbar
     * sind.
     * @param standort Standort
     * @param anfang Anfangsdatum
     * @param ende Enddatum
     * @return ArrayList mit allen verfügbaren Fahrzeugen
     */
    public ArrayList<String> gibVerfuegbareFahrzeuge(String standort,
            String anfang, String ende) {
        
        ArrayList<String> namen = new ArrayList();
        
        Iterator<Fahrzeug> it = fahrzeugListe.iterator();
        
        while (it.hasNext()) {
            Fahrzeug aktuellesFahrzeug = it.next();
            
            if (aktuellesFahrzeug.gibStandort().equals(standort)
                    && aktuellesFahrzeug.istBuchbar(anfang, ende)) {
                namen.add(aktuellesFahrzeug.gibName());
            }
        }
        
        Collections.sort(namen);
        
        return namen;
    }
    
}
