package iut.gon.gribouillepartie1;

import controleurs.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CadreGribouille.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);

        Controleur controleur = fxmlLoader.getController();

        stage.setScene(scene);
        stage.titleProperty().bind(controleur.dessin.nomDuFichierProperty());


        stage.setOnCloseRequest(event -> {
            if (!controleur.onQuitter()) {
                event.consume();
            }
        });


        stage.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            controleur.onKeyPressed(event.getText());
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}