package controleurs;

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
    }

    @Override
    public void onMouseDragged(MouseEvent evt) {
        controleur.trace(
            controleur.precX.get(), controleur.precY.get(),
            evt.getX(), evt.getY()
        );
        figureCourante.addPoint(evt.getX(), evt.getY());
    }
}