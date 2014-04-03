package personal;

/**
 *
 * @author Maaster
 */
public class Vorgesetzter extends Mitarbeiter {

    /**
     * Bestelllimit für diesen Vorgesetzten.
     */
    private int bestelllimitVorgesetzter;

    /**
     * Erstellt einen neuen Vorgesetzten mit dem angegebenen Namen.
     * @param name Name des Vorgesetzten
     */
    public Vorgesetzter(String name) {
        super(name);
        this.bestelllimitVorgesetzter = -1;
        super.setzeRang("Vorgesetzter");
    }

    /**
     * Setzt das Bestelllimit dieses Vorgesetzten.
     * @param limit Neues Limit
     */
    public void setzeBestelllimit(int limit) {
        this.bestelllimitVorgesetzter = limit;
    }

    /**
     * Überprüft, ob dieser Vorgesetzte etwas mit dem angegebenen
     * Wert bestellen darf.
     * @param kosten Kosten der Bestellung
     * @return true genau dann, wenn er bestellen darf
     */
    @Override
    public boolean darfBestellen(int kosten) {

        boolean darfBestellen;

        if (bestelllimitVorgesetzter > 0) {
            darfBestellen = kosten <= bestelllimitVorgesetzter;
        } else {
            darfBestellen = kosten <= Mitarbeiter.gibLimit();
        }

        return darfBestellen;
    }

    /**
     * Gibt Info des Vorgesetzten wieder.
     * @return String mit Info des Vorgesetzten
     */
    @Override
    public String gibInfo() {
        String infoText;
        int limit;

        if (bestelllimitVorgesetzter > 0) {
            limit = bestelllimitVorgesetzter;
        } else {
            limit = Mitarbeiter.gibLimit();
        }

        if (this.gibVorgesetzten() == null) {
            infoText = "Ich bin Vorgesetzter, Name " + this.gibName()
                            + ". Mein Bestelllimit ist "
                                + limit + " EUR.";
        } else {
            infoText = "Ich bin Vorgesetzter, Name " + this.gibName()
                            + ". Mein Vorgesetzter ist "
                                + this.gibVorgesetzten().gibName()
                                    + ". Mein Bestelllimit ist "
                                        + limit + " EUR.";
        }

        return infoText;
    }
}
