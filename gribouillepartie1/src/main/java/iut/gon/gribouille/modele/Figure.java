package iut.gon.gribouille.modele;

import java.util.ArrayList;
import java.util.List;

/**
 Stocke une figure
 */
public abstract class Figure {
  protected int epaisseur;
  protected String couleur;
  protected List<Point> points = new ArrayList<>();

  /**
   Crée une figure avec une épaisseur et une couleur
   */
  public Figure(int epaisseur, String couleur) {
    this.epaisseur = epaisseur;
    this.couleur = couleur;
  }

  /**
   Retourne l'épaisseur du tracé de la figure
   */
  public int getEpaisseur() {
    return epaisseur;
  }

  /**
   Retourne la couleur du tracé de la figure

   @return un int représentant la couleur de la figure
   */
  public String getCouleur() {
    return couleur;
  }

  /**
   Retourne les points de la figure dans l'ordre du tracé
   */
  public List<Point> getPoints() {
    return points;
  }

  /**
   Ajoute un nouveau point à la fin du tracé
   */
  public void addPoint(Point nouveau) {
    points.add(nouveau);
  }

  /**
   Ajoute un nouveau point à la fin du tracé
   */
  public void addPoint(double x, double y) {
    addPoint(new Point(x, y));
  }

  /**
   Crée une nouvelle figure qui continue la figure courante avec une nouvelle couleur
   */
  public abstract Figure changeCouleur(String nouvelleCouleur);

  /**
   Crée une nouvelle figure qui continue la figure courante avec une nouvelle couleur
   */
  public abstract Figure changeEpaisseur(int nouvelleEpaisseur);
} // public class Figure
