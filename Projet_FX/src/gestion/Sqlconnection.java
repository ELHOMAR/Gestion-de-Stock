package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class Sqlconnection {
    
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stockproduits","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    
    }
    
    public static ObservableList<Produit> getDataproduit(){
    	 Connection conn = ConnectDb();
         ObservableList<Produit> list = FXCollections.observableArrayList();
         try {
             PreparedStatement ps = conn.prepareStatement("select * from produit");
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()){   
                 list.add(new Produit((Integer.parseInt(rs.getString("id"))),rs.getString("libelle"),(Integer.parseInt(rs.getString("prix"))),(Integer.parseInt(rs.getString("quantite")))));               
             }
         } catch (Exception e) {
         }
         return list;
    }
    
}
