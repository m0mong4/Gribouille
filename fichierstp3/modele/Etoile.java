package iut.gon.gribouille.modele;

/**
 Stocke une Figure "Etoile"
 */
public class Etoile extends Figure {
  /**
   Le point central de l'étoile
   */
  private Point centre;

  /**
   Crée une étoile avec une épaisseur, une couleur et un centre
   */
  public Etoile(int epaisseur, String couleur, double xc, double yc) {
    super(epaisseur, couleur);
    centre = new Point(xc, yc);
  }

  /**
   Retourne le point central de l'étoile
   */
  public Point getCentre() {
    return centre;
  }

  public Figure changeCouleur(String nouvelleCouleur) {
    return new Etoile(epaisseur, nouvelleCouleur, centre.getX(), centre.getY());
  }

  public Figure changeEpaisseur(int nouvelleEpaisseur) {
    return new Etoile(nouvelleEpaisseur, couleur, centre.getX(), centre.getY());
  }

}
