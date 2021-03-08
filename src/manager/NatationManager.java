package manager;

import gui.Authentification;
import model.Individu;
import model.Seance_course;
import model.Seance_natation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class NatationManager {

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

    public static long nbSeanceCourse(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_natation sn where sn.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    public static String idSeance(Individu individu) {
        int nbOcc = ((int)nbSeanceCourse(individu)) + 1;
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

    public static float tempsMoyLongueur(int nbLongueur, int min) {
        int sec = min*60;
        float tempsMoy = sec/nbLongueur; //Temps en secondes

        return tempsMoy;
    }

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

    //Fonction qui renvoie tous les individus dans la table
    public static ArrayList<Individu> listIndividuNatation(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        //Query readQuery = session.createQuery("from Seance_natation");
        Query readQuery = session.createQuery("from Seance_natation sn where sn.individu not in (SELECT sn.individu from Seance_natation sn where sn.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            //System.out.println(sc.toString());
            individus.add(sn.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }

    public static ArrayList<Seance_natation> listNatation() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_natation> listNatation = new ArrayList<Seance_natation>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_natation sn");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_natation sn = (Seance_natation) iterator.next();
            //System.out.println(sc.toString());
            listNatation.add(sn);
        }
        readTransaction.commit();
        return listNatation;
    }

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
