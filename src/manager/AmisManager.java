package manager;

import model.Amis;
import model.Demande;
import model.Individu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AmisManager {
    public static Amis amis;

    public static void ajouterAmis(int no_amis, String id_amis, String id_individu){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Amis amis = new Amis();
        amis.setNo_amis(no_amis);
        amis.setId_amis(id_amis);
        amis.setId_individu(id_individu);

        session.save(amis);
        session.getTransaction().commit();
    }

    //Retourne dans une arraylist la table complete Amis
    public static ArrayList<Amis> listAmis() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Amis> listAmis = new ArrayList<Amis>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Amis a");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Amis amis = (Amis) iterator.next();
            //System.out.println(sc.toString());
            listAmis.add(amis);
        }
        readTransaction.commit();
        return listAmis;
    }

    //Ajouter un amis avec en plus la clé primaire qui s'incremente
    public static void ajout(String id_amis, String id_individu) {
        ArrayList<Amis> listAmis = listAmis();
        int nb = 1;

        if(!listAmis.isEmpty()) {
            for(int i = 0; i<listAmis.size(); i++) {
                if(i == (listAmis.size()-1)) {
                    nb = listAmis.get(i).getNo_amis() + 1;
                }
            }
        }
        else {
            nb = 1;
        }

        ajouterAmis(nb, id_amis, id_individu);
    }

    //Retourne une arraylist de string avec les demandes que l'utilisateur a reçu
    public static ArrayList<String> invitation(Individu individu) {
        ArrayList<String> invite = new ArrayList<String>();
        ArrayList<Demande> demande = DemandeManager.listDemande();

        for(int i = 0; i<demande.size(); i++) {
            if(demande.get(i).getId_destinataire().equals(individu.getId_individu())) {
                invite.add(demande.get(i).getId_expediteur());
            }
        }

        return invite;
    }

    public static ArrayList<String> dejaAmis(Individu individu) {
        ArrayList<String> dejaAmis = new ArrayList<String>();
        ArrayList<Amis> listAmis = listAmis();

        for(int i = 0; i<listAmis.size();i++) {
            if(listAmis.get(i).getId_amis().equals(individu.getId_individu())) {
                dejaAmis.add(listAmis.get(i).getId_individu());
            }
        }
        for(int i = 0; i<listAmis.size();i++) {
            if(listAmis.get(i).getId_individu().equals(individu.getId_individu())) {
                dejaAmis.add(listAmis.get(i).getId_amis());
            }
        }

        return dejaAmis;
    }

}
