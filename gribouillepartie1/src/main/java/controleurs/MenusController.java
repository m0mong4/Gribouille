package controleurs;

import iut.gon.gribouillepartie1.Dialogues;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MenusController implements Initializable {

    @FXML public ToggleGroup choixepaisseur;
    @FXML public ToggleGroup choixstylo; // fx:id du ToggleGroup dans FXML 
    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Listener sur le changement d'outil dans le menu [cite: 106]
        choixstylo.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            if (newV == menuCrayon) {
                controleur.onCrayon(); // [cite: 107]
            } else if (newV == menuEtoile) {
                controleur.onEtoile(); // [cite: 108]
            }
        });
    }

    @FXML
    public void onQuitte(ActionEvent evt) {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}