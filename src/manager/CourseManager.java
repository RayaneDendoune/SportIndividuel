package manager;

import model.Individu;
import model.Seance_course;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;

public class CourseManager {

    public void ajouterCourse(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Seance_course seance_course = new Seance_course();
        seance_course.setId_seance_course(id_seance_course);
        seance_course.setDistance(distance);
        seance_course.setTemps(temps);
        seance_course.setVitesse_moy(vitesse_moy);
        seance_course.setNb_pas(nb_pas);
        seance_course.setDate(date);
        seance_course.setIndividu(individu);

        session.save(seance_course);
        session.getTransaction().commit();
    }



}
