package manager;

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

        return error;
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
