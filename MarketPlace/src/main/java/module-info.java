module ipl.marketplace {
    requires javafx.controls;
    requires javafx.fxml;


    opens ipl.marketplace to javafx.fxml;
    exports ipl.marketplace;
}