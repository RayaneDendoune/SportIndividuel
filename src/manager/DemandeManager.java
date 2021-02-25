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

    public static void ajouterDemande(int no_demande, String id_destinataire, String id_expediteur){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Demande demande = new Demande();
        demande.setNo_demande(no_demande);
        demande.setId_destinataire(id_destinataire);
        demande.setId_expediteur(id_expediteur);

        session.save(demande);
        session.getTransaction().commit();
    }

    public static ArrayList<Integer> nombreDemande() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ArrayList<Integer> nombreDemande = new ArrayList<Integer>();
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Demande d");


        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Demande demande = (Demande) iterator.next();
            //System.out.println(sc.toString());
            nombreDemande.add(demande.getNo_demande());
        }
        readTransaction.commit();
        return nombreDemande;
    }

    public static void ajout(String id_destinataire, String id_expediteur) {
        ArrayList<Integer> nombreDemande = nombreDemande();
        int nb = 1;

        if(!nombreDemande.isEmpty()) {
            for(int i = 0; i<nombreDemande.size(); i++) {
                if(i == (nombreDemande.size()-1)) {
                    nb = nombreDemande.get(i) + 1;
                }
            }
        }
        else {
            nb = 1;
        }

        ajouterDemande(nb, id_destinataire, id_expediteur);

    }
}
