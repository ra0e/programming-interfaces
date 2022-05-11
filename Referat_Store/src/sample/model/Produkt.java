package sample.model;

import javafx.beans.property.*;

public class Produkt {
    private IntegerProperty id;
    private StringProperty name;
    private FloatProperty price;
    private FloatProperty gewicht;
    private IntegerProperty lieferzeit;

    public Produkt() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
        this.gewicht = new SimpleFloatProperty();
        this.lieferzeit = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public float getGewicht() {
        return gewicht.get();
    }

    public FloatProperty gewichtProperty() {
        return gewicht;
    }

    public void setGewicht(float gewicht) {
        this.gewicht.set(gewicht);
    }

    public int getLieferzeit() {
        return lieferzeit.get();
    }

    public IntegerProperty lieferzeitProperty() {
        return lieferzeit;
    }

    public void setLieferzeit(int lieferzeit) {
        this.lieferzeit.set(lieferzeit);
    }
}
