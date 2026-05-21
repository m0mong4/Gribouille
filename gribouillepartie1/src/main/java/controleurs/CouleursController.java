package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private Rectangle rectanglePrecedent = null;
    private double arcWidthPrecedent;
    private double arcHeightPrecedent;
    private double strokeWidthPrecedent;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
    @FXML
    public void onVBoxClique(MouseEvent evt) {
        if (!(evt.getTarget() instanceof Rectangle)) return;

        Rectangle rect = (Rectangle) evt.getTarget();
        Color couleur = (Color) rect.getFill();

        if (rectanglePrecedent != null) {
            rectanglePrecedent.setArcWidth(arcWidthPrecedent);
            rectanglePrecedent.setArcHeight(arcHeightPrecedent);
            rectanglePrecedent.setStrokeWidth(strokeWidthPrecedent);
        }
        
        arcWidthPrecedent    = rect.getArcWidth();
        arcHeightPrecedent   = rect.getArcHeight();
        strokeWidthPrecedent = rect.getStrokeWidth();
        rectanglePrecedent   = rect;


        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setStrokeWidth(5);


        controleur.setCouleur(couleur);
    }
}