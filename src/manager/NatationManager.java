package manager;

import model.Individu;
import model.Seance_natation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;

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

        String seance = id + "_" + nbOcc;
        return seance;
    }

    public static float tempsMoyLongueur(int nbLongueur, int min) {
        int sec = min*60;
        float tempsMoy = sec/nbLongueur; //Temps en secondes
        //return tempsMoy; //Le temps est en seconde il faudra le mettre en TIME

        /*int heure = (int)tempsMoy/3600;
        int tempsT = (int)tempsMoy - (heure*3600);

        int minutes = tempsT/60;
        int secondes = tempsT%60;*/

        return tempsMoy;
    }

    public static float calories(Individu individu, String typeNage, int temps) {
        int met = 0;
        if(typeNage.equals("Brasse")) {
            met = 10;
        }
        else if(typeNage.equals("Dos-Crawlé")|| typeNage.equals("Crawl")) {
            met = 8;
        }
        else if(typeNage.equals("Papillon") ) {
            met = 11;
        }

        float calorie = (float) ((met * 3.5 * individu.getPoids())/200);

        float totalCalories = calorie * temps;
        return totalCalories; //Résultat en Kcal et pas en calorie + TypeNage ne sert a riiien
    }
}
