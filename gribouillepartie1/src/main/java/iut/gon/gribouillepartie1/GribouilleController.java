package iut.gon.gribouillepartie1;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.gribouille.modele.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GribouilleController implements Initializable{

    @FXML
    private Label abscisse;

    @FXML
    private Rectangle blanc;

    @FXML
    private Rectangle bleu;

    @FXML
    private ColorPicker choix_couleur;

    @FXML
    private ToggleGroup choixepaisseur;

    @FXML
    private ToggleGroup choixstylo;

    @FXML
    private Rectangle cyan;

    @FXML
    private Label epais;

    @FXML
    private Rectangle jaune;

    @FXML
    private Rectangle noir;

    @FXML
    private Label ordonnee;

    @FXML
    private Pane pane;

    @FXML
    private Rectangle rose;

    @FXML
    private Rectangle rouge;

    @FXML
    private Canvas toile;

    @FXML
    private Rectangle vert;
    
    private SimpleDoubleProperty prevX;
    private SimpleDoubleProperty prevY;
    private Dessin de;
    private Trace t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		prevX= new SimpleDoubleProperty(0);
	    prevY=new SimpleDoubleProperty(0);
		
		
		toile.widthProperty().bind(pane.widthProperty());
		toile.heightProperty().bind(pane.heightProperty());
		toile.widthProperty().addListener((obs, old, newV) -> {
			for(Figure f : de.getFigures()) {
				for (int j=0;j<f.getPoints().size()-1; j++) {
					Point p= f.getPoints().get(j);
					Point p2= f.getPoints().get(j+1);
					toile.getGraphicsContext2D().strokeLine(p.getX(), p.getY(), p2.getX(), p2.getY());
				}
			}	
		});
		toile.heightProperty().addListener((obs, old, newV) -> {
			for(Figure f : de.getFigures()) {
				for (int j=0;j<f.getPoints().size()-1; j++) {
					Point p= f.getPoints().get(j);
					Point p2= f.getPoints().get(j+1);
					toile.getGraphicsContext2D().strokeLine(p.getX(), p.getY(), p2.getX(), p2.getY());
				}
			}
		});
		
		abscisse.textProperty().bind(prevX.asString());
		ordonnee.textProperty().bind(prevY.asString());

	}
	
	public void onMousePressed(MouseEvent evt) {
		t= new Trace(1, "", evt.getX(), evt.getY());
        prevX.set(evt.getX());
        prevY.set(evt.getY());
        de.addFigure(t);
        
	}
	
	public void onMouseDragged(MouseEvent evt) {
		toile.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY());
		prevX.set(evt.getX());
        prevY.set(evt.getY());
    	t.addPoint(prevX.get(), prevY.get());
	}
	
	public void setDessin(Dessin d) {
		this.de=d;
	}
	

}

