package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

/**
 * \file DataInit.java
 * \brief Classe qui permet la finalisation de la création de toutes les tables dans la base de données.
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 */
public class DataInit {

    public static void createTables() {
        AnnotationConfiguration config = HibernateUtil.getConfig();
        SchemaUpdate schemaUpdate = new SchemaUpdate(config);
        schemaUpdate.execute(true, true);
    }
}

