package manager;

import model.Individu;
import model.Partie_echec;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.Time;

public class EchecManager {

    public void ajouterEchec(String id_partie_echec, int elo_adversaire, Time duree, int niveau_competence_mentale, char issue_partie, int niveau_concentration, Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Partie_echec partie_echec = new Partie_echec();
        partie_echec.setId_partie_echec(id_partie_echec);
        partie_echec.setElo_adversaire(elo_adversaire);
        partie_echec.setDuree(duree);
        partie_echec.setNiveau_competence_mentale(niveau_competence_mentale);
        partie_echec.setIssue_partie(issue_partie);
        partie_echec.setNiveau_concentration(niveau_concentration);
        partie_echec.setIndividu(individu);

        session.save(partie_echec);
        session.getTransaction().commit();
    }
}
