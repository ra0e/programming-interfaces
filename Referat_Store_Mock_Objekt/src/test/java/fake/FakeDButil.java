package fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeDButil implements DButil {
    Map<Integer, Produkt > produktStore = new HashMap<>();

    @Override
    public void speichern(Produkt produkt) {
        produktStore.put(produkt.getId(), produkt);
        // Hier wird Produkten in unsere Map gespeichert .

    }

    @Override
    public Collection<Produkt> findAll() {
        return produktStore.values();
        // Das Output dieser Methode ist die Anzahl der Produkte
    }
}
