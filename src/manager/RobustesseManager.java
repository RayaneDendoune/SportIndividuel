package manager;

import gui.*;

import javax.swing.*;

/**
 * \file RobustesseManager.java
 * \brief Classe qui s'occupe de toutes les opérations concernant la robustesse dans le code
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la robustesse.
 *
 */
public class RobustesseManager {

    /**
     * \fn int StringToFloat(String phrase)
     * \brief Fonction qui renvoie un numéro d'erreur si le String passé en paramètre ne peut pas être transformé en Float
     * \param [in] phrase Paramètre que nous voulons transformer en Float (Type String)
     * \return Retourne un Integer qui correspond au numéro d'erreur si le String passé en paramètre ne peut pas être transformé en Float, 0 sinon
     */
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

    /**
     * \fn int StringToInteger(String phrase)
     * \brief Fonction qui renvoie un numéro d'erreur si le String passé en paramètre ne peut pas être transformé en Integer
     * \param [in] phrase Paramètre que nous voulons transformer en Integer (Type String)
     * \return Retourne un Integer qui correspond au numéro d'erreur si le String passé en paramètre ne peut pas être transformé en Integer, 0 sinon
     */
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

    /**
     * \fn int StringtoString(String phrase)
     * \brief Fonction qui renvoie un numéro d'erreur si le String passé en paramètre contient des caractères numériques non désiré
     * \param [in] phrase Paramètre que nous voulons transformer en String (Type String)
     * \return Retourne un Integer qui correspond au numéro d'erreur si le String passé en paramètre contient des caractères numériques non désiré, 0 sinon
     */
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

    /**
     * \fn String noErreur(int erreur)
     * \brief Fonction qui le message d'erreur selon le numéro de l'erreur passé en paramètre
     * \param [in] erreur Numéro de l'erreur (Type Integer)
     * \return Retourne un String qui correspond au message d'erreur selon le numéro de l'erreur passé en paramètre
     */
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

    /**
     * \fn boolean modificationRobustesse(int selection)
     * \brief Fonction qui retourne si il a une erreur dans les champs d'écriture selon le sport choisi
     * \param [in] selection Numéro du sport choisis (Type Integer)
     * \return Retourne false si il n'y a pas de probleme, True sinon
     */
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

    /**
     * \fn void modificationProbleme(int selection)
     * \brief Fonction qui ouvre une fenêtre d'erreur si l'utilisateur a fait une faute dans les champs selon le sport choisi passé en paramètre
     * \param [in] selection Numéro du sport choisis (Type Integer)
     */
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

    /**
     * \fn int tailleFrame(String text)
     * \brief Fonction qui renvoie une taille de fenêtre selon le texte qui est écrit
     * \param [in] text Texte écrit dans la fenêtre (Type String)
     * \return Retourne la taille de la fenêtre
     */
    public static int tailleFrame(String text) {
        char[] charArray = text.toCharArray();
        return charArray.length * 7;
    }

    /**
     * \fn void erreur(int erreur, JLabel label)
     * \brief Fonction qui ouvre une fenêtre d'erreur selon le numéro d'erreur passé en paramètre
     * \param [in] erreur Numéro de l'erreur (Type Integer)
     * \param [in] label Champs dans lequel l'utilisateur s'est tromper (Type JLabel)
     */
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
