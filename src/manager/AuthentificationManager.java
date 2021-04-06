package manager;

import model.Individu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

/**
 * \file AuthentificationManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant l'authentification de l'individu
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à l'authentification.
 *
 */
public class AuthentificationManager {

    public static String nom;
    public static String prenom;
    public static Individu personne;

    /**
     * \fn boolean existIndividu(Session session, String id_individu)
     * \brief Fonction qui retourne si l'individu passé en paramètre existe ou s'il n'existe pas dans la table Individu de notre base de données.
     *
     * \param [in] session Session pour se connecté à la base de données (Type Session)
     * \param [in] id_individu Identifiant de l'individu qui veut se connecter (Type String)
     * \return True si l'individu existe dans la table Individu, False si il n'existe pas
     */
    public static boolean existIndividu(Session session, String id_individu) {
        Boolean present = false;
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Individu i where i.id_individu=:id_individu");
        readQuery.setString("id_individu", id_individu);

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Individu individu = (Individu) iterator.next();
            present=true;
        }
        readTransaction.commit();

        return present;
    }

    /**
     * \fn String existPassword(String id_individu)
     * \brief Fonction qui retourne le mot de passe de l'identifiant de l'individu passé en paramètre s'il existe.
     *
     * \param [in] id_individu Identifiant de l'individu qui veut se connecter (Type String)
     * \return Si l'individu existe, la fonction retourne son mot de passe, sinon la fonction ne retourne rien
     *
     */
    public static String existPassword(String id_individu) {
        Session session = HibernateUtil.getSession();
        if(existIndividu(session, id_individu)) {
            Boolean present = false;
            Transaction readTransaction = session.beginTransaction();

            Query readQuery = session.createQuery("from Individu i where i.id_individu=:id_individu");
            readQuery.setString("id_individu", id_individu);

            List result = readQuery.list();
            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                Individu individu = (Individu) iterator.next();
                present = true;
                personne = individu;
                prenom = individu.getPrenom();
                nom = individu.getNom();
                return individu.getMdp();
            }

            if (!present) {
                //Le mot de passe n'existe pas
                return "";
            }

            readTransaction.commit();
            session.close();
        }
        else {
            //L'individu n'existe pas
            return "";
        }
        return "";
    }
}
