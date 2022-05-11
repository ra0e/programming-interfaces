package spyVsMock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class SpyVsMock {

    @Test
    public void Mock(){
        //erstellen Ein Mock von ArrayList

        ArrayList test = mock(ArrayList.class);
        Assertions.assertEquals(0,test.size());

        test.add("Dummy");
        Assertions.assertEquals(0,test.size());

    }

    @Test
    public void spy(){
        //erstellen Ein Spy von ArrayList
        ArrayList test = Mockito.spy(ArrayList.class);
        Assertions.assertEquals(0,test.size());
        test.add("Dummy");
        Assertions.assertEquals(1,test.size());




    }
}
