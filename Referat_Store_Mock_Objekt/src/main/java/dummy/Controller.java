package dummy;

public class Controller {
    private DButil dButil;
    private EmailService emailService;

    public Controller(DButil dButil, EmailService emailService) {
        this.dButil = dButil;
        this.emailService = emailService;
    }
    public void addProdukt(Produkt produkt){
         dButil.speichern(produkt);
    };

    public int findNumberOfProdukt(){
        return dButil.findAll().size();
    }
}
