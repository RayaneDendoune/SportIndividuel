package manager;

import model.Individu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ReseauManager {

    public static ArrayList<Individu> sportJouer(Individu individu) {
        int courseOcc, natationOcc, tennisOcc, cyclismeOcc, echecOcc;
        ArrayList<Individu> courseArray, natationArray, tennisArray, cyclismeArray, echecArray, finalArray = new ArrayList<Individu>();

        courseOcc = (int)CourseManager.nbSeanceCourse(individu);
        natationOcc = (int)NatationManager.nbSeanceCourse(individu);
        tennisOcc = (int)TennisManager.nbSeanceTennis(individu);
        cyclismeOcc = (int)CyclismeManager.nbSeanceCyclisme(individu);
        echecOcc = (int)EchecManager.nbPartieEchec(individu);

        if(courseOcc != 0) {
            courseArray = CourseManager.listIndividuCourse(AuthentificationManager.personne);
            finalArray.addAll(courseArray);
            //System.out.println("Fait de la course");
        }
        if(natationOcc != 0) {
            natationArray = NatationManager.listIndividuNatation(AuthentificationManager.personne);
            /*for (int i =0 ; i<natationArray.size() ;i++){
                if(!finalArray.contains(natationArray.get(i))){
                    finalArray.add(natationArray.get(i));
                }
            }*/
            finalArray.addAll(natationArray);
            //System.out.println("Fait de la natation");
        }
        if(tennisOcc != 0) {
            tennisArray = TennisManager.listIndividuTennis(AuthentificationManager.personne);
            /*for (int i =0 ; i<tennisArray.size() ;i++){
                if(!finalArray.contains(tennisArray.get(i))){
                    finalArray.add(tennisArray.get(i)));
                }
            }*/
            finalArray.addAll(tennisArray);
            //System.out.println("Fait du tennis");
        }
        if(cyclismeOcc != 0) {
            cyclismeArray = CyclismeManager.listIndividuCyclisme(AuthentificationManager.personne);
            /*for (int i =0 ; i<cyclismeArray.size() ;i++){
                if(!finalArray.contains(cyclismeArray.get(i))){
                    finalArray.add(cyclismeArray.get(i)));
                }
            }*/
            finalArray.addAll(cyclismeArray);
            //System.out.println("Fait de la cyclisme");
        }
        if(echecOcc != 0) {
            echecArray = EchecManager.listIndividuEchec(AuthentificationManager.personne);
            /*for (int i =0 ; i<echecArray.size() ;i++){
                if(!finalArray.contains(echecArray.get(i))){
                    finalArray.add(individu);
                }
            }*/
            finalArray.addAll(echecArray);
            //System.out.println("Fait des echecs");
        }

        //On peut mettre ces 3 lignes en commentaires, cela ne change rien
        Set set = new HashSet() ;
        set.addAll(finalArray);
        finalArray = new ArrayList(set) ;

        Collections.sort(finalArray);
        /*while(finalArray.contains(AuthentificationManager.personne)){
            finalArray.remove(AuthentificationManager.personne);
        }*/


        return finalArray;

    }

}
