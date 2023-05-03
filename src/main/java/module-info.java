module com.mancala.mancalagame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.mancala.mancalagame to javafx.fxml;
    exports com.mancala.mancalagame;
    exports com.mancala.mancalagame.usercontroller;
    opens com.mancala.mancalagame.usercontroller to javafx.fxml;
    exports com.mancala.mancalagame.admincontroller;
    opens com.mancala.mancalagame.admincontroller to javafx.fxml;
    exports com.mancala.mancalagame.gamecontroller;
    opens com.mancala.mancalagame.gamecontroller to javafx.fxml;
}