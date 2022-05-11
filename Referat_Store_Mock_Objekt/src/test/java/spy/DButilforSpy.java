package spy;

public class DButilforSpy implements DButil {
    int speichernCalled = 0 ;
    Produkt lastAddedProdukt = null ;

    @Override
    public void speichern(Produkt produkt) {
        speichernCalled++;
        lastAddedProdukt = produkt;
    }
    public int timesCalled(){
        return speichernCalled;
    }
    public boolean calledwith(Produkt produkt){
        return  lastAddedProdukt.equals(produkt);
    }
}
