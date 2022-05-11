package fake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

public class FakeTest {

    @Test
    public void testFakeOhneMockito(){
        //Ein Objekt von FakeDButil angelegt , benutzt dButil als Parameter für Controller
        //Dann definieren wir 2 Produkte , mithilfe Methode Addprodukt
        //Dann testen wir , ob findNumberofProdukt funktioniert richtig oder nicht
        //Wir erwarten hier Antwort von Methode find numberofProdukt 2 sein  .

        DButil dButil = new FakeDButil();
        Controller controller = new Controller(dButil);
        controller.addProdukt(new Produkt(1,"Messgraet",100,1,3 ));
        controller.addProdukt(new Produkt(13,"PC",1200,10,20 ));
        Assertions.assertEquals(2,controller.findNumberOfProdukt());
    }


    @Test
    public void testFakeMitMockito(){
        //Mithilfe Annotation Mock können wir einfach ein Dbutil Erstellen ,
        // benutzen dButil als Parameter für Controller
        //Aber hier müssen wir ein ArrayList  und zwei PrODUKTE definieren .
        //Mit dieser Methode when then return sieht es so aus, als hätten wir eine Datenbank .
        //Hier wird ein ArrayList geantwortet , wenn diese findall aufgerufen wird .
        //Dann hier wieder benutzen wir assertions.asserequals .

        DButil dButil = mock(DButil.class);
        Controller controller = new Controller(dButil);
        Collection<Produkt> produkte = new ArrayList<>();
        Produkt produkt1 = new Produkt(1,"Messgraet",100,1,3 );
        Produkt produkt2 = new Produkt(13,"PC",1200,10,20 );
        produkte.add(produkt1);
        produkte.add(produkt2);
        when(dButil.findAll()).thenReturn(produkte);
        Assertions.assertEquals(2,controller.findNumberOfProdukt());

    }
}
