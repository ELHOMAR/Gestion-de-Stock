package gestion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.event.*;


public class SampleController implements Initializable {
    

    @FXML
    private TableView<Produit> table_produits;

    @FXML
    private TableColumn<Produit, Integer> col_id;

    @FXML
    private TableColumn<Produit, String> col_libelle;

    @FXML
    private TableColumn<Produit, Integer> col_prix;

    @FXML
    private TableColumn<Produit, Integer> col_quantite;

  
    
     @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_libelle;

    @FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_quantite;
        
   
    
 
    
       
    ObservableList<Produit> listM;
    ObservableList<Produit> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public void Add_produit (ActionEvent event){    
        conn = Sqlconnection.ConnectDb();
        String sql = "insert into produit (id,libelle,prix,quantite)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_libelle.getText());
            pst.setString(3, txt_prix.getText());
            pst.setString(4, txt_quantite.getText());
           
            pst.execute();
            
            JOptionPane.showMessageDialog(null, " produit ajouté avec succé");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    
    @FXML
    void getSelected (MouseEvent event){
    index = table_produits.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_libelle.setText(col_libelle.getCellData(index).toString());
    txt_prix.setText(col_prix.getCellData(index).toString());
    txt_quantite.setText(col_quantite.getCellData(index).toString());
    
    
    }

    public void Edit (){   
        try {
            conn = Sqlconnection.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_libelle.getText();
            String value3 = txt_prix.getText();
            String value4 = txt_quantite.getText();
         
            String sql = "update produit set id= '"+value1+"',libelle= '"+value2+"',prix= '"+
                    value3+"',quantite= '"+value4+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
        conn = Sqlconnection.ConnectDb();
        String sql = "delete from produit where id = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_id.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete");
                UpdateTable();
      
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        }

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
        col_libelle.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelle"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
        
        listM = Sqlconnection.getDataproduit();
        table_produits.setItems(listM);
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();

    } 
}

