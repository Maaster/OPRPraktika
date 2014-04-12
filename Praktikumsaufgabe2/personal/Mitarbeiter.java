package personal;

/**
 *
 * @author Maaster
 */
public class Mitarbeiter {

    /**
     * Allgemeines Bestelllimit.
     */
    private static int bestelllimit = 20;

    /**
     * Bestelllimit für diesen Vorgesetzten.
     */
    private int bestelllimitVorgesetzter;

    /**
     * Name des Mitarbeiters.
     */
    private String name;

    /**
     * Vorgesetzter des Mitarbeiters.
     */
    private Vorgesetzter vorgesetzten;

    /**
     * Rang des Mitarbeiters.
     */
    private String rang;

    /**
     * Erstellt einen Mitarbeiter mit den angegebenen Namen.
     * @param name Name des Mitarbeiters
     */
    public Mitarbeiter(String name) {
        this.name = name;
        rang = "freier Mitarbeiter";
        this.bestelllimitVorgesetzter = -1;
    }

    /**
     * Setzt das allgemeine Limit für Bestellungen.
     * @param limit Das Limit für Bestellungen
     */
    public static void setzeAllgemeinesLimit(int limit) {
        bestelllimit = limit;
    }

    /**
     * Setzt das Bestelllimit dieses Vorgesetzten.
     * @param limit Neues Limit
     */
    public void setzeBestelllimit(int limit) {
        this.bestelllimitVorgesetzter = limit;
    }
    
    /**
     * Gibt den Vorgesetzten dieses Mitarbeiters wieder.
     * @return Vorgesetzten des Mitarbeiters
     */
    public Vorgesetzter gibVorgesetzten() {
        return vorgesetzten;
    }

    /**
     * Setzt einen neuen Vorgesetzten, falls keine angegeben wurde
     * wird der Mitarbeiter ein freier Mitarbeiter.
     * @param neuerVorgesetzter der neue Vorgesetzte oder null.
     */
    public void setzeVorgesetzten(Vorgesetzter neuerVorgesetzter) {

        if (neuerVorgesetzter != null) {
            vorgesetzten = neuerVorgesetzter;
            if ("freier Mitarbeiter".equals(rang)) {
                rang = "Mitarbeiter";
            }
        } else {
            vorgesetzten = null;
            if ("Mitarbeiter".equals(rang)) {
                rang = "freier Mitarbeiter";
            }
        }

    }

    /**
     * Gibt wieder, ob der Mitarbeiter für den angegeben Wert eine
     * Bestellung durchführen darf.
     * @param kosten Die Kosten der Bestellung
     * @return true genau dann, wenn der Mitarbeiter bestellen darf
     */
    public boolean darfBestellen(int kosten) {
        return kosten <= this.gibLimit();
    }

    /**
     * Gibt die Info des Mitarbeiters wieder.
     * @return String mit Informationen des Mitarbeiters
     */
    public String gibInfo() {
        String infoText;

        if ("freier Mitarbeiter".equals(rang)) {
            infoText = "Ich bin freier Mitarbeiter, Name " + name
                        + ". Mein Bestelllimit ist "
                            + bestelllimit + " EUR.";
        } else if ("Mitarbeiter".equals(rang)) {
            infoText = "Ich bin Mitarbeiter" + ", Name " + name
                        + ". Mein Vorgesetzter ist " + vorgesetzten.gibName()
                            + ". Mein Bestelllimit ist "
                                + bestelllimit + " EUR.";
        } else if (vorgesetzten == null && "Vorgesetzter".equals(rang)) {
            infoText = "Ich bin Vorgesetzter, Name " + this.gibName()
                            + ". Mein Bestelllimit ist "
                                + this.gibLimit() + " EUR.";
        } else {
            infoText = "Ich bin Vorgesetzter, Name " + this.gibName()
                            + ". Mein Vorgesetzter ist "
                                + this.gibVorgesetzten().gibName()
                                    + ". Mein Bestelllimit ist "
                                        + this.gibLimit() + " EUR.";
        }

        return infoText;
    }

    /**
     * Gibt die Hierarchie des Mitarbeiters wieder.
     * @return String mit Hierarchie
     */
    public String gibHierarchie() {

        return (vorgesetzten == null)
                ? rang + " " + name
                : vorgesetzten.gibHierarchie() + '\n' +  rang + " " + name;
    }

    /**
     * Gibt den Namen des Mitarbeiters wieder.
     * @return Name des Mitarbeiters
     */
    public String gibName() {
        return name;
    }

    /**
     * Setzt den Rang eines Mitarbeiters neu.
     * @param neuerRang Der neue Rang
     */
    public void setzeRang(String neuerRang) {
        this.rang = neuerRang;
    }

    /**
     * Gibt das Bestelllimit wieder.
     * @return Bestelllimit
     */
    public int gibLimit() {
        return ("Mitarbeiter".equals(rang))
                ? bestelllimit
                : (bestelllimitVorgesetzter < 0)
                    ? bestelllimit
                    : bestelllimitVorgesetzter;
    }
}
