package labyrinth;

import util.Punkt;
/**
 * Diese Klasse repräsentiert ein Wandelement im Labyrinth.
 * @author maaster
 */
public class Wandelement {

    /**
     * Startpunkt dieses Wandelementes.
     */
    private Punkt startpunkt;

    /**
     * Endpunkt dieses Wandelementes.
     */
    private Punkt endpunkt;

    /**
     * Erzeugt ein Wandelement.
     */
    public Wandelement() {

    }

    /**
     * Gibt den Startpunkt wieder.
     * @return Den Startpunkt
     */
    public Punkt getStartpunkt() {
        return startpunkt;
    }

    /**
     * Setzt den Startpunkt.
     * @param startpunkt Der zu setzende Startpunkt
     */
    public void setStartpunkt(Punkt startpunkt) {
        this.startpunkt = startpunkt;
    }

    /**
     * Gibt den Endpunkt wieder.
     * @return Den Endpunkt
     */
    public Punkt getEndpunkt() {
        return endpunkt;
    }

    /**
     * Setzt den Endpunkt.
     * @param endpunkt Der zu setzende Endpunkt
     */
    public void setEndpunkt(Punkt endpunkt) {
        this.endpunkt = endpunkt;
    }

}
