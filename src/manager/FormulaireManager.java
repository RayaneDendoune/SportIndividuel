package manager;

/**
 * \file FormulaireManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant le formulaire d'inscription
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées au formulaire d'inscription.
 *
 */
public class FormulaireManager {

    /**
     * \fn int choixElo(String elo, String frequence_jeu)
     * \brief Fonction qui retourne filtre si l'individu connais son Elo ou pas, et s'il ne le connais pas, un elo lui sera attribué selon sa frequence de jeu
     * \param [in] elo Elo que l'utilisateur aura écrit (Type String)
     * \param [in] frequence_jeu Frequence de jeu que l'utilisateur aura sélectionner (Type String)
     * \return Retourne un Integer avec l'elo de l'individu selon ce qu'il a entré comme paramètre.
     *
     */
    public static int choixElo(String elo, String frequence_jeu){
        int elofinal = 0;

        if(elo.isBlank()) {
            if(frequence_jeu.equals("Débutant")) {elofinal = 1000 ;}
            else if(frequence_jeu.equals("Joueur occasionnel")) { elofinal = 1200 ; }
            else if(frequence_jeu.equals("Bon joueur de club")) {elofinal = 1600 ;}
            else if(frequence_jeu.equals("Très bon joueur de club")) {elofinal = 1800 ;}
            else if(frequence_jeu.equals("Niveau national")) {elofinal = 2000 ;}
            else if(frequence_jeu.equals("Candidat maître")){elofinal = 2200;}
            else if(frequence_jeu.equals("Maître de la Fédération internationale des échecs")) {elofinal = 2300 ;}
            else if(frequence_jeu.equals("Maître international")) {elofinal = 2400 ;}
            else if(frequence_jeu.equals("Grand maître international")) {elofinal = 2500;}
        }
        else {
            elofinal = Integer.parseInt(elo);
        }
        return elofinal;
    }
}
