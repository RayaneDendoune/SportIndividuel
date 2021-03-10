package manager;

import gui.*;

import javax.swing.*;

public class RobustesseManager {
    public static int StringToFloat(String phrase) {
        boolean existPoint = false;
        char[] charArray = phrase.toCharArray();
        for(int i=0; i< charArray.length; i++) {
            if (!((charArray[i] >= '0' && charArray[i] <= '9') || charArray[i] == '.')) {
                //System.out.println("votre entrée comporte des caracteres non compatibles");
                return 1;
            }
            else {
                if(charArray[i] >= '0' && charArray[i] <= '9'){}
                else if(charArray[0] == '.' || charArray[charArray.length-1]=='.') {
                    //System.out.println("Il y a un point à la premiere ou la derniere position");
                    return 2;
                }
                else if(charArray[i] == '.' && !existPoint) {
                    existPoint = true;
                }
                else {
                    //System.out.println("Il y a déjà eu un point avant à la position" + i);
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
                //System.out.println("votre entrée comporte des caracteres non compatibles");
                return 1;
            }
        }
        return 0;
    }

    public static int StringtoString(String phrase) {
        char[] charArray = phrase.toCharArray();
        for(int i=0; i< charArray.length; i++) {
            if (!((charArray[i] >= 'A' && charArray[i] <= 'Z') || (charArray[i] >= 'a' && charArray[i] <= 'z'))) {
                //System.out.println("votre entrée comporte des caracteres non compatibles");
                return 5;
            }
        }
        return 0;
    }

    public static String noErreur(int erreur) {
        String error = "";

        if(erreur == 1) {
            error += "Votre entrée comporte des caracteres non compatibles";
        }

        if(erreur == 2) {
            error += "Il y a un point à la premiere ou la derniere position";
        }

        if(erreur == 3) {
            error += "Il y a déjà eu un point avant";
        }

        if(erreur == 4) {
            error += "Un ou plusieurs champs n'ont pas été remplis";
        }

        if(erreur == 5) {
            error +=  "Aucun caractère numérique n'est attendu";
        }

        if(erreur == 6) {
            error += "L'identifiant est déjà utiliser veuillez en selectionner un nouveau";
        }

        if(erreur == 7) {
            error += "Un elo entre 1000 et 3200 est attendu";
        }

        return error;
    }

    public static boolean modificationRobustesse(int selection) {
        if(selection == 1) {
            if(RobustesseManager.StringToFloat(Course.dist.getText()) == 0 && RobustesseManager.StringToInteger(Course.time.getText()) == 0) {
                return false;
            }
        }
        else if(selection == 2) {
            if(RobustesseManager.StringToInteger(Natation.longueur.getText()) == 0 && RobustesseManager.StringToInteger(Natation.time.getText())==0) {
                return false;
            }
        }
        else if(selection == 3) {
            if(RobustesseManager.StringToFloat(Tennis.PS.getText()) ==0 && RobustesseManager.StringToFloat(Tennis.DS.getText()) ==0 && RobustesseManager.StringToFloat(Tennis.TS.getText()) ==0 && RobustesseManager.StringToInteger(Tennis.set.getText())==0) {
                return false;
            }
        }
        else if(selection == 4) {
            if(RobustesseManager.StringToFloat(Cyclisme.weight.getText())==0) {
                return false;
            }
        }
        else if(selection == 5) {
            if(RobustesseManager.StringToInteger(Echecs.adversaire.getText()) == 0 && RobustesseManager.StringToInteger(Echecs.time.getText()) == 0) {
                return false;
            }
        }
        else {
            return true;
        }

        return false;
    }

    public static void modificationProbleme(int selection) {
        if(selection == 1) {
            if(RobustesseManager.StringToFloat(Course.dist.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(Course.dist.getText()), Course.distance); }
            if(RobustesseManager.StringToInteger(Course.time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Course.time.getText()), Course.temps); }
        }
        else if(selection == 2) {
            if(RobustesseManager.StringToInteger(Natation.longueur.getText()) !=0){ RobustesseManager.erreur(RobustesseManager.StringToInteger(Natation.longueur.getText()), Natation.nb_longueur);}
            if(RobustesseManager.StringToInteger(Natation.time.getText())!=0){ RobustesseManager.erreur(RobustesseManager.StringToInteger(Natation.time.getText()), Natation.temps);}
        }
        else if(selection == 3) {
            if(RobustesseManager.StringToFloat(Tennis.PS.getText()) !=0){ RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.PS.getText()), Tennis.premierService);}
            if(RobustesseManager.StringToFloat(Tennis.DS.getText()) !=0){ RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.DS.getText()), Tennis.deuxiemeService);}
            if(RobustesseManager.StringToFloat(Tennis.TS.getText()) !=0){ RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.TS.getText()), Tennis.troisiemeService);}
            if(RobustesseManager.StringToInteger(Tennis.set.getText())!=0){ RobustesseManager.erreur(RobustesseManager.StringToInteger(Tennis.set.getText()),Tennis.nbSet);}
        }
        else if(selection == 4) {
            if(RobustesseManager.StringToFloat(Cyclisme.weight.getText())!=0){ RobustesseManager.erreur(RobustesseManager.StringToFloat(Cyclisme.weight.getText()),Cyclisme.poids);}
        }
        else if(selection == 5) {
            if(RobustesseManager.StringToInteger(Echecs.adversaire.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Echecs.adversaire.getText()), Echecs.eloAdv); }
            if(RobustesseManager.StringToInteger(Echecs.time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Echecs.time.getText()), Echecs.temps); }
        }
    }

    public static int tailleFrame(String text) {
        char[] charArray = text.toCharArray();
        return charArray.length * 7;
    }

    public static void erreur(int erreur, JLabel label) {
        JLabel errorAttribut = new JLabel();
        JFrame jf = new JFrame();

        String text ="";

        JPanel container = new JPanel();
        if(erreur != 4 && erreur !=6) {
            text = RobustesseManager.noErreur(erreur) + " au champs \"" + label.getText() + "\"";
        }
        else {
            text = RobustesseManager.noErreur(erreur);
        }

        errorAttribut.setText(text);

        container.add(errorAttribut);
        jf.add(container);
        jf.setSize(RobustesseManager.tailleFrame(text), 80);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setLocationRelativeTo(null);
    }
}
