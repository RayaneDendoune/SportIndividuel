package manager;

import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Time;
import java.util.*;

/**
 * \file EchecManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le sport échecs
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au sport Echecs.
 *
 */
public class EchecManager {

    /**
     * \fn void ajouterEchec(String id_partie_echec, int elo_adversaire, int futur_elo, Time duree, String niveau_competence_mentale, char issue_partie, int niveau_concentration, Individu individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Partie_echec dans la base de donnée grâce aux données entrées en paramètres.
     *
     * \param [in] id_partie_echec Clé primaire de la table Partie_echec (Type String)
     * \param [in] elo_adversaire Elo de l'adversaire (Type Integer)
     * \param [in] futur_elo Future Elo de l'individu après la partie en cours (Type Integer)
     * \param [in] duree Duree totale de la partie (Type Time)
     * \param [in] niveau_competence_mentale Niveau de Compétence Mentale (Type String)
     * \param [in] issue_partie Issue de la partie (Type Character)
     * \param [in] niveau_concentration Niveau de Concentration (Type Integer)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
    public static void ajouterEchec(String id_partie_echec, int elo_adversaire, int futur_elo, Time duree, String niveau_competence_mentale, char issue_partie, int niveau_concentration, Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Partie_echec partie_echec = new Partie_echec();
        partie_echec.setId_partie_echec(id_partie_echec);
        partie_echec.setElo_adversaire(elo_adversaire);
        partie_echec.setFutur_elo(futur_elo);
        partie_echec.setDuree(duree);
        partie_echec.setNiveau_competence_mentale(niveau_competence_mentale);
        partie_echec.setIssue_partie(issue_partie);
        partie_echec.setNiveau_concentration(niveau_concentration);
        partie_echec.setIndividu(individu);

        session.save(partie_echec);
        session.getTransaction().commit();
    }

    /**
     * \fn void updateEchec(Partie_echec partie, int eloAdversaire, Time temps, char issue, int newElo)
     * \brief Fonction qui met à jour la table Partie_echec et refait de nouveaux calculs grâce aux données entrées en paramètres.
     * \param [in] partie Partie d'échecs que l'on souhaite modifier (Type Partie_echec)
     * \param [in] elo_adversaire Elo de l'adversaire (Type Integer)
     * \param [in] temps Temps total de la partie effectué (Type Time)
     * \param [in] issue Issue de la partie (Type Character)
     * \param [in] newElo Nouveau Elo après la modification de tous les paramètre (Type Integer)
     */
    public static void updateEchec(Partie_echec partie, int eloAdversaire, Time temps, char issue, int newElo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        partie.setFutur_elo(newElo);
        partie.setElo_adversaire(eloAdversaire);
        partie.setDuree(temps);
        partie.setIssue_partie(issue);
        partie.setNiveau_competence_mentale(competenceMentale(AuthentificationManager.personne));
        partie.setNiveau_concentration(niveauConcentration(AuthentificationManager.personne,issue,competenceMentale(AuthentificationManager.personne)));



        session.update(partie);
        session.getTransaction().commit();
    }


    /**
     * \fn long nbPartieEchec(Individu individu)
     * \brief Fonction qui retourne le nombre de partie d'échec que individu passé en paramètre a fait.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un Long avec le nombre de partie d'échec que l'utilisateur a effectué.
     */
    public static long nbPartieEchec(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Partie_echec pe where pe.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    /**
     * \fn String idSeance(Individu individu)
     * \brief Fonction qui retourne un nouveau identifiant de séance grâce au nombre de partie d'échec que l'utilisateur a effectué incrémenter de 1.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec un nouvel identifiant de partie d'échecs pour l'individu passé en paramètre
     */
    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbPartieEchec(individu)) + 1;
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
     * \fn void updateValue(Individu individu, int newElo)
     * \brief Fonction qui met à jour la table Individu et modifie l'elo de l'individu passé en paramètre.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \param [in] newElo Nouveau Elo par lequel nous voulons modifier l'elo actuel (Type Integer)
     */
    public static void updateValue(Individu individu, int newElo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        individu.setElo(newElo);
        session.update(individu);
        session.getTransaction().commit();
    }

    /**
     * \fn int newElo(Individu individu, int eloAdv, String issue)
     * \brief Fonction qui calcul le nouvel elo de l'individu grâce à l'elo de son adversaire et l'issue de la partie
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \param [in] eloAdv Elo de l'adversaire (Type Integer)
     * \param [in] issue Issue de la partie (Type Character)
     * \return Retourne un Integer avec le nouvel elo de l'individu après calcul
     */
    public static int newElo(Individu individu, int eloAdv, String issue) {
        int difference = individu.getElo() - eloAdv;
        float proba = (float) (1/(1+Math.pow(10, ((-difference)/400))));

        int nbJeu = (int)nbPartieEchec(individu);
        int k = 0;
        float w = 0;

        if(eloAdv<=2400) {
            k=20;
        }
        else if(nbJeu<30) {
            k=40;
        }
        else if(eloAdv>2400) {
            k=10;
        }

        if(issue.equals("Victoire")) {
            w=1;
        }
        else if(issue.equals("Nul")) {
            w=0.5f;
        }
        else if(issue.equals("Défaite")) {
            w=0;
        }

        int futurElo = (int) (individu.getElo() + k * (w - proba));
        return futurElo;
    }

    /**
     * \fn char issue(String issue)
     * \brief Fonction qui retourne un Character en fonction de l'issue de la partie qui lui est rentré en paramètre
     * \param [in] issue Issue de la partie (Type String)
     * \return Retourne un Character en fonction de l'issue de la partie
     */
    public static char issue(String issue) {
        if(issue.equals("Victoire")) {
            return 'V';
        }
        else if(issue.equals("Nul")) {
            return 'N';
        }
        else if(issue.equals("Défaite")) {
            return 'D';
        }
        return ' ';
    }

    /**
     * \fn ArrayList<Integer> elo(Individu individu)
     * \brief Fonction qui retourne l'elo de l'individu passé en paramètre pour chaque séance de la table Partie_echec depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec les elos de l'individu à chaque séance.
     */
    public static ArrayList<Integer> elo(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> elo = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Partie_echec pe where pe.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Partie_echec pe = (Partie_echec) iterator.next();
            //System.out.println(sc.toString());
            elo.add(pe.getFutur_elo());
        }
        readTransaction.commit();
        return elo;
    }

    /**
     * \fn ArrayList<Individu> listIndividuEchec(Individu individu)
     * \brief Fonction qui renvoie tous les individus de la table Partie_echec excepté l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList d'Individu qui sont dans la table Partie_echec excepté l'individu passé en paramètre
     */
    //Fonction qui renvoie tous les individus dans la table excepté l'individu passé en paramètre
    public static ArrayList<Individu> listIndividuEchec(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Partie_echec pe where pe.individu not in (SELECT pe.individu from Partie_echec pe where pe.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Partie_echec pe = (Partie_echec) iterator.next();
            //System.out.println(sc.toString());
            individus.add(pe.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }

    /**
     * \fn ArrayList<Integer> concentration(Individu individu)
     * \brief Fonction qui retourne le niveau de concentration de l'individu passé en paramètre pour chaque partie de la table Partie_echec depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec le nombre de pas de l'individu à chaque séance.
     */
    public static ArrayList<Integer> concentration(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> niveauConcentration = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Partie_echec pe where pe.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Partie_echec pe = (Partie_echec) iterator.next();
            //System.out.println(sc.toString());
            niveauConcentration.add(pe.getNiveau_concentration());
        }
        readTransaction.commit();
        return niveauConcentration;
    }

    /**
     * \fn String competenceMentale(Individu individu)
     * \brief Fonction qui renvoie la competence mentale de l'individu
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec la competence mentale de l'individu
     */
    public static String competenceMentale(Individu individu) {
        int elo = individu.getElo();
        String text = "";
        if(elo < 1500) {
            text += "novice";
        }
        else if(elo>=1500 && elo <2300) {
            text += "intermédiaire";
        }
        else if(elo>=2300 && elo <2600) {
            text += "confirmé";
        }
        else if(elo>=2600 && elo < 3200) {
            text += "expert";
        }

        return text;
    }

    /**
     * \fn int niveauConcentration(Individu individu, char issue, String competence)
     * \brief Fonction qui calcule le niveau de concentration de l'individu en fonction de l'issue de la partie et de son niveau de compétence mental passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \param [in] competence Niveau de Compétence Mentale (Type String)
     * \return Retourne un Integer avec le niveau de concentration de l'individu durant la séance.
     */
    public static int niveauConcentration(Individu individu, char issue, String competence) {
        float issueMatch = 0;
        int niveauConcentration = 0;
        int competenceMentale = 0;

        if(issue == 'V') {
            issueMatch = 1;
        }
        else if(issue == 'N') {
            issueMatch = 0.75f;
        }
        else if(issue == 'D') {
            issueMatch = 0.5f;
        }

        if(competence.equals("novice")) {
            competenceMentale = 15;
        }
        else if(competence.equals("intermédiaire")) {
            competenceMentale = 23;
        }
        else if(competence.equals("confirmé")) {
            competenceMentale = 26;
        }
        else if(competence.equals("expert")) {
            competenceMentale = 32;
        }

        niveauConcentration = (int) ((individu.getElo()/competenceMentale)*issueMatch);

        return niveauConcentration;
    }

    /**
     * \fn ArrayList<Partie_echec> listEchec()
     * \brief Fonction qui parcours la table Partie_echec et qui retourne toutes les données de cette table sous forme d'ArrayList de Partie_echec
     * \return ArrayList de Partie_echec correspondant à toutes les données compris dans la table Partie_echec dans la base de données
     */
    public static ArrayList<Partie_echec> listEchec() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Partie_echec> listEchec = new ArrayList<Partie_echec>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Partie_echec partieEchec");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Partie_echec partieEchec = (Partie_echec) iterator.next();
            //System.out.println(sc.toString());
            listEchec.add(partieEchec);
        }
        readTransaction.commit();
        return listEchec;
    }

    /**
     * \fn ArrayList<Partie_echec> seanceEchecIndividu(Individu individu)
     * \brief Fonction qui filtre la table Partie_echec afin de retourner uniquement les Partie_echec de l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Partie_echec avec uniquement les parties de l'individu passé en paramètre.
     */
    public static ArrayList<Partie_echec> seanceEchecIndividu(Individu individu) {
        ArrayList<Partie_echec> listEchec = listEchec();
        ArrayList<Partie_echec> seanceIndividu = new ArrayList<Partie_echec>();

        for(int i = 0; i<listEchec.size(); i++) {
            if(listEchec.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listEchec.get(i));
            }
        }

        return seanceIndividu;
    }

}
