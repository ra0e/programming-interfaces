package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.model.Buyers;
import sample.model.Produkt;
import sample.util.DBUtil;

import java.sql.SQLException;

public class Controller {

    @FXML
    private TableColumn<Produkt, Float> produktGewichtcolumn;

    @FXML
    private TableColumn<Produkt, Integer> produktIdcolumn;

    @FXML
    private TableColumn<Produkt, Integer> produktLieferzeitcolumn;

    @FXML
    private TableColumn<Produkt, String> produktNamecolumn;

    @FXML
    private TableColumn<Produkt, Float> produktPricecolumn;

    @FXML
    private TableColumn<Buyers, Float> buyerFinalPrice;

    @FXML
    private TableColumn<Buyers, Integer> buyerIdcolumn;

    @FXML
    private TableColumn<Buyers, Integer> buyeranzahlcolumn;

    @FXML
    private TableColumn<Buyers, Integer> buyerbestellteProduktescolumn;

    @FXML
    private TableColumn<Buyers, String> buyernachNamecolumn;

    @FXML
    private TableColumn<Buyers, String> buyernamedasProduktescolumn;

    @FXML
    private TableColumn<Buyers, String> buyervoreNamecolumn;

    @FXML
    private Button btn_delete_buyers;

    @FXML
    private Button btn_delete_produkt;

    @FXML
    private Button btn_save_buyers;

    @FXML
    private Button btn_save_produkt;

    @FXML
    private Button btn_update_buyers;

    @FXML
    private Button btn_update_produkt;

    @FXML
    private TextField input_anzahl;

    @FXML
    private TextField input_bestellte;

    @FXML
    private TextField input_gewicht;

    @FXML
    private TextField input_id;

    @FXML
    private TextField input_lieferzeit;

    @FXML
    private TextField input_nackname;

    @FXML
    private TextField input_nameProdukt;

    @FXML
    private TextField input_nameprodukt;

    @FXML
    private TextField input_price;

    @FXML
    private TextField input_vorname;

    @FXML
    private TableView<Buyers> tb_buyers;

    @FXML
    private TableView<Produkt> tb_produkt;

    @FXML
    private TextArea tf_cosole_buyers;

    @FXML
    private TextArea tf_cosole_produkt;
    @FXML
    private void initialize() {

        produktIdcolumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        produktNamecolumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        produktGewichtcolumn.setCellValueFactory(cellData -> cellData.getValue().gewichtProperty().asObject());
        produktLieferzeitcolumn.setCellValueFactory(cellData -> cellData.getValue().lieferzeitProperty().asObject());
        produktPricecolumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        buyerIdcolumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        buyervoreNamecolumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
        buyerbestellteProduktescolumn.setCellValueFactory(cellData -> cellData.getValue().produkt_idProperty().asObject());
        buyernachNamecolumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
        buyernamedasProduktescolumn.setCellValueFactory(cellData -> cellData.getValue().produkt_nameProperty());
        buyeranzahlcolumn.setCellValueFactory(cellData -> cellData.getValue().anzahlProperty().asObject());
        buyerFinalPrice.setCellValueFactory(cellData -> cellData.getValue().final_priceProperty().asObject());

        showAllBuyers();
        showAllProdukt();
    }

    @FXML
    void click(MouseEvent event) {
        showAllBuyers();
        showAllProdukt();
    }

    @FXML
    void delete_buyers(ActionEvent event) {
        Buyers model = tb_buyers.getSelectionModel().getSelectedItem();
        try {
            if (model != null) {
                DBUtil.executeRemove(model.getId(), 1);//For remove from buyers
                DBUtil.excuteReordering();
                tf_cosole_buyers.setText("The selected buyers has been removed.!");
            }
            showAllBuyers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete_produkt(ActionEvent event) {
        Produkt model = tb_produkt.getSelectionModel().getSelectedItem();
        try {
            if (model != null) {
                DBUtil.executeRemove(model.getId(), 0);//For remove from buyers
                tf_cosole_produkt.setText("The selected produkt has been removed.!");
            }
            showAllProdukt();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void getIdFromName(KeyEvent event) {
        if (event.getCode() == KeyCode.BACK_SPACE && input_nameprodukt.isFocused())
            input_nameprodukt.clear();
        else {
            String input = input_nameprodukt.getText() + event.getText();
            try {
                Produkt item = DBUtil.executeGetIdFromNameProdukt(input);
                if (item != null) {
                    input_bestellte.setText(item.getId()+"");
                    tf_cosole_buyers.setText("Produkt found!");
                }else{
                    input_bestellte.setText("Not found!");
                    tf_cosole_buyers.setText("Produkt not found!");
                }
            } catch (SQLException e) {
            }
        }
    }

    @FXML
    void insertToDB_buyers(ActionEvent event) {
        if (!input_vorname.getText().trim().isEmpty() &&
                !input_nackname.getText().trim().isEmpty() &&
                !input_nameprodukt.getText().trim().isEmpty() &&
                !input_bestellte.getText().trim().isEmpty() &&
                !input_anzahl.getText().trim().isEmpty() &&
                !input_bestellte.getText().trim().equals("Not found!")) {
            if (isNumeric(input_anzahl.getText())) {
                Buyers model = new Buyers();
                model.setAnzahl(Integer.valueOf(input_anzahl.getText().trim()));
                model.setProdukt_name(input_nameprodukt.getText().trim());
                model.setVorname(input_vorname.getText());
                model.setNachname(input_nackname.getText());
                model.setProdukt_id(Integer.valueOf(input_bestellte.getText()));
                try {
                    float price = DBUtil.executeGetPriceProdukt(Integer.valueOf(input_bestellte.getText().toString()));
                    model.setFinal_price(
                            Float.valueOf(input_anzahl.getText().toString().trim())
                            * price);
                } catch (SQLException e) {
                    tf_cosole_buyers.setText("Price not found.");
                    e.printStackTrace();
                }

                try {
                    DBUtil.executeInsertBuyer(model);
                    tf_cosole_buyers.setText("Successfully buyer inserted.");
                    showAllBuyers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void insertToDB_produkt(ActionEvent event) {
        if (!input_nameProdukt.getText().trim().isEmpty() &&
                !input_id.getText().trim().isEmpty() &&
                !input_price.getText().trim().isEmpty() &&
                !input_gewicht.getText().trim().isEmpty() &&
                !input_lieferzeit.getText().trim().isEmpty()) {
            try {
                float exist = DBUtil.executeGetPriceProdukt(Integer.valueOf(input_id.getText()));
                if(exist == -1){
                    if (isNumeric(input_price.getText())) {
                        Produkt model = new Produkt();
                        model.setId(Integer.valueOf(input_id.getText().trim()));
                        model.setName(input_nameProdukt.getText());
                        model.setLieferzeit(Integer.valueOf(input_lieferzeit.getText()));
                        model.setGewicht(Float.valueOf(input_gewicht.getText()));
                        model.setPrice(Float.valueOf(input_price.getText()));
                        try {
                            DBUtil.executeInsertProdukt(model);
                            tf_cosole_produkt.setText("Successfully produkt inserted.");
                            showAllProdukt();
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void update_buyers(ActionEvent event) {
        showAllBuyers();
    }

    @FXML
    void update_produkt(ActionEvent event) {
        showAllProdukt();
    }

    void showAllBuyers() {
        try {
            ObservableList<Buyers> data_list = DBUtil.executeGetAllBuyers();
            tb_buyers.setItems(data_list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllProdukt() {
        ObservableList<Produkt> data_list = null;
        try {
            data_list = DBUtil.executeGetAllProdukt();
            tb_produkt.setItems(data_list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
