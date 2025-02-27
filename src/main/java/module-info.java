module com.example.test2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.test2.DAO to org.hibernate.orm.core;
    opens com.example.test2 to javafx.fxml;
    exports com.example.test2;
    exports com.example.test2.Controllers;
    opens com.example.test2.Controllers to javafx.fxml;
    exports com.example.test2.Hibernate;
    opens com.example.test2.Hibernate to javafx.fxml;
}