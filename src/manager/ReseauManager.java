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
            courseArray = CourseManager.listIndividuCourse();
            finalArray.addAll(courseArray);
            //System.out.println("Fait de la course");
        }
        if(natationOcc != 0) {
            natationArray = NatationManager.listIndividuNatation();
            finalArray.addAll(natationArray);
            //System.out.println("Fait de la natation");
        }
        if(tennisOcc != 0) {
            tennisArray = TennisManager.listIndividuTennis();
            finalArray.addAll(tennisArray);
            //System.out.println("Fait du tennis");
        }
        if(cyclismeOcc != 0) {
            cyclismeArray = CyclismeManager.listIndividuCyclisme();
            finalArray.addAll(cyclismeArray);
            //System.out.println("Fait de la cyclisme");
        }
        if(echecOcc != 0) {
            echecArray = EchecManager.listIndividuEchec();
            finalArray.addAll(echecArray);
            //System.out.println("Fait des echecs");
        }

        //J'arrive pas a trié les nom et éliminer les doublons
        //Si j'arrive a faire sa, il faudra que je supprime l'individu qui est connecter
        Collections.sort(finalArray);

        Set set = new HashSet() ;
        set.addAll(finalArray);
        finalArray = new ArrayList(set) ;

        return finalArray;
    }

}
