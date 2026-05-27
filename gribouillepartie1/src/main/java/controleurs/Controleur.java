package controleurs;

import iut.gon.gribouille.modele.Dessin;
import javafx.scene.control.ButtonBar;
import iut.gon.gribouille.modele.Etoile;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouillepartie1.Dialogues;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    
    public final Dessin dessin;
    public final SimpleDoubleProperty precX;
    public final SimpleDoubleProperty precY;
    public final SimpleObjectProperty<Color> couleur;
    public final SimpleIntegerProperty epaisseur;

    public Figure figureCourante;

    private Outil outil;
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

        this.outil = new OutilCrayon(this);
    }

    public boolean onQuitter() {
        if (!dessin.getEstModifie()) {
            return true; 
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Le dessin a été modifié.");
        alert.setContentText("Voulez-vous sauvegarder avant de quitter ?");

        ButtonType btnSauvegarder  = new ButtonType("Sauvegarder et quitter");
        ButtonType btnSansSauvegarde = new ButtonType("Quitter sans sauvegarder");
        ButtonType btnAnnuler      = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnSauvegarder, btnSansSauvegarde, btnAnnuler);

        Optional<ButtonType> resultat = alert.showAndWait();

        if (resultat.isEmpty() || resultat.get() == btnAnnuler) {
            return false; 
        } else if (resultat.get() == btnSansSauvegarde) {
            return true; 
        } else {
            try {
                sauvegarde();
                return dessin.getNomDuFichier() != null
                    && !dessin.getNomDuFichier().equals("Sans Nom"); 
                } catch (Exception e) {
                return false; 
            }
        }
    }

    public void trace(double x1, double y1, double x2, double y2) {
        dessinController.trace(x1, y1, x2, y2);
    }

    public void onMousePressed(MouseEvent evt) {
        precX.set(evt.getX());
        precY.set(evt.getY());
        outil.onMousePressed(evt);
    }

    public void onMouseDragged(MouseEvent evt) {
        outil.onMouseDragged(evt);
        precX.set(evt.getX());
        precY.set(evt.getY());
    }

    public void setEpaisseur(int ep) {
        this.epaisseur.set(ep);
        dessinController.setEpaisseur(ep);
    }

    public void setCouleur(Color c) {
        this.couleur.set(c);
        dessinController.setCouleur(c);
        if (figureCourante != null) {
            Figure nouvelleFigure = figureCourante.changeCouleur(c.toString());
            dessin.addFigure(nouvelleFigure);
            outil.setFigureCourante(nouvelleFigure);
            figureCourante = nouvelleFigure;
        }
    }

    public void onCrayon() {
        this.outil = new OutilCrayon(this);
        statutController.outil.setText("Crayon");
    }

    public void onEtoile() {
        this.outil = new OutilEtoile(this);
        statutController.outil.setText("Étoile");
    }

    public void onKeyPressed(String key) {
        switch (key) {
            case "c" -> onCrayon();
            case "e" -> onEtoile();

            case "1" -> setEpaisseur(1);
            case "2" -> setEpaisseur(2);
            case "3" -> setEpaisseur(3);
            case "4" -> setEpaisseur(4);
            case "5" -> setEpaisseur(5);
            case "6" -> setEpaisseur(6);
            case "7" -> setEpaisseur(7);
            case "8" -> setEpaisseur(8);
            case "9" -> setEpaisseur(9);

            case "r" -> setCouleur(Color.RED);
            case "v" -> setCouleur(Color.GREEN);
            case "b" -> setCouleur(Color.BLUE);
            case "n" -> setCouleur(Color.BLACK);
            case "j" -> setCouleur(Color.YELLOW);
            case "p" -> setCouleur(Color.PINK);
            case "w" -> setCouleur(Color.WHITE);
            case "q" -> setCouleur(Color.CYAN);

            default -> { /* touche non gérée */ }
        }
    }


    public void dessine() {
        for (Figure f : dessin.getFigures()) {
            dessinController.setEpaisseur(f.getEpaisseur());
            dessinController.setCouleur(Color.valueOf(f.getCouleur()));
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
        dessinController.setEpaisseur(epaisseur.get());
        dessinController.setCouleur(couleur.get());
    }
    
    public void sauvegarde() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder le dessin");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers dessin", "*.gri")
        );
        File fichier = fileChooser.showSaveDialog(null);
        if (fichier != null) {
            dessin.sauveSous(fichier.getAbsolutePath());
        }
    }
    
    public void charge() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un dessin");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers dessin", "*.gri")
        );
        File fichier = fileChooser.showOpenDialog(null);
        if (fichier != null) {
            dessin.charge(fichier.getAbsolutePath());
            dessinController.efface();
            dessine();
        }
    }
}