package manager;

import model.Individu;
import model.Seance_course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseManager {
    public static Seance_course seanceC;
    public static void ajouterCourse(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu){
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



    public static String existSeanceCourse(Session session, String id_seance_course) {
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

        return "";
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

    public static float vitesseMoyenne(float distanceKM, int min) {
        float distanceM = distanceKM*1000;
        int sec = min/60;
        float vitesse = distanceKM/sec;

        return vitesse;
    }

    public static int nbPas(float distance){
        int nombrePas = (int)((distance*100000)/65);
        return nombrePas;
    }

    /*public static float VitesseMoy(Session session,Seance_course seance_course){
        Transaction readTransaction = session.beginTransaction();
        float vitesse_moyenne= seance_course.getVitesse_moy();
        System.out.println("la distance est "+ vitesse_moyenne);
        readTransaction.commit();
        return vitesse_moyenne;

    }*/

    public static ArrayList<Float> VitesseMoy(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Float> vitesse = new ArrayList<Float>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_course sc where sc.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_course sc = (Seance_course) iterator.next();
            //System.out.println(sc.toString());
            vitesse.add(sc.getVitesse_moy());
        }
        readTransaction.commit();
        return vitesse;
    }




    public static void main (String []args){
        /*Session session = HibernateUtil.getSession();
        Seance_course sc = new Seance_course("Katarina_5");
        CourseManager.seanceC.setId_seance_course("Katarina_5");
        DistanceCourse(session, CourseManager.seanceC);


        System.out.println("id seance course :" + sc.getId_seance_course() + "\ndistance :" + sc.getDistance() + "\ntemps :" + sc.getTemps() + "\nnb_pas :" + sc.getNb_pas() + "\ndate :" + sc.getDate());
        Session session = HibernateUtil.getSession();
        existSeanceCourse(session, "atarina_5");
        nbSeanceCourse(AuthentificationManager.personne);
        session.close();

         */
    }





}
