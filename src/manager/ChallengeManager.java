package manager;

import model.Individu;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * \file ChallengeManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le challenge entre deux individus
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au challenge entre deux individus.
 *
 */
public class ChallengeManager {

    /**
     * \fn ArrayList<String> sportEnCommun(Individu i1, Individu i2)
     * \brief Fonction qui renvoie les sports en communs entre deux individus.
     *
     * \param [in] i1 Individu qui est actuellement connecté (Type Individu)
     * \param [in] i2 Individu avec lequel on peut comparer (Type Individu)
     * \return ArrayList de String avec les sports en communs entre les deux individus passés en paramètres
     *
     */
    public static ArrayList<String> sportEnCommun(Individu i1, Individu i2) {
        ArrayList<String> sportEnCommun = new ArrayList<String>();
        int courseOccI1, courseOccI2, natationOccI1, natationOccI2, tennisOccI1, tennisOccI2, cyclismeOccI1, cyclismeOccI2, echecOccI1, echecOccI2;

        courseOccI1 = (int)CourseManager.nbSeanceCourse(i1);
        courseOccI2 = (int)CourseManager.nbSeanceCourse(i2);
        natationOccI1 = (int)NatationManager.nbSeanceNatation(i1);
        natationOccI2 = (int)NatationManager.nbSeanceNatation(i2);
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
