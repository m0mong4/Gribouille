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
    @FXML public ToggleGroup choixstylo; 
    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choixstylo.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            if (newV == menuCrayon) {
                controleur.onCrayon();
            } else if (newV == menuEtoile) {
                controleur.onEtoile();
            }
        });


        choixepaisseur.selectedToggleProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                String texte = ((RadioMenuItem) newV).getText();
                controleur.setEpaisseur(Integer.parseInt(texte));
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