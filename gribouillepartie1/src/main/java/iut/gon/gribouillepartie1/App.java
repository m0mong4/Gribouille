package iut.gon.gribouillepartie1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double prevX;
    private double prevY;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(event -> {
        	if(Dialogues.confirmation()==true) {
        		event.consume();
        	}
        	
        	
        });
        Canvas dessin = (Canvas) scene.lookup("Canvas");
        dessin.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->{
        	prevX=event.getX();
        	prevY=event.getY();
        });
        dessin.addEventHandler(MouseEvent.MOUSE_DRAGGED, event ->{
        	dessin.getGraphicsContext2D().strokeLine(prevX, prevY, event.getX(), event.getY());
        	prevX=event.getX();
        	prevY=event.getY();
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}