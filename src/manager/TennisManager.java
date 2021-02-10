package manager;

import model.Individu;
import model.Seance_tennis;
import org.hibernate.Session;
import util.HibernateUtil;

public class TennisManager {
    public void ajouterTennis(String id_seance_tennis, float premier_service, float deuxieme_service, float troisieme_service, int nb_set, char issue_match, float vitesse_moy_service, Individu individu) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Seance_tennis seance_tennis = new Seance_tennis();
        seance_tennis.setId_seance_tennis(id_seance_tennis);
        seance_tennis.setPremier_service(premier_service);
        seance_tennis.setDeuxieme_service(deuxieme_service);
        seance_tennis.setTroisieme_service(troisieme_service);
        seance_tennis.setNb_set(nb_set);
        seance_tennis.setIssue_match(issue_match);
        seance_tennis.setVitesse_moy_service(vitesse_moy_service);
        seance_tennis.setIndividu(individu);

        session.save(seance_tennis);
        session.getTransaction().commit();

    }

    }
