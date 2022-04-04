package sample.model;

import javafx.beans.property.*;

public class Buyers {
    private IntegerProperty id;
    private StringProperty vorname;
    private IntegerProperty produkt_id;
    private StringProperty nachname;
    private StringProperty produkt_name;
    private IntegerProperty anzahl;
    private FloatProperty final_price;

    public Buyers() {
        this.id = new SimpleIntegerProperty();
        this.vorname = new SimpleStringProperty();
        this.produkt_id = new SimpleIntegerProperty();
        this.nachname = new SimpleStringProperty();
        this.produkt_name = new SimpleStringProperty();
        this.anzahl = new SimpleIntegerProperty();
        this.final_price = new SimpleFloatProperty();
    }

    public int getId() {
        return id.getValue();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getVorname() {
        return vorname.getValue();
    }

    public StringProperty vornameProperty() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname.set(vorname);
    }

    public int getProdukt_id() {
        return produkt_id.getValue();
    }

    public IntegerProperty produkt_idProperty() {
        return produkt_id;
    }

    public void setProdukt_id(int produkt_id) {
        this.produkt_id.set(produkt_id);
    }

    public String getNachname() {
        return nachname.getValue();
    }

    public StringProperty nachnameProperty() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname.set(nachname);
    }

    public String getProdukt_name() {
        return produkt_name.getValue();
    }

    public StringProperty produkt_nameProperty() {
        return produkt_name;
    }

    public void setProdukt_name(String produkt_name) {
        this.produkt_name.set(produkt_name);
    }

    public int getAnzahl() {
        return anzahl.getValue();
    }

    public IntegerProperty anzahlProperty() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl.set(anzahl);
    }

    public float getFinal_price() {
        return final_price.getValue();
    }

    public FloatProperty final_priceProperty() {
        return final_price;
    }

    public void setFinal_price(float final_price) {
        this.final_price.set(final_price);
    }
}
