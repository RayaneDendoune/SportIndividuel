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

/**
 * \file AmisManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant les amis
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux amis.
 */
public class AmisManager {
    public static Amis amis;

    /**
     * \fn void ajouterAmis(int no_amis, String id_amis, String id_individu)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Amis dans la base de donnée grâce aux données entrées en paramètres. Néanmoins, le développeur devra passé par la fonction void ajout(String id_amis, String id_individu) pour qu'une nouvelle ligne s'ajoute car la clé primaire sera ajouter grâce à elle. La fonction actuelle sera appelé dans ajout.
     *
     * \param [in] no_amis Clé primaire de la table Amis (Type Integer)
     * \param [in] id_amis Identifiant de l'ami (Type String)
     * \param [in] id_individu Identifiant de l'individu qui est actuellement connecté (Type String)
     */
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

    /**
     * \fn ArrayList<Amis> listAmis()
     * \brief Fonction qui parcours la table Amis et qui retourne toutes les données de cette table sous forme d'ArrayList d'Amis
     * \return ArrayList d'Amis correspondant à toutes les données compris dans la table Amis dans la base de données
     */
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

    /**
     * \fn void ajout(String id_amis, String id_individu)
     * \brief Fonction qui vérifie le numéro d'amis de la dernière ligne de la table Amis afin de l'incrémenter et ainsi d'avoir un nouveau numéro d'amis qui est la clé primaire de la table Amis. De plus, la fonction ajouterAmis est appelé afin d'ajouter la nouvelle ligne à la table Amis.
     *
     * \param [in] id_amis Identifiant de l'ami (Type String)
     * \param [in] id_individu Identifiant de l'individu qui est actuellement connecté (Type String)
     */
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


    /**
     * \fn ArrayList<String> invitation(Individu individu)
     * \brief Fonction qui retourne les invitations que l'individu qui est connecté a reçu afin qu'il les accepte ou non comme Amis.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return ArrayList de String avec toutes les demandes qui ont été faite à l'individu qui est actuellement connecté
     */
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

    /**
     * \fn ArrayList<String> dejaAmis(Individu individu)
     * \brief Fonction qui retourne l'identifiant des individus avec lesquelles notre utilisateur est déjà Amis.
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return ArrayList de String avec l'identifiant des individus avec lesquelles notre utilisateur est déjà Amis.
     */
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
