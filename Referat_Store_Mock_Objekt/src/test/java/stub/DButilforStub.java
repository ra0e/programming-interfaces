package stub;

import java.util.ArrayList;
import java.util.List;

public class DButilforStub implements DButil {
    @Override
    public List<Produkt> findNewProdukts(int days) {
        List<Produkt> newProdukts = new ArrayList<>();
        Produkt produkt1 = new Produkt(1,"Messgraet",500,1,3);
        newProdukts.add(produkt1);

        return  newProdukts;
    }
}
