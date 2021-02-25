package manager;

import model.Demande;
import model.Individu;
import model.Seance_course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DemandeManager {
    public static Demande demande;

    public static void ajouterDemande(int nb, String id_destinataire, String id_expediteur){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Demande demande = new Demande();
        demande.setNo_demande(nb);
        demande.setId_destinataire(id_destinataire);
        demande.setId_expediteur(id_expediteur);

        session.save(demande);
        session.getTransaction().commit();
    }

    //Retourne dans une arraylist la table complete Demande
    public static ArrayList<Demande> listDemande() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Demande> listDemande = new ArrayList<Demande>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Demande d");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Demande demande = (Demande) iterator.next();
            //System.out.println(sc.toString());
            listDemande.add(demande);
        }
        readTransaction.commit();
        return listDemande;
    }

    //Ajouter une demande avec en plus la clé primaire qui s'incremente
    public static void ajout(String id_destinataire, String id_expediteur) {
        ArrayList<Demande> listDemande = listDemande();
        int nb = 1;

        if(!listDemande.isEmpty()) {
            for(int i = 0; i<listDemande.size(); i++) {
                if(i == (listDemande.size()-1)) {
                    nb = listDemande.get(i).getNo_demande() + 1;
                }
            }
        }
        else {
            nb = 1;
        }

        ajouterDemande(nb, id_destinataire, id_expediteur);

    }

    //Retourne une arraylist de string avec le nom des individu qui ont recu les demandes envoyé par individu passé en paramètre
    public static ArrayList<String> idDemandeEnvoyer(Individu individu) {
        ArrayList<String> destinataire = new ArrayList<String>();
        ArrayList<Demande> listDemande = listDemande();

        for(int i = 0; i<listDemande.size(); i++) {
            if(listDemande.get(i).getId_expediteur().equals(individu.getId_individu())) {
                destinataire.add(listDemande.get(i).getId_destinataire());
            }
        }

        return destinataire;
    }

    //Retrouve dans la table Demande la ligne a supprimer
    public static Demande suppDemande(String id_destinataire, String id_expediteur) {
        ArrayList<Demande> listDemande = DemandeManager.listDemande();
        Demande demande = new Demande();
        for(int i = 0; i<listDemande.size(); i++) {
            if(listDemande.get(i).getId_destinataire().equals(id_destinataire) && listDemande.get(i).getId_expediteur().equals(id_expediteur)) {
                demande = listDemande.get(i);
            }
        }
        return demande;
    }

    //Supprime une demande de la table Demande
    public static void deleteValue(Demande demande) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(demande);
        session.getTransaction().commit();
    }
}
