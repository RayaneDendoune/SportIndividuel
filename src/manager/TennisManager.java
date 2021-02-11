package manager;

import model.Individu;
import model.Seance_tennis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class TennisManager {
    //Je pense que nbset ne sert a rien du tout, on peut l'enlever de la table.
    public static void ajouterTennis(String id_seance_tennis, float premier_service, float deuxieme_service, float troisieme_service, int nb_set, char issue_match, float vitesse_moy_service, Individu individu) {

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

    public static long nbSeanceCourse(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_tennis st where st.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbSeanceCourse(individu)) + 1;
        String id = individu.getId_individu();

        String seance = id + "_" + nbOcc;
        return seance;
    }


    public static float vitesseService(float service1, float service2, float service3) {
        float vitesse = (service1 + service2 + service3)/3;
        return vitesse;
    }

    public static char issueMatch(String issue) {
        if(issue.equals("Victoire")) {
            return 'V';
        }
        else if(issue.equals("Défaite")) {
            return 'D';
        }
        return ' ';
    }

}
