package manager;

import model.Individu;
import org.hibernate.Session;
import util.HibernateUtil;

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
}
