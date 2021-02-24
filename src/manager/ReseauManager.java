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
        }
        if(natationOcc != 0) {
            natationArray = NatationManager.listIndividuNatation(AuthentificationManager.personne);
            finalArray.addAll(natationArray);
        }
        if(tennisOcc != 0) {
            tennisArray = TennisManager.listIndividuTennis(AuthentificationManager.personne);
            finalArray.addAll(tennisArray);
        }
        if(cyclismeOcc != 0) {
            cyclismeArray = CyclismeManager.listIndividuCyclisme(AuthentificationManager.personne);
            finalArray.addAll(cyclismeArray);
        }
        if(echecOcc != 0) {
            echecArray = EchecManager.listIndividuEchec(AuthentificationManager.personne);
            finalArray.addAll(echecArray);
        }

        Collections.sort(finalArray);
        finalArray = suppOcc(finalArray);

        return finalArray;
    }

    public static ArrayList<Individu> suppOcc(ArrayList<Individu> finalArray) {

        int nbOcc= 0;
        int j = 0;

        for(int i = 0; i<finalArray.size(); i++) {
            nbOcc = 0;
            j = 0;

            while(j<finalArray.size()) {
                if(finalArray.get(i).getId_individu().equals(finalArray.get(j).getId_individu())) {
                    nbOcc= nbOcc +1;
                    //System.out.println(finalArray.get(i).getId_individu() + " : " + nbOcc);
                }
                j++;
            }

            int n = 0;
            while(n<(nbOcc-1)) {
                finalArray.remove(finalArray.get(i));
                n++;
            }
        }

        return finalArray;
    }

}
