package stub;

import java.util.List;

public class Controller {
    private DButil dButil;

    public Controller(DButil dButilForPackageStub) {
        this.dButil = dButilForPackageStub;
    }
    //Berechnet den Rabatt und nimmt 2 Int als Eingabe
    public List<Produkt> getNewProduktsWithAppliedDiscount(int discountRate, int days){
        List<Produkt> newProdukts = dButil.findNewProdukts(days);
        // 500  = 10% Rabatt darauf  --- 10% von 500 = 50  -> 500 - 50 = 450
        for(Produkt produkt : newProdukts){
            float price = produkt.getPrice();
            float newPrice = price - (discountRate * price / 100);
            produkt.setPrice(newPrice);
        }
        return newProdukts;
    }
}
