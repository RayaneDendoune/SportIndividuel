package manager;

import model.Individu;
import model.Seance_cyclisme;
import org.hibernate.Session;
import util.HibernateUtil;

public class CyclismeManager {

    public void ajouterCyslisme(String id_seance_cyclisme, float niveau_activite_physique, float poids, String objectif_seance, float depense_energetique, int besoin_proteine, Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Seance_cyclisme seance_cyclisme = new Seance_cyclisme();
        seance_cyclisme.setId_seance_cyclisme(id_seance_cyclisme);
        seance_cyclisme.setNiveau_activite_physique(niveau_activite_physique);
        seance_cyclisme.setPoids(poids);
        seance_cyclisme.setObjectif_seance(objectif_seance);
        seance_cyclisme.setDepense_energetique(depense_energetique);
        seance_cyclisme.setBesoin_proteine(besoin_proteine);
        seance_cyclisme.setIndividu(individu);


        session.save(seance_cyclisme);
        session.getTransaction().commit();
    }
}
