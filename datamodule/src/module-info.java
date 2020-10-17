module datamodule {
    requires java.sql;
    requires org.apache.commons.io;
    requires org.json;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    opens com.dsdata.entities to org.hibernate.orm.core;

    exports com.dsdata;
    exports com.dsdata.entities;
}