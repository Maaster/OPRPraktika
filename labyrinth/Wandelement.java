package labyrinth;


/**
 *
 * @author maaster
 */
public class Wandelement {
    
    private Punkt startpunkt;
    private Punkt endpunkt;
    
    /**
     * Erstellt das Wandelement
     */
    public Wandelement() {
        
    }

    /**
     * @return the startpunkt
     */
    public Punkt getStartpunkt() {
        return startpunkt;
    }

    /**
     * @param startpunkt the startpunkt to set
     */
    public void setStartpunkt(Punkt startpunkt) {
        this.startpunkt = startpunkt;
    }

    /**
     * @return the endpunkt
     */
    public Punkt getEndpunkt() {
        return endpunkt;
    }

    /**
     * @param endpunkt the endpunkt to set
     */
    public void setEndpunkt(Punkt endpunkt) {
        this.endpunkt = endpunkt;
    }
}
