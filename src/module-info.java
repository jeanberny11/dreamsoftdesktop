module dreamsoftdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires dreamsoftcomponent;
    requires SupremeMdiWindow;
    requires logicmodule;
    requires com.jfoenix;
    requires io.reactivex.rxjava2;
    requires rxjavafx;
    requires org.controlsfx.controls;
    requires datamodule;
    requires java.logging;

    opens com.dreamsoft to javafx.fxml;
    opens com.dreamsoft.controller to javafx.fxml;

    exports com.dreamsoft;
    exports com.dreamsoft.controller;
    exports com.dreamsoft.utils;
    exports com.dreamsoft.managers;
}