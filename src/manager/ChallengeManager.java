package manager;

import model.Individu;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChallengeManager {

    public static ArrayList<String> sportEnCommun(Individu i1, Individu i2) {
        ArrayList<String> sportEnCommun = new ArrayList<String>();
        int courseOccI1, courseOccI2, natationOccI1, natationOccI2, tennisOccI1, tennisOccI2, cyclismeOccI1, cyclismeOccI2, echecOccI1, echecOccI2;

        courseOccI1 = (int)CourseManager.nbSeanceCourse(i1);
        courseOccI2 = (int)CourseManager.nbSeanceCourse(i2);
        natationOccI1 = (int)NatationManager.nbSeanceCourse(i1);
        natationOccI2 = (int)NatationManager.nbSeanceCourse(i2);
        tennisOccI1 = (int)TennisManager.nbSeanceTennis(i1);
        tennisOccI2 = (int)TennisManager.nbSeanceTennis(i2);
        cyclismeOccI1 = (int)CyclismeManager.nbSeanceCyclisme(i1);
        cyclismeOccI2 = (int)CyclismeManager.nbSeanceCyclisme(i2);
        echecOccI1 = (int)EchecManager.nbPartieEchec(i1);
        echecOccI2 = (int)EchecManager.nbPartieEchec(i2);

        if(courseOccI1 != 0 && courseOccI2 != 0) {
            sportEnCommun.add("Course");
        }
        if(natationOccI1 != 0 && natationOccI2 != 0) {
            sportEnCommun.add("Natation");
        }
        if(tennisOccI1 != 0 && tennisOccI2 != 0) {
            sportEnCommun.add("Tennis");
        }
        if(cyclismeOccI1 != 0 && cyclismeOccI2 != 0) {
            sportEnCommun.add("Cyclisme");
        }
        if(echecOccI1 != 0 && echecOccI2 != 0) {
            sportEnCommun.add("Echec");
        }

        return sportEnCommun;
    }

}
