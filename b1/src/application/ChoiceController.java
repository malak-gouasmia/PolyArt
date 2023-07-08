package application;

	import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ChoiceController {
	
	
	
	    @FXML
	    private TextField x;

	    @FXML
	    private TextField nb;

	    @FXML
	    private Button entrer;

	    @FXML
	    void CREATION(ActionEvent event) throws  Exception {
	    	
	    
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Adulte.fxml"));
		
	    Parent root =loader.load();
	    
	    Stage stage = new Stage();
	    
	    stage.setScene(new Scene(root));
	    
	    stage.setMinHeight(900);
		stage.setMinWidth(1400);
		stage.setMaxHeight(900);
		stage.setMaxWidth(1400);
		
	    stage.setTitle("Creer un polygone");
	    
	    stage.setFullScreen(true);
	    
	    stage.initModality( Modality.NONE );
	    
	    stage.initStyle(StageStyle.DECORATED);
	    
	    stage.show();}
	    	

	    }

	



