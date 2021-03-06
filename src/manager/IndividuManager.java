package manager;

import model.Demande;
import model.Individu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IndividuManager {

    public IndividuManager() { }

    public void ajouterIndividu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo, String frequence_jeu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Individu i = new Individu();
        i.setId_individu(id_individu);
        i.setNom(nom);
        i.setPrenom(prenom);
        i.setMdp(mdp);
        i.setSexe(sexe);
        i.setAge(age);
        i.setPoids(poids);
        i.setTaille(taille);
        i.setElo(elo);
        i.setFrequence_jeu(frequence_jeu);
        session.save(i);
        session.getTransaction().commit();
       // session.close();
    }

    public void supprimerIndividu(String id_individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Individu i = (Individu) session.load(Individu.class,id_individu);
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

    //Retourne dans une arraylist la table complete Demande
    public static ArrayList<Individu> listIndividu() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Individu> listIndividu = new ArrayList<Individu>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Individu i");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Individu individu = (Individu) iterator.next();
            //System.out.println(sc.toString());
            listIndividu.add(individu);
        }
        readTransaction.commit();
        return listIndividu;
    }

    public static Individu rechercheIndividuParId(String id) {
        Individu individu = new Individu();
        ArrayList<Individu> listIndividu = listIndividu();
        for(int i = 0; i<listIndividu.size(); i++) {
            if(listIndividu.get(i).getId_individu().equals(id)) {
                individu = listIndividu.get(i);
            }
        }
        return individu;
    }

    public static boolean existIndividu(String id) {
        ArrayList<Individu> individus = listIndividu();
        for(int i = 0; i<individus.size(); i++) {
            if(individus.get(i).getId_individu().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
