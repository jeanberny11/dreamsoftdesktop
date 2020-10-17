module logicmodule {
    requires java.sql;
    requires datamodule;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires java.persistence;
    exports com.logica.repositories;
}