package iut.gon.gribouille.modele;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.util.List;

/**
 Stocke un dessin constitué de figures
 */
public class Dessin {
  /**
   Indique si le Dessin a été modifié depuis la dernière sauvegarde
   */
  private final SimpleBooleanProperty estModifie = new SimpleBooleanProperty(false);

  /**
   Le nom du fichier. Ou "Sans Nom", si non sauvegardé.
   */
  private final SimpleStringProperty nomDuFichier = new SimpleStringProperty("Sans Nom");
  /**
   La liste des figures
   */
  private List<Figure> figures;

  /**
   Crée un dessin vide sans nom
   */
  public Dessin() {
    figures = FXCollections.observableArrayList();
  }

  /**
   Retourne La liste des figures
   */
  public List<Figure> getFigures() {
    return figures;
  }

  /**
   Modifie La liste des figures
   */
  public void setFigures(List<Figure> figures) {
    this.figures = figures;
  }

  /**
   Ajoute une nouvelle figure
   */
  public void addFigure(Figure figure) {
    figures.add(figure);
    estModifie.set(true);
  }

  /**
   Teste si le dessin a été modifié depuis la dernière sauvegarde
   */
  public boolean getEstModifie() {
    return estModifie.get();
  }

  /**
   Modifie l'état de modification du dessin
   */
  public void setEstModifie(boolean valeur) {
    estModifie.set(valeur);
  }

  /**
   Teste si le dessin a été modifié depuis la dernière sauvegarde
   */
  public BooleanExpression estModifieProperty() {
    return estModifie;
  }

  /**
   Retourne le nom du fichier
   */
  public String getNomDuFichier() {
    return nomDuFichier.get();
  }

  /**
   Retourne le nom du fichier
   */
  public void setNomDuFichier(String nomDuFichier) {
    this.nomDuFichier.set(nomDuFichier);
  }

  /**
   Le nom du fichier. Ou "Sans Nom", si non sauvegardé.
   */
  public StringExpression nomDuFichierProperty() {
    return nomDuFichier;
  }

} // public class Dessin
