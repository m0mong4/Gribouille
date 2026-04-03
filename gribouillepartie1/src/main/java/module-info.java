module iut.gon.gribouillepartie1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.gribouillepartie1 to javafx.fxml;
    exports iut.gon.gribouillepartie1;
}
