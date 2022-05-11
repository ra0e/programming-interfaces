package dummy;

public class Produkt {
    private int id;
    private String name;
    private float price;
    private float gewicht;
    private int lieferzeit;

    public Produkt(int id, String name, float price, float gewicht, int lieferzeit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gewicht = gewicht;
        this.lieferzeit = lieferzeit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getGewicht() {
        return gewicht;
    }

    public void setGewicht(float gewicht) {
        this.gewicht = gewicht;
    }

    public int getLieferzeit() {
        return lieferzeit;
    }

    public void setLieferzeit(int lieferzeit) {
        this.lieferzeit = lieferzeit;
    }
}
