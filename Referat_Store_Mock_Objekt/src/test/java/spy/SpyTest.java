package spy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class SpyTest {
    @Test
    public  void demoSpy(){
        DButilforSpy dButilforSpy = new DButilforSpy();
        Controller controller = new Controller(dButilforSpy);

        Produkt produkt1 = new Produkt(1,"Messgraet",100,1,3 );
        Produkt produkt2 = new Produkt(1,"Messgraet",100,1,3 );
        controller.addProdukt(produkt1);
        controller.addProdukt(produkt2);

        Assertions.assertEquals(2,dButilforSpy.timesCalled());
        Assertions.assertTrue(dButilforSpy.calledwith(produkt2));

    }

    @Test
    public void demoSpyWithMockito(){
        //Der einzige Unterschied zwischen Mock und Spy ist hier


        DButil dButil = Mockito.spy(DButil.class);
        Controller controllerForPackageSpy = new Controller(dButil);
        Produkt produkt1 = new Produkt(1,"Messgraet",100,1,3 );
        Produkt produkt2 = new Produkt(1,"Messgraet",100,1,3 );
        controllerForPackageSpy.addProdukt(produkt1);
        controllerForPackageSpy.addProdukt(produkt2);
        Mockito.verify(dButil, Mockito.times(1)).speichern(produkt1);

    }
}
