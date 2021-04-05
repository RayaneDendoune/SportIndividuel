package manager;

import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.*;

/**
 * \file CyclismeManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le sport Cyclisme
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au sport Cyclisme.
 *
 */
public class CyclismeManager {

    /**
     * \fn void ajouterCyclisme(String id_seance_cyclisme, String niveau_activite_physique, float poids, String objectif_seance, int depense_energetique, int besoin_proteine, Individu individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Seance_cyclisme dans la base de donnée grâce aux données entrées en paramètres.
     *
     * \param [in] id_seance_cyclisme Clé primaire de la table Seance_cyclisme (Type String)
     * \param [in] niveau_activite_physique Niveau d'activité physique durant la séance (type String)
     * \param [in] poids Poids actuel de l'individu (Type Float)
     * \param [in] objectif_seance Objectif de la séance de l'individu (type String)
     * \param [in] depense_energetique Dépense énergétique faite par l'individu durant sa séance (type Integer)
     * \param [in] besoin_proteine Besoin en protéïne de l'individu après sa séance (type Integer)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
    public static void ajouterCyclisme(String id_seance_cyclisme, String niveau_activite_physique, float poids, String objectif_seance, int depense_energetique, int besoin_proteine, Individu individu){
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

    /**
     * \fn void updateCyclisme(Seance_cyclisme seance, float poids, String objectifSeance, String niv_activite_physique)
     * \brief Fonction qui met à jour la table Seance_cyclisme et refait de nouveaux calculs grâce aux données entrées en paramètres.
     * \param [in] seance Seance de Cyclisme que l'on souhaite modifier (Type Seance_cyclisme)
     * \param [in] poids Poids actuel de l'individu (Type Float)
     * \param [in] objectifSeance Objectif de la séance de l'individu (Type String)
     * \param [in] niv_activite_physique Niveau d'activité physique durant la séance (Type String)
     */
    public static void updateCyclisme(Seance_cyclisme seance, float poids, String objectifSeance, String niv_activite_physique) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        int nrj = CyclismeManager.depenseNRJ(AuthentificationManager.personne, niv_activite_physique);
        int besoin = CyclismeManager.besoinProteine((int)poids);

        seance.setPoids(poids);
        seance.setObjectif_seance(objectifSeance);
        seance.setNiveau_activite_physique(niv_activite_physique);
        seance.setBesoin_proteine(besoin);
        seance.setDepense_energetique(nrj);

        session.update(seance);
        session.getTransaction().commit();
    }

    /**
     * \fn long nbSeanceCyclisme(Individu individu)
     * \brief Fonction qui retourne le nombre de séance de Cyclisme que individu passé en paramètre a fait.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un Long avec le nombre de séance de Cyclisme que l'utilisateur a effectué.
     */
    public static long nbSeanceCyclisme(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_cyclisme sc where sc.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    /**
     * \fn String idSeance(Individu individu)
     * \brief Fonction qui retourne un nouveau identifiant de séance grâce au nombre de séance de Cyclisme que l'utilisateur a effectué incrémenter de 1.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec un nouvel identifiant de séance de Cyclisme pour l'individu passé en paramètre
     */
    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbSeanceCyclisme(individu)) + 1;
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
     * \fn int depenseNRJ(Individu individu, String niveau)
     * \brief Fonction qui calcule les dépenses énergétique de l'individu en fonction des données de l'utilisateur et de son niveau d'activité durant la séance
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \param [in] niveau Niveau d'activité physique durant la séance (Type String)
     * \return Retourne un Integer avec la dépense énergétique qui a été calculé.
     */
    public static int depenseNRJ(Individu individu, String niveau) {
        char sexe = individu.getSexe();
        int age = individu.getAge();
        float taille = individu.getTaille() *100;
        float poids = individu.getPoids();
        float calcul = 0;
        float nv = 0;

        if(sexe=='F') {
            calcul = 230;
        }
        else if(sexe=='M') {
            calcul = 259;
        }
        calcul *= (Math.pow(poids,0.48)) * (Math.pow(taille,0.50) * (Math.pow(age,(-0.13))));

        if(niveau.equals("Sedentaire")) {
            nv = 1.37f;
        }
        else if(niveau.equals("Actif")) {
            nv = 1.55f;
        }
        else if(niveau.equals("Sportif")) {
            nv = 1.80f;
        }

        calcul *= nv;
        calcul/=10;
        return (int)calcul;
    }

    /**
     * \fn ArrayList<Integer> energie(Individu individu)
     * \brief Fonction qui retourne les dépenses énergétique de l'individu passé en paramètre pour chaque séance de la table Seance_cyclisme depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec les dépenses énergétique de l'individu passé en paramètre à chaque séance.
     */
    public static ArrayList<Integer> energie(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> nrj = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme sc where sc.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme sc = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            nrj.add(sc.getDepense_energetique());
        }
        readTransaction.commit();
        return nrj;
    }

    /**
     * \fn ArrayList<Integer> proteine(Individu individu)
     * \brief Fonction qui retourne les besoins en protéïne de l'individu passé en paramètre pour chaque séance de la table Seance_cyclisme depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec les besoins en protéïne de l'individu passé en paramètre à chaque séance.
     */
    public static ArrayList<Integer> proteine(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> proteine = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme sc where sc.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme sc = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            proteine.add(sc.getBesoin_proteine());
        }
        readTransaction.commit();
        return proteine;
    }

    /**
     * \fn ArrayList<Individu> listIndividuCyclisme(Individu individu)
     * \brief Fonction qui renvoie tous les individus de la table Seance_cyclisme excepté l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList d'Individu qui sont dans la table Seance_cyclisme excepté l'individu passé en paramètre
     */
    //Fonction qui renvoie tous les individus dans la table excepté l'individu passé en paramètre
    public static ArrayList<Individu> listIndividuCyclisme(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme sc where sc.individu not in (SELECT sc.individu from Seance_cyclisme sc where sc.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme sc = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            individus.add(sc.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }

    /**
     * \fn int besoinProteine(int poids)
     * \brief Fonction qui calcule les besoins en protéïnes de l'individu en fonction du poids de l'utilisateur
     * \param [in] poids Poids actuel de l'individu (Type Integer)
     * \return Retourne un Integer avec le besoin en protéïne qui a été calculé.
     */
    public static int besoinProteine(int poids) {
        int besoin = (int)(poids*1.2);
        return besoin;
    }

    /**
     * \fn ArrayList<Seance_cyclisme> listCyclisme()
     * \brief Fonction qui parcours la table Seance_cyclisme et qui retourne toutes les données de cette table sous forme d'ArrayList de Seance_cyclisme
     * \return ArrayList de Seance_cyclisme correspondant à toutes les données compris dans la table Seance_cyclisme dans la base de données
     */
    public static ArrayList<Seance_cyclisme> listCyclisme() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_cyclisme> listCyclisme = new ArrayList<Seance_cyclisme>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme seanceCyclisme");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme seanceCyclisme = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            listCyclisme.add(seanceCyclisme);
        }
        readTransaction.commit();
        return listCyclisme;
    }

    /**
     * \fn ArrayList<Seance_cyclisme> seanceCyclismeIndividu(Individu individu)
     * \brief Fonction qui filtre la table Seance_cyclisme afin de retourner uniquement les Seance_cyclisme de l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Seance_cyclisme avec uniquement les séances de l'individu passé en paramètre.
     */
    public static ArrayList<Seance_cyclisme> seanceCyclismeIndividu(Individu individu) {
        ArrayList<Seance_cyclisme> listCyclisme = listCyclisme();
        ArrayList<Seance_cyclisme> seanceIndividu = new ArrayList<Seance_cyclisme>();

        for(int i = 0; i<listCyclisme.size(); i++) {
            if(listCyclisme.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listCyclisme.get(i));
            }
        }

        return seanceIndividu;
    }

}
