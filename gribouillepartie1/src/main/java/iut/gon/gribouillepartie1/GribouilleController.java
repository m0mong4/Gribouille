package iut.gon.gribouillepartie1;

import java.net.URL;
import java.util.ResourceBundle;

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
    
    private double prevX;
    private double prevY;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		toile.widthProperty().bind(pane.widthProperty());
		toile.heightProperty().bind(pane.heightProperty());
	}
	
	public void onMousePressed(MouseEvent evt) {
        prevX=evt.getX();
        prevY=evt.getY();
	}
	
	public void onMouseDragged(MouseEvent evt) {
		toile.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
    	prevX=evt.getX();
    	prevY=evt.getY();
}
	

}

