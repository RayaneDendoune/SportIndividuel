package manager;

import gui.Tennis;
import model.Individu;
import model.Seance_tennis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Time;
import java.util.*;

/**
 * \file TennisManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le sport Tennis
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au sport Tennis.
 *
 */
public class TennisManager {
    /**
     * \fn void ajouterTennis(String id_seance_tennis, float premier_service, float deuxieme_service, float troisieme_service, int nb_set, char issue_match, float vitesse_moy_service, Individu individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Seance_tennis dans la base de donnée grâce aux données entrées en paramètres.
     * \param [in] id_seance_tennis Clé primaire de la table Seance_tennis (Type String)
     * \param [in] premier_service Premier Service meilleur service (Type Float)
     * \param [in] deuxieme_service Deuxieme Service meilleur service  (Type Float)
     * \param [in] troisieme_service Troisième Service meilleur service (Type Float)
     * \param [in] nb_set Nombre de Set durant le match (Type Integer)
     * \param [in] issue_match Issue du match (Type Character)
     * \param [in] vitesse_moy_service Vitesse moyenne des meilleurs services durant ce match (Type Float)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     *
     */
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

    /**
     * \fn void updateTennis(Seance_tennis seance, float premierService, float deuxiemeService, float troisiemeService, int nbSet,char issueMatch)
     * \brief Fonction qui met à jour la table Seance_tennis et refait de nouveaux calculs grâce aux données entrées en paramètres.
     * \param [in] seance Seance de Tennis que l'on souhaite modifier (Type Seance_tennis)
     * \param [in] premier_service Premier Service meilleur service (Type Float)
     * \param [in] deuxieme_service Deuxieme Service meilleur service  (Type Float)
     * \param [in] troisieme_service Troisième Service meilleur service (Type Float)
     * \param [in] nb_set Nombre de Set durant le match (Type Integer)
     * \param [in] issue_match Issue du match (Type Character)
     */
    public static void updateTennis(Seance_tennis seance, float premierService, float deuxiemeService, float troisiemeService, int nbSet,char issueMatch) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        float vitesse = TennisManager.vitesseService(premierService, deuxiemeService, troisiemeService);

        seance.setPremier_service(premierService);
        seance.setDeuxieme_service(deuxiemeService);
        seance.setTroisieme_service(troisiemeService);
        seance.setNb_set(nbSet);
        seance.setIssue_match(issueMatch);
        seance.setVitesse_moy_service(vitesse);

        session.update(seance);
        session.getTransaction().commit();
    }

    /**
     * \fn long nbSeanceTennis(Individu individu)
     * \brief Fonction qui retourne le nombre de séance de Tennis que individu passé en paramètre a fait.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un Long avec le nombre de séance de Tennis que l'utilisateur a effectué.
     */
    public static long nbSeanceTennis(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_tennis st where st.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    /**
     * \fn String idSeance(Individu individu)
     * \brief Fonction qui retourne un nouveau identifiant de séance grâce au nombre de séance de Tennis que l'utilisateur a effectué incrémenter de 1.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec un nouvel identifiant de séance de Tennis pour l'individu passé en paramètre
     */
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

    /**
     * \fn float vitesseService(float service1, float service2, float service3)
     * \brief Fonction qui calcule la vitesse moyenne des services
     * \param [in] service1 Premier Service meilleur service (Type Float)
     * \param [in] service2 Deuxieme Service meilleur service  (Type Float)
     * \param [in] service3 Troisième Service meilleur service (Type Float)
     * \return Retourne un Float avec la moyenne de la vitesse des services qui a été calculé.
     */
    public static float vitesseService(float service1, float service2, float service3) {
        float vitesse = (service1 + service2 + service3)/3;
        return vitesse;
    }

    /**
     * \fn char issueMatch(String issue)
     * \brief Fonction qui retourne un Character en fonction de l'issue de la partie (String) qui lui est rentré en paramètre
     * \param [in] issue Issue de la partie (Type Character)
     * \return Retourne un Character en fonction de l'issue de la partie
     */
    public static char issueMatch(String issue) {
        if(issue.equals("Victoire")) {
            return 'V';
        }
        else if(issue.equals("Défaite")) {
            return 'D';
        }
        return ' ';
    }

    /**
     * \fn ArrayList<Float> VitesseMoy(Individu individu)
     * \brief Fonction qui retourne les vitesses moyennes des services d'un individu pour chaque séance de la table Seance_tennis depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)!
     * \return Retourne une ArrayList de Float avec les vitesses moyennes de l'individu à chaque séance.
     */
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

    /**
     * \fn ArrayList<Character> nbVictoire(Individu individu)
     * \brief Fonction qui retourne les issues des matchs de l'individu pour chaque séance de la table Seance_tennis depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)!
     * \return Retourne une ArrayList de Character avec les issues des match de l'individu à chaque séance.
     */
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

    /**
     * \fn ArrayList<Individu> listIndividuTennis(Individu individu)
     * \brief Fonction qui renvoie tous les individus de la table Seance_tennis excepté l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList d'Individu qui sont dans la table Seance_tennis excepté l'individu passé en paramètre
     */
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

    /**
     * \fn ArrayList<Seance_tennis> listTennis()
     * \brief Fonction qui parcours la table Seance_tennis et qui retourne toutes les données de cette table sous forme d'ArrayList de Seance_tennis
     * \return ArrayList de Seance_tennis correspondant à toutes les données compris dans la table Seance_tennis dans la base de données
     */
    public static ArrayList<Seance_tennis> listTennis() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_tennis> listNatation = new ArrayList<Seance_tennis>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_tennis st");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_tennis st = (Seance_tennis) iterator.next();
            //System.out.println(sc.toString());
            listNatation.add(st);
        }
        readTransaction.commit();
        return listNatation;
    }

    /**
     * \fn ArrayList<Seance_tennis> seanceTennisIndividu(Individu individu)
     * \brief Fonction qui filtre la table Seance_tennis afin de retourner uniquement les Seance_tennis de l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Seance_tennis avec uniquement les séances de l'individu passé en paramètre.
     */
    public static ArrayList<Seance_tennis> seanceTennisIndividu(Individu individu) {
        ArrayList<Seance_tennis> listTennis = listTennis();
        ArrayList<Seance_tennis> seanceIndividu = new ArrayList<Seance_tennis>();

        for(int i = 0; i<listTennis.size(); i++) {
            if(listTennis.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listTennis.get(i));
            }
        }

        return seanceIndividu;
    }


}
