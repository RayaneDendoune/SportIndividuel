package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public class DataInit {

    public static void createTables() {
        AnnotationConfiguration config = HibernateUtil.getConfig();
        /*SchemaExport schemaExport = new SchemaExport(config);
        schemaExport.create(true, true);*/

        SchemaUpdate schemaUpdate = new SchemaUpdate(config);
        schemaUpdate.execute(true, true);
    }
}

