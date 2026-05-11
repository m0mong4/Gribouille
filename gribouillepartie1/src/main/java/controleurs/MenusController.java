package controleurs;

import iut.gon.gribouillepartie1.Dialogues;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenusController {

    @FXML public ToggleGroup choixepaisseur;
    @FXML public ToggleGroup choixstylo;
    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @FXML
    public void onQuitte(ActionEvent evt) {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}