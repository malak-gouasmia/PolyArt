package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdulteController {
	
	@FXML
    private AnchorPane scene1;
	
	Group root = new Group();
	
	@FXML
    private SubScene b ;
	
	ArrayList<ArrayList<Polygone>> UnionT=new ArrayList<ArrayList<Polygone>>();
	
	ArrayList<Polygone> Poly=new ArrayList<Polygone>();
	
	ArrayList<Polygone> tabCrayon =new ArrayList<Polygone>();
	
	ArrayList<Polygone> Display=new ArrayList<Polygone>();
	
	CopierColler c = new CopierColler();
	
    CouperColler cc = new CouperColler();

	  Polygones Po = new Polygones();

	 
	  ArrayList<Polygone> P = new ArrayList<Polygone>(); 
    
	  
    @FXML
    private Button retour;

    @FXML
    private Button OR;

    @FXML
    private Button AND;

    @FXML
    private Button NOT;

    @FXML
    private Button XOR;
    @FXML
    private Button rotation;

    @FXML
    private Button Polygone_scr;

    
    @FXML
    private Button rectangle;
    
    @FXML
    private Button help;

    @FXML
    private ColorPicker Color;
    
    @FXML
    private Button copier;

    @FXML
    private Button coller;
    
    @FXML
    private Button select;
    
    @FXML
    private Button Crayon ;
    
    @FXML
    private Button couper ;
    
   
    void initialise()
    {   b.setRoot(root);  Polygone.Display = Display ; Polygone.UnionT = UnionT; Polygone.root = root;
    Polygone.tabCrayon = tabCrayon; }
    
    


    @FXML
    void couper(ActionEvent event) {
    	
    	initialise();
    	
   	     Po.Display = Display;
	 
   	     Po.Polygone = Poly;
   	 
   	     Po.Union = UnionT; 
    	    	
   	     cc.couper(Po, P.get(0), root);
    	
   	 P.remove(0);

  		
    }
    
    @FXML
    void save(ActionEvent event) {
    	
    	initialise();
    	
    	FileChooser r = new FileChooser();
    	
		r.setTitle("Save");
		
		r.setInitialFileName("mySave");
		
		r.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("bin file", "*.bin"));
		
		File s = r.showSaveDialog(null);
		
		if(s!=null) {
			
			r.setInitialDirectory(s.getParentFile());
			
			String nom_fich=s.getPath();
			
			Po.Display = Display;  Po.Polygone = P;  Po.Union = UnionT;
			
	    	Sauvgarde sauv = new Sauvgarde(nom_fich,Po.Union,Po.Polygone,Po.Display);
	    	
	    	sauv.sauvgarder(Po);
		}
			
    	
        
         
  		
    }
    
    @FXML
    void ouvrir(ActionEvent event) {
    	
    	root = new Group();
    	
    	String filePath = null;
    	
    	FileChooser r = new FileChooser();
    	
		File s = r.showOpenDialog(null);
		
		if(s!=null) {
			
			filePath = s.getAbsolutePath();
			
			Chargement c = new Chargement(filePath);
			
			System.out.println("path: "+filePath);
			
			c.chargement(Po, root);
			
			
			initialise();
			
			Affichage_Display.Afficher(Po.Display, Po.Union, root);
			
			
			
		}
		else {
			System.out.println("fileNotFound");
		}
		
    }
    
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
		Affichage_Display.afficher_crayon(root, tabCrayon);
		

    }
    
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
    	
   

   
    @FXML
    void colorer2(ActionEvent event) {
    	
    	
    }

    @FXML
    void colorere(ActionEvent event) {
    	
    	 P.get(0).color = Color.getValue(); 
    	 
    	 P.get(0).P.setFill( Color.getValue()); 
    	 
    	 Affichage_Display.Afficher(Display, UnionT, root); 
    	 Affichage_Display.afficher_crayon(root, Display);
    	
    }
    
    

    @FXML
    void Polygone_scr(ActionEvent event) {
    	
    	MiniWindow.poly_scr();
    	
    	System.out.println(" NB "+MiniWindow.n+" r "+MiniWindow.r);
    	
    	
    	
    	Point centre = new Point(300,300);
    	
    	Color C = new Color(0.5,0.5,0.5,1.0);
    	
          
    	Point premier_point = new Point( centre.getx() + calc.convert(MiniWindow.r +1), centre.gety() );
    	
    	Polygone_SCR P = new Polygone_SCR(centre, MiniWindow.nom ,C ,MiniWindow.n , premier_point,root);
    	
    	Poly.add(P);
    	
    	Display.add(P);
    	
    	P.Dis = Display.size() -1 ;
    	
    	P.Reference_Polygone=P;
    	
    	root.getChildren().add(P.getDessin());
    	
    	initialise();
    	
    }
    
    @FXML
    void rectangle(ActionEvent event) {
    	
    	MiniWindow.rect();
    	
    	System.out.println(" NB "+MiniWindow.n+" r "+MiniWindow.r);
    	
    	Point centre = new Point(300,300);
    	
    	Color C = new Color(0.5,0.5,0.5,1.0);
    	
       
    	Rectangle P = new Rectangle(centre,MiniWindow.nom, C, calc.convert( MiniWindow.LO +1), calc.convert( MiniWindow.LA +1),root);
    	
    	Poly.add(P);
    	
    	Display.add(P);
    	
    	P.Dis = Display.size() -1 ;
    	P.Reference_Polygone=P;
    	
    	root.getChildren().add(P.getDessin());
    	
    	initialise();
    }
    
    /***********************************/
    @FXML
    void Crayon(ActionEvent event) {
    	
    	
    	ArrayList< Point > B = null;
    	
    	Button a = new Button("Colorer");
    	
    	scene1.getChildren().add(a); 
    	
    	 a.setLayoutX(Crayon.getLayoutX()  );  a.setLayoutY(Crayon.getLayoutY() + 60); 
    	 
    	initialise(); 
    	
    	Operation_bool.crayon( b , root , true , tabCrayon, a);
    	
    	
    }
    
    /**********************************/
    @FXML
    void OR(ActionEvent event) {
    	
    	     initialise();
    		Operation_bool.ORT( P.get(0), P.get(1), Poly, Display, UnionT, P.get(0).color, root);
        	
    		
    		Affichage_Display.Afficher(Display, UnionT, root);
    		Affichage_Display.afficher_crayon(root, tabCrayon);
    		
    	
    		
        	P.remove(1); P.remove(0);

    }

    @FXML
    void AND(ActionEvent event) {
    	
    	initialise();
    	
		Operation_bool.ANDT( P.get(0), P.get(1), Poly, Display, UnionT, P.get(0).color, root);
    	
		
		Affichage_Display.Afficher(Display, UnionT, root);
		Affichage_Display.afficher_crayon(root, tabCrayon);
		
    	
    	
    	P.remove(1); P.remove(0);
    
        }

    @FXML
    void NOT(ActionEvent event) {
    	
    	initialise();
    	
		Operation_bool.NOTT( P.get(0), P.get(1), Poly, Display, UnionT, root);
    	
    	Affichage_Display.Afficher(Display, UnionT, root); 
    	Affichage_Display.afficher_crayon(root, tabCrayon);
	
    	
    	P.remove(1); P.remove(0);

    }
    @FXML
    void XOR(ActionEvent event) {
    	
    	initialise();

		Operation_bool.XORT( P.get(0), P.get(1), Poly, Display, UnionT, root);
    	
		
		Affichage_Display.Afficher(Display, UnionT, root);
		Affichage_Display.afficher_crayon(root, tabCrayon);
		
    	P.remove(1); P.remove(0);

    }
    /***********************************/

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
    void exit(ActionEvent event) { System.exit(0); }
    

    @FXML
    
	void helpp(ActionEvent event) throws IOException {
		
		FXMLLoader loader =new FXMLLoader(getClass().getResource("help3.fxml"));
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


    @FXML
    void home(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) retour.getScene().getWindow();
    	
    	stage.close();

    }
    
    

    @FXML
    void angle(ActionEvent event) {}
    
    @FXML
    void num(ActionEvent event) {

    }


    @FXML
    void rotation(ActionEvent event) {
    	
    		
            MiniWindow.tourne();
    		
    		if( MiniWindow.b == 2 ) { P.get(0).boge = 2; }
    		
    		else { if( MiniWindow.b == 3 ) { P.get(0).tourner(MiniWindow.angle );}}
			
    		P.remove(0);
    	

    }
    
    @FXML
    void dep(ActionEvent event) {
    	
    	MiniWindow.deplace();
    	
    	
    	 if( MiniWindow.b == 2 ) {  P.get(0).deplace = true ;  }
 		
 		else { if( MiniWindow.b == 3 ) { P.get(0).Deplacer( MiniWindow.DX , MiniWindow.DY );}}
			
 		
            
            P.remove(0);
    }
    @FXML
    void close(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }
    
    @FXML
    void BIB(ActionEvent event) {
    	

		
    	Chargement_Bibbel cb = new Chargement_Bibbel("scr\\application\\test.bin");
    	
    	
    	initialise();
    	
    	
    	cb.chargement(Po, root);
    	
    	for (int i = 0; i < Po.Display.size(); i++) {
    		
    	Polygone.Display.add( Po.Display.get(i) );	
			
		}
    	
    	for (int i = 0; i < Po.Display.size(); i++) {
    		
        	Poly.add( Po.Display.get(i) );	
    			
    		}
    	
 for (int i = 0; i < Po.Union.size(); i++) {
    		
        	Polygone.UnionT.add( Po.Union.get(i) );	
    			
    		}
    	
		Affichage_Display.Afficher(Polygone.Display, Polygone.UnionT, root);
		
		
    	
    }

}
