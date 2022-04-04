package sample.util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Buyers;
import sample.model.Produkt;

import java.sql.*;
import sample.exceptions.customException;

public class DBUtil {

    //Declare JDBC Driver
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    //Connection
    private static Connection conn = null;
    //Connection String
    private static final String connStr = "jdbc:sqlite:db_store.db";
    //Connect to DB
    public static void dbConnect() throws Exception {
        //Setting Oracle JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle SQLITE Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("SQLITE JDBC Driver Registered!");
        //Establish the Oracle Connection using Connection String
        try {
            conn = DriverManager.getConnection(connStr);
            throw new customException("Connected");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
    }
    //Close Connection
    public static void dbDisconnect() throws  customException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                throw new customException("Disconnect");
            }
        } catch (Exception e){
            throw new customException("Not disconnect");
        }
    }

    public static  ObservableList<Produkt> executeGetAllProdukt() throws SQLException {
        ObservableList<Produkt> list = FXCollections.observableArrayList();

        try {
            if (conn != null && !conn.isClosed()) {
                ResultSet res = conn.prepareStatement("SELECT * from products;").executeQuery();
                while (res.next()){
                    Produkt model = new Produkt();
                    model.setId(res.getInt("id"));
                    model.setName(res.getString("names"));
                    model.setPrice(res.getFloat("price"));
                    model.setGewicht(res.getFloat("gewicht"));
                    model.setLieferzeit(res.getInt("lieferzeit"));
                    list.add(model);
                }
            }
        } catch (Exception e){
            throw e;
        }
        return list;
    }

    public static ObservableList<Buyers> executeGetAllBuyers() throws SQLException {
        ObservableList<Buyers> list = FXCollections.observableArrayList();

        try {
            if (conn != null && !conn.isClosed()) {
                ResultSet res = conn.prepareStatement("SELECT * from buyers;").executeQuery();
                while (res.next()){
                    Buyers model = new Buyers();
                    model.setId(res.getInt("id"));
                    model.setVorname(res.getString("vorname"));
                    model.setProdukt_id(res.getInt("produkt_id"));
                    model.setNachname(res.getString("nachname"));
                    model.setProdukt_name(res.getString("produkt_name"));
                    model.setAnzahl(res.getInt("anzahl"));
                    model.setFinal_price(res.getFloat("final_price"));
                    list.add(model);
                }
            }
        } catch (Exception e){
            throw e;
        }
        return list;
    }

    public static Produkt executeGetIdFromNameProdukt(String name) throws SQLException {
        Produkt model;
        try {
            if (conn != null && !conn.isClosed()) {
                PreparedStatement preparedStatement = conn.prepareStatement("select * from products where names = ?;");
                preparedStatement.setString(1, name);
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()){
                    model = new Produkt();
                    model.setId(res.getInt("id"));
                    model.setName(res.getString("names"));
                    model.setPrice(res.getFloat("price"));
                    model.setPrice(res.getFloat("gewicht"));
                    model.setLieferzeit(res.getInt("lieferzeit"));
                    return model;
                }
            }
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    public static float executeGetPriceProdukt(int id) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                PreparedStatement preparedStatement = conn.prepareStatement("select * from products where id = ?;");
                preparedStatement.setInt(1, id);
                ResultSet res = preparedStatement.executeQuery();
                while (res.next()){
                    return  res.getFloat("price");
                }
            }
        } catch (Exception e){
            throw e;
        }
        return -1;
    }

    public static boolean executeInsertProdukt(Produkt model) {
        try {
            if (conn != null && !conn.isClosed()) {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO products (id,names,price,gewicht,lieferzeit)" +
                                " VALUES (?,?,?,?,?);");
                preparedStatement.setInt(1, model.getId());
                preparedStatement.setString(2, model.getName());
                preparedStatement.setFloat(3, model.getPrice());
                preparedStatement.setFloat(4, model.getGewicht());
                preparedStatement.setInt(5, model.getLieferzeit());
                preparedStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    public static void executeInsertBuyer(Buyers model) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO buyers (vorname, produkt_id, nachname, produkt_name, anzahl, final_price)" +
                                " VALUES (?,?,?,?,?,?);");
                preparedStatement.setString(1, model.getVorname());
                preparedStatement.setInt(2, model.getProdukt_id());
                preparedStatement.setString(3, model.getNachname());
                preparedStatement.setString(4, model.getProdukt_name());
                preparedStatement.setInt(5, model.getAnzahl());
                preparedStatement.setFloat(6, model.getFinal_price());
                preparedStatement.execute();
                excuteReordering();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static void executeRemove(int id, int state) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                PreparedStatement preparedStatement;
                if(state == 0) {
                    preparedStatement = conn.prepareStatement(
                            "DELETE FROM products\n" +
                                    "WHERE id = ?;");
                    excuteReordering();
                }
                else preparedStatement = conn.prepareStatement(
                            "DELETE FROM buyers\n" +
                                    "WHERE id = ?;");
                preparedStatement.setInt(1, id);
                preparedStatement.execute();

            }
        } catch (Exception e){
            throw e;
        }
    }

    public static void excuteReordering(){
        PreparedStatement prs;
        try {
            prs = conn.prepareStatement(
                    "UPDATE buyers SET id = (SELECT COUNT(*) FROM buyers t  WHERE t.id <= buyers.id);");

            prs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}