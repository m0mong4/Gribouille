package controleurs;

import iut.gon.gribouille.modele.Etoile;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import javafx.scene.input.MouseEvent;

public class OutilEtoile extends Outil {

    private Etoile figureCourante;

    public OutilEtoile(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePressed(MouseEvent evt) {
        figureCourante = new Etoile(
            controleur.epaisseur.get(),
            controleur.couleur.get().toString(),
            evt.getX(), evt.getY()
        );
        controleur.dessin.addFigure(figureCourante);
        controleur.figureCourante = figureCourante;
        controleur.setEpaisseur(controleur.epaisseur.get());
        controleur.setCouleur(controleur.couleur.get());
    }

    @Override
    public void onMouseDragged(MouseEvent evt) {
        Point centre = figureCourante.getCentre();
        controleur.trace(
            centre.getX(), centre.getY(),
            evt.getX(), evt.getY()
        );
        figureCourante.addPoint(evt.getX(), evt.getY());
    }

    @Override
    public void setFigureCourante(Figure figure) {
        if (figure instanceof Etoile) {
            this.figureCourante = (Etoile) figure;
            controleur.figureCourante = figure;
        }
    }
}