package application;

	import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
	//import javafx.fxml.*;
	import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class EnfantController {
	
	
	
		 
		@FXML private Button btn;
		
		@FXML private AnchorPane a ;
		
		@FXML private Button B3,retour ;
		
		@FXML private Button B4 ;
		
		@FXML private Button B5 ;
		
		@FXML private Button triangle,carre,rectangle,hexagone,pentagone,decagone;
		
		@FXML private Button open,save,bib,copier,coller,menu,home;
		
		@FXML private Button combine,and,or,not,xor,deplacement,rotation;
		
		@FXML private SubScene b ;
		
		@FXML private ColorPicker Color;
		
		@FXML private Button helpE;
				
		CopierColler c = new CopierColler();
		 CouperColler cc = new CouperColler();

		//Group racine = new Group();
		Group root = new Group();
		
		ArrayList<Polygone> P = new ArrayList<Polygone>(); 
		 
		Polygones Po = new Polygones();
		
		ArrayList<ArrayList<Polygone>> UnionT=new ArrayList<ArrayList<Polygone>>();
		ArrayList<Polygone> Poly=new ArrayList<Polygone>();
		ArrayList<Polygone> Display=new ArrayList<Polygone>();
		
		/***********************/
		  void initialise()
		    {   b.setRoot(root);  Polygone.Display = Display ; Polygone.UnionT = UnionT;
		        Polygone.root = root;
		    }
		    
		    @FXML
		    void save(ActionEvent event) {
		    	
		    	MiniWindow.nom_fichier(" Donner le nom d fichier ou enregistrer ");
		    	
		    	String nom_fich = MiniWindow.nom_fich;
		    	
		    	Sauvgarde s = new Sauvgarde(nom_fich,Po.Union,Po.Polygone,Po.Display);
		    	
		         s.sauvgarder(Po);
		          
		  		
		    }
		    
		    @FXML
		    void Color(ActionEvent event) {
		    	
		    	 P.get(0).color = Color.getValue(); 
		    	 
		    	 P.get(0).P.setFill( Color.getValue()); 
		    	 
		    	 Affichage_Display.Afficher(Display, UnionT, root); 
		    	 
		    	   
		    }
		    @FXML
		    void dep(ActionEvent event) {
		    	
		            P.get(0).deplace = true ;
		            
		            P.remove(0); 
		    }
		/***********************/
		    
		    @FXML
		    void ouvrir(ActionEvent event) {
		    	
		    	 MiniWindow.s = b;
		    	 
		        MiniWindow.nom_fichier(" Donner le nom d fichier à ouvrir  ");
		    	
		    	String nom_fich = MiniWindow.nom_fich;
		    	
		        Chargement c=new Chargement(nom_fich);
				
				c.chargement(Po, root);
		    }
		    
		    
		@FXML
		 void exit(ActionEvent event)
		{System.exit(0);}

	   @FXML
	    void creer3(ActionEvent event) {
		   Color C = new Color(0.7,0.28,0.4,1.0);
	    	
	    	Point centre = new Point(400,400);
	    	
	        double r = 3;
	        
	    	Point premier_point = new Point( centre.getx() + calc.convert(r +1 ), centre.gety() );
	    	
	    	Polygone_SCR P = new Polygone_SCR(centre,"3" ,C ,3 , premier_point,root);
	    	
	        Poly.add(P);
	    	
	    	Display.add(P);
	    	
	    	P.Dis = Display.size() -1 ;
	    	
	    	P.deplace = true;
	    	
	    	P.Reference_Polygone=P;
	    	
	    	root.getChildren().add(P.getDessin());
	    	
	    	initialise();
		   
		  
	   }

	   @FXML
	    void creer4(ActionEvent event) {
			
		      Color C = new Color(0.8,0.4,0.29,1.0);
		    	
		    	Point centre = new Point(400,400);
		    	
		        double r = 3;
		        
		    	Point premier_point = new Point( centre.getx() + calc.convert(r +1 ), centre.gety() );
		    	
		    	Polygone_SCR P = new Polygone_SCR(centre,"4" ,C ,4 , premier_point,root);
		    	
		        Poly.add(P);
		    	
		    	Display.add(P);
		    	
		    	P.Dis = Display.size() -1 ;
		    	
		    	P.Reference_Polygone=P;
		    	
		    	root.getChildren().add(P.getDessin());
		    	
		    	initialise();
		   
		  
	   }

	    @FXML
	    void creer5(ActionEvent event) {
	    	  Color C = new Color(0.5,0.8,0.5,1.0);
	      	
	      	Point centre = new Point(400,400);
	      	
	          double r = 3;
	          
	      	Point premier_point = new Point( centre.getx() + calc.convert(r +1 ), centre.gety() );
	      	
	      	Polygone_SCR P = new Polygone_SCR(centre,"5" ,C ,5 , premier_point,root);
	      	
	         Poly.add(P);
	      	
	      	Display.add(P);
	      	
	      	P.Dis = Display.size() -1 ;
	      	
	      	P.Reference_Polygone=P;
	      	
	      	root.getChildren().add(P.getDessin());
	      	
	      	initialise();
	    	
	    	
	    	
	    }
	    
	    @FXML
	    void creer6(ActionEvent event) {
	    	 Color C = new Color(0.2,0.2,0.2,1.0);
	     	
	     	Point centre = new Point(400,400);
	     	
	         double r = 3;
	         
	     	Point premier_point = new Point( centre.getx() + calc.convert(r +1 ), centre.gety() );
	     	
	     	Polygone_SCR P = new Polygone_SCR(centre,"6" ,C ,6 , premier_point, root);
	     	
	         Poly.add(P);
	     	
	     	Display.add(P);
	     	
	     	P.Dis = Display.size() -1 ;
	     	
	     	P.Reference_Polygone=P;
	     	
	     	root.getChildren().add(P.getDessin());
	     	
	     	initialise();
	    	
	    	
	    	
	    }

	    @FXML
	    void creer10(ActionEvent event) {
	    	Color C = new Color(0.3,0.2,0.3,1.0);
	    	
	    	Point centre = new Point(400,400);
	    	
	        double r = 3;
	        
	    	Point premier_point = new Point( centre.getx() + calc.convert(r +1 ), centre.gety() );
	    	
	    	Polygone_SCR P = new Polygone_SCR(centre,"10" ,C ,10 , premier_point,root);
	    	
	        Poly.add(P);
	    	
	    	Display.add(P);
	    	
	    	P.Dis = Display.size() -1 ;
	    	
	    	P.Reference_Polygone=P;
	    	
	    	P.deplace = true;
	    	
	    	root.getChildren().add(P.getDessin());
	    	
	    	initialise();
	    	
	    	 
	    }
	    
	    @FXML
	    public  void home(ActionEvent event) 
	    {
	    	Stage stage = (Stage) retour.getScene().getWindow();
	    	stage.close();
	    }
	    
	     @FXML
	    void creerRectangle(ActionEvent event) {
	    	 Color C = new Color(0.7,0.9,0.9,1.0);
	     	
	         Point centre = new Point(400,400);
	     	
	         double LO = 5; double LA = 3;
	         
	     	Rectangle P = new Rectangle(centre,"Rectangle", C, calc.convert( LO +1), calc.convert( LA +1), root);
	         
	     	Poly.add(P);
	     	
	     	Display.add(P);
	     	
	     	P.Dis = Display.size() -1 ;
	     	
	     	P.Reference_Polygone=P;
	     	
	     	root.getChildren().add(P.getDessin());
	     	
	     	initialise();
	    	 
	    	 
	     }
	   
	     @FXML
	     void disable(ActionEvent event) {
		
	     }

	  @FXML
	  void minimize(ActionEvent event) {
	      
	   }

	   @FXML
	   void tourne(ActionEvent event) {
		

      
		 
	    	 }
		   /***********/
	   @FXML
	    void select (ActionEvent event) { 
	    	
	    	
	    	MiniWindow.s = b ; 
	    	
	    	for (int ind = 0; ind < Poly.size(); ind++) {
	    		Poly.get(ind).cliq = 0 ; }
	    	
	    	
	    	 int i = outil.recherch(Poly);
	    	 
	    	
	    	if(i == -1 )  { 
	    		
	    		MiniWindow.warning("Vous devez reselectioner un polygone ! ");}
	    	
	    	else { 
	    		
	    		
	    		P.add( Poly.get(i) ); 
	    		}
	    	
	    }
	   
	   /***********************************/
	    @FXML
	    void OR(ActionEvent event) {
	    	
	      initialise();
	    	
           Operation_bool.ORT( P.get(0), P.get(1), Poly, Display, UnionT, P.get(0).color, root);
        	
    		
    		Affichage_Display.Afficher(Display, UnionT, root);
    		
    	
        	P.remove(1); P.remove(0);


	    }

	    @FXML
	    void AND(ActionEvent event) {
	    	
	    	initialise();
	    	
	    	Operation_bool.ANDT( P.get(0), P.get(1), Poly, Display, UnionT, P.get(0).color, root);
	    	
			
			Affichage_Display.Afficher(Display, UnionT, root);
			
	    	P.remove(1); P.remove(0);
	    }

	    @FXML
	    void NOT(ActionEvent event) {
	    	
           initialise();
           
	    	Operation_bool.NOTT( P.get(0), P.get(1), Poly, Display, UnionT, root);
	    	
	    	Affichage_Display.Afficher(Display, UnionT, root); 
		
	    	
	    	P.remove(1); P.remove(0);


	    }
	    @FXML
	    void XOR(ActionEvent event) {
	    	
	    	initialise();
	    	
	    	Operation_bool.XORT( P.get(0), P.get(1), Poly, Display, UnionT, root);
	    	
			
			Affichage_Display.Afficher(Display, UnionT, root);
		
			
	    	P.remove(1); P.remove(0);

	    }
	    /*****/
	   
	    
	    @FXML
	    void copier(ActionEvent event) {
	    	
	    	 Po.Display = Display;
	    	 
	    	 Po.Polygone = Poly;
	    	 
	    	 Po.Union = UnionT; 
	    	 
	    	
	    	 c.copier(P.get(0), Po, root); 
	    	 
	    	 P.remove(0);
	    	
	    }
	    @FXML
	    void coller(ActionEvent event) {
	    	
	    	if(c.polygoneCopie.listePoints!= null && c.polygoneCopie.listePoints.size()!=0)  c.coller(Po, root);
	    	else if(cc.polygoneCoupe.listePoints != null && cc.polygoneCoupe.listePoints.size()!=0)
	    	cc.coller(Po, root);
			
	    	initialise();
			Affichage_Display.Afficher(Display, UnionT, root);
			

	    }
	    /***********************************/
	    @FXML
	    void angle(ActionEvent event) {}
	    
	    @FXML
	    void num(ActionEvent event) {

	    }


	    @FXML
	    void rotation(ActionEvent event) {
	    	
	    		MiniWindow.tourne2();
	           
	    		 P.get(0).boge = 2; 
	    		
	    		 P.remove(0);
	    	

	    }
	    
	    @FXML
	    void colorer(ActionEvent event) {
	    	
	    	 P.get(0).color = Color.getValue(); 
	    	 
	    	 P.get(0).P.setFill( Color.getValue()); 
	    	 
	    	 Affichage_Display.Afficher(Display, UnionT, root); 
	    	   

	    }
	    @FXML
	    void helpe(ActionEvent event) throws IOException {
			
			FXMLLoader loader =new FXMLLoader(getClass().getResource("Help.fxml"));
			
		    Parent root =loader.load();
		    
		    Stage stage = new Stage();
		    
		    stage.setScene(new Scene(root));
		    
		    stage.setMinHeight(600);
		    stage.setMinWidth(600);
		    stage.setMaxHeight(600);
		    stage.setMaxWidth(600);
		    
		    stage.setTitle("Help");
		    
		    stage.show();


	}
	   /**********/
	  
	   }



	
	
	 


