package controleurs;

import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    // Modèle
    public final Dessin dessin;
    public final SimpleDoubleProperty precX;
    public final SimpleDoubleProperty precY;
    public final SimpleObjectProperty<Color> couleur;
    public final SimpleIntegerProperty epaisseur;

    // Non-final car change au fil du dessin
    public Figure figureCourante;

    // Contrôleurs internes (nested) — nommés <fx:id>Controller
    @FXML private MenusController menusController;
    @FXML private StatutController statutController;
    @FXML private CouleursController couleursController;
    @FXML private DessinController dessinController;

    public Controleur() {
        this.dessin = new Dessin();
        this.precX = new SimpleDoubleProperty(0);
        this.precY = new SimpleDoubleProperty(0);
        this.couleur = new SimpleObjectProperty<>(Color.BLACK);
        this.epaisseur = new SimpleIntegerProperty(1);
        this.figureCourante = null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menusController.setControleur(this);
        statutController.setControleur(this);
        couleursController.setControleur(this);
        dessinController.setControleur(this);
    }

    /**
     * Appelée par MenusController pour demander confirmation de quitter.
     * @return true si on doit fermer, false sinon
     */
    public boolean onQuitter() {
        return !iut.gon.gribouillepartie1.Dialogues.confirmation();
    }
}