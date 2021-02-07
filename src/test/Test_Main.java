package test;

import manager.IndividuManager;
import util.HibernateUtil;

public class Test_Main {

    public static void main(String[] args) {
        IndividuManager im = new IndividuManager();
        im.ajouterIndividu("Rdendoune", "Dendoune", "Rayane", "Obito95.", 'M', 22, 70.2f, 1.65f, 1206, "Debutant");
        im.ajouterIndividu("Baltringue", "Cerini", "Enzo", "Princessedu95", 'F', 20, 78.2f, 1.84f, 1489, "Debutant");
        im.ajouterIndividu("Ave le A", "Obeyesekara", "Avishka", "Skirioupapa", 'M', 20, 88.2f, 1.85f, 1803, "Debutant");

        HibernateUtil.sessionFactory.close();


    }
}
