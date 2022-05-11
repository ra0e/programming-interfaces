package dummy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeDButilForDummyTest implements DButil {
    Map<Integer, Produkt> produktStoreForDummy =  new HashMap<>();

    @Override
    public void speichern(Produkt produkt) {
        produktStoreForDummy.put(produkt.getId(), produkt);

    }

    @Override
    public Collection<Produkt> findAll() {
        return produktStoreForDummy.values();
    }
}
