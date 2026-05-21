package controleurs;

import iut.gon.gribouille.modele.Figure;
import javafx.scene.input.MouseEvent;

public abstract class Outil {

    protected Controleur controleur;

    public Outil(Controleur controleur) {
        this.controleur = controleur;
    }

    public abstract void onMousePressed(MouseEvent evt);
    public abstract void onMouseDragged(MouseEvent evt);

    
    public abstract void setFigureCourante(Figure figure);
}