package spy;


public class Controller {

    private DButil dButil;

    public Controller(DButil dButil) {
        this.dButil = dButil;
    }

    //Wir wollen nun sicherstellen , wird Speichern  aufgerufen wird oder nicht
    public void addProdukt(Produkt produkt) {
        dButil.speichern(produkt);
    }


}