package labyrinth;

import java.util.Random;
import util.Punkt;

/**
*
* @author Maaster
*/
public class Labyrinth {

    /**
    * Mögliche Richtungen: Links(-1,0), Hoch(0,-1),
    * Rechts(1,0), Runter(0,1).
    */
    private static final Punkt[] RICHTUNG = {new Punkt(-1, 0),
                        new Punkt(0, -1), new Punkt(1, 0), new Punkt(0, 1) };

    /**
     * Breite des Labyrinthes.
     */
    private int breite;

    /**
     * Höhe des Labyrinthes.
     */
    private int hoehe;

    /**
     * Index, der angibt, wieviele Wände schon gezeichnet wurden.
     */
    private int index;

    /**
     * Maximale Anzahl Wandelemente.
     */
    private int anzahlWandelemente;

    /**
     * Array, das alle Wandelemente speichert.
     */
    private Wandelement[] wandelemente;

    /**
     * Array, das alle Punkte speichert, die bereits belegt ist.
     */
    private boolean[][] belegtePunkte;

    /**
     * Erstellt das Labyrinth mit der angegeben Breite und Höhe.
     * @param breite Breite des Labyrinthes
     * @param hoehe Länge des Labyrinthes
     */
    public Labyrinth(int breite, int hoehe) {

        this.breite = breite;
        this.hoehe = hoehe;
        anzahlWandelemente = (breite + 1) * (hoehe + 1);
        wandelemente = new Wandelement[anzahlWandelemente];
        belegtePunkte = new boolean[breite + 1][hoehe + 1]; 
        index = 0;

        erzeugeWandelemente();
    }

    /**
     * Gibt die Höhe des Labyrinthes wieder.
     * @return Höhe des Labyrinthes
     */
    public int gibHoehe() {
        return hoehe;
    }
    
    /**
     * Gibt die Breite des Labyrinthes wieder.
     * @return Breite des Labyrinthes
     */
    public int gibBreite() {
        return breite;
    }

    /**
     * Gibt die Anzahl aller möglichen Wandelemente wieder.
     * @return Anzahl aller möglichen Wandelemente
     */
    public int gibAnzahlWandelemente() {
        return anzahlWandelemente;
    }

    /**
     * Gibt den Startpunkt des x-ten Wandelementes wieder, wobei x
     * durch den übergebenen Parameter index definiert wird.
     * @param index Wandelement, von dem Startpunkt gefordert wird
     * @return Startpunkt des Wandelementes index
     */
    public Punkt gibStartpunkt(int index) {
        return wandelemente[index].getStartpunkt();
    }

    /**
     * Gibt den Endpunkt des x-ten Wandelementes wieder, wobei x
     * durch den übergebenen Parameter index definiert wird.
     * @param index Wandelement, von dem Endpunkt gefordert wird
     * @return Endpunkt des Wandelementes index
     */
    public Punkt gibEndpunkt(int index) {
        return wandelemente[index].getEndpunkt();
    }

    /**
     * Erzeugt das Labyrinth.
     */
    public void erzeugeWandelemente() {
        erzeugeAussenwand();

        //Zufällige Richtung und Punkt auswählen
        Random rand = new Random();

        Punkt aktuellerPunkt;
        Punkt zufaelligeRichtung;

        //Restliche Wandelemente erzeugen
        while (index < anzahlWandelemente) {
            //Zufälligen Endpunkt auswählen
            aktuellerPunkt = wandelemente[rand.nextInt(index)].getEndpunkt();

            //Neue Richtung zufällige setzen und testen, ob er gültig ist
            zufaelligeRichtung = RICHTUNG[rand.nextInt(4)];

            //Falls gültig
            if (istGueltigerPunkt(aktuellerPunkt.addiere(zufaelligeRichtung))) {
                erstelleWand(aktuellerPunkt, zufaelligeRichtung);
            }
        }
    }

    /**
     * Erzeugt die Aussenwand des Labyrinthes.
     */
    public void erzeugeAussenwand() {

        for (int i = 0; i <= breite - 1; i++) {
            //Obere Seite
            erstelleWand(new Punkt(i, 0), RICHTUNG[2]);

            //Untere Seite
            erstelleWand(new Punkt(i, hoehe), RICHTUNG[2]);
        }

        for (int i = 0; i < hoehe; i++) {
            //Linke Seite
            erstelleWand(new Punkt(0, i), RICHTUNG[3]);

            //Rechte Seite
            erstelleWand(new Punkt(breite, i), RICHTUNG[3]);
        }
    }

    /**
     * Überprüft, ob der angegebene Punkt gültig ist, das heisst
     * ob er innerhalb des Labyrinthes liegt und noch nicht belegt ist.
     * @param naechsterPunkt Der zu überprüfende Punkt
     * @return true genau dann, wenn Punkt innerhalb des Labyrinthes
     * ist und noch nicht belegt ist
     */
    public boolean istGueltigerPunkt(Punkt naechsterPunkt) {
        boolean istGueltig = true;

        //Check ob Wand ausserhalb ist
        if (!naechsterPunkt.istInnerhalb(
                    new Punkt(0, 1), new Punkt(breite, hoehe))) {
            istGueltig = false;
        }

        //Check ob Wand bereits existiert
        //bzw ein geschlossener Raum gezogen wird
        if (istGueltig
            && belegtePunkte[naechsterPunkt.gibX()][naechsterPunkt.gibY()]) {
                istGueltig = false;
        }

        return istGueltig;
    }

    /**
     * Erstellt ein neues Wandelement und setzt die
     * Start- und Endpunkte.
     * @param start Startpunkt der Wand
     * @param richtung Richtung, in die die Wand gezogen werden soll
     */
    public void erstelleWand(Punkt start, Punkt richtung) {

        Punkt ende;

        //Wandelement erstellen
        wandelemente[index] = new Wandelement();

        //Start und Endpunkt setzen
        wandelemente[index].setStartpunkt(start);
        ende = start.addiere(richtung);
        wandelemente[index].setEndpunkt(ende);

        //Endpunkt belegen
        belegtePunkte[ende.gibX()][ende.gibY()] = true;

        //Index erhöhen
        index++;
    }
}
