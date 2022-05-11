package dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DummyTest {
    @Test
    public void demoDummy(){
        EmailService emailService = new DummyEmailService();
        DButil dButil = new FakeDButilForDummyTest();
        Controller controller = new Controller(dButil,emailService);
        controller.addProdukt(new Produkt(1,"pc",100,1,1));
        Assertions.assertEquals(1,controller.findNumberOfProdukt());
    }


    @Test
    public void demoDummyWithMockito(){
        DButil dButil = mock(DButil.class);
        //Aber Mit Mockito können wir mit Mock einfach ein Dummy von Emailservice erstellen .
        EmailService emailService = mock(EmailService.class);


        Controller controller = new Controller(dButil,emailService);
        Collection<Produkt> produkte = new ArrayList<>();
        Produkt produkt1 = new Produkt(1,"Messgraet",100,1,3 );
        Produkt produkt2 = new Produkt(2,"Messgraet1",100,1,3 );
        produkte.add(produkt1);
        produkte.add(produkt2);
        when(dButil.findAll()).thenReturn(produkte);
        Assertions.assertEquals(2,controller.findNumberOfProdukt());

    }


}
