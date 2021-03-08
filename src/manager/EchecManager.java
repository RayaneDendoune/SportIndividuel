package manager;

import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Time;
import java.util.*;

public class EchecManager {

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


    public static long nbPartieEchec(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Partie_echec pe where pe.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

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

    public static void updateValue(Individu individu, int newElo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        individu.setElo(newElo);
        session.update(individu);
        session.getTransaction().commit();
    }

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

    //Fonction qui renvoie tous les individus dans la table
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
