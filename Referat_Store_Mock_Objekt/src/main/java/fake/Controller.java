package fake;

public class Controller {
    private DButil dButil;

    public Controller(DButil dButil) {
        this.dButil = dButil;
    }

    public void addProdukt(Produkt produkt){
        dButil.speichern(produkt);
    }

    public int findNumberOfProdukt(){
        return dButil.findAll().size();
    }
}
