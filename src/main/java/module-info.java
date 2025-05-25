module com.example.suivipatientjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;
    requires java.desktop;

    opens com.example.suivipatientjavafx to javafx.fxml;
    opens view to javafx.fxml;
    opens com.example.suivipatientjavafx.model to org.hibernate.orm.core, javafx.base, javafx.fxml, javafx.controls, javafx.graphics;
    opens com.example.suivipatientjavafx.Controller to javafx.fxml;
    opens com.example.suivipatientjavafx.dao to org.hibernate.orm.core;
    opens com.example.suivipatientjavafx.util to org.hibernate.orm.core;
    exports com.example.suivipatientjavafx;


}