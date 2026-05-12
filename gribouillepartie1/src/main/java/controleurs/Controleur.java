package controleurs;

import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Etoile;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouillepartie1.Dialogues;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    // --- Modèle (Attributs publics final selon consigne 2.1) ---
    public final Dessin dessin;
    public final SimpleDoubleProperty precX;
    public final SimpleDoubleProperty precY;
    public final SimpleObjectProperty<Color> couleur;
    public final SimpleIntegerProperty epaisseur;

    // Figure courante (non-final)
    public Figure figureCourante;

    // Outil courant (Stratégie)
    private Outil outil;

    // --- Contrôleurs internes ---
    @FXML private MenusController menusController;
    @FXML private StatutController statutController;
    @FXML private CouleursController couleursController;
    @FXML private DessinController dessinController;

    public Controleur() {
        this.dessin    = new Dessin();
        this.precX     = new SimpleDoubleProperty(0);
        this.precY     = new SimpleDoubleProperty(0);
        this.couleur   = new SimpleObjectProperty<>(Color.BLACK);
        this.epaisseur = new SimpleIntegerProperty(1);
        this.figureCourante = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menusController.setControleur(this);
        statutController.setControleur(this);
        couleursController.setControleur(this);
        dessinController.setControleur(this);
        
        // Initialisation par défaut sur l'outil Crayon [cite: 102]
        this.outil = new OutilCrayon(this);
    }

    public boolean onQuitter() {
        return !Dialogues.confirmation();
    }

    public void trace(double x1, double y1, double x2, double y2) {
        dessinController.trace(x1, y1, x2, y2);
    }

    // --- Souris : Mise à jour des coordonnées puis délégation à l'outil ---
    public void onMousePressed(MouseEvent evt) {
        precX.set(evt.getX());
        precY.set(evt.getY());
        outil.onMousePressed(evt); // [cite: 102]
    }

    public void onMouseDragged(MouseEvent evt) {
        outil.onMouseDragged(evt); // [cite: 102]
        precX.set(evt.getX());
        precY.set(evt.getY());
    }

    // --- Changement d'outil [cite: 109, 110] ---
    public void onCrayon() {
        this.outil = new OutilCrayon(this);
        statutController.outil.setText("Crayon");
    }

    public void onEtoile() {
        this.outil = new OutilEtoile(this);
        statutController.outil.setText("Étoile");
    }

    // --- Redessiner les figures (gère désormais les Étoiles) [cite: 114, 115] ---
    public void dessine() {
        for (Figure f : dessin.getFigures()) {
            List<Point> pts = f.getPoints();
            if (f instanceof Etoile) {
                Point centre = ((Etoile) f).getCentre();
                for (Point p : pts) {
                    dessinController.trace(centre.getX(), centre.getY(), p.getX(), p.getY());
                }
            } else {
                for (int i = 0; i < pts.size() - 1; i++) {
                    dessinController.trace(pts.get(i).getX(), pts.get(i).getY(),
                                           pts.get(i+1).getX(), pts.get(i+1).getY());
                }
            }
        }
    }
}