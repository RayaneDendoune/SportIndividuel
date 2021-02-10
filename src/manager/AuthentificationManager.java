package manager;

import model.Individu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class AuthentificationManager {
    /*public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        //existIndividu(session, "Le S");
        existPassword(session, "Le S");
        session.close();
    }*/

    public static boolean existIndividu(Session session, String id_individu) {
        Boolean present = false;
        Transaction readTransaction = session.beginTransaction();

        Query readQuery = session.createQuery("from Individu i where i.id_individu=:id_individu");
        readQuery.setString("id_individu", id_individu);

        List result = readQuery.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            Individu individu = (Individu) iterator.next();
            //System.out.println(individu.toString());
            present=true;
        }

        /*if(!present) {
            System.out.println("L'individu n'existe pas");
        }*/
        readTransaction.commit();

        return present;
    }

    public static String existPassword(String id_individu) {
        Session session = HibernateUtil.getSession();
        if(existIndividu(session, id_individu)) {
            //System.out.println("L'individu existe");
            Boolean present = false;
            Transaction readTransaction = session.beginTransaction();

            Query readQuery = session.createQuery("from Individu i where i.id_individu=:id_individu");
            readQuery.setString("id_individu", id_individu);

            List result = readQuery.list();
            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                Individu individu = (Individu) iterator.next();
                //System.out.println("Le mot de passe est " + individu.getMdp());
                present = true;
                return individu.getMdp();
            }

            if (!present) {
                //System.out.println("Le mot de passe n'existe pas");
                return "";
            }

            readTransaction.commit();
            session.close();
        }
        else {
            //System.out.println("L'individu n'existe pas");
            return "";
        }
        return "";
    }
}
