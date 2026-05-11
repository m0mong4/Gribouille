package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class CouleursController {

    @FXML public ColorPicker choix_couleur;
    @FXML public Rectangle rouge;
    @FXML public Rectangle vert;
    @FXML public Rectangle bleu;
    @FXML public Rectangle cyan;
    @FXML public Rectangle rose;
    @FXML public Rectangle jaune;
    @FXML public Rectangle noir;
    @FXML public Rectangle blanc;

    private Controleur controleur;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}