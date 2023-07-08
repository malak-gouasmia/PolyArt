package application;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.SubScene;


public class MiniWindow {
	
	static Scene scene ;
	
	static ArrayList<ArrayList<Polygone>> Union;
	
	static ArrayList< Polygone > Dis;
	
	 static SubScene s ;
	
	 static int n;
	 
	 static double r;
	 
	 static String nom;
	 
	 static double LO;
	 
	 static double LA;
	 
	 static int b;
	 
	 static double angle;
	 
	 static Point p;
	 
	 static String nom_fich ;
	 
	 static Group root;
	 
	 static double DX,DY;
	 
		

	public static void display( String title, String message, String a ) {
		
		Stage window= new Stage();
		
		window.initModality( Modality.NONE );
		
		window.setTitle(title);
		
		window.setMinWidth(250);
		
		Label label = new Label( " Donner le nombre de cote " );
		
		
		Button submit = new Button(a);
		
		submit.setTranslateX(10);
		
		submit.setTranslateY(20);
		
		submit.setOnAction(e -> window.close() );
	
		
		//-------------------------
		Group racine = new Group();
		
		racine.getChildren().addAll(label,submit);
		
		Scene scene=new Scene(racine);
		
		window.setScene(scene);
		
		window.showAndWait();
		
	}
	
public static void poly_scr( )  {
		
		Stage window= new Stage();
		
		window.initModality( Modality.APPLICATION_MODAL );
		
		window.setTitle(" infos sur polygone ");
		
		window.setMinWidth(500);
		
		
		//////////////////////////////
		
		Label l1 = new Label( " le nombre de cote " );
		
        l1.setTranslateX(10);
		
		l1.setTranslateY(10);
		
		TextField nb = new TextField();
		
        nb.setTranslateX(180);
		
		nb.setTranslateY(10);
		
		///////////////////////////////
		
       Label l2 = new Label( "le Rayon " );
		
        l2.setTranslateX(10);
		
		l2.setTranslateY(70);
		
		TextField rayon = new TextField();
		
        rayon.setTranslateX(180);
		
		rayon.setTranslateY(70);
		
        Label l3 = new Label( "Le nom  " );
		
        l3.setTranslateX(10);
		
		l3.setTranslateY(130);
		
		TextField N = new TextField();
		
        N.setTranslateX(180);
		
		N.setTranslateY(130);
		
		
		Button submit = new Button(" Cree ");
		
		submit.setTranslateX(400);
		
		submit.setTranslateY(400);
		
	
	
		submit.setOnAction(e-> {
				
					n = (int) Integer.parseInt(  nb.getText() );
					
					r = (double )Double.parseDouble(  rayon.getText() );
					
					nom = N.getText();
					
				
			window.close();
		});
		
		//-------------------------
		Group racine = new Group();
		
		racine.getChildren().addAll(l1,l2,l3,N,rayon,nb,submit);
		
		Scene scene=new Scene(racine);
		
		window.setScene(scene);
		
		window.showAndWait();
		
	}
	
	
public static void rect( )  {
	
	Stage window= new Stage();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setTitle(" infos sur polygone ");
	
	window.setMinWidth(500);
	
	
	//////////////////////////////
	
	Label l1 = new Label( "Longuer " );
	
    l1.setTranslateX(10);
	
	l1.setTranslateY(10);
	
	TextField nb = new TextField();
	
    nb.setTranslateX(100);
	
	nb.setTranslateY(10);
	
	///////////////////////////////
	
   Label l2 = new Label( "Largeur " );
	
    l2.setTranslateX(10);
	
	l2.setTranslateY(70);
	
	TextField rayon = new TextField();
	
    rayon.setTranslateX(100);
	
	rayon.setTranslateY(70);
	
    Label l3 = new Label( "Le nom  " );
	
    l3.setTranslateX(10);
	
	l3.setTranslateY(130);
	
	TextField N = new TextField();
	
    N.setTranslateX(100);
	
	N.setTranslateY(130);
	
	
	Button submit = new Button(" Cree ");
	
	submit.setTranslateX(400);
	
	submit.setTranslateY(400);
	


	submit.setOnAction(e-> {
			
				LO = (double) Double.parseDouble(  nb.getText() );
				
				LA = (double )Double.parseDouble(  rayon.getText() );
				
				nom = N.getText();
				
			
		window.close();
	});
	
	//-------------------------
	Group racine = new Group();
	
	racine.getChildren().addAll(l1,l2,l3,N,rayon,nb,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}

public static void tourne( )  {
	
	Stage window= new Stage();
	
	Group racine = new Group();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setMinWidth(300);
	
    Button l1 = new Button( "Par Souris" );
	
    l1.setTranslateX(50);
	
	l1.setTranslateY(50);
	
	l1.setOnMouseClicked( new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			
		b = 2 ;
		
		}
		
	});
	
	///////////////////////////////
	
   Button l2 = new Button( "Numérique " );
	
    l2.setTranslateX(200);
	
	l2.setTranslateY(50);
	
	TextField t = new TextField( "Entrer angle " );
	
    t.setTranslateX(150);
	
	t.setTranslateY(90);
	
	l2.setOnMouseClicked( new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			
		b = 3 ;
		 
		
		
		racine.getChildren().add(t);
		
		
		}
		
	});
	
Button submit = new Button(" terminé  ");
	
	submit.setTranslateX(250);
	
	submit.setTranslateY(250);
	


	submit.setOnAction(e-> {
			
				if(b == 3 )
				{
					angle  = (double )Double.parseDouble(  t.getText() );
				}
				
			
		window.close();
	});
	
	
	
	//////////////////////////////
	
 
	
	racine.getChildren().addAll(l1,l2,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}
/********************************************/

public static void tourne2( )  {
	
	Stage window= new Stage();
	
	Group racine = new Group();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setMinWidth(300);
	
    Button l1 = new Button( "Par Souris" );
	
    l1.setTranslateX(50);
	
	l1.setTranslateY(50);
	
	l1.setOnMouseClicked( new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			
		b = 2 ;
		
		}
		
	});
	
	///////////////////////////////
	

	
Button submit = new Button(" terminé  ");
	
	
	submit.setOnAction(e-> {
			
		window.close();
	});
	
	
	
	//////////////////////////////
	
 
	
	racine.getChildren().addAll(l1,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}
/********************************************/
public static void deplace( )  {
	
	Stage window= new Stage();
	
	Group racine = new Group();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setMinWidth(300);
	
    Button l1 = new Button( "Par Souris" );
	
    l1.setTranslateX(50);
	
	l1.setTranslateY(50);
	
	l1.setOnMouseClicked( new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			
		b = 2 ;
		
		}
		
	});
	
	///////////////////////////////
	
   Button l2 = new Button( "Numérique " );
	
    l2.setTranslateX(200);
	
	l2.setTranslateY(50);
	
	TextField t1 = new TextField( "Entrer DX " );
	
    t1.setTranslateX(150);
	
	t1.setTranslateY(90);
	
TextField t2 = new TextField( "Entrer DY " );
	
    t2.setTranslateX(150);
	
	t2.setTranslateY(160);
	
	l2.setOnMouseClicked( new EventHandler<Event>() {

		@Override
		public void handle(Event event) {
			
		b = 3 ;
		
		racine.getChildren().addAll(t1,t2);
		
		
		}
		
	});
	
Button submit = new Button(" terminé  ");
	
	submit.setTranslateX(250);
	
	submit.setTranslateY(250);
	


	submit.setOnAction(e-> {
			
				if(b == 3 )
				{
					DX  = (double )Double.parseDouble(  t1.getText() );
					DY = (double )Double.parseDouble(  t2.getText() );
				}
				
			
		window.close();
	});
	
	
	
	//////////////////////////////
	
 
	
	racine.getChildren().addAll(l1,l2,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}

/********************************************/

public static void select_poly(  String msg )  {
	
	
	Stage window = new Stage();
	
	window.initModality( Modality.NONE );
	
	window.setTitle(" select polygone ");
	
	window.setMinWidth(300);
	
	
	//////////////////////////////
	
	Label l1 = new Label( msg );
	
    l1.setTranslateX(10);
	
	l1.setTranslateY(10);
	
	
	
	/*Button submit = new Button(" Done ! ");
	
	submit.setTranslateX(300);
	
	submit.setTranslateY(300); */
	
	
	s.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			window.close();
			
			}});

/*	submit.setOnAction(e-> {
		
		s.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				
				
				
				p.setx(t.getSceneX());
				p.sety(t.getSceneY());
				
				System.out.println(" p.x "+p.getx()+" y "+p.gety());
				
				}});
	
		
		window.close();
	}); */
	
	//-------------------------
	Group racine = new Group();
	
	racine.getChildren().addAll(l1);
	
	Scene scene = new Scene(racine);
	
	window.setScene(scene);
	
	
	
	window.showAndWait();
	
}
/****************/
public static void warning(  String msg )  {
	
	Stage window= new Stage();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setTitle(" Warning ");
	
	window.setMinWidth(300);
	
	
	//////////////////////////////
	
	Label l1 = new Label( msg );
	
    l1.setTranslateX(10);
	
	l1.setTranslateY(10);
	
	
	
	Button submit = new Button(" ok ! ");
	
	submit.setTranslateX(300);
	
	submit.setTranslateY(300);
	
	
	submit.setOnAction(e-> { window.close(); });
	
	//-------------------------
	Group racine = new Group();
	
	racine.getChildren().addAll(l1,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}

public static void nom_fichier(  String msg )  {
	
	Stage window= new Stage();
	
	window.initModality( Modality.APPLICATION_MODAL );
	
	window.setTitle(" Nom de fichier ");
	
	window.setMinWidth(200);
	
	
	//////////////////////////////
	
	Label l1 = new Label( msg );
	
    l1.setTranslateX(10);
	
	l1.setTranslateY(10);
	
   TextField t  = new TextField();
	
    t.setTranslateX(70);
	
	t.setTranslateY(10);
	
	Button submit = new Button(" ok ! ");
	
	submit.setTranslateX(300);
	
	submit.setTranslateY(300);
	
	
	submit.setOnAction(e-> { 
		
		 nom_fich = t.getText();
		
		window.close(); }
	);
	
	
	
	//-------------------------
	Group racine = new Group();
	
	racine.getChildren().addAll(l1,t,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
}


public static ArrayList<Point> cry(  String msg )  {
	
	Stage window= new Stage();
	
	window.initModality( Modality.NONE );
	
	window.setTitle("Crayon ");
	
	window.setMinWidth(200);
	
	
	//////////////////////////////
	
	Label l1 = new Label( msg );
	
    l1.setTranslateX(10);
	
	l1.setTranslateY(10);
	
  
	
	Button submit = new Button(" ok ! ");
	
	submit.setTranslateX(300);
	
	submit.setTranslateY(300);
	
	ArrayList<Point>  A = null ;
	
	
	submit.setOnAction(e-> { 
		
		
		
		window.close(); 
		
	   }
	);
	
	
	
	//-------------------------
	Group racine = new Group();
	
	racine.getChildren().addAll(l1,submit);
	
	Scene scene=new Scene(racine);
	
	window.setScene(scene);
	
	window.showAndWait();
	
	return( A );
	
   }
	
}
