package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatutController {

    @FXML public Label abscisse;
    @FXML public Label ordonnee;
    @FXML public Label epais;
    @FXML public Label outil;
    @FXML public Label couleurLabel;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}