module iut.gon.gribouillepartie1 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens iut.gon.gribouillepartie1 to javafx.fxml;
    exports iut.gon.gribouillepartie1;
}
