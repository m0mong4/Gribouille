package iut.gon.gribouille.modele;

/**
 Stocke une figure "Trace"
 */
public class Trace extends Figure {

  /**
   Crée une Trace avec une épaisseur, une couleur et un premier point
   */
  public Trace(int epaisseur, String couleur, double x0, double y0) {
    super(epaisseur, couleur);
    points.add(new Point(x0, y0));
  }

  @Override
  public String toString() {
    return "Trace{" +
        "épaisseur=" + epaisseur +
        ", couleur=" + couleur +
        ", points=" + points +
        '}';
  }

  @Override
  public Figure changeCouleur(String nouvelleCouleur) {
    Point dernier = points.get(points.size() - 1);
    return new Trace(epaisseur, nouvelleCouleur, dernier.getX(), dernier.getY());
  }

  @Override
  public Figure changeEpaisseur(int nouvelleEpaisseur) {
    Point dernier = points.get(points.size() - 1);
    return new Trace(nouvelleEpaisseur, couleur, dernier.getX(), dernier.getY());
  }
}
