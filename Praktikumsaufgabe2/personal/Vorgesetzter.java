package personal;

/**
 *
 * @author Maaster
 */
public class Vorgesetzter extends Mitarbeiter {

    /**
     * Erstellt einen neuen Vorgesetzten mit dem angegebenen Namen.
     * @param name Name des Vorgesetzten
     */
    public Vorgesetzter(String name) {
        super(name);
        super.setzeRang("Vorgesetzter");
    }

}
