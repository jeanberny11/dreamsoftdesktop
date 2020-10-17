module dreamsoftcomponent {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.logging;
    requires org.controlsfx.controls;
    opens com.dscomponent to javafx.fxml;
    exports com.dscomponent;
}