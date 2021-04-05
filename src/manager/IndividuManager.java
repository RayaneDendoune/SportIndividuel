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

/**
 * \file IndividuManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant les individus
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux individus.
 *
 */
public class IndividuManager {

    public IndividuManager() { }

    /**
     * \fn void ajouterIndividu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo)
     * \brief Fonction qui ajoute une nouvelle ligne à la table Individu dans la base de donnée grâce aux données entrées en paramètres.
     *
     * \param [in] id_individu Clé primaire de la table Individu (Type String)
     * \param [in] nom Nom de l'individu (Type String)
     * \param [in] prenom Prenom de l'individu (Type String)
     * \param [in] mdp Mot de passe choisi par l'individu (Type String)
     * \param [in] sexe Sexe de l'individu (Type Character)
     * \param [in] age Age de l'individu (Type Integer)
     * \param [in] poids Poids de l'individu (Type Float)
     * \param [in] taille Taille de l'individu (Type Float)
     * \param [in] elo Elo de l'individu (Type Integer)
     */
    public void ajouterIndividu(String id_individu, String nom, String prenom, String mdp, char sexe, int age, float poids, float taille, int elo) {
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
        session.save(i);
        session.getTransaction().commit();
       // session.close();
    }

    /**
     * \fn void supprimerIndividu(String id_individu)
     * \brief Fonction qui supprime une ligne de la table Individu de la base de donnée grâce à l'identifiant de l'individu passé en paramètre
     * \param [in] id_individu Identifiant de l'individu que l'on souhaite supprimer (Type String)
     */
    public void supprimerIndividu(String id_individu) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Individu i = (Individu) session.load(Individu.class,id_individu);
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * \fn ArrayList<Individu> listIndividu()
     * \brief Fonction qui parcours la table Individu et qui retourne toutes les données de cette table sous forme d'ArrayList de Individu
     * \return ArrayList de Individu correspondant à toutes les données compris dans la table Individu dans la base de données
     */
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

    /**
     * \fn Individu rechercheIndividuParId(String id)
     * \brief Fonction qui retrouve un individu grâce à son identifiant passé en paramètre
     * \param [in] id Identifiant de l'individu que l'on souhaite trouver (Type String)
     * \return Retourne un Individu.
     */
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

    /**
     * \fn boolean existIndividu(String id)
     * \brief Fonction qui retourne si un individu fait partie de la table Individu grâce à son identifiant
     * \param [in] id Identifiant de l'individu (Type String)
     * \return True si l'individu existe, False sinon
     */
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
