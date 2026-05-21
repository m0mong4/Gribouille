package controleurs;

import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Trace;
import javafx.scene.input.MouseEvent;

public class OutilCrayon extends Outil {

    private Trace figureCourante;

    public OutilCrayon(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePressed(MouseEvent evt) {
        figureCourante = new Trace(
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
        controleur.trace(
            controleur.precX.get(), controleur.precY.get(),
            evt.getX(), evt.getY()
        );
        figureCourante.addPoint(evt.getX(), evt.getY());
    }

    @Override
    public void setFigureCourante(Figure figure) {
        if (figure instanceof Trace) {
            this.figureCourante = (Trace) figure;
            controleur.figureCourante = figure;
        }
    }
}