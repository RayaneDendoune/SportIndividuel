package manager;

import model.Demande;
import model.Individu;
import model.Seance_course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

/**
 * \file CourseManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le sport Course
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au sport Course.
 *
 */
public class CourseManager {
    public static Seance_course seanceC;

    /**
     * \fn void ajouterCourse(String id_seance_course, float distance, Time temps, float vitesse_moy, int nb_pas, Date date, Individu individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Seance_course dans la base de donnée grâce aux données entrées en paramètres.
     *
     * \param [in] id_seance_course Clé primaire de la table Seance_course (Type String)
     * \param [in] distance Distance parcourue durant la séance (Type Float)
     * \param [in] temps Temps total de la séance effectué (Type Time)
     * \param [in] vitesse_moy Vitesse Moyenne que l'individu avait durant la séance (Type Float)
     * \param [in] nb_pas Nombre de pas moyen effectué par l'utilisateur durant la séance (Type Integer)
     * \param [in] date Date où la séance à été effectuée (Type Date)
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     */
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

    /**
     * \fn void updateCourse(Seance_course seance, float distance, Time temps)
     * \brief Fonction qui met à jour la table Seance_course et refait de nouveaux calculs grâce aux données entrées en paramètres.
     * \param [in] seance Seance de Course que l'on souhaite modifier (Type Seance_course)
     * \param [in] distance Distance parcourue durant la séance (Type Float)
     * \param [in] temps Temps total de la séance effectué (Type Time)
     */
    public static void updateCourse(Seance_course seance, float distance, Time temps) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        int timeH = temps.getHours()*60;
        int time = timeH + temps.getMinutes();

        seance.setDistance(distance);
        seance.setTemps(temps);
        seance.setNb_pas(nbPas(distance));
        seance.setVitesse_moy(vitesseMoyenne(distance, time));

        session.update(seance);
        session.getTransaction().commit();
    }

    /**
     * \fn String existSeanceCourse(Session session, String id_seance_course)
     * \brief Fonction qui retourne si un individu à déjà pratiquer ce sport
     * \param [in] session Session pour se connecté à la base de données (Type Session)
     * \param [in] id_seance_course Clé primaire de la table Seance_course (Type String)
     * \return Retourne un message d'erreur si l'individu n'a jamais pratiquer ce sport
     */
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

    /**
     * \fn long nbSeanceCourse(Individu individu)
     * \brief Fonction qui retourne le nombre de séance de Course que individu passé en paramètre a fait.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un Long avec le nombre de séance de Course que l'utilisateur a effectué.
     */
    public static long nbSeanceCourse(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

       Query query = session.createQuery("select count(*) from Seance_course sc where sc.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

    /**
     * \fn String idSeance(Individu individu)
     * \brief Fonction qui retourne un nouveau identifiant de séance grâce au nombre de séance de Course que l'utilisateur a effectué incrémenter de 1.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne un String avec un nouvel identifiant de séance de Course pour l'individu passé en paramètre
     */
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

    /**
     * \fn float vitesseMoyenne(float distanceKM, int min)
     * \brief Fonction qui calcule la vitesse moyenne en fonction de la distance en km et le temps en minute passé en paramètre
     * \param [in] distanceKM Distance effectué par l'individu durant sa séance (Type Float)
     * \param [in] min Temps en minute effectué par l'individu durant sa séance (Type Integer)
     * \return Retourne un Float avec la vitesse moyenne qui a été calculée.
     */
    public static float vitesseMoyenne(float distanceKM, int min) {
        float distanceM = distanceKM*1000;
        int sec = min*60;
        float vitesse = distanceM/sec;

        return vitesse;
    }

    /**
     * \fn int nbPas(float distance)
     * \brief Fonction qui calcule le nombre de pas moyen en fonction de la distance passé en paramètre
     * \param [in] distance Distance effectué par l'individu durant sa séance (Type Float)
     * \return Retourne un Integer avec le nombre de pas moyen qui a été calculé.
     */
    public static int nbPas(float distance){
        int nombrePas = (int)((distance*100000)/65);
        return nombrePas;
    }

    /**
     * \fn ArrayList<Float> VitesseMoy(Individu individu)
     * \brief Fonction qui retourne les vitesses moyennes d'un individu pour chaque séance de la table Seance_course depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)!
     * \return Retourne une ArrayList de Float avec les vitesses moyennes de l'individu à chaque séance.
     */
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

    /**
     * \fn ArrayList<Integer> nombrePas(Individu individu)
     * \brief Fonction qui retourne le nombre de pas de l'individu passé en paramètre pour chaque séance de la table Seance_course depuis la base de données.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Integer avec le nombre de pas de l'individu à chaque séance.
     */
    public static ArrayList<Integer> nombrePas(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> nbPas = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_course sc where sc.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_course sc = (Seance_course) iterator.next();
            //System.out.println(sc.toString());
            nbPas.add(sc.getNb_pas());
        }
        readTransaction.commit();
        return nbPas;
    }

    /**
     * \fn ArrayList<Individu> listIndividuCourse(Individu individu)
     * \brief Fonction qui renvoie tous les individus de la table Seance_course excepté l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList d'Individu qui sont dans la table Seance_course excepté l'individu passé en paramètre
     */
    //Fonction qui renvoie tous les individus dans la table excepté l'individu passé en paramètre
    public static ArrayList<Individu> listIndividuCourse(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_course sc where sc.individu not in (SELECT sc.individu from Seance_course sc where sc.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_course sc = (Seance_course) iterator.next();
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
     * \fn ArrayList<Seance_course> listCourse()
     * \brief Fonction qui parcours la table Seance_course et qui retourne toutes les données de cette table sous forme d'ArrayList de Seance_course
     * \return ArrayList de Seance_course correspondant à toutes les données compris dans la table Seance_course dans la base de données
     */
    public static ArrayList<Seance_course> listCourse() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_course> listCourse = new ArrayList<Seance_course>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_course sc");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_course sc = (Seance_course) iterator.next();
            //System.out.println(sc.toString());
            listCourse.add(sc);
        }
        readTransaction.commit();
        return listCourse;
    }

    /**
     * \fn ArrayList<Seance_course> seanceCourseIndividu(Individu individu)
     * \brief Fonction qui filtre la table Seance_Course afin de retourner uniquement les Seance_course de l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return Retourne une ArrayList de Seance_Course avec uniquement les séances de l'individu passé en paramètre.
     */
    public static ArrayList<Seance_course> seanceCourseIndividu(Individu individu) {
        ArrayList<Seance_course> listCourse = listCourse();
        ArrayList<Seance_course> seanceIndividu = new ArrayList<Seance_course>();

        for(int i = 0; i<listCourse.size(); i++) {
            if(listCourse.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listCourse.get(i));
            }
        }

        return seanceIndividu;
    }
}
