package mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;


public class TestMock {
    @Test
    public void demoMock(){
        DButilforMock dButilforMock = new DButilforMock();
        Controller controller = new Controller(dButilforMock);

        Produkt produkt1 = new Produkt(1,"Messgraet",100,1,3 );
        Produkt produkt2 = new Produkt(1,"Messgraet",100,1,3 );
        controller.addProdukt(produkt1);
        controller.addProdukt(produkt2);

        dButilforMock.verify(produkt2,2);

    }
    @Test
    public void demoMockWithMockito(){
        //wie Immer erstellen wir fake Dbutil .
        DButil dButil = mock(DButil.class);
        Controller controller = new Controller(dButil);
        Produkt produkt1 = new Produkt(1,"Messgraet",1010,1,3 );
        Produkt produkt2 = new Produkt(1,"PC",1300,12,43 );
        Produkt produkt3 = new Produkt(1,"PS5",1030,51,33 );
        //Wir wollen nun sicherstellen , wird Speichern Methode aufgerufen wird oder nicht
        //Nutzen wir hier Mockit.verify , dann hier die klasse, die haben wir mockiert .
        // dann Mockito .times muss 1 sein und dann Speichern .
        //Hier wird überprüft ob speichern ( produkt1 ) aufgefufen wird oder nicht .
        // und wir erwarten hier 1 mal aufgerufen werden .
        controller.addProdukt(produkt1);
        controller.addProdukt(produkt2);
        controller.addProdukt(produkt3);

        Mockito.verify(dButil, Mockito.times(1)).speichern(produkt1);

        //Mit diesem Befehl wird überprüft, wie oft er im Allgemeinen aufgerufen wurde

        Mockito.verify(dButil, times(3)).speichern(any());
        //Können wir auch hier atlest oder atmost definieren .
        // das heisst hier erwarten wir mindestens 1 mall aufgerufen werden .
        Mockito.verify(dButil,atLeast(1)).speichern(any());
        Mockito.verify(dButil,atMost(4)).speichern(any());






    }
}
