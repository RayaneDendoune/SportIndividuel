package manager;

import model.Demande;
import model.Individu;
import org.hibernate.Session;
import util.HibernateUtil;

public class DemandeManager {
    public static Demande demande;

    public static void ajouterDemande(String id_destinataire, String id_expediteur){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Demande demande = new Demande();
        demande.setId_destinataire(id_destinataire);
        demande.setId(id_expediteur);

        session.save(demande);
        session.getTransaction().commit();
    }
}
