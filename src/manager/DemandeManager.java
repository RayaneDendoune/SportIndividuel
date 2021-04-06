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

/**
 * \file DemandeManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant les demandes d'amis
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux demandes d'amis.
 *
 */
public class DemandeManager {
    public static Demande demande;

    /**
     * \fn void ajouterDemande(int nb, String id_destinataire, String id_expediteur)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Demande dans la base de données grâce aux données entrées en paramètres.
     * Néanmoins, le développeur devra passé par la fonction void ajout(String id_destinataire, String id_expediteur) pour qu'une nouvelle
     * ligne s'ajoute car la clé primaire sera ajouter grâce à elle. La fonction actuelle sera appelé dans ajout.
     *
     * \param [in] nb Clé primaire de la table Demande (Type Integer)
     * \param [in] id_destinataire Identifiant du destinataire (Type String)
     * \param [in] id_expediteur Identifiant de l'expéditeur (Type String)
     *
     */
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

    /**
     * \fn ArrayList<Demande> listDemande()
     * \brief Fonction qui parcours la table Demande et qui retourne toutes les données de cette table sous forme d'ArrayList de Demande
     * \return ArrayList de Demande correspondant à toutes les données compris dans la table Demande dans la base de données
     */
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
            listDemande.add(demande);
        }
        readTransaction.commit();
        return listDemande;
    }

    /**
     * \fn void ajout(String id_destinataire, String id_expediteur)
     * \brief Fonction qui vérifie le numéro de demande d'amis de la dernière ligne de la table Demande afin de l'incrémenter
     * et ainsi d'avoir un nouveau numéro de demande d'amis qui est la clé primaire de la table Demande. De plus,
     * la fonction ajouterDemande est appelé afin d'ajouter la nouvelle ligne à la table Demande.
     * \param [in] id_destinataire Identifiant du destinataire (Type String)
     * \param [in] id_expediteur Identifiant de l'expéditeur (Type String)
     */
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

    /**
     * \fn ArrayList<String> idDemandeEnvoyer(Individu individu)
     * \brief Fonction qui retourne le nom des individus ayant reçus une invitation par l'individu passé en paramètre
     * \param [in] individu Individu qui est actuellement connecté (Type Individu)
     * \return ArrayList de String avec le nom des individus ayant reçus une invitation de l'utilisateur actuel
     */
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

    /**
     * \fn Demande suppDemande(String id_destinataire, String id_expediteur)
     * \brief Fonction qui retrouve une Demande grâce aux paramètres de la fonction.
     * \param [in] id_destinataire Identifiant du destinataire (Type String)
     * \param [in] id_expediteur Identifiant de l'expéditeur (Type String)
     * \return Retourne une Demande qui a été retrouvé grâce aux paramètres de la fonction.
     */
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

    /**
     * \fn void deleteValue(Demande demande)
     * \brief Fonction qui supprime une ligne de la table Demande de la base de données
     * \param [in] demande Demande que l'on souhaite supprimer (Type Demande)
     */
    //Supprime une demande de la table Demande
    public static void deleteValue(Demande demande) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(demande);
        session.getTransaction().commit();
    }
}
