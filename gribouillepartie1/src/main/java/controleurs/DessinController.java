package controleurs;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DessinController {

    @FXML public Canvas toile;
    @FXML public Pane pane;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @FXML
    public void onMousePressed(MouseEvent evt) {
        // sera délégué au Controleur en 2.3
    }

    @FXML
    public void onMouseDragged(MouseEvent evt) {
        // sera délégué au Controleur en 2.3
    }
}