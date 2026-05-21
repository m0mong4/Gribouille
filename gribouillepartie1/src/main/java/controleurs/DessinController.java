package controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class DessinController implements Initializable {

    @FXML public Canvas toile;
    @FXML public Pane pane;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        toile.widthProperty().addListener((obs, old, newV) -> dessine());
        toile.heightProperty().addListener((obs, old, newV) -> dessine());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toile.widthProperty().bind(pane.widthProperty());
        toile.heightProperty().bind(pane.heightProperty());
    }

    public void setEpaisseur(double ep) {
        toile.getGraphicsContext2D().setLineWidth(ep);
    }


    public void setCouleur(Color couleur) {
        toile.getGraphicsContext2D().setStroke(couleur);
    }

    public void efface() {
        toile.getGraphicsContext2D().clearRect(0, 0, toile.getWidth(), toile.getHeight());
    }

    public void trace(double x1, double y1, double x2, double y2) {
        toile.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }

    private void dessine() {
        if (controleur == null) return;
        efface();
        controleur.dessine();
    }

    @FXML
    public void onMousePressed(MouseEvent evt) {
        controleur.onMousePressed(evt);
    }

    @FXML
    public void onMouseDragged(MouseEvent evt) {
        controleur.onMouseDragged(evt);
    }
}