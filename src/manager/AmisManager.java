package manager;

import model.Amis;
import model.Demande;
import org.hibernate.Session;
import util.HibernateUtil;

public class AmisManager {
    public static Amis amis;

    public static void ajouterAmis(String id_amis, String id_individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Amis amis = new Amis();
        amis.setId_amis(id_amis);
        amis.setId(id_individu);

        session.save(amis);
        session.getTransaction().commit();
    }
}
