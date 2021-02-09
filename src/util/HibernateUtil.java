package util;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static AnnotationConfiguration config;

    public static AnnotationConfiguration getConfig() {
        if (config == null) {
            config = new AnnotationConfiguration();
            config.addAnnotatedClass(Individu.class);
            config.addAnnotatedClass(Amis.class);
            config.addAnnotatedClass(Demande.class);
            config.addAnnotatedClass(Partie_echec.class);
            config.addAnnotatedClass(Seance_course.class);
            config.addAnnotatedClass(Seance_cyclisme.class);
            config.addAnnotatedClass(Seance_natation.class);
            config.addAnnotatedClass(Seance_tennis.class);

            String packageName = HibernateUtil.class.getPackage().getName();
            config.configure(packageName + "/hibernate.cfg.xml");
        }
        return config;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                AnnotationConfiguration config = getConfig();
                sessionFactory = config.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
