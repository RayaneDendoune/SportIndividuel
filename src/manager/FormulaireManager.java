package manager;

public class FormulaireManager {


    //Avant de faire cette fonction dans formulaire, il faut gérer le cas ou le mec ne rentre pas des valeur numérique
    //Faire une condition que l'elo doit etre entre 1000 et 2800
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
