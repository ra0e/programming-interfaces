package fake;


import java.util.Collection;

public interface DButil {

    void  speichern (Produkt produkt);

    Collection<Produkt> findAll();


}
