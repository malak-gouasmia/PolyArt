package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import jdk.internal.org.jline.utils.Display;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.*;

import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.shape.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


class SerializablePath extends Path implements Serializable,Cloneable{
	  SerializablePath(){
		  super();
	  }
}

class calc
{
   public static double pro(double k)
   {
	   double l=(k*100000000);
		  
	    double f= (double)   ((double)(Math.round(l) / (double) 100000000) ) ;  
	  
	    return( f );
	   
   }
   
   public static double convert_pix_cm(double px)
   {
	   
	   return(px*0.0264583333);
   }
   
   public static double convert(double cm)
   {
	   
	   return(cm/0.0264583333);
   }
   
   
   
}



class Point implements Serializable,Cloneable{
		
	private double x;
	private double y;
	private int indice;
	public void setind(int ind)
	{
		indice = ind;
	}
	public int getind()
	{
		return indice ;
	}
	public void setx(double x) {
		this.x=x;
	}
	
	public double getx(){
		return x;
	}
	
	public void sety(double y) {
		this.y=y;
	}
	
	public double gety(){
		return y;
	}
	
	public Point(double absc, double ord){
		x = absc;
		y = ord;
	}
	
	public void init(double absc, double ord){
		x = absc;
		y = ord;
	}
	
	public Point() {}
	
	public void deplacer(double dx, double dy){
		x = x + dx;
		y = y + dy;
	}
	
	public void tourner(Point centre,double angle){
	    angle = Math.PI*angle/180;
		double dx = x - centre.getx();
	    double dy = y - centre.gety();
	    x = dx * Math.cos(angle) + dy * Math.sin(angle) + centre.getx();
	    y = -dx * Math.sin(angle) + dy * Math.cos(angle) + centre.gety();
	}
	
	public double distance ( Point P ) 
	{
		double k=Math.sqrt( (this.x - P.getx() )*(this.x - P.getx() ) + (this.y - P.gety())*(this.y - P.gety()) ); 		
		
		return(calc.pro(k));		  	
	}
	
	public double distance ( double PX, double PY ) 
	{
		double k=Math.sqrt( (this.x - PX )*(this.x - PX) + (this.y - PY)*(this.y - PY) ); 		
		
		return(calc.pro(k));		  	
	}
	
}

class Polygone implements Serializable,Cloneable{
	
static 	ArrayList<ArrayList<Polygone>> UnionT ;
	
static 	ArrayList<Polygone> Display ;

static 	ArrayList<Polygone> tabCrayon ;

protected ArrayList<Point> listePoints = new ArrayList<Point>();

protected double orgSceneX,orgSceneY;

protected transient Color color;

protected String colorS;

protected String nom;

public SerializablePath P = new SerializablePath();

protected Polygone Reference_Polygone ;

protected int cliq ;

static int nb;

protected int n;

protected int boge; // 1 depplacment, 2 rotation, O sinon

protected int Union ;

protected static transient Group root;

protected int Dis;

protected ArrayList<Polygone> Diff;

protected boolean deplace;

//---------Copier attribut !-----------

public int doesExistInList(ArrayList<Polygone> Lp) {
	int index=0;
	
	for(Polygone p:Lp) {
		
		if(this.nom.equals(p.nom))
			return index;
		index++;
	}
		
	return -1;
}
public void copierattribut(Polygones P,Polygone Polygone2) {//this<===Polygone2
	this.boge=Polygone2.boge;
	this.cliq=Polygone2.cliq;
	this.color=Polygone2.color;
	this.copierlistDeDiff(P,Polygone2);
	//this.diff_GPere=Polygone2.diff_GPere;
	//this.diff_Pere=Polygone2.diff_Pere;
	this.Dis=P.Display.size();
	this.copierListeDePoints(Polygone2);
	this.n=Polygone2.n;
	this.nom=Polygone2.nom + "1";
	this.orgSceneX=Polygone2.orgSceneX;
	this.orgSceneY=Polygone2.orgSceneY;
	this.P=new SerializablePath();
	//this.racine=Polygone2.racine;
	this.Union=Polygone2.Union;
	this.nb=Polygone2.nb;
	this.UnionT=Polygone2.UnionT;
	this.root = Polygone2.root;
	this.colorS=color.toString();

}

public void copierListeDePoints(Polygone P2) {
	this.listePoints=new ArrayList<Point>();
	for(Point p:P2.listePoints) {
		this.listePoints.add(new Point(p.getx()+5,p.gety()+5));
		
	}
		
	
}

public void copierlistDeDiff(Polygones P,Polygone P2) {
	this.Diff=new ArrayList<Polygone>();
	for(int i=0;i<P2.Diff.size();i++) {
		this.Diff.add(new Polygone());
		this.Diff.get(i).copierattribut(P,P2.Diff.get(i));
	}
}



public Polygone() {  
	Diff  = new ArrayList<Polygone>();  Union= -1; boge = 1;  //diff_Pere=null; racine=null;
    Dis = -1;

P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			cliq=1;		
			
		}
		
	});

	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
		  orgSceneX = t.getSceneX();
          orgSceneY = t.getSceneY();
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			//System.out.println(" RELEASED  ");
		 double offsetX = t.getSceneX() - orgSceneX;
          double offsetY = t.getSceneY() - orgSceneY;
  
          //Deplacer(offsetX, offsetY);
          if( deplace = true ) {
          if(Union == -1 && Diff.size() == 0) {
        	  
        	  deplacer(offsetX, offsetY);
        	  
     		  Affichage_Display.Afficher(Display,UnionT,root);
     		  Affichage_Display.afficher_crayon(root, Display);
          } 
          else
          {
        	  Deplacer(offsetX, offsetY);
     		  
          }
          

           
          deplace = false ; }
           }
		
	});

}
public void polygone2() {
	
	P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			cliq=1;
			
			
			
		}
	});
	
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
		  orgSceneX = t.getSceneX();
          orgSceneY = t.getSceneY();
       
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
		 double offsetX = t.getSceneX() - orgSceneX;
          double offsetY = t.getSceneY() - orgSceneY;
  
          if( deplace ) {
          if(Union == -1 && Diff.size() == 0) {
        	  deplacer(offsetX, offsetY);
     		  Affichage_Display.Afficher(Display,UnionT,root);
          } 
          else
          {
        	  Deplacer(offsetX, offsetY);
     		  Affichage_Display.Afficher(Display,UnionT,root);
          }           }
		deplace = false; }
		
	});
}

public Polygone(String nom,Color color,Group root){
	
	this.nom=nom;
	this.colorS=color.toString();
	this.root=root;
	
	this.color=color;
	
	Diff  = new ArrayList<Polygone>();	
	
	Union= -1;   Dis = -1; 
	
	boge = 1;
	
}

public Polygone(ArrayList<Point> listePoints,String nom,Color color,Group root){
	
	this.listePoints = listePoints;
	
	this.root = root;
	
	this.nom=nom;
	
	this.color = color;
	
	this.root = root;
	
	this.colorS=color.toString();

    Diff  = new ArrayList<Polygone>();	
    
    Union = -1;
    
    P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			cliq=1;
			
		}
	});
	
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
         
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
           // Deplacer(offsetX, offsetY);
            //Affichage_Display.Afficher(Display,UnionT,root);
if(deplace) {
            if(Union == -1 && Diff.size() == 0) {
          	  deplacer(offsetX, offsetY);
       		  Affichage_Display.Afficher(Display,UnionT,root);
       		  Affichage_Display.afficher_crayon(root, tabCrayon);
            }
            else
            {
          	  Deplacer(offsetX, offsetY);
       		  Affichage_Display.Afficher(Display,UnionT,root);
       		Affichage_Display.afficher_crayon(root, tabCrayon);
            }
		deplace = false;} 
   }
	});
}


public Path getDessin() {
	
	dessiner();
	
	return P;
}

public void dessiner() {
	
	Point p;

	
	if(listePoints.size() > 0) {
		
		P.getElements().clear();
		
		MoveTo start = new MoveTo();
		
		start.setX(listePoints.get(0).getx());
		start.setY(listePoints.get(0).gety());
		
		P.getElements().add(start);
		
		for(int i = 1; i < listePoints.size()-1 ; i++) {
			
			p = listePoints.get(i);
			LineTo ligne= new LineTo(p.getx(),p.gety());
			P.getElements().add(ligne);
			
		}
		
		if( (listePoints.get(listePoints.size()-1).getx() == listePoints.get(0).getx() ) && (listePoints.get(listePoints.size()-1).gety() == listePoints.get(0).gety() ) )
		{  
			LineTo ligne= new LineTo(listePoints.get(0).getx(),listePoints.get(0).gety());
			listePoints.remove( listePoints.size() -1 );
			P.getElements().add(ligne);	
		}
		else
		{  
			p = listePoints.get( listePoints.size()-1);
			LineTo ligne= new LineTo(p.getx(),p.gety());
			P.getElements().add(ligne);
			
            LineTo ligne1= new LineTo(listePoints.get(0).getx(),listePoints.get(0).gety());
			
			P.getElements().add(ligne1);
			
		}
		
		
		P.getElements().add(new ClosePath());
		
		P.setFill( this.color );
		
		P.setStroke(Color.TRANSPARENT);
		
		P.setStrokeWidth(1);
		
		
	}
}
public void deplacer(double dx, double dy) {
	
	for(Point p:listePoints)
	{
		p.deplacer(dx, dy);
	}
	
	dessiner();
	
}

public void Deplacer(double dx,double dy)
{
	 if(Union == -1 ) {
		deplacer(dx, dy);  outil.Deplacer_Diff( Reference_Polygone ,UnionT, dx,dy, root);}
	 
	 else {
		 
		 for (Polygone p : UnionT.get(Union)) {
			 
			 p.deplacer(dx, dy);  outil.Deplacer_Diff( p,UnionT, dx,dy, root);   }
		
	    }
	 
	 Affichage_Display.Afficher(Display,UnionT,root);
}

public void tourner(double angle) {
	
	
}


}

/*****************************************************************/

class Rectangle extends Polygone implements Serializable,Cloneable{

private Point centre,premier_point;

private double longeur,largeur;

//private double orgSceneX,orgSceneY;


public Rectangle(){ 
	
	Dis = -1; Union = -1;
	
	P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			cliq=1;
			
			
			
		}
	});
	
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
         
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
    
          
            switch (boge) {
			case 1: {
				if(deplace) {
	             
		          if(Union == -1 && Diff.size() == 0) {
		        	  deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          }  
		          else
		          {
		        	  Deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		          }  deplace = false;  }
		          break;    
		          }
			case 2:{ 
			Point A = new Point( orgSceneX, orgSceneY );
			Point B = new Point( t.getSceneX(), t.getSceneY() );
			
			Segment OA = new Segment(centre, A  );
			Segment OB = new Segment(centre, B  );
			
			double o = Seg_Seg.angle_produit_scalaire(OB, OA)[1];
			
			if( Seg_Seg.angle_produit_scalaire(OB, OA)[0]< 0 )
		    { tourner(-o); }
		
		     else {  tourner(+o);  }
			
			break;
			}
			
            }
            boge=1;
           
		}
	});
};

public Rectangle(Point centre,String nom,Color color,double longeur,double largeur,Group root) {
	
	super(nom,color,root);
	
	this.centre = centre;
	
	this.premier_point=new Point((centre.getx()+longeur/2),(centre.getx()- largeur/2));
	
	this.largeur=largeur;
	
	this.longeur=longeur;
	
	listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	
	Union=-1; Dis = -1;
	
	
	
	dessiner();
	
	P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			cliq=1;
			
			
		}
	});
	 
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
         
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
    
          
           
            switch (boge) {
			case 1: {    
				if( deplace ) {
		          if(Union == -1 && Diff.size() == 0) {
		        	  deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          }  
		          else
		          {
		        	  Deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          }
		           deplace = false ;}
				break;    }
			case 2:{ 
			Point A = new Point( orgSceneX, orgSceneY );
			Point B = new Point( t.getSceneX(), t.getSceneY() );
			Segment OA = new Segment(centre, A  );
			Segment OB = new Segment(centre, B  );
			
			double o = Seg_Seg.angle_produit_scalaire(OB, OA)[1];
			
			if( Seg_Seg.angle_produit_scalaire(OB, OA)[0]< 0 )
			    { tourner(-o); }
			
			else {  tourner(+o);  }
			
			
               //LE SENS O OU -O  
			break;
			}
			
            }
            boge=1;
           
		}
	});
}

public Point getCentre() {
	return centre;
}
public Point getPremier_point() {
	return premier_point;
}
	
public void deplacer(double dx, double dy) {
	
	centre.deplacer(dx,dy);
	
	premier_point.deplacer(dx, dy);
	
	
	listePoints.clear();
	
	
    listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	
	
	dessiner();
}
	
public void tourner(double angle) {
	
	premier_point.tourner(centre, angle);

    listePoints.clear();
	
    listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	
	dessiner();
}


public ArrayList<Point> gen_points(){
	
	Point p = new Point(premier_point.getx(),premier_point.gety());
	
	ArrayList<Point> l = new ArrayList<Point>();
	
	double angle = Math.toDegrees(2*Math.atan(longeur/largeur));
	
	    p.tourner(centre, angle);
	    
	    Point point = new Point(p.getx(),p.gety());
	    
	    l.add(point);
	    
	    p.tourner(centre, 180-angle);
	    
	    point = new Point(p.getx(),p.gety());
	    
	    l.add(point);
	    
	    p.tourner(centre, angle);
	    
	    point = new Point(p.getx(),p.gety());
	    
	    l.add(point);
	    
	    p.tourner(centre, 180-angle);
	    
	    point = new Point(p.getx(),p.gety());
	    
	    l.add(point);
	return l;
}


}
/****************************************************/
class  Polygone_SCR extends Polygone implements Serializable,Cloneable{

private Point centre,premier_point;

private int nb_sommet;



public Polygone_SCR(){ 
	
	Dis = -1;
	
	P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			cliq=1;
			
			
		}
	});
	
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
         
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
    
          
            switch (boge) {
			case 1: { 
				
				if(deplace ) {
					
		          if(Union == -1 && Diff.size() == 0) {
		        	  
		        	  deplacer(offsetX, offsetY);
		        	  
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		  
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          }  
		          else
		          {
		        	  Deplacer(offsetX, offsetY);
		        	  
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		  
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		     		 
		          } deplace = false ;}
				
				break;    }
			
			case 2:{ 
				
			Point A = new Point( orgSceneX, orgSceneY );
			Point B = new Point( t.getSceneX(), t.getSceneY() );
			
			Segment OA = new Segment(centre, A  );
			Segment OB = new Segment(centre, B  );
			
			double o = Seg_Seg.angle_produit_scalaire(OB, OA)[1];
			
			if( Seg_Seg.angle_produit_scalaire(OB, OA)[0]< 0 )
		    { tourner(-o); }
		
		     else {  tourner(+o);  }
			
			break;
			}
			
            }
            boge=1;
           
		}
	});
};

public Polygone_SCR(Point centre,String nom,Color color,int nb_sommet,Point premier_point,Group root) {
	
	super(nom,color,root);
	
	
	this.centre = centre;
	this.nb_sommet=nb_sommet;
	this.premier_point=premier_point;
	
	listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	Union=-1; Dis = -1;

	
	dessiner();
	
	P.setOnMouseClicked( new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			cliq=1;
			
			
			
		}
	});
	
	P.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			
			orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
            
		}
		
	});
	
	P.setOnMouseReleased(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			
			double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
    
          
            switch (boge) {
			case 1: { 
				
				if( deplace ) {
	              
		          if(Union == -1 && Diff.size() == 0) {
		        	  deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          }  
		          else
		          {
		        	  Deplacer(offsetX, offsetY);
		     		  Affichage_Display.Afficher(Display,UnionT,root);
		     		 Affichage_Display.afficher_crayon(root, tabCrayon);
		          } 
				
				deplace = false; }
				
		          break;    }
			
			case 2:{ 
				
			Point A = new Point( orgSceneX, orgSceneY );
			Point B = new Point( t.getSceneX(), t.getSceneY() );
			
			Segment OA = new Segment(centre, A  );
			Segment OB = new Segment(centre, B  );
			
			double o = Seg_Seg.angle_produit_scalaire(OB, OA)[1];
			
			if( Seg_Seg.angle_produit_scalaire(OB, OA)[0]< 0 )
		    { tourner(-o); }
		
		     else {  tourner(+o);  }
			
			break;
			
			}
			
            }
            boge=1;
           
		}
	});
}




public Point getCentre() {
	return centre;
}

public Point getPremier_point() {
	return premier_point;
}

public void deplacer(double dx, double dy) {
	
	centre.deplacer(dx,dy);
	
	premier_point.deplacer(dx, dy);
	
	listePoints.clear();
	
    listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	
	dessiner();
}

public void tourner(double angle) {
	
	premier_point.tourner(centre, angle);
	
    listePoints.clear();
	
    listePoints.add(premier_point);
	
	listePoints.addAll(gen_points());
	
	dessiner();
}

public ArrayList<Point> gen_points(){
	
	Point p = new Point(premier_point.getx(),premier_point.gety());
	
	ArrayList<Point> l = new ArrayList<Point>();
	double angle = 360/nb_sommet;
	
	for(int i=0;i<nb_sommet-1;i++) {
		
		p.tourner(centre, angle);
		
		Point point = new Point(p.getx(),p.gety());
		
		l.add(point);
	}

	return l;
}
}

/****************************************************************/

class Segment{
	
    private Point P1,P2;
    private double a, b; 
    private boolean fini = false; // vrai si a est fini, faux sinon
    
    public Segment(Point P1,Point P2) {
    	this.P1=P1;
    	this.P2=P2;
    }

    public void  geo_equ_droite(){
    	if( P1.getx() == P2.getx()) { a = P1.getx(); }
    	else {
	 	   	fini = true ;    
	 	   	a = (P2.gety() - P1.gety()) / ( P2.getx() - P1.getx()) ;            
	 	   	b = P1.gety() - a*P1.getx();  
    	}
    }

    public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}
    
    public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public boolean isFini() {
		return fini;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}
	
	public Point getP1() {
		return P1;
	}
	
	public Point getP2() {
		return P2;
	}
}

/**************************************************************************/
class Seg_pt{
	  
	//verifier si (P) inclus dans (Seg)
    
    static boolean verifie_inclu(Segment seg, Point P) { 
    	
    	double d = seg.getP1().distance(seg.getP2()) ;
    
    	if( ( seg.getP1().distance(P) <= d ) && ( seg.getP2().distance(P) <= d)) { 
    		 return true; 
    	}
    	else {
    		return false; 
    	}
    }
    
    /**************************************************/
    
    static Point proj_per( Point P , Segment S )
    {
    	S.geo_equ_droite();
    	Point pro  = new Point();
    
       if( S.isFini() ) {
    
    	   double a1= S.getA() ;
    	   
             if ( a1 != 0)  { 
            	 double  a2 = -1/a1 ;
                 double  b2 = P.gety() - a2*P.getx();
                 double  b1 = S.getB();
                 
                  pro.setx(  (b2-b1) / (a1-a2) );
    
                  pro.sety( a2*pro.getx() + b2 ); }
             
               else { pro.setx( P.getx());   pro.sety( S.getB() ); }
             
                        }
       
       else{ pro.setx( S.getA() );  pro.sety( P.gety() ) ; }
       
       return(pro);
     
    }
    


/**********************************************************************************/

   static boolean min_proj ( ArrayList<Point> poly , Point P , Segment S , int w[])
  
   { S.geo_equ_droite();
      Point  A = new Point();
    
       // déterminer un point de droite (AB) qui situe à l’un des axes
	     if (S.isFini()==false) { A.setx( S.getA() ); A.sety(0);  }
      else{
           if (S.getA() == 0) { A.setx(0);   A.sety( S.getB() );   }
           else {  A.setx( -S.getB()  /  S.getA() );  A.sety(0);   }
         } 
	     
	 double minp,maxp;  
	 
	 minp= proj_per(poly.get(0) , S).distance(A);
	 maxp=minp;
	 
	 
	 
	for ( int i = 1; i < poly.size(); i++ ) {
		
		Point P1 = proj_per(poly.get(i) , S);
		
    	
		minp=Math.min( P1.distance(A), minp);
		maxp=Math.max( P1.distance(A), maxp);
	} 
	
	Point P2 = proj_per(P , S);
	
	
	if ( (P2.distance(A) < minp )|| ( maxp < P2.distance(A) ) ) { 
	return(false); }
	
	else { 
	return(true);  } 
	
	
	 // avoir    
   }
   
  /*  FIIIIIIIIIIIIIIINNNNNN*/
   
   
   /***********************/
 
   /***********************/

}

/**********************************************************************************/

class Seg_Seg { 
	
	
	static double[] angle_produit_scalaire( Segment S1, Segment S2 )
	{ 
		double X1 = S1.getP2().getx() - S1.getP1().getx();
		double X2 = S2.getP2().getx() - S2.getP1().getx();
		double Y1 = S1.getP2().gety() - S1.getP1().gety();
		double Y2 = S2.getP2().gety() - S2.getP1().gety();
		
		double X =  (X1)*(X2);
		double Y =  (Y1 )*(Y2 );
		
		double det = X1*Y2 - X2*Y1;
		
		double o = 1;
		
		if (  ( S1.getP1().distance(S1.getP2()) )* ( S2.getP1().distance(S2.getP2()) ) != 0)
		{
		 o = ( (X + Y) / ( ( S1.getP1().distance(S1.getP2()) )* ( S2.getP1().distance(S2.getP2()) ) ));
		 
		 
		 if(Math.abs(o) > 1) { o = Math.round(o);  } 
		
		}
		
		double tab[] = { det, Math.acos(o) * (180/Math.PI) }  ; 
		
		return( tab ); 
	}
/*************************************************************************************/
	
   static int Remplir_ParallelCol(Segment S1, Segment S2) {    // 2 col, 1 par, 0 sinon
    	  
    	   int Parallel_colin=0; 
    	   S1.geo_equ_droite();
    	   S2.geo_equ_droite();
    	   
    	   if(S1.isFini() && S2.isFini()){
    	       if(S1.getA() == S2.getA()){
    	          if(S1.getB() == S2.getB()) { Parallel_colin=2; }
    	          else  { Parallel_colin=1; }
    	       }
    	   }
    	   else
    	   {
    	       if(!(S1.isFini()) && !(S2.isFini())) {
    	          if(S1.getA() == S2.getA()) { Parallel_colin=2;}
    	          else { Parallel_colin=1; }
    	       }  
    	   }
		return Parallel_colin;
       }
       
  /********************************************************************/
       
       static Point Calcul_intersection(Segment S1,Segment S2) {
    	   
    	   double k;
    	  
    	   Point Inter = new Point();
    	   
    	   if( S1.isFini() && S2.isFini() ){
    		   if( S1.getA() != S2.getA() ){ 
    			  k=(( S2.getB() - S1.getB() )/( S1.getA() - S2.getA()));
    			  Inter.setx(k);
    	          Inter.sety((S1.getA()*k) + S1.getB())  ;
    	         
    	       }      	   
    	   }
    	   else{        
    	       if(!S1.isFini() && S2.isFini()){
    	            if( S2.getA() == 0) {  
    	            	Inter.setx(S1.getA());  
    	            	Inter.sety(S2.getB());   
    	            }
    	            else {
    	            	Inter.setx(S1.getA());
    	            	Inter.sety(S2.getA()*S1.getA()  + S2.getB());
    	            }
    	       }  
    	       if(!S2.isFini() && S1.isFini())
    	       {
    	            if(  S1.getA() == 0){ 
    	            	Inter.setx(S2.getA());
    	            	Inter.sety(S1.getB());
    	            }
    	            else { 
    	            	Inter.setx(S2.getA());
    	            	Inter.sety(S1.getA()* S2.getA()  + S1.getB());
    	            }
    	       }    
    	   }
    	   
    	   if ( Seg_pt.verifie_inclu(S1,Inter) )
    	        { 
    		   if (  Seg_pt.verifie_inclu(S2,Inter)  ){ 
    			   
    			   		 
    			   return(Inter); 
    			  }
    		   else { return (null); }
    	        }
    	   else { return (null); }
    	   
    	   
    	  
        }
       
	/********************************************************/    
       /*LES INTERSECTIONS CAS DE COLINARE*/
       
       static ArrayList<Point> pos_seg(Segment S1, Segment S2) {
    	   
	  	     double min,max,X,Y,max1=0,min1=0,max2=0,min2=0; int i;
	  	     Point P;
	  	     Point tabf[] = new Point[4];    // tabf : tableau de points de taille 4  
	  	     Boolean prend=false; 
	  	     ArrayList<Point> RES = new ArrayList<Point>();
	  	   
	  	                   // déterminer un point de droite (AB) qui situe à l’un des axes
	  	     if (S1.isFini()==false) { X= S1.getA(); Y=0;  }
	           else{
	                if (S1.getA() == 0) { X=0;   Y=S1.getB();   }
	                else {  X= -(S1.getB()) / (S1.getA());  Y=0;   }
	           } 
	  	     
	  	     P = new Point(X,Y);
	  	     
	  	     // test pour verifier en dehors  
	  	     
/*S1*/       max1 = Math.max( S1.getP1().distance(P)  ,S1.getP2().distance(P) );
	  	     min1 = Math.min( S1.getP1().distance(P)  ,S1.getP2().distance(P)  );
	  	     
/*S2*/	     max2 = Math.max(  S2.getP1().distance(P) , S2.getP2().distance(P) );
	  	     min2 = Math.min(  S2.getP1().distance(P) , S2.getP2().distance(P) );
	  	     
	  	      
	  	     if( ! ((max1< min2)||(max2 <min1)) ) {
	  	    	 tabf[0]=S1.getP1() ;  tabf[1]=S1.getP2(); 
	             tabf[2]=S2.getP1();   tabf[3]=S2.getP2();  
	             // On détermine le points des segments le plus proche, loins de P
	     
	               min= Math.min( min1, min2 ) ;
	               max= Math.max( max2, max1 ) ; 
	          // cherche les points entre max et min
	               
	        for (i=0; i < 4; i++)   
	           { 
	        	 if((max1==max2)&&(min1 != min2)) { 
	        		      if ((tabf[i]).distance(P)  != min )
	        	               { 
	        		    	     if( (prend==false)&&((tabf[i]).distance(P)==max1 ) ) 
	                                 { RES.add(tabf[i]) ; prend=true; }   
	        	               }     
	        	        }
	        	 else {
	        		 if((min1==min2)&&(max1 != max2)) { if((tabf[i]).distance(P)  != max )
	        		 
	        		                   {if( (prend==false)&&((tabf[i]).distance(P)==min1 ) ) 
	                                   { RES.add(tabf[i]) ; prend=true; }}  } 
	        		 }
	        		 
	        	 if ( ( (tabf[i]).distance(P)  != min ) && ( (tabf[i]).distance(P) != max ) ) 
	        	             {
	     		   RES.add(tabf[i]) ;   
	      	              } 
	        	 }
	                  
	        
	      if(RES.size()==0) {  
	      RES.add(S1.getP1()) ;  RES.add(S1.getP2());   }
	      
	        
	       }
	   return(RES);
	    	  
	   } 
       
       
 /********************************************************************************/
       
     static void pos_2_seg( Segment seg1, Segment seg2,  ArrayList<Point> RES) 
     // retournes tableau d'interesction entre les 2 segmensts
       { 

           Point P = new Point();

           Point Inter;
           boolean trouv;
           
           int ParallelCol = Remplir_ParallelCol (seg1,seg2);
           
           if ( ParallelCol == 0) {
                   if(Calcul_intersection(seg1,seg2)!=null) { 

                	    P = Calcul_intersection(seg1,seg2);

                        Inter = new Point( calc.pro(P.getx()),calc.pro(P.gety()) );
                        
                       	
                        int i=0;
     	               	  trouv=false;
     	               	  if(RES.size()!=0) {
                         
     	              while(i<RES.size()&&!trouv)
     	               	   {
     	               		   if(RES.get(i).distance(Inter)==0) {trouv=true;}
     	               		   i+=5;}
     	              }

     	               	   if(!trouv) {
     	               		   
       	               RES.add(Inter);
       	               RES.add(seg1.getP1());RES.add(seg1.getP2());
       	               RES.add(seg2.getP1());RES.add(seg2.getP2());
       	                              
     	               	   }
     	       
     
                   }
           }
           else {
                if(ParallelCol == 2) {
                    if( pos_seg(seg1,seg2).size() != 0 ) {
                    	
                     ArrayList<Point> R = new ArrayList<Point>();
                         R = pos_seg(seg1,seg2);
                          
                        for (int k=0; k < R.size(); k++) {

                            Inter = new Point(  (R.get(k).getx()), (R.get(k).gety()) );
     
                     
       	               	  int i=0;
       	               	  trouv=false;
       	               	  if(RES.size()!=0) {
                            while(i<RES.size()&&!trouv)
       	               	   {
       	               		   if(RES.get(i).distance(Inter)==0) {trouv=true;}
       	               		   i+=5;}}
       	               	   if(!trouv) {
         	               RES.add(Inter);
         	               RES.add(seg1.getP1());RES.add(seg1.getP2());
         	               RES.add(seg2.getP1());RES.add(seg2.getP2());
         	               }
             	               
             	               
                              }

                        }
                }
          }
     }
}
/************************************************************************************/
class outil
{
	   static ArrayList<Point> ind = new ArrayList<Point>();
       static Boolean OperationBool = false; //concernant les 4 operations booleenes ___entre 2 polygones
       static Boolean Operation = false;// concernant le deplacement, rotation, copier, coller___pour un seul polygone
       static int numOP = -1;
       
    public static ArrayList<Point> Det_Col( ArrayList<Point> Poly1, ArrayList<Point> Poly2 )
    {
	
	   Point A,B,C,D =new Point();
       int  i,j;
       Segment seg1,seg2; ;
       ArrayList<Point> RES = new ArrayList<Point>();
       
       A = Poly1.get(0); 
     
       for ( i=1; i < Poly1.size(); i++) 
       { 
         	B = Poly1.get(i);   seg1 = new Segment(A,B);
         	
            C = Poly2.get(0);
    
            for ( j=1; j < Poly2.size(); j++) 
            {
                D=Poly2.get(j);  seg2 = new Segment(C,D);
                
                Seg_Seg.pos_2_seg( seg1, seg2, RES);
                
               C = D ;
            }
            
           A = B;
       }
        
     return (RES);
    }  
    
    
  /************************************************************************/
    public static boolean Verifie_Inclus_Pt_Poly(Point P, ArrayList<Point> Poly2, int taille, ArrayList<Point> Poly, Group root) 
    {
       	
       	 if( indexOf(Poly,P) != -1 ) {
       		 
       		// System.out.println(" ce point exist dans LISTE DE poly2 donc true" );
       		 
       		 return true ; }
       	 
       	Point I,A,B=new Point(0,0);
       	
    	    int i=0;
    	    
    	    Segment Seg1,Seg2;  
    	    
    	    ArrayList<Point> RES = new ArrayList<>();

    	    I = new Point( 0,P.gety());  
    	   
    	    Seg1= new Segment(I,P);
    	    	    
    	     
    	    for (i=0; i < Poly2.size()  ; i++)
    	    { 
    	    	A = Poly2.get(i);   B = Poly2.get(  (i+1)%( Poly2.size() ) );
    	    	
    	        Seg2 = new Segment(A,B);  Seg_Seg.pos_2_seg(Seg1, Seg2, RES); 
    	    }
    	    
    	 
    	    boolean sommet = false;
    	    
    	    i=0;
    	    
    	    while( i < RES.size() && !sommet )            // calcul les points sans doublons
    	    { 
    	    	if ( indexOf(Poly2, P) != -1)
    	    	    {  sommet = true;    }
    	    	 
    	    	 i = i+5; 
    	    }
    	    
    	    //TEST 
    	    if( !sommet ) { 
    	    	if (( (RES.size() /5) %2 ) != 0) {  
    	    		
    	    		return( true );  }
    	    	else {   
    	    		
    	    		return (false ); }
    	    	}
    	    
        else { 
        	 
        	boolean interne1 = true ,interne2 =true ,interne3=true ,interne4 = true; i=0;
        	
       	 while (( i < Poly2.size())&&( interne1 ) ) {  // PROJECTION AELA LES COTES 
       		 
       		A = Poly2.get(i) ; B = Poly2.get( (i+1)%(taille) );
       		
       		Segment s = new Segment(A,B);
       		
       		
       		
   	    	
       		interne1 = Seg_pt.min_proj( Poly2, P, s, null);
       		
       		
       		
       		i++;
   		}  
       	 
       /************************************************************/
       	 
               if(interne1) {
               	
               
       		 int j=0; 
       		 
       		 
       		 while( (j < Poly2.size() )&&(interne2)) {
       			 
       				A = Poly2.get(j) ; B = Poly2.get( (j+1)%( Poly2.size() ) );
       				
       				Point mil = new Point( (A.getx()+ B.getx() )/2 , (A.gety()+ B.gety())/2 );
       	    		
       	    		Segment axe = new Segment( Poly2.get( (j+2)% Poly2.size() ), mil );
       	    	
       	    		
       	    		interne2 = Seg_pt.min_proj( Poly2, P, axe ,null); 
       	    		
       	    	
       				
   				j++;
   			}
       	 
            
               
              if( interne2 && Poly.size() > 3 )
           	   
              { int ind=0; 
              
              while( (ind < Poly2.size() )&&(interne3)) {
     			 
           	   A = Poly2.get(ind) ; B = Poly2.get( (ind+1)%( Poly.size() ) );
   				
   				Point mil = new Point( (A.getx()+ B.getx() )/2 , (A.gety()+ B.gety())/2 );
   	    		
   	    		Segment axe = new Segment( Poly2.get( (ind+3)% Poly2.size() ), mil );
   	    		
   	    	
   	    		interne3 = Seg_pt.min_proj( Poly2, P, axe ,null); 
   	    		
   	    		
   				
   			ind++;
              }
              
              
              if( interne3 )
              { ind=0; 
              
              while( (ind < Poly2.size() )&&(interne4)) {
     			 
   				Point C = Poly2.get(ind) ;Point D = Poly2.get( (ind+2)%( Poly2.size() ) );
   				
   	    		Segment axe = new Segment( C, D );
   	    		
   	    		
   	    	    
   	    		
   	    		interne4 = Seg_pt.min_proj( Poly2, P, axe ,null); 
   	    		
   	    		
   			ind++;
   		       }
              
              }     	   
           	   
            }
           } 
               
       	 return(interne1&&interne2&&interne3&&interne4); } 
       	 
       
    }
   /*********************************************************/
/*********************************************************/

	public static int Verifie_Inclusion_2Poly_Pas_intersect(ArrayList<Point> Poly1,ArrayList<Point> Poly2 , int tab[],ArrayList<Point> poly1,ArrayList<Point> poly2,Group root)
	{//si le premier est inclu dans l'autre return 1,si l'inverse return 2 sinon return 0
	 
		if(Verifie_Inclus_Pt_Poly(Poly1.get(0),Poly2,tab[1],poly2,root)) { 
	   return 1; }
	   
	   if(Verifie_Inclus_Pt_Poly(Poly2.get(0),Poly1,tab[0],poly1,root)) { 
	   return 2; 
	   }
	   
	   return 0;
	}   
 

    public static void Reconstruction(ArrayList<Point> Poly1,ArrayList<Point> Poly2,ArrayList<Point> RES, int tab[]) 
    {   		  
	   
    	Point I , g ,d ; int L=0,I1,I2,j,i=0;  
		ArrayList<Point> np=new ArrayList<>();	        
		ArrayList<Point> p=Poly1;
		L= Poly1.size() ;
		int L2= Poly2.size();
	    int  m=0,last;
	    
/**********************************POLY1********************************************/  
		            while(i<RES.size())//i=i+5
		            {      
		                 I= RES.get(i); g=RES.get(i+1) ; d= RES.get(i+2);
		                 
		                 I1= Math.min(p.indexOf(g),p.indexOf(d));
		                 I2=Math.max(p.indexOf(g),p.indexOf(d));
		                
		                   m=I1+1;
		                   last = 0;
		                  
		                 while(( m < I2) &&(last==0)) {
		                	if(  Poly1.indexOf(p.get(m)) != -1) { last=1; }   	  
		                 }
		                 
		                 if(last==1) { I2 = p.size()-1; I1 =  p.indexOf(g) ; }		                 
		                 g=p.get(I1);     d=p.get(I1);
		                      
		                 if (g.distance(I)!=0 && d.distance(I)!=0)
		                 {
		                       j= I1;
		                       // parcours poly.listpts de g à d et on s’arrête lorsqu’on trouve un point plus loin de d que( I) ou jusqu’on arrive à g
		                       while(j < I2 && p.get(j).distance(d) <(d.distance(I) )) { j=j+1 ; }
		                      
		                       if(d.distance(p.get(j))  > d.distance(I)){
		                               np.addAll(0,p.subList(0, j));
		                               np.add(j,I);
		                               np.addAll(j+1,p.subList(j,L));
		                               L=L+1;        
		                               p.addAll(0,np.subList(0, L));
		                                   np.ensureCapacity(L);
		                       }
		                 }
		                 i=i+5;
		            } 		        
		            np.ensureCapacity(L);
		         	
		            Poly1=np;        
		            tab[0] = L;
		         	
		            np.clear();
		  /**********************************POLY2********************************************/  
		        
		            p=Poly2;
		            i=0;
		            while(i<RES.size())
		            {      
		                 I= RES.get(i); g=RES.get(i+3) ; d= RES.get(i+4);
		                 I1= Math.min(p.indexOf(g),p.indexOf(d));
		                 I2=Math.max(p.indexOf(g),p.indexOf(d));
		                 last=0; 
		                 m=I1+1;
		                 while(( m < I2) &&(last==0)){
		                	if(  Poly2.indexOf(p.get(m)) != -1) { last=1; }	  
		                 }      
		                 if(last==1) { I2 = p.size()-1; I1 =  p.indexOf(g) ; }
		                 g=p.get(I1);d=p.get(I1);
		                 if ((g.distance(I)!=0) && (d.distance(I)!=0)){
		                      j= I1;
		                      while(j < I2 && p.get(j).distance(d) <(d.distance(I) )) { j=j+1 ;  } 
		                      if(d.distance(p.get(j))  > d.distance(I)){
		                             np.addAll(0,p.subList(0, j));
		                             np.add(j,I);
		                             np.addAll(j+1,p.subList(j,L2));
		                             L2=L2+1;        
		                             p.addAll(0,np.subList(0, L2));
		                             np.ensureCapacity(L2);    
		                      }
		                 }
		                 i=i+5;
		            }
		            np.ensureCapacity(L2);
		            Poly2=np;
		            tab[1] = L2;
		         	
		    
	 }
    public static int indexOf(ArrayList<Point> l,Point p){
    	int i=0; 
    	
    	while(i<l.size())
    	{
    		if(l.get(i).distance(p)==0) return (i);
    		i++;
    	}
    	
    	return -1;
    	
    }
	public static Path Dessiner(ArrayList<Point> p,Color color) {
		Path dessin = null;
		if(p.size() > 0 ) {
			dessin = new Path();
			
		 	dessin.setStroke(Color.TRANSPARENT);
		 	
	 		dessin.setStrokeWidth(0);
	 		
	 		Point debut = p.get(0);
	 		
	 		MoveTo start = new MoveTo();
	    	start.setX(debut.getx());
	    	start.setY(debut.gety());
	    	dessin.getElements().add(start);
	    	
	    	for(int i = 1; i < p.size(); i++) {
	    		Point parcours = p.get(i);
	    		LineTo ligne = new LineTo(parcours.getx(),parcours.gety());
		    	dessin.getElements().add(ligne);
	    	}
	    	
		}
		dessin.setFill(color);
		return dessin;
	}
	public static void recopier(Polygone P1,Polygone P2) //P1 dans P2
	{
				 
					 if(P1.listePoints.get(0)==P1.listePoints.get(P1.listePoints.size()-1)) {
						 for(int i=0 ; i<P1.listePoints.size()-1;i++) {

					  	     Point q = new Point(P1.listePoints.get(i).getx(),P1.listePoints.get(i).gety());
							 P2.listePoints.add(q);
					     }
				     }
					 else
					 {
						 for(int i=0 ; i<P1.listePoints.size();i++) {

					  	     Point q = new Point(P1.listePoints.get(i).getx(),P1.listePoints.get(i).gety());
							 P2.listePoints.add(q);
					     }
					 }
				 P2.color=P1.color;
				 
				 P2.nom = P1.nom;
				 
				 P2.Union = P1.Union;
				 
				 P2.Dis = P1.Dis;
				 				 
			     for ( int i = 0 ; i < P1.Diff.size(); i++) 
			     {
			    	 Polygone P = new Polygone();
			    	 P2.Diff.add(P);
			    	 recopier(P1.Diff.get(i), P2.Diff.get(i));
			     }
	}
	
	public static void supp_nul(Polygone P,Group root)
	{

		int i=0;
	     while( i < P.Diff.size()) 
	     {
	    	 if(P.Diff.get(i).listePoints==null) 
	    	 {
	    		  P.Diff.remove(i);
	    	 }
	    	 else
	    	 {
	    	      supp_nul(P.Diff.get(i), root);  
	    	      i++;
	    	 }
	     }
	}
	public static Boolean Verifie_Dif(Point p,Polygone P,Color color, Group root)
	{
		 int i = 0;
	     while ( i < P.Diff.size()) 
	     {
	    	 if(Verifie_Inclus_Pt_Poly(p, P.Diff.get(i).listePoints, P.Diff.get(i).listePoints.size(),P.Diff.get(i).listePoints,root))
	    	 {
	    		 if(color==Color.WHITE)
		    	 {    
	              	 if(P.Diff.get(i).Diff.size()==0)
	               	 {
	               		 return false;
	               	 }
	               	 else
	               	 {
	               		Color color2 = P.color;
	      	            Verifie_Dif(p,P.Diff.get(i), color2,root); 
	               	 }
	    	     }
	    		 else
	    		 {
	              	 if(P.Diff.get(i).Diff.size()==0)
	               	 {
	               		 return true;
	               	 }
	               	 else
	               	 {
	               		Color color2=Color.WHITE;
	      	            Verifie_Dif(p,P.Diff.get(i),color2,root); 
	               	 } 
	    		 }
	    	 }
	    	 i++;
	     }
		return true;
	}

	public static void SelectionnerPoly(ArrayList<Polygone> Dis,ArrayList<Polygone> Polygone,ArrayList<ArrayList<Polygone>> Union,Group root,Scene scene)
	{
		   
           scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent t) {
					Point p = new Point();
					p.setx(t.getSceneX());
					p.sety(t.getSceneY());
                    p.setind( Affichage_Display.ChercherPoly(p.getx(),p.gety(),Dis,Union,root));    
					ind.add(p);
		        	
		        	if(OperationBool) {
		        		if(ind.size() == 1) {
		        			if(ind.get(0).getind()==-1) {
		        				ind.clear();
		        			}
		        		}
		        		else {
		        			if(ind.get(1).getind()==-1) {
		        				ind.remove(1);
		        			}
		        			else {
		        				if(ind.get(0).getind()==ind.get(1).getind()) {
		        					ind.remove(1);
		        				}
		        				else
		        				{
		        					 int ind1 = ind.get(0).getind();
		     		           	     int ind2 = ind.get(1).getind();
		     		           	   
		                             switch(numOP)
		                             {
		                                case(0):
		                                {
		                                	Operation_bool.NOTT(Dis.get(ind1), Dis.get(ind2)	,Polygone,Dis,Union,root); 
		                             	   break;
		                                }
		                                case(1):
		                                {
		                                	Operation_bool.ORT(Dis.get(ind1), Dis.get(ind2)	,Polygone,Dis,Union,Dis.get(ind1).color,root);
		                             	   break;

		                                }
		                                case(2):
		                                {
		                             	   Operation_bool.ANDT(Dis.get(ind1), Dis.get(ind2)	,Polygone,Dis,Union,Dis.get(ind1).color,root); 
		                             	   break;

		                                }
		                                case(3):
		                                {
		                             	   Operation_bool.XORT(Dis.get(ind1),  Dis.get(ind2)  , Polygone, Dis, Union, root);
		                             	   break;

		                                }  
		       
		                             }
		                             OperationBool= false;
		                             Affichage_Display.Afficher(Dis,Union,root);
		                     		 ind.clear();
		        				}
		        			}
		        		}
		        				
		        	}
		        	else {
		        		if(Operation) {
		        			if(ind.size()==-1) {
		        				if(ind.get(0).getind()==-1)
		        				{
		        					ind.clear();
		        				}
		        			}
		        			else {
		        				int ind1 = ind.get(0).getind();
		                        switch(numOP)
		                        {
		                           case(4): //Copier
		                           {
		                        		  try{
		                        			  System.out.println("copCol: ");
		                        			 
		                        		  }catch (Exception e) {
		                        			  System.out.println("here");
											// TODO: handle exception
										}
		                        	  

		                        	   break;
		                           }
		                           case(5): //Couper
		                           {
		                        	   break;
		                           }
		                           case(6): //Coller
		                           {
		                        	   break;
		                           }
		                        } 
			            	 Operation = false;
		        			}        			
		        		}      		
		        	}
		             if(!OperationBool && ! Operation) {ind.clear();}		             
				}
			});	
	}
	
	public static void SelectOP(int i)
	{
		//OperationBool = true;
		if(i<4) {
		OperationBool = true;
		}
		else {
			Operation = true;
		}
        numOP=i;
	}
	 public static void Deplacer_Diff( Polygone P,ArrayList <ArrayList< Polygone > > UnionT,double dx,double dy, Group root)
	 {
		 
	        for ( int i = 0 ; i < P.Diff.size(); i++) 
	        {	 
		    	P.Diff.get(i).deplacer(dx, dy);
              
	    	    Deplacer_Diff(P.Diff.get(i),UnionT, dx,dy, root);  	 
	        }
	 }
	 
	 public static int recherch( ArrayList< Polygone > Poly  )
	 {
		 for (Polygone p : Poly) {  p.cliq = 0; }
	    	
	      MiniWindow.select_poly(" Slectionner un polygone ");
	    	
	    	int i = 0; boolean trouv = false;  
	    	
	    	while  ( i < Poly.size() && !trouv ) {
	    		
	    		if(Poly.get(i).cliq == 1 ) { trouv = true ;   Poly.get(i).cliq = 0; }
	    		i++;
	    	}
	    	
	    	if(trouv ) { return (i-1) ; }
	    	else{ return -1 ; }
	 }
	
}

class Operation_bool
   // retourne les points d'intersction entre 2 polygones 
{


	  private static int OR( Polygone P1, Polygone P2, ArrayList<ArrayList<Polygone>> UnionT , Group root) {
			
		    
	    ArrayList<Polygone> Poly1 = new ArrayList<Polygone>();
	    ArrayList<Polygone> Poly2 = new ArrayList<Polygone>();
	    ArrayList<Polygone> UNI = new ArrayList<Polygone>();
	   
	    
	    int i,itr=1;
	    	
	    if( (P1.Union == -1 ) & (P2.Union == -1) )
	    { /************cas 1 **************/
	     //Union.add(P1);  Union.add(P2); 
	     
	     Poly1.add(P1);   Poly2.add(P2); 
	     
	     // test 
	    
	     
	     if( itr == 0) { i = -1; }
	     
	     else {
	      UNI.addAll(Poly1);
	      UNI.addAll(Poly2);
	     
	      
	      UnionT.add(UNI);
	      
	      i = UnionT.size()-1;
	      
	      P1.Union = i ;  P2.Union = i;
	     }
	     
	    }
	    
	    else
	    { /************cas 2 **************/  
	    	if( Math.min( P1.Union , P2.Union) == -1 ) 
	    	 {
	    		if( P1.Union == -1 ) 
	    		  { 
	    			
	    		    i = P2.Union;
	    			
	    		    P1.Union = i;
	    		    
	    			Poly1.add(P1); 
	    			
	    			Poly2.addAll( UnionT.get(i));
	    			
	    			// test
	    			
	    			 if( itr == 0) { i = -1; }
	    		     
	    		     else { 
	    		    	 
	    		    	  UNI.addAll(Poly1);
	    			      UNI.addAll(Poly2);
	    			     
	    			      
	    			      UnionT.add(i,UNI);
	    			    
	    		         }
	    		  }
	    		
	    		else 
	    		  {
	    		  
	    			i = P1.Union;
	    			
	    			P2.Union = i;
	    			
	    			Poly2.add(P2); 
	    			
	    			Poly1.addAll( UnionT.get(i));
	    			
	    			// test
	    			
	    			 if( itr == 0) { i = -1; }
	    		     
	    		     else {
	    		    	  UNI.addAll(Poly1);
	    			      UNI.addAll(Poly2);
	    			     
	    			      
	    			      UnionT.add(i,UNI);
	    		         }
	    		  }
	    		
	    		/************cas 3 **************/
	    	 }
	    	
	    	else 
	    	{ 
	    	
	    	 i =  Math.min(P1.Union, P2.Union);
	    	int j =  Math.max(P1.Union, P2.Union);
	    	
	    	
	    	
	    	Poly1.addAll(UnionT.get(i));
	    	Poly2.addAll(UnionT.get(j));
	    	
	         // test 
	    	
			 if( itr == 0) { i = -1; }
		     
		     else {
		    	 
		    	  UNI.addAll(Poly1);
			      UNI.addAll(Poly2);
			     
			      
			      UnionT.add(i,UNI);
	    	 
	    		  // changment d'adress
	    	
	    		  for (int ind=0; ind < UnionT.get(j).size(); ind++)
	    		  {
	    			UnionT.get(j).get(ind).Union = i;   
	    		  }
	    		  //remove
	    		  UnionT.remove(j);
		      }	
	    	}
	    	
	    	
	    } 
	       
	       for (int j = 0; j < Poly1.size(); j++) {
	    	   
	    	   for (int k = 0; k < Poly2.size(); k++) {
		    	 
	    		   Not_majOR(Poly1.get(j), Poly2.get(k), root);
	    		   
			    }
		    }
	       
	   
	    return(i);
}
	  
	  static public void ORT( Polygone P1, Polygone P2,ArrayList<Polygone> Polygone, ArrayList<Polygone> Dis,ArrayList<ArrayList<Polygone>> UnionT,Color color, Group root )
	  {
		int ind = OR(P1, P2, UnionT,root) ;
		
		
		Polygone P; 
		
		int indI = Math.min(P1.Dis, P2.Dis);
		
		
   if(ind != -1 ) {
	   
	   if( P1.Dis == indI ) 
		{ 
		 Affichage_Display.removeD(P2,Dis,Polygone,UnionT); }

	 else {  
		 Affichage_Display.removeD(P1,Dis,Polygone,UnionT); }
		
		 for (int i=0; i < UnionT.get(ind).size(); i++)
		 {
			  P =  UnionT.get(ind).get(i);
			  
			  P.color = color;
			  P.Union = ind ;
			  
			  P.Dis = indI;
			 
			  
			  P.P.setFill( color  ) ;
		 }
		 
			
     }
 }
	    
	/******************************************************************/
     	     
	   public static Polygone AND(Polygone P1, Polygone P2, Group root) {
	     	
	    	
	    	int ind = 0, ind1,ind2 = 0;
	     	int choix = 1;
	     	Point debut,parcours,prochain;
	     	int[] tab = new int[2];
	     	
	     	ArrayList<Point> pList1 = new ArrayList<Point>();
	     	ArrayList<Point> pList2 = new ArrayList<Point>();
	     	
	     	ArrayList<Point> L1 = new ArrayList<Point>();
	     	ArrayList<Point> L2 = new ArrayList<Point>();
	     	
	     	
	     	ArrayList<Point> intersection = new ArrayList<Point>();
	     	
	     	if( P1.listePoints.size() != 0 && P2.listePoints.size() != 0 ) {
	     		
	     	pList1.addAll(P1.listePoints);
	     	pList2.addAll(P2.listePoints);
	     	
	     	pList1.add( P1.listePoints.get(0) );
	     	pList2.add( P2.listePoints.get(0) );
	     	
	     	L1.addAll(P1.listePoints);
	     	L2.addAll(P2.listePoints);
	     	
	     	
	     	ArrayList<Point> RES = outil.Det_Col(pList1,pList2);
	     	
	     	
	    
	     	if(RES.size()==0)
	     		
	     	{ 
	     		tab[0] = pList1.size(); tab[1] = pList2.size();
	     		
	     		int incl = outil.Verifie_Inclusion_2Poly_Pas_intersect(pList1, pList2,tab,pList1, pList2,root);
	     		
	     		if(incl==0) return null;
	     		
	     		else if(incl==1) 
	     		{ 
	     			
	     	         
	     	         Polygone resu = new Polygone(L1, "",P1.color ,root);
	     	         
	     	     	
	     			return resu;
	     		}
	     		else
	     		{ 
	     			 
	     	        
	     			Polygone resu = new Polygone(L2, "",P1.color ,root);
	     			
	    	         
	     			return resu;
	     		}
	     		
	     	}
	     	
	     	else {
	     		
	     	outil.Reconstruction(pList1,pList2,RES,tab);
	     	
	     	int i = 0; 
	     	
	     	for (Point p : pList1) { 
	     		 if( i < tab[0] ) {  }; 
	     		 i++;
	     	     } 
	     	i = 0; 
	     	     	for (Point p : pList2) { 
	     	     		if( i < tab[1] ) {  }; 
	     	     		 i++;
	     	     	   }
	     	
	     	
	    Point p0 = null;
	     	
	     	if(RES.size() > 0) {
	     		
	     			
	    	    	debut = RES.get(0);
	    	    	if(ind + 5 < RES.size()){
	    	    		ind += 5;
	    	    	}
	    	    	
	    	    	p0 = new Point(debut.getx(),debut.gety());
	    	    	
	    	    	intersection.add(p0);
	    	    	
	    	    	parcours = debut;
	    	    	
	    	    	
	    	    	ind1 = outil.indexOf(pList1,parcours);
	    	    	
	    	    	if(ind1+1<tab[0])ind1++;
	    	    	else ind1=1;
	    	    	
	    	    	prochain = pList1.get(ind1);
	    	    	
	    	    	
	    	    	Line l1 = new Line( prochain.getx(),prochain.gety(),0, 0 );
	    	    	
	    	    	l1.setFill(Color.RED);
	    	    	
	    	    	root.getChildren().add(l1);
	    	    	
	    	    
	    	    	
	    	    	if(!outil.Verifie_Inclus_Pt_Poly(prochain,L2,tab[1],pList2,root)) {
	    	    		

	    	    		choix = 2;
	    	    		
	    	    		ind2 = outil.indexOf(pList2,parcours);
	    	    		
	    		    	if(ind2+1<tab[1])ind2++;
	    		    	else ind2=1;

	    	    		prochain = pList2.get(ind2);
	    	    		
	    	    		
	    		    	if(ind2+1<tab[1])ind2++;
	    		    	else ind2=1;

	    	    	}
	    	    	else { 
	    	    		
	    	    		
	    		    	if(ind1+1<tab[0])ind1++;
	    		    	else ind1=1;
	    		    	choix = 1;
	    		    	
	    		    	
	    	    	}
	    	    
	    	    	
	    	    	parcours = prochain;
	    	    	
	    	    	p0 = new Point(parcours.getx(),parcours.gety());
	    	    	
	    	    	intersection.add(p0);
	    	    	
	    	    	
	    	    	while( parcours.distance(debut) != 0) {
	    	    		
	    		    	if(RES.get(ind) == parcours) {

	    		    		
	    			    	ind1 = outil.indexOf(pList1,parcours);
	    			    	
	    			    	if(ind1+1<tab[0])ind1++;
	    			    	else ind1=1;

	    			    	prochain = pList1.get(ind1);
	    			    	
	    			    	
	    			    	
	    		    		if(!outil.Verifie_Inclus_Pt_Poly(prochain, L2 ,tab[1], pList2,root)) {
	    		    		
	    		    			choix = 2;
	    		    			
	    			    		ind2 = outil.indexOf(pList2,parcours);
	    			    		
	    				    	if(ind2+1<tab[1])ind2++;
	    				    	else ind2=1;

	    				    	
	    			    		prochain = pList2.get(ind2);
	    			    		
	    			    		
	    				    	if(ind2+1<tab[1])ind2++;
	    				    	else ind2=1;
	    			    	}
	    		    		
	    			    	else { 
	    			    		
	    			    		   choix = 1;
	    			              
	    				    	    if(ind1+1<tab[0])ind1++;
	    				    	    else ind1=1;
	    				    	 	

	    			    	}
	    		    		
	    		    		
	    		    		parcours = prochain;
	    		    		
	    		    		
	    		    		if(ind + 5 < RES.size()){
	    			    		ind += 5;
	    			    	}
	    		    	}
	    		    	
	    		    	else { 
	    		    		
	    		    		if(choix == 1) {
	    		    			
	    		    			parcours = pList1.get(ind1);
	    		    			
	    		    	    	    if(ind1+1<tab[0])ind1++;
	    		    	    	    
	    		    	    	    else ind1=1;
	    		    	    	    
	    				    	 
	    		    		               }
	    		    		else {
	    		    			
	    		    			parcours = pList2.get(ind2);
	    		    			
	    				         	if(ind2+1<tab[1])ind2++;
	    				         	
	    				    	    else ind2=1;
	    				         	
	    				    	 
	    		    		    }
	    		    		
	    		    		
	    		    	}
	    		    	 p0 = new Point(parcours.getx(), parcours.gety());
	    		    	intersection.add(parcours);
	    	 	}
	     	}
	     }	
	     	     
	     	      Polygone resu = new Polygone(intersection, "",P1.color ,root);
	     	   
	     	      
	     	    
	    	return resu;
	     }
	     	else return null; 
	    }


	
	/*******************************************************************/
	 public static void ANDT( Polygone P1, Polygone P2, ArrayList<Polygone> Polygone, ArrayList<Polygone> Dis,ArrayList <ArrayList< Polygone > > UnionT,Color color, Group root)
{
		
		 
		 /**************************************/
		 
				
			  if( (P1.Union == -1) && (P2.Union ==-1) )
			     {  
				  
				   Polygone RES= AND(P1,P2,root);
				   
				   // mise a jour 
				 if( RES != null) {
					   
				  Not_majAND(P1, P2, RES, root);
				  
				   RES.color = color;
				   
				   Polygone.add(RES);
				   
				    int indI = Math.min(P1.Dis, P2.Dis);
				    
					 RES.Dis = indI;
					 
					 Dis.set( indI, RES );
				  
					 if(P1.Dis==indI) 
						    Affichage_Display.removeD(P2,Dis,Polygone,UnionT);
						else 
							Affichage_Display.removeD(P1,Dis,Polygone,UnionT); 
				   }
				}
			  
			  else { int indI = -1;
				     ArrayList<Polygone> uni1= new ArrayList<Polygone>();
				   	 ArrayList<Polygone> uni2= new ArrayList<Polygone>();
				   	 
				     if( Math.min(P1.Union, P2.Union) == -1 )
				     {
				    	if( P1.Union == -1 ) { uni1.add(P1); uni2.addAll( UnionT.get(P2.Union)); } 
				    	else { uni2.add(P2); uni1.addAll( UnionT.get(P1.Union));  }
				     }
				     
				     else  // 2 DIFF DE -1 
				     {
				    	 uni1.addAll( UnionT.get(P1.Union));
				    	 uni2.addAll( UnionT.get(P2.Union));
				     }
				     
				      ArrayList<Polygone> intersect = new ArrayList<Polygone>();
						
					
				    
				    	 for (int i= 0; i < uni1.size(); i++) {
						
							 Polygone p1 = uni1.get(i);
							
							 for (int j= 0; j < uni2.size(); j++) {
								
								Polygone p2 = uni2.get(j);
								
								Polygone RES= AND(p1,p2,root);
								
								 if( RES != null) { 
								 
								Not_majAND(p1, p2, RES, root);
								
								RES.color = color;
								RES.Union = UnionT.size();
								
								Polygone.add(RES);
								
							 indI = Math.min(p1.Dis, p2.Dis);
							 
							 RES.Dis = indI;
							 
							 Dis.set( indI, RES );
							 	
							 intersect.add(RES);
								
								 }
								
							}
							
						}
				     
				   UnionT.add(intersect); 
				   
				   
					 if(P1.Dis==indI) 
						    Affichage_Display.removeD(P2,Dis,Polygone,UnionT);
						else 
							Affichage_Display.removeD(P1,Dis,Polygone,UnionT);  
				   
			  
		     }
   }
		/***************************************************/
		 /**************************************/
		

		private static void Not_rec(Polygone P1 , Polygone Poly, Group root)
		{
			if( AND(P1, Poly, root) != null)
		    { 
				 ArrayList<Point> l = AND(P1, Poly, root).listePoints;
				 
				 Poly.listePoints.clear();
				 
				 Poly.listePoints.addAll(l);
				 
				 //Poly.diff_GPere = P1;
				 
			     for ( int i = 0 ; i < Poly.Diff.size(); i++) 
			     {
				     Not_rec(P1, Poly.Diff.get(i),root);
			     }
			}
			
			else 
			{
				 Poly.listePoints.clear();
				 Poly.listePoints=null;

			}
		}


	
    private static void NOT(Polygone P1 , Polygone P2, Group root)
    {
    	Polygone p2 = new Polygone(); 
    	
    	outil.recopier(P2,p2);
    	 
        Not_rec(P1, p2,root);
        
        P1.Diff.add(p2);
        
        
        outil.supp_nul(P1,root);
    }
    
	private static void Not_majAND(Polygone P1,Polygone P2,Polygone RES,  Group root)
	
	{
          int i;
		  for (i = 0 ; i < P1.Diff.size()&& i < P2.Diff.size(); i++) 
		  {
			  NOT(RES, P1.Diff.get(i), root);   
			  NOT(RES, P2.Diff.get(i), root);   

		  }
		  if(i<P1.Diff.size())
		  {
			  while(i<P1.Diff.size())
			  {
				   NOT(RES, P1.Diff.get(i), root); 
				   i++;
			  }
			  
		  }
		  else
			  if(i<P2.Diff.size())
			  {
				  while(i<P2.Diff.size())
				  {
					   NOT(RES, P2.Diff.get(i), root);   
					   i++;
				  }
				  
			  }
		  
	}
		
	private static void Not_majOR(Polygone P1,Polygone P2,Group root)
	{
		
        int i;

		  for (i = 0 ; i < P1.Diff.size()&& i < P2.Diff.size(); i++) 
		  {
		      Polygone P3=new Polygone();
		      outil.recopier( P2, P3);
			  NOT(P1.Diff.get(i), P3, root);   
		      Polygone P4=new Polygone();
		      outil.recopier( P1, P4);
			  NOT(P2.Diff.get(i), P4, root);   

		  }
		  if(i<P1.Diff.size())
		  {
			  while(i<P1.Diff.size())
			  {
			      Polygone P3=new Polygone();
			      outil.recopier( P2, P3);
				  NOT(P1.Diff.get(i), P3, root);
				   i++;
			  }
			  
		  }
		  else
			  if(i<P2.Diff.size())
			  {
				  while(i<P2.Diff.size())
				  {
				      Polygone P4=new Polygone();
				      outil.recopier( P1, P4);				  
					  NOT(P2.Diff.get(i), P4, root);   
					   i++;
				  }
				  
			  }
		  
	}	
	

public static void NOTT(Polygone P1, Polygone P2,ArrayList<Polygone> Polygone, ArrayList<Polygone> Dis,ArrayList <ArrayList< Polygone > > UnionT,Group root) {
		
		ArrayList<Polygone> dif1= new ArrayList<Polygone>();
		ArrayList<Polygone> dif2= new ArrayList<Polygone>(); 
		
	    if(P1.Union == -1 )
	    {   dif1.add(P1);
	    
	    	if( P2.Union == -1  ) { dif2.add(P2);   }
	    	else { dif2.addAll( UnionT.get( P2.Union ) ) ;  }
	    
	    }
	    
	    else { dif1.addAll( UnionT.get( P1.Union) ) ;  
	          if( P2.Union == -1  ) { dif2.add(P2);  }
    	     else { dif2.addAll( UnionT.get( P2.Union) ) ; UnionT.remove( P2.Union );  }
	         }
	    
	   
		for(Polygone p1 : dif1) {
						
			for(Polygone p2 : dif2) {
				
				
				NOT(p1,p2,root);
				
				
           }
		}
		
		Affichage_Display.removeD(P2,Dis,Polygone,UnionT);

	}
 	 
	/*************************************************/
	 
public static void XORT( Polygone P1, Polygone P2,ArrayList<Polygone> Polygone, ArrayList<Polygone> Dis,ArrayList <ArrayList< Polygone > > UnionT,Group root)
{
	 
	 
	ORT(P1, P2, Polygone , Dis, UnionT, P1.color, root);
	
	ArrayList<Polygone> U = UnionT.get(P1.Union);
	
	for (int i = 0; i < U.size(); i++) {
		
		Polygone p1 = U.get(i);
		
		for (int j = i+1 ; j < U.size(); j++) {
			
			Polygone p2 = U.get(j);
			
			Polygone P = Operation_bool.AND(p1, p2, root) ;  
			
			P.Dis = -1; 
			
			p1.Diff.add(P); p2.Diff.add(P);
			
		}
		
	}
		
	
} 

public static void crayon( SubScene scene,Group racine, boolean possible,ArrayList<Polygone> cra, Button fin  )
{ 
	ArrayList<Point>  A  = new ArrayList<Point> ();
	
	boolean po = possible ; 
	 
	
	if( po )
	 {
		
	scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
		Point p = new Point( t.getSceneX()  , t.getSceneY()  );
			
		
		 
	    A.add( p ); 
		  
	 if (A.size()  > 1) { 
		 
	   
	  Line l = new Line( A.get( A.size() - 2 ).getx(), A.get( A.size() - 2 ).gety() ,A.get (A.size() - 1 ).getx(), A.get( A.size() - 1 ).gety() );
	   
	   racine.getChildren().add(l);
	   
	                    }
             
	 }});
	
	
	
} 
	
	if(po) { 
	 fin.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				Polygone b = new Polygone();
				
				Color c = Color.PINK;
				
				b.color = c ;
				
				b.listePoints = A ;
				
				cra.add(b);
				
				racine.getChildren().add( b.getDessin() );
				
				
				
			}
		 });  
	
	
	}
	
	
}
	
	
	

	/******************************************************/
	
}

class Affichage_Display
{
	static ArrayList<Polygone> Display;
	
	 public static void removeD(Polygone P,ArrayList<Polygone> Dis,ArrayList<Polygone > Polygone,ArrayList<ArrayList<Polygone>> Union)
	 {
		
		 
		 if(P.Dis>=0) 
		 {
			      
			 
			       for(Polygone p:Dis)
			       {
			    	   if(p.Union == -1){
			    		   
			    	   if(p.Dis > P.Dis)
			    	   {
			    		   p.Dis = p.Dis-1;
			    	   }
			    	 
			           }
			    	   
			    	   else {
			    		   
			    		   for(Polygone q: Union.get(p.Union) )
			    		   {
					    	   if(q.Dis > P.Dis)
					    	   {
					    		   q.Dis = q.Dis-1;
					    	   }
					    	 
			    		   }
			    	   }
			       }
			       
			       Dis.remove(P.Dis);

	     }
	 }
	 public static void AddD(Polygone P,int ind,ArrayList<Polygone> Dis,ArrayList<Polygone > Polygone)
	 {
		 
		 if(ind>=0) 
		 {
			 
			       Dis.add(ind,P);
			       P.Dis=ind;
		           for(Polygone p:Polygone)
		           {
			            if(p.Union != -1 && p.Union==P.Union) p.Dis=ind;
			            else
				        if (p.Dis>=ind) p.Dis=p.Dis+1;
		           }
	     }
		  }
	 
	 public static void AND_colorer_Dis (Polygone D,Polygone Pdiff,Color color, Group root)
	{
	     for ( int i = 0 ; i < D.Diff.size(); i++) 
	     {
	    	 Polygone p=Operation_bool.AND(D.Diff.get(i),Pdiff,root);
			 if(p != null && p.listePoints != null)
			 {
		    	 
	   	         root.getChildren().add(outil.Dessiner(p.listePoints,color));  	
	    	     Color color2;
	    	     if(color != Color.WHITE) color2 = Color.WHITE;
	    	     else color2=D.color;
	    	     AND_colorer_Dis(D.Diff.get(i),Pdiff, color2, root); 
			 } 
	     }
	}
		public static void Not_colorer(Polygone P,Color color, Group root)
		{
			     for ( int i = 0 ; i < P.Diff.size(); i++) 
			     {
			    	 root.getChildren().add( outil.Dessiner(P.Diff.get(i).listePoints,color ));
			    	 P.Diff.get(i).P.setFill(color);
			    	 P.Diff.get(i).color=color;
			    	 Color color2;
			    	 if(color != Color.WHITE) color2 = Color.WHITE;
			    	 else color2=P.color;
			    	 Not_colorer(P.Diff.get(i), color2, root);  
			     }
	    }

		public static void Not_colorer_Dis(Polygone P,Color color,ArrayList<Polygone> Dis, Group root)
		{
			
			     for ( int i = 0 ; i < P.Diff.size(); i++) 
			     {
			    	
			    	 if(color==Color.WHITE)
			    	 {    
			    		 
			    		 
			    		 P.Diff.get(i).color = Color.WHITE;
			    		 
			    		 ColorPicker C = new ColorPicker();
			    		 
			    		 C.setValue(Color.BLUE);
			    		 
		    	         root.getChildren().add( outil.Dessiner(P.Diff.get(i).listePoints, Color.WHITE) ); 
		    	        	
				    	
		    	       
	                     if(P.Dis > 0 ) {
	                    	 
		    	         for(int j=0; j < P.Dis; j++)
				    	 {
		    	        	 Polygone res = Operation_bool.AND(P.Diff.get(i),Dis.get(j),root);

				    		 ArrayList<Point> l = null;
				    		 
				    		 if(res != null) 
				    			 l=res.listePoints;

				    		 if(res != null && l != null)
				    		  {    
				    	          root.getChildren().add(outil.Dessiner(l, Dis.get(j).color));
				    	          
				    	          AND_colorer_Dis(Dis.get(j),P.Diff.get(i),Color.WHITE,root);
				    	          
					    	      Color color2;
					    	      
					    	      color2=P.color;
					    	      
					    	      Not_colorer_Dis(P.Diff.get(i), color2,Dis, root); 
				    		  }
				    		 else {
				    			 
				    	      Color color2;
				    	      
				    	      color2 = P.color;
				    	      
				    	      Not_colorer_Dis(P.Diff.get(i), color2,Dis, root); 
				    	      }

				    	  }		
			    	    }
	                     
	                  }
			    	 
			    	 else
			    	 {
			    		 
			    	     root.getChildren().add( outil.Dessiner(P.Diff.get(i).listePoints, color) );  	
				    	 
			    	     Color color2;
			    	     
				    	 color2 = Color.WHITE;
				    	 
				    	 Not_colorer_Dis(P.Diff.get(i), color2, Dis, root); 
			    	 }	    	  
			     }
		}

	public static void Afficher(ArrayList<Polygone> Dis,ArrayList<ArrayList<Polygone>> Union,Group root)
	 {
		 root.getChildren().clear();	 
		
		 
		 for(Polygone p:Dis)
		 {
			 if(p.Union == -1){	 
				
				 root.getChildren().add(p.getDessin());
				 
		    	 if(p.Dis == 0 ) {
		    		 Not_colorer(p,Color.WHITE,root);
		    	 }
		    	 else {
				 Not_colorer_Dis(p,Color.WHITE,Dis, root);
		    		 }
			 }
			 
			 else {
				 
				
				 for(Polygone q: Union.get(p.Union) )
				 {
					
					 root.getChildren().add(q.getDessin());
					 
					 
			    	 if(q.Dis == 0 ) {
			    		 Not_colorer(q,Color.WHITE,root);
			    	 }
			    	 else {
					 Not_colorer_Dis(q, Color.WHITE, Dis, root);}
				 }
			  }
			 
		 } 
	 }
	
	public static void afficher_crayon( Group root, ArrayList<Polygone> P )
	{
		
	for (Polygone p : P) {
		
		root.getChildren().add( p.getDessin());
	                     }	
	}
	
	public static int ChercherPoly(double x , double y, ArrayList<Polygone> Dis,ArrayList<ArrayList<Polygone>> Union,Group root)
	{// retourner la case Display qui correspond lles coordonnées (x,y) ou retouner -1 si c'est le vide 
		Point p= new Point(x,y);
		int i =Dis.size()-1;
		boolean trouv=false;
		while(!trouv && i>=0 )
		{
			if(Dis.get(i).Union== -1)
			{	
		 		  
		 	   if(outil.Verifie_Inclus_Pt_Poly(p, Dis.get(i).listePoints, i, Dis.get(i).listePoints,root))
			   {
		 		   
			 	   if(outil.Verifie_Dif(p,Dis.get(i), Color.WHITE,root))
				   {
			 		  
				 	  trouv = true;
					  return i;
				   }
				   else
				   {
					   i--;
				   }
			   }
			   else
			   {   
			 	   i--;		
			   }		
		   }
		   else
		   {
			    int u= Dis.get(i).Union; int j=0;
			    while (i>=0 && !trouv && j<Union.get(u).size())
				{	
				 	 if(outil.Verifie_Inclus_Pt_Poly(p, Union.get(u).get(j).listePoints, i, Union.get(u).get(j).listePoints,root))
					 {
					      if(outil.Verifie_Dif(p,Union.get(u).get(j), Color.WHITE,root))
					      {
						     trouv = true;
					 	     return i;
					      }
					 	  else
					 	  {  
					 		 
						     i--;
					 		 
					      }
					  }
					  else
					  {
					    j++;		
					  }		
				}
			    if(!trouv && j>=Union.get(u).size() ) i--;
		   }
				
		}
		return -1;
   }

}
class SauvgardeElements implements Serializable{
	public int nb_Union;
	public List<Integer> nbPolygonInEachUnion;
	public int nb_Polygon_In_Polygon_List;
	public int nb_Polygon_In_Display;
}

class Sauvgarde{
	 SauvgardeElements sauvElem;
	 String fileName;
	 Sauvgarde(){}
	 Sauvgarde(String fileName,ArrayList<ArrayList<Polygone>> Union,ArrayList<Polygone> Polygone,ArrayList<Polygone> Display){
		 this.fileName=fileName;
		 this.sauvElem=new SauvgardeElements();
		 this.sauvElem.nb_Union=Union.size();
		 this.sauvElem.nbPolygonInEachUnion=new ArrayList<Integer>();
		 for(int i=0;i<this.sauvElem.nb_Union;i++)
			 this.sauvElem.nbPolygonInEachUnion.add(Union.get(i).size());
		 this.sauvElem.nb_Polygon_In_Polygon_List=Polygone.size();
		 this.sauvElem.nb_Polygon_In_Display=Display.size();
	 }
	 
	 public void sauvgarder(Polygones P) {
		 try {
			 //sauvgarder SauvElem (le nombre des iterations necessaire pour parcourir le fichier sans erreur)
				ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(this.fileName));
				os.writeObject(sauvElem);
				
				/**iterer sur chaque tableau de structure pour sauvgarder les objet*/
				//sauvgarde des elements de la structure d'uninon
				 for(int i=0;i<this.sauvElem.nb_Union;i++) {
					 for(int j=0;j<this.sauvElem.nbPolygonInEachUnion.get(i);j++)
						 os.writeObject(P.Union.get(i).get(j));
				 }
				 //sauvgarder les elements de tableau Polygon
				 for(int i=0;i<this.sauvElem.nb_Polygon_In_Polygon_List;i++) {
					 os.writeObject(P.Polygone.get(i));
				 }
				 //sauvgarder les elements du tableau display
				 for(int i=0;i<this.sauvElem.nb_Polygon_In_Display;i++) {
					 os.writeObject(P.Display.get(i));
				 }
				 //fermeture du fichier
				 os.close();
			} catch (FileNotFoundException e) {
				
				System.out.println("Je n'ai pas trouvé le fichier");
				e.printStackTrace();
				
			} catch (IOException e) {
				System.out.println("pb de inputOutput");
				e.printStackTrace();
			}
		
	 }
}


class Chargement{
	
	 SauvgardeElements chgElem;
	 
	 String fileName;
	 
	 Chargement(String fileName){
		 
		 this.fileName=fileName;
		 
	 }
	 
	 public void chargement(Polygones P , Group root) {
		 try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
				try {
					
					//chargement de la class qui contient le nombre d'elemnt pour chaque type :Union,Polygone et display
					this.chgElem=(SauvgardeElements)is.readObject();
					/**chargement des structures*/
					//structure d'uninon
					P.Union=new ArrayList<ArrayList<Polygone>>();
					
					for(int i=0;i<this.chgElem.nb_Union;i++) {
						P.Union.add(new ArrayList<Polygone>());
						for(int j=0;j<this.chgElem.nbPolygonInEachUnion.get(i);j++) {
							P.Union.get(i).add((Polygone)is.readObject());
						}
					}
					//structure de tableau de polygones
					P.Polygone=new ArrayList<Polygone>();
					
					for(int i=0;i<this.chgElem.nb_Polygon_In_Polygon_List;i++) {
						
						P.Polygone.add((Polygone)is.readObject());
						P.Polygone.get(i).polygone2();//Attention !
						P.Polygone.get(i).Reference_Polygone = P.Polygone.get(i);
						P.Polygone.get(i).root = root;
						P.Polygone.get(i).color = Color.RED;
					}
					//structure de tableau de display
					P.Display=new ArrayList<Polygone>();
					for(int i=0;i<this.chgElem.nb_Polygon_In_Display;i++) {
						P.Display.add((Polygone)is.readObject());
						P.Display.get(i).color = Color.web(P.Display.get(i).colorS);
						
					}
					
					is.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
}



class Chargement_Bibbel{
	
	 SauvgardeElements chgElem;
	
	 String fileName;
	 
	 Chargement_Bibbel(String fileName){ //contient le nom du fichier du complex elle peut etre integré il suffit juste de nommer le fichier //netfahmo 3la tasmia
		
		 this.fileName = fileName;
		 
	 }
	 
	 
	 public void chargement(Polygones P , Group root) {
		 try {
				ObjectInputStream is=new ObjectInputStream(new FileInputStream(fileName));
				
				try {
					
					//chargement de la class qui contient le nombre d'elemnt pour chaque type :Union,Polygone et display
					
					this.chgElem = (SauvgardeElements)is.readObject();
					
					/**chargement des structures*/
					//structure d'uninon
					if (P.Union==null)
						P.Union=new ArrayList<ArrayList<Polygone>>();
					
					for(int i=0;i<this.chgElem.nb_Union;i++) {
						
						P.Union.add(new ArrayList<Polygone>());
						
						for(int j=0;j<this.chgElem.nbPolygonInEachUnion.get(i);j++) {
							P.Union.get(i).add((Polygone)is.readObject());
						}}
					
						P.Polygone = new ArrayList<Polygone>();
						
						for(int i=0;i<this.chgElem.nb_Polygon_In_Polygon_List;i++) {
							
							P.Polygone.add((Polygone)is.readObject());
							
							P.Polygone.get(i).polygone2();//Attention !
							
							P.Polygone.get(i).Reference_Polygone = P.Polygone.get(i);
							
							P.Polygone.get(i).root = root;
							
							P.Polygone.get(i).color = Color.RED;
						}
						//structure de tableau de display
						P.Display = new ArrayList<Polygone>();
						
						for( int i=0;i<this.chgElem.nb_Polygon_In_Display;i++) {
							
							P.Display.add((Polygone)is.readObject());
							
							P.Display.get(i).color = Color.web(P.Display.get(i).colorS);
							
						}
					
					
					is.close();
					
				} catch (ClassNotFoundException e) {
					
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	 }
	 
	 
}

class Polygones{
	
	static ArrayList<ArrayList<Polygone>> Union=new ArrayList<ArrayList<Polygone>>();
	
	static ArrayList<Polygone> Polygone=new ArrayList<Polygone>();
	
	static ArrayList<Polygone> Display=new ArrayList<Polygone>();
}
class Fonctionalité // Copier Couper Coller Sauvegarder Ouvrir
{
	
}

class CopierColler{
	
	protected Polygone polygoneCopie= new Polygone();
	Polygone polygone_a_coller=new Polygone();
	ArrayList<Polygone> listeDesUnions= new ArrayList<Polygone>();
	
	public void copier(Polygone Pselectionne,Polygones P1,Group root) {
	    polygoneCopie.copierattribut(P1,Pselectionne);
		polygoneCopie.Reference_Polygone = polygoneCopie;
		polygoneCopie.root = root;
		CouperColler c = new CouperColler();
		c.polygoneCoupe = new Polygone();
		this.listeDesUnions=new ArrayList<Polygone>();
		if(Pselectionne.Union == -1) return;
		for(int i=0;i<P1.Union.get(Pselectionne.Union).size();i++) {
			this.listeDesUnions.add(new Polygone());
			listeDesUnions.get(i).Reference_Polygone = listeDesUnions.get(i);

			this.listeDesUnions.get(i).copierattribut(P1,P1.Union.get(Pselectionne.Union).get(i));
		}
	}
	
	public void coller(Polygones P , Group root) {
		polygone_a_coller.copierattribut(P,polygoneCopie);
		polygone_a_coller.polygone2();
		polygone_a_coller.Reference_Polygone = polygone_a_coller;

		if(listeDesUnions.size()>0)
		{
		   for(Polygone p : listeDesUnions) {
			   p.Union = P.Union.size();
			   p.polygone2();
			   p.Dis = P.Display.size();
			   polygone_a_coller.Union = P.Union.size();
		   }
		}
		else 
		{
			polygone_a_coller.Union = -1;
			polygone_a_coller.Dis = P.Display.size();
		}
		if(this.polygoneCopie.Union!=-1) 
		{
		     P.Union.add(this.listeDesUnions);
			 P.Polygone.addAll(this.listeDesUnions);
		}
		else {P.Polygone.add(polygone_a_coller);}
		P.Display.add(polygone_a_coller);
		Affichage_Display.Afficher(P.Display,P.Union,root);

	}

}
class CouperColler{
	protected Polygone polygoneCoupe=new Polygone();
	Polygone polygone_a_coller=new Polygone();
	ArrayList<Polygone> listeDesUnions= new ArrayList<Polygone>();
	
	
	public void couper(Polygones P,Polygone Pselectionne, Group root) {
		polygoneCoupe.copierattribut(P,Pselectionne);	
		polygoneCoupe.Reference_Polygone = polygoneCoupe;
		
		CopierColler c = new CopierColler();
		c.polygoneCopie = new Polygone() ; 
       polygoneCoupe.root = root;
		this.listeDesUnions=new ArrayList<Polygone>();
		
				
		Affichage_Display.removeD(Pselectionne, P.Display, P.Polygone, P.Union);		
		if(Pselectionne.Union != -1) {
		for(int i=0;i<P.Union.get(Pselectionne.Union).size();i++) {
			this.listeDesUnions.add(new Polygone());
			listeDesUnions.get(i).Reference_Polygone = listeDesUnions.get(i);
			this.listeDesUnions.get(i).copierattribut(P,P.Union.get(Pselectionne.Union).get(i));}
			P.Union.remove(Pselectionne.Union);
		}
		Affichage_Display.Afficher(P.Display,P.Union,root);

	}
	
	public void coller(Polygones P,Group root) {
		polygone_a_coller.copierattribut(P,polygoneCoupe);
		polygone_a_coller.polygone2();
		polygone_a_coller.Reference_Polygone = polygone_a_coller;
		polygone_a_coller.root = polygoneCoupe.root;
		P.Polygone.add(polygone_a_coller);
		
		if(listeDesUnions.size()>0)
		{
		   for(Polygone p : listeDesUnions) {
			   p.Union = P.Union.size();
			   p.polygone2();
			   p.Dis = P.Display.size();
			   polygone_a_coller.Union = P.Union.size();
		   }
		}
		else 
		{
			polygone_a_coller.Union = -1;
			polygone_a_coller.Dis = P.Display.size();
		}
		if(this.polygoneCoupe.Union!=-1) 
		{
		     P.Union.add(this.listeDesUnions);
			 P.Polygone.addAll(this.listeDesUnions);
		}
		else {P.Polygone.add(polygone_a_coller);}
		P.Display.add(polygone_a_coller);
		Affichage_Display.Afficher(P.Display,P.Union,root);
	}
}


/**********************************************************************************/
public class Main extends Application {
	
	public void start(Stage primaryStage) {
		

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("Page1.fxml"));
			
			Scene scene = new Scene(root);
			
			
			
			primaryStage.setScene(scene);
			
			
			primaryStage.setMinHeight(900);
			primaryStage.setMinWidth(1400);
			primaryStage.setMaxHeight(900);
			primaryStage.setMaxWidth(1400);
			
			//primaryStage.setFullScreen(true);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setMinHeight(1000);
			primaryStage.setMinWidth(1000);
			primaryStage.setMaxHeight(1000);
			primaryStage.setMaxWidth(1200);
			primaryStage.setFullScreen(true);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
		    primaryStage.show();
		    
		    } catch(Exception e) {
			e.printStackTrace();
		                         }
	                                    }  
	/*@Override
	
  public void start(Stage primaryStage) {
		
		
		ArrayList<ArrayList<Polygone>> Union=new ArrayList<ArrayList<Polygone>>();
		ArrayList<Polygone> Polygone=new ArrayList<Polygone>();
		ArrayList<Polygone> Display=new ArrayList<Polygone>();
		
		Polygone_SCR.Display = Display;
		Polygone_SCR.UnionT = Union;

		Group racine = new Group();

		Point centre = new Point(200,200);
        Point premier_point = new Point(320,320);

        Color C = Color.RED;
        
        Polygone_SCR P1 = new Polygone_SCR(centre,"P1", C,8,premier_point,racine);
        P1.Reference_Polygone=P1;
        Point centre2 = new Point(280,280);
        Point premier_point2 = new Point(400,400);

        C = Color.GREEN ;
        Polygone_SCR P2 = new Polygone_SCR(centre2,"P2", C,7,premier_point2,racine);
        P2.Reference_Polygone=P2;

        Point centre3 = new Point(500,200);
        Point premier_point3 = new Point(450,400);
        
        C = Color.BLUE;
        
        Polygone_SCR P3 = new Polygone_SCR(centre3,"P3",C,5,premier_point3,racine);
        P3.Reference_Polygone=P3;

        Point centre4 = new Point(170,170);
        Point premier_point4 = new Point(250,200);
        
        C = Color.PURPLE ;
        
        Polygone_SCR P4 = new Polygone_SCR(centre4,"P4",C,4,premier_point4,racine);
        P4.Reference_Polygone=P4;

        Point centre5 = new Point(290,290);
        Point premier_point5 = new Point(390,300);
        
        C = Color.ORANGE ;
        
        Polygone_SCR P5 = new Polygone_SCR(centre5,"P5",C,4,premier_point5,racine);
        P5.Reference_Polygone=P5;

        Point centre6 = new Point(230,230);
        Point premier_point6 = new Point(310,310);
        
        C = Color.BLACK ;
        
        Polygone_SCR P6 = new Polygone_SCR(centre6,"P6",C,4,premier_point6,racine);
        P6.Reference_Polygone=P6;

        Point centre7 = new Point(230,230);
        Point premier_point7 = new Point(250,250);
        
        C = Color.YELLOW ;
        
        Polygone_SCR P7 = new Polygone_SCR(centre7,"P7",C,12,premier_point7,racine);
        P7.Reference_Polygone=P7;
        
        Scene scene = new Scene(racine); 

        ArrayList<Point> A = new ArrayList<Point>();
        
        ArrayList<Point> B = new ArrayList<Point>();
        
        Affichage_Display.Afficher(Display,Union,racine);
		
		
		
		MiniWindow.root  = racine;
		
		MiniWindow.scene = scene;
		
		Operation_bool.crayon(scene, racine, true );
		
		
		
	    
		 
		
		MiniWindow.scene = scene;
		MiniWindow.Dis = Display;
		MiniWindow.Union = Union;

		
		
		Display.add(P1);
		Polygone.add(P1);

		P1.Dis=0;
        racine.getChildren().add(P1.getDessin());

		Display.add(P2);
		Polygone.add(P2);

		P2.Dis=1;


		Display.add(P5);
		Polygone.add(P5);

		P5.Dis=2; */ 

		//Affichage_Display.Afficher(Display,Union,racine); 

		
		
		/**TEST AND**/

		/*Display.add(P1);
		Polygone.add(P1);

		P1.Dis=0;

		Display.add(P2);
		Polygone.add(P2);

		P2.Dis=1;
		
		Display.add(P3);
		Polygone.add(P3);

		P3.Dis=2;	
		
		Display.add(P4);
		Polygone.add(P4);

		P4.Dis=3;
								
		Operation_bool.ANDT(P1, P2	,Polygone,Display,Union,"PINK", racine);
		
		Operation_bool.Afficher(Display,Union,racine);*/
		
		
		/**TEST OR**/
		  
		/*Display.add(P1);
		Polygone.add(P1);

		P1.Dis=0;

		Display.add(P2);
		Polygone.add(P2);

		P2.Dis=1;
		   

        Operation_bool.ORT(P1, P2	,Polygone,Display,Union,P1.color, racine);
		Operation_bool.Afficher(Display,Union,racine);*/
		
		/**TEST NOT**/
		/*Display.add(P7);
		Polygone.add(P7);

		P1.Dis=0;
		
		
		Display.add(P1);
		Polygone.add(P1);

		P1.Dis=1;

		Display.add(P2);
		Polygone.add(P2);

		P2.Dis=2;
		
		 Operation_bool.NOTT(P1, P2	,Polygone,Display,Union,racine);


		Operation_bool.Afficher(Display,Union,racine);*/
		
		
		
		/**TEST Deplacement_complexe**/

		//System.out.println("Avant:"+P1.Diff.get(0).listePoints.get(0).getx());
		 //Operation_bool.Deplacer(P1,100,200, racine);
		// System.out.println("Apres:"+P1.Diff.get(0).listePoints.get(0).getx());
		 //Operation_bool.Afficher(Display,Union,racine);
		
		
		
		/**TEST SELECTIONNER**/
		/*Display.add(P1);
		Polygone.add(P1);

		P1.Dis=0;
		
		Display.add(P4);
		Polygone.add(P4);

		P4.Dis=1;
		
		Display.add(P3);
		Polygone.add(P3);

		P3.Dis=2;
		Display.add(P2);
		Polygone.add(P2);

		P2.Dis=3;*/
		/*****primaryStage.setScene(scene);
		primaryStage.show();  *********/
		
		 //Affichage_Display.Afficher(Display,Union,racine);
		
		// outil.SelectionnerPoly(Display,Polygone, Union,racine,scene);
        //il faut lappeler la premiere fois pour que mouse event ybda 
		//aprés selectinner loperation, on entre son numero l operation SelectOP
		
		 //Affichage_Display.Afficher(Display,Union,racine);
		 //outil.SelectOP(2);
		 

		 
       /***  Polygones P = new Polygones();

         P.Display = Display;
         P.Polygone = Polygone;
         P.Union = Union; ****/
         
			//CopierColler cc=new CopierColler();
			// Operation_bool.NOTT(P2, P3, Polygone, Display, Union, racine);
				
			//cc.copier(P3);
			//cc.coller(P);
			
		//Affichage_Display.Afficher(Display,Union,racine);

         
        // Sauvgarde s = new Sauvgarde("ttestt.bin",P.Union,P.Polygone,P.Display);
       //  s.sauvgarder(P);
         
 		/*Chargement c=new Chargement("ttestt.bin");
		
 		c.chargement(P);
 		
		System.out.println(" Union");
		for(int i=0;i<P.Union.size();i++) {
			System.out.println("Polygone " + i +":");
			for(int j=0;j<P.Union.get(i).size();j++)
				System.out.println(P.Union.get(i).get(j).nom);
		}
		
		System.out.println("Polygone ");
		for(int i=0;i<P.Polygone.size();i++) {
			System.out.println(P.Polygone.get(i).nom);
		}
		

		System.out.println("Display ");
		for(int i=0;i<P.Display.size();i++) {
			System.out.println(P.Display.get(i).nom);
		}*/
		 //racine.getChildren().add(P.Display.get(0).getDessin());

		//Affichage_Display.Afficher(P.Display,P.Union,racine);

		//Operation_bool.SelectOP(2);
		//System.out.println("OP3: "+Operation_bool.OperationBool);

		//Operation_bool.SelectOP(7);

		
		/*primaryStage.setScene(scene);
	 
		primaryStage.show(); }  */  
	
	public static void main(String[] args) {
		launch(args);
	}
}




