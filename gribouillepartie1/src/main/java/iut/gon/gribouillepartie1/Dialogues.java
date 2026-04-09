package iut.gon.gribouillepartie1;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


public class Dialogues {
	
	public static boolean confirmation() {
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Voulez-vous vraiment quitter ?");
        if(alert.showAndWait().get()==ButtonType.YES) {
        	return true;
        }
        return false;
        
       }
}
