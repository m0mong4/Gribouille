package controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DessinController implements Initializable {

    @FXML public Canvas toile;
    @FXML public Pane pane;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        // Redessiner quand le canvas est redimensionné
        toile.widthProperty().addListener((obs, old, newV) -> dessine());
        toile.heightProperty().addListener((obs, old, newV) -> dessine());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Liaison canvas <-> pane (taille automatique)
        toile.widthProperty().bind(pane.widthProperty());
        toile.heightProperty().bind(pane.heightProperty());
    }

    /** Efface tout le canvas */
    public void efface() {
        toile.getGraphicsContext2D().clearRect(0, 0, toile.getWidth(), toile.getHeight());
    }

    /** Trace un segment sur le canvas */
    public void trace(double x1, double y1, double x2, double y2) {
        toile.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }

    /** Redessine toutes les figures du dessin */
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