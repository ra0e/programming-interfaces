package stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {
    @Test
    public void demoStub(){
        DButil dButil = new DButilforStub();
        Controller controller = new Controller(dButil);

        List<Produkt> newProduktWithAppliedDiscount =  controller.getNewProduktsWithAppliedDiscount(10,7);
        Assertions.assertEquals(450,newProduktWithAppliedDiscount.get(0).getPrice());

    }

    @Test
    public void demoStubWithMockito(){
        //nutzen wir Mock , und wie Immer erstellen wir fake Dbutil
        DButil dButil = mock(DButil.class);
        Controller controller = new Controller(dButil);
        Produkt produkt1 = new Produkt(1,"pc",500,2,10);
        List<Produkt> ListOfProdukt = new ArrayList<>();
        ListOfProdukt.add(produkt1);
        //Hier wird ein ArrayList  ( ListofPrudukt ) geantwortet ,
        // wenn diese findallnewProdukts mit 7 Days  aufgerufen wird .

        when(dButil.findNewProdukts(7)).thenReturn(ListOfProdukt);
        //Dann hier haben wir die Methode, die eigentlich testen wollen .
        List<Produkt> newProduktWithAppliedDiscount =  controller.getNewProduktsWithAppliedDiscount(10,7);
        Assertions.assertEquals(1,newProduktWithAppliedDiscount.size());
        //Dann die nächste ist price von Produkt nach dem Rabatt . muss 450 sein
        Assertions.assertEquals(450,newProduktWithAppliedDiscount.get(0).getPrice());


    }
}
