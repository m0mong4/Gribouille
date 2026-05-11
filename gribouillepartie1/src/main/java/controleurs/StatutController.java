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
        // Liaisons avec les propriétés du contrôleur global
        abscisse.textProperty().bind(controleur.precX.asString("%.0f"));
        ordonnee.textProperty().bind(controleur.precY.asString("%.0f"));
        epais.textProperty().bind(controleur.epaisseur.asString());
        couleurLabel.textProperty().bind(controleur.couleur.asString());
    }
}