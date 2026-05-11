package controleurs;

import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouille.modele.Trace;
import iut.gon.gribouillepartie1.Dialogues;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    // --- Modèle ---
    public final Dessin dessin;
    public final SimpleDoubleProperty precX;
    public final SimpleDoubleProperty precY;
    public final SimpleObjectProperty<Color> couleur;
    public final SimpleIntegerProperty epaisseur;

    // Non-final : change à chaque tracé
    public Figure figureCourante;

    // --- Contrôleurs internes (nom = fx:id + "Controller") ---
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
        // Transmettre this à chaque contrôleur interne
        menusController.setControleur(this);
        statutController.setControleur(this);   // lie precX, precY, epaisseur, couleur
        couleursController.setControleur(this);
        dessinController.setControleur(this);   // lie canvas/pane + redessine au resize
    }

    // ----------------------------------------------------------------
    // 2.3.1 — Quitter
    // ----------------------------------------------------------------

    /**
     * Demande confirmation et retourne true si on doit fermer.
     * Appelée depuis MenusController ET depuis App.setOnCloseRequest.
     */
    public boolean onQuitter() {
        return !Dialogues.confirmation();
    }

    // ----------------------------------------------------------------
    // 2.3.2 — Dessin (logique souris + redessiner)
    // ----------------------------------------------------------------

    public void onMousePressed(MouseEvent evt) {
        precX.set(evt.getX());
        precY.set(evt.getY());
        figureCourante = new Trace(epaisseur.get(), couleur.get().toString(),
                                   evt.getX(), evt.getY());
        dessin.addFigure(figureCourante);
    }

    public void onMouseDragged(MouseEvent evt) {
        dessinController.trace(precX.get(), precY.get(), evt.getX(), evt.getY());
        precX.set(evt.getX());
        precY.set(evt.getY());
        figureCourante.addPoint(evt.getX(), evt.getY());
    }

    /** Redessine toutes les figures (appelée lors d'un resize du canvas). */
    public void dessine() {
        for (Figure f : dessin.getFigures()) {
            java.util.List<Point> pts = f.getPoints();
            for (int i = 0; i < pts.size() - 1; i++) {
                Point p1 = pts.get(i);
                Point p2 = pts.get(i + 1);
                dessinController.trace(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }
        }
    }
}