package manager;

import gui.Authentification;
import model.Individu;
import model.Seance_natation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * \file NatationManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le sport Natation
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au sport Natation.
 *
 */
public class NatationManager {

    /**
     * \fn void ajouterNatation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, Individu individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Seance_natation dans la base de données grâce aux données entrées en paramètres.
     * \param [in] id_seance_natation Clé primaire de la table Seance_natation (Type String)
     * \param [in] nb_longueur Nombre de longueur effectué durant la séance (Type Integer)
     * \param [in] temps_total Temps total passé durant la séance (Type Time)
     * \param [in] type_nage Type de nage effectué par l'individu durant sa séance (Type String)
     * \param [in] calorie_perdu Nombre de calorie perdues par l'individu pendant sa séance (Type Integer)
     * \param [in] temps_moy_longueur Temps moyen par longueur fait par l'individu durant sa séance (Type Time)
     * \param [in] date Date où la séance à été effectuée (Type Date)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     *
     */
    public static void ajouterNatation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Seance_natation seance_natation = new Seance_natation();
        seance_natation.setId_seance_natation(id_seance_natation);
        seance_natation.setNb_longueur(nb_longueur);
        seance_natation.setTemps_total(temps_total);
        seance_natation.setType_nage(type_nage);
        seance_natation.setCalorie_perdu(calorie_perdu);
        seance_natation.setTemps_moy_longueur(temps_moy_longueur);
        seance_natation.setDate(date);
        seance_natation.setIndividu(individu);

        session.save(seance_natation);
        session.getTransaction().commit();
    }

    /**
     * \fn void updateNatation(Seance_natation seance, int nbLongueur, Time temps, String typeNage)
     * \brief Fonction qui met à jour la table Seance_natation et refait de nouveaux calculs grâce aux données entrées en paramètres.
     * \param [in] seance Seance de Natation que l'on souhaite modifier (Type Seance_natation)
     * \param [in] nbLongueur Nombre de longueur effectué durant la séance (Type Integer)
     * \param [in] temps Temps total passé durant la séance (Type Time)
     * \param [in] typeNage Type de nage effectué par l'individu durant sa séance (Type String)
     */
    public static void updateNatation(Seance_natation seance, int nbLongueur, Time temps, String typeNage) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        int timeH = temps.getHours()*60;
        int time = timeH + temps.getMinutes();
        float tempsMoy = NatationManager.tempsMoyLongueur(nbLongueur, time);
        int heure = (int) tempsMoy / 3600;
        int tempsT = (int) tempsMoy - (heure * 3600);
        int minutes = tempsT / 60;
        int secondes = tempsT % 60;
        final Time tempsLongueur = new Time(heure, minutes, secondes);

        seance.setNb_longueur(nbLongueur);
        seance.setTemps_total(temps);
        seance.setTemps_moy_longueur(tempsLongueur);
        seance.setCalorie_perdu((int) calories(AuthentificationManager.personne, typeNage, time));
        seance.setType_nage(typeNage);

        session.update(seance);
        session.getTransaction().commit();
    }

    /**
     * \fn long nbSeanceNatation(Individu individu)
     * \brief Fonction qui retourne le nombre de séances de Natation que l'individu passé en paramètre a fait.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un Long avec le nombre de séance de Natation que l'utilisateur a effectué.
     */
    public static long nbSeanceNatation(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_natation sn where sn.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    /**
     * \fn String idSeance(Individu individu)
     * \brief Fonction qui retourne un nouveau identifiant de séance grâce au nombre de séances de Natation que l'utilisateur a effectué incrémenter de 1.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec un nouvel identifiant de séance de Natation pour l'individu passé en paramètres
     */
    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbSeanceNatation(individu)) + 1;
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
     * \fn float tempsMoyLongueur(int nbLongueur, int min)
     * \brief Fonction qui calcule le temps moyen par longueur en fonction du nombre de longueurs et du temps en minute passé en paramètres
     * \param [in] nbLongueur Nombre de longueur effectué durant la séance (Type Integer)
     * \param [in] min Temps en minute effectué par l'individu durant sa séance (Type Integer)
     * \return Retourne un Float avec la vitesse moyenne qui a été calculée.
     */
    public static float tempsMoyLongueur(int nbLongueur, int min) {
        int sec = min*60;
        float tempsMoy = sec/nbLongueur; //Temps en secondes

        return tempsMoy;
    }

    /**
     * \fn float calories(Individu individu, String typeNage, int temps)
     * \brief Fonction qui calcule le nombre de calories brulées lors de la séance grâce au type de nage et le temps mis durant la séance passés en paramètres
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \param [in] type_nage Type de nage effectuée par l'individu durant sa séance (Type String)
     * \param [in] temps Temps total passé durant la séance (Type Time)
     * \return Retourne un Float avec le nombre de calories brulées qui a été calculé.
     */
    public static float calories(Individu individu, String typeNage, int temps) {
        int met = 0;
        if(typeNage.equals("Brasse")) {
            met = 10;
        }
        else if(typeNage.equals("Dos-Crawle")|| typeNage.equals("Crawl")) {
            met = 8;
        }
        else if(typeNage.equals("Papillon") ) {
            met = 11;
        }

        float calorie = (float) ((met * 3.5 * individu.getPoids())/200);

        float totalCalories = calorie * temps;
        return totalCalories; //Résultat en Kcal et pas en calorie + TypeNage ne sert a riiien
    }

    /**
     * \fn ArrayList<Integer> nbCaloriesPerdues(Individu individu)
     * \brief Fonction qui retourne le nombre de calories perdues par l'individu passé en paramètres pour chaque séance de la table Seance_natation depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec le nombre de calories perdues de l'individu à chaque séance.
     */
    public static ArrayList<Integer> nbCaloriesPerdues(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> nbCalorie = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_natation sn where sn.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            //System.out.println(sc.toString());
            nbCalorie.add(sn.getCalorie_perdu());
        }
        readTransaction.commit();
        return nbCalorie;
    }

    /**
     * \fn ArrayList<Time> tpsMoyLongueur(Individu individu)
     * \brief Fonction qui retourne le temps moyen par longueur de l'individu passé en paramètre pour chaque séance de la table Seance_natation depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Time avec le temps moyen par longueur de l'individu à chaque séance.
     */
    public static ArrayList<Time> tpsMoyLongueur(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Time> tempsMoyenLong = new ArrayList<Time>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_natation sn where sn.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            //System.out.println(sc.toString());
            tempsMoyenLong.add(sn.getTemps_moy_longueur());
        }
        readTransaction.commit();
        return tempsMoyenLong;
    }

    /**
     * \fn  ArrayList<Individu> listIndividuNatation(Individu individu)
     * \brief Fonction qui renvoie tous les individus de la table Seance_natation excepté l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList d'Individu qui sont dans la table Seance_natation excepté l'individu passé en paramètre
     */
    //Fonction qui renvoie tous les individus dans la table excepté l'individu passé en paramètre
    public static ArrayList<Individu> listIndividuNatation(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_natation sn where sn.individu not in (SELECT sn.individu from Seance_natation sn where sn.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            individus.add(sn.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }

    /**
     * \fn ArrayList<Seance_natation> listNatation()
     * \brief Fonction qui parcours la table Seance_natation et qui retourne toutes les données de cette table sous forme d'ArrayList de Seance_natation
     * \return ArrayList de Seance_natation correspondant à toutes les données compris dans la table Seance_natation dans la base de données
     */
    public static ArrayList<Seance_natation> listNatation() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_natation> listNatation = new ArrayList<Seance_natation>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_natation sn");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            listNatation.add(sn);
        }
        readTransaction.commit();
        return listNatation;
    }

    /**
     * \fn ArrayList<Seance_natation> seanceNatationIndividu(Individu individu)
     * \brief Fonction qui filtre la table Seance_natation afin de retourner uniquement les Seance_natation de l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Seance_natation avec uniquement les séances de l'individu passé en paramètre.
     */
    public static ArrayList<Seance_natation> seanceNatationIndividu(Individu individu) {
        ArrayList<Seance_natation> listNatation = listNatation();
        ArrayList<Seance_natation> seanceIndividu = new ArrayList<Seance_natation>();

        for(int i = 0; i<listNatation.size(); i++) {
            if(listNatation.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listNatation.get(i));
            }
        }

        return seanceIndividu;
    }
}
