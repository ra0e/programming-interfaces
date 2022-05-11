/*package test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import sample.controller.Controller;
import sample.exceptions.customException;
import sample.model.Produkt;
import sample.util.DBUtil;

public class testDatabase {
    @Mock
    private  DBUtil dbUtil;

    // Mock object & test null and not null
    @Test
    public void testMockObject(){
        assertNull(dbUtil);
        dbUtil = Mockito.mock(DBUtil.class);
        assertNotNull(dbUtil);

    }
    //Test connection Db & static method call (::)
    @Test
    public void testConnection(){
        Throwable exception  = assertThrows(customException.class, DBUtil::dbConnect);
        assertEquals("Connected", exception.getMessage());
        exception = assertThrows(customException.class, DBUtil::dbDisconnect);
        assertEquals("Not disconnect", exception.getMessage());
    }
    //Test insert db with mock, and send to insert method & static method call (::)
    @Spy
    private Produkt produkt = new Produkt(); // mocking this class
    @Before
    @Test
    public void testSendMockObjectToDB() {
        MockitoAnnotations.initMocks(this);

        Field id = null;
        Field name = null;
        Field price = null;
        Field gewicht = null;
        Field lieferzeit = null;
        try {
              id = Produkt.class.
                    getDeclaredField("id");
              name = Produkt.class.
                    getDeclaredField("name");
              price = Produkt.class.
                    getDeclaredField("price");
              gewicht = Produkt.class.
                    getDeclaredField("gewicht");
              lieferzeit = Produkt.class.
                    getDeclaredField("lieferzeit");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        id.setAccessible(true);
        name.setAccessible(true);
        price.setAccessible(true);
        gewicht.setAccessible(true);
        lieferzeit.setAccessible(true);
        produkt.setId(113122);
        produkt.setName("keyboard asus 16");
        produkt.setLieferzeit(5);
        produkt.setPrice(1000f);
        produkt.setGewicht(5.5f);
        Throwable exception  = assertThrows(customException.class, DBUtil::dbConnect);
        assertEquals("Connected", exception.getMessage());
        assertEquals("keyboard asus 16", produkt.getName());
        Boolean b = DBUtil.executeInsertProdukt(produkt);
        assertEquals(true, b);
    }
    //Mock and Spy correction with use doReturn for stubbing
    @Test
    void testObjectSpyCorrect() {
        Produkt produkt = Mockito.mock(Produkt.class);
        doReturn("ps5").when(produkt).getName();
        assertEquals("ps5", produkt.getName());
    }




}
*/