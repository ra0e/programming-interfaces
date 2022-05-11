package mock;


import org.junit.jupiter.api.Assertions;

public class DButilforMock implements DButil {
    int speichernCalled = 0 ;
    Produkt lastAddedProdukt = null ;


    @Override
    public void speichern(Produkt produktForPackageMock) {
        speichernCalled++;
        lastAddedProdukt = produktForPackageMock ;
    }

    public void verify(Produkt produktForPackageMock, int times){
        Assertions.assertEquals(times,speichernCalled);
        Assertions.assertEquals(produktForPackageMock,lastAddedProdukt);



    }


}
