package application;

	import java.io.IOException;

	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.stage.Stage;
	import javafx.stage.StageStyle;

public class Page1Controller {
	
		
		@FXML
		 void exit(ActionEvent event)
		{ System.exit(0); }
		@FXML
		void ado(ActionEvent event) throws IOException {
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Adulte.fxml"));
	   
		Parent root = loader.load();
		
	    Stage stage = new Stage();
	    
	    stage.setScene(new Scene(root));
	    
	    stage.setMinHeight(900);
		stage.setMinWidth(1400);
		stage.setMaxHeight(900);
		stage.setMaxWidth(1400);
		
	    stage.setTitle("Ecpace Adulte ") ;
	   // stage.setFullScreen(true);
	    
	   stage.initStyle(StageStyle.UNDECORATED);
	   stage.setMaximized(true);
	   
	    stage.show();}
		
		@FXML
		void enf(ActionEvent event) throws IOException {
			
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Enfant.fxml"));
		
	    Parent root =loader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    
	    stage.setMinHeight(900);
		stage.setMinWidth(1400);
		stage.setMaxHeight(900);
		stage.setMaxWidth(1400);
		
	    stage.setTitle("Ecpace Enfant");
	   // stage.setFullScreen(true);
	 stage.initStyle(StageStyle.UNDECORATED);
	 stage.setMaximized(true);
	    stage.show(); 
	    }
	    
		@FXML private Button  help;
		
		@FXML
			void helpp(ActionEvent event) throws IOException {
				
				FXMLLoader loader =new FXMLLoader(getClass().getResource("Help.fxml"));
			    Parent root =loader.load();
			    Stage stage = new Stage();
			    stage.setScene(new Scene(root));
			    stage.setMinHeight(1000);
				stage.setMinWidth(1000);
				stage.setMaxHeight(1000);
				stage.setMaxWidth(1200);
			    stage.setTitle("Help");
			   
			    stage.show();


		}

	}



