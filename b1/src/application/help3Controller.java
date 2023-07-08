package application;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class help3Controller {
	
	 private Stage stage;
	 private Scene scene;
	
	 @FXML private Button  suiv;
	 
	 @FXML
	 void suivant(ActionEvent event) throws IOException {
	
	 	  Parent root = FXMLLoader.load(getClass().getResource("help4.fxml"));
	 	  
	 	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 	  
	 	  scene = new Scene(root);
	 	  
	 	  stage.setScene(scene);
	 	  
	 	  stage.show();

	     }

}
