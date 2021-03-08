package manager;

import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.*;

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

    public static void updateCyclisme(Seance_cyclisme seance, float poids, String objectifSeance, String niv_activite_physique) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        int nrj = CyclismeManager.depenseNRJ(AuthentificationManager.personne, niv_activite_physique);
        int besoin = CyclismeManager.besoinProteine((int)poids);

        seance.setPoids(poids);
        seance.setObjectif_seance(objectifSeance);
        seance.setNiveau_activite_physique(niv_activite_physique);
        seance.setBesoin_proteine(besoin);
        seance.setDepense_energetique(nrj);

        session.update(seance);
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

        if(niveau.equals("Sedentaire")) {
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

    public static ArrayList<Integer> proteine(Individu individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> proteine = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme sc where sc.individu=:individu");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme sc = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            proteine.add(sc.getBesoin_proteine());
        }
        readTransaction.commit();
        return proteine;
    }

    //Fonction qui renvoie tous les individus dans la table
    public static ArrayList<Individu> listIndividuCyclisme(Individu individu) {
        ArrayList<Individu> individus = new ArrayList<Individu>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme sc where sc.individu not in (SELECT sc.individu from Seance_cyclisme sc where sc.individu=:individu)");
        readQuery.setString("individu", individu.getId_individu());

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme sc = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            individus.add(sc.getIndividu());
        }

        Set set = new HashSet() ;
        set.addAll(individus) ;
        individus = new ArrayList(set) ;

        readTransaction.commit();

        return individus;
    }

    public static int besoinProteine(int poids) {
        int besoin = (int)(poids*1.2);
        return besoin;
    }

    public static ArrayList<Seance_cyclisme> listCyclisme() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Seance_cyclisme> listCyclisme = new ArrayList<Seance_cyclisme>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Seance_cyclisme seanceCyclisme");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Seance_cyclisme seanceCyclisme = (Seance_cyclisme) iterator.next();
            //System.out.println(sc.toString());
            listCyclisme.add(seanceCyclisme);
        }
        readTransaction.commit();
        return listCyclisme;
    }

    public static ArrayList<Seance_cyclisme> seanceCyclismeIndividu(Individu individu) {
        ArrayList<Seance_cyclisme> listCyclisme = listCyclisme();
        ArrayList<Seance_cyclisme> seanceIndividu = new ArrayList<Seance_cyclisme>();

        for(int i = 0; i<listCyclisme.size(); i++) {
            if(listCyclisme.get(i).getIndividu().getId_individu().equals(individu.getId_individu())) {
                seanceIndividu.add(listCyclisme.get(i));
            }
        }

        return seanceIndividu;
    }

}
