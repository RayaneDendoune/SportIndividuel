package manager;

import gui.Tennis;
import model.Individu;
import model.Seance_course;
import model.Seance_natation;
import model.Seance_tennis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.*;

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

    public static long nbSeanceTennis(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_tennis st where st.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbSeanceTennis(individu)) + 1;
        String id = individu.getId_individu();
        String seance ="";
        if(nbOcc<10) {
            seance = id + "_0" + nbOcc;
        }
        else {
            seance = id + "_" + nbOcc;
        }

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

    public static ArrayList<Float> VitesseMoy(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Float> vitesse = new ArrayList<Float>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_tennis st where st.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_tennis st = (Seance_tennis) iterator.next();
            //System.out.println(sc.toString());
            vitesse.add(st.getVitesse_moy_service());
        }
        readTransaction.commit();
        return vitesse;
    }

    public static ArrayList<Character> nbVictoire(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Character> issue = new ArrayList<Character>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_tennis st where st.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_tennis st = (Seance_tennis) iterator.next();
            //System.out.println(sc.toString());
            issue.add(st.getIssue_match());
        }
        readTransaction.commit();
        return issue;
    }

    //Fonction qui renvoie tous les individus dans la table
    public static ArrayList<Individu> listIndividuTennis(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        //Query readQuery = session.createQuery("from Seance_tennis");
        Query readQuery = session.createQuery("from Seance_tennis st where st.individu not in (SELECT st.individu from Seance_tennis st where st.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_tennis st = (Seance_tennis) iterator.next();
            //System.out.println(sc.toString());
            individus.add(st.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }


}
