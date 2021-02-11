package manager;

import model.Individu;
import model.Seance_course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;

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



    public static boolean existSeanceCourse(Session session, String id_seance_course) {
        Boolean present = false;
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_course sc where sc.id_seance_course=:id_seance_course");
        readQuery.setString("id_seance_course", id_seance_course);

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_course sc = (Seance_course) iterator.next();
            System.out.println(sc.toString());
            present = true;
        }

        if(!present) {
            System.out.println("L'individu n'a jamais pratiqué ce sport");
        }
        readTransaction.commit();

        return present;
    }

    public static long nbSeanceCourse(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

       Query query = session.createQuery("select count(*) from Seance_course sc where sc.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }



    public static void main (String []args){

        //Seance_course sc = new Seance_course();
        //System.out.println("id seance course :" + sc.getId_seance_course() + "\ndistance :" + sc.getDistance() + "\ntemps :" + sc.getTemps() + "\nnb_pas :" + sc.getNb_pas() + "\ndate :" + sc.getDate());
        Session session = HibernateUtil.getSession();


        existSeanceCourse(session, "atarina_5");
        nbSeanceCourse(AuthentificationManager.personne);
        session.close();
    }





}
