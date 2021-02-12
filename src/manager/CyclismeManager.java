package manager;

import model.Individu;
import model.Seance_course;
import model.Seance_cyclisme;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CyclismeManager {

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

    public static long nbSeanceCyclisme(Individu individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction readTransaction = session.beginTransaction();

        Query query = session.createQuery("select count(*) from Seance_cyclisme sc where sc.individu=:individu");
        query.setString("individu", individu.getId_individu());
        Long count = (Long)query.uniqueResult();

        readTransaction.commit();
        return count;
    }

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

        if(niveau.equals("Sédentaire")) {
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
}
