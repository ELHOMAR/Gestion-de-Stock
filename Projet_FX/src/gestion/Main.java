package gestion;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
        Scene scene = new Scene(root); 
        Image icon = new Image("AA.png");
		stage.getIcons().add(icon);
		stage.setTitle("Les produits");
        stage.setScene(scene);
        stage.show();
    }

   
    public static void main(String[] args) throws SQLException {
    	Connection conn =
    			DriverManager.getConnection("jdbc:mysql://localhost:3306/stockproduits","root","");
    			System.out.println(conn);
        launch(args);

    }  
 
}
