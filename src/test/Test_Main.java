package test;

import manager.IndividuManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DataInit;
import util.HibernateUtil;

public class Test_Main {

    public static void main(String[] args) {
        /*DataInit.createTables();

        Session session = HibernateUtil.getSession();
        Transaction persistTransaction = session.beginTransaction();

        IndividuManager im = new IndividuManager();
        im.ajouterIndividu("Rdendoune", "Dendoune", "Rayane", "Obito95.", 'M', 22, 70.2f, 1.65f, 1206, "Debutant");
        im.ajouterIndividu("Baltringue", "Cerini", "Enzo", "Princessedu95", 'F', 20, 78.2f, 1.84f, 1489, "Debutant");
        im.ajouterIndividu("Ave le A", "Obeyesekara", "Avishka", "Skirioupapa", 'M', 20, 88.2f, 1.85f, 1803, "Debutant");

        persistTransaction.commit();

        session.close();*/

        String phrase = "12.3a456";
        System.out.println("Erreur numéro " + StringToFloat(phrase));
    }


    public static int StringToFloat(String phrase) {
        boolean existPoint = false;
        char[] charArray = phrase.toCharArray();
        for(int i=0; i< charArray.length; i++) {
            if (!((charArray[i] >= '0' && charArray[i] <= '9') || charArray[i] == '.')) {
                System.out.println("votre entrée comporte des caracteres non compatibles");
                return 1;
            }
            else {
                if(charArray[i] >= '0' && charArray[i] <= '9'){}
                else if(charArray[0] == '.' || charArray[charArray.length-1]=='.') {
                    System.out.println("Il y a un point à la premiere ou la derniere position");
                    return 2;
                }
                else if(charArray[i] == '.' && !existPoint) {
                    existPoint = true;
                }
                else {
                    System.out.println("Il y a déjà eu un point avant à la position" + i);
                    return 3;
                }
            }
        }
        return 0;
    }

    public static int StringToInteger(String phrase) {
        char[] charArray = phrase.toCharArray();
        for(int i=0; i< charArray.length; i++) {
            if (!(charArray[i] >= '0' && charArray[i] <= '9')) {
                System.out.println("votre entrée comporte des caracteres non compatibles");
                return 1;
            }
        }
        return 0;
    }
}
