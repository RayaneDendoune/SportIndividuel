package manager;

import model.Individu;
import model.Seance_natation;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;

public class NatationManager {

    public void ajouterNatation(String id_seance_natation, int nb_longueur, Time temps_total, String type_nage, int calorie_perdu, Time temps_moy_longueur, Date date, Individu individu) {
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
}
