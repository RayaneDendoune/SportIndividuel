package gui;

import manager.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

/**
 * \file ModifSport.java
 * \brief Classe qui permet de créer l'interface de la page ModifSport
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface de la page ModifSport.
 *
 */
//Classe qui permet de modifier les données de l'utilisateur en interface graphique
public class ModifSport extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();
    private int selection;
    private JLabel nomColonne = new JLabel();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    private JButton retour = new JButton("Retour");
    private JLabel titre = new JLabel();

    /**
     * \fn ModifSport(int selection)
     * \brief Constructeur de ModifSport
     * \param [in] selection Chiffre qui permet de selectionner le sport voulu (Type Integer)
     */
    public ModifSport(int selection) {
        this.selection = selection;
        build(selection);
    }

    /**
     * \fn void build()
     * \brief Fonction qui permet la construction de la fenêtre de la page ModifSport
     */
    public void build(int selection) {
        this.setSize(750, 500);
        //this.setPreferredSize(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

       /* ArrayList<JButton> nbButtons = nbButton(CourseManager.seanceCourseIndividu(AuthentificationManager.personne));
        for(int i = 0; i<nbButtons.size(); i++) {
            nbButtons.get(i).addActionListener(this);
        }*/
        ArrayList<JButton> nbButtons = new ArrayList<JButton>();
        JPanel donneeEntree = new JPanel();

        if(selection == 1) {
            nbButtons = nbButtonCourse(CourseManager.seanceCourseIndividu(AuthentificationManager.personne));
            donneeEntree = Course.donnee();
            nomColonne.setText("Id seance/Distance/Temps/Date");
            titre.setText("Modication Course");
        }
        else if(selection == 2) {
            nbButtons = nbButtonNatation(NatationManager.seanceNatationIndividu(AuthentificationManager.personne));
            donneeEntree = Natation.donnee();
            nomColonne.setText("Id seance/Nb longueur/Type Nage/Temps total/Date");
            titre.setText("Modication Natation");
        }
        else if(selection == 3) {
            nbButtons = nbButtonsTennis(TennisManager.seanceTennisIndividu(AuthentificationManager.personne));
            donneeEntree = Tennis.donnee();
            nomColonne.setText("Id seance/Service 1/Service 2/Service 3/Nb Set/Issue match");
            titre.setText("Modication Tennis");
        }
        else if(selection == 4) {
            nbButtons = nbButtonsCyclisme(CyclismeManager.seanceCyclismeIndividu(AuthentificationManager.personne));
            donneeEntree = Cyclisme.donnee();
            nomColonne.setText("Id seance/Objectif Seance/Niveau activité physique/Poids");
            titre.setText("Modication Cyclisme");

        }
        else if(selection == 5) {
            nbButtons = nbButtonsEchec(EchecManager.seanceEchecIndividu(AuthentificationManager.personne));
            donneeEntree = Echecs.donnee();
            nomColonne.setText("Id seance/Elo adversaire/Duree/Issue partie");
            titre.setText("Modication Echecs");
        }

        for(int i = 0; i<nbButtons.size(); i++) {
            nbButtons.get(i).addActionListener(this);
        }

        retour.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JScrollPane grid = buttons(nbButtons);
        JPanel completeEast = completeEast(donneeEntree);
        //completeEast.add(donneeEntree);
        JPanel completeCenter = completeCenter(grid, completeEast, nomColonne);

        JPanel complete = complete(completeCenter);

        pan.add(complete, BorderLayout.WEST);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    /**
     * \fn JScrollPane buttons(ArrayList<JButton> but)
     * \brief Fonction qui renvoie un JScrollPane avec des boutons correspondant aux différentes séances de l'utilisateur
     * \param [in] but ArrayList<JButton> (Type ArrayList<JButton>)
     * \return Retourne un JScrollPane de boutons
     */
    public JScrollPane buttons(ArrayList<JButton> but) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for(int i = 0; i<but.size(); i++) {
            //c.insets = new Insets(0,20,0,0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = i;
            grid.add(but.get(i), c);
        }

        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(300, 110));
        return scrollPane;
    }

    /**
     * \fn JPanel completeEast(JPanel donnee)
     * \brief Fonction qui renvoie un JPanel
     * \param [in] donnee JPanel (Type JPanel)
     * \return Retourne un JPanel
     */
    public JPanel completeEast(JPanel donnee) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        grid.add(donnee, c);

        return grid;
    }

    /**
     * \fn JPanel completeCenter(JScrollPane scroll, JPanel completeEast, JLabel nomColonne)
     * \brief Fonction qui renvoie un JPanel qui assemble un JPanel, un JLabel & JScrollPane
     * \param [in] scroll JScrollPane (Type JScrollPane)
     * \param [in] completeEast JPanel (Type JPanel)
     * \param [in] nomColonne JLabel (Type JLabel)
     * \return Retourne un JPanel
     */
    public JPanel completeCenter(JScrollPane scroll, JPanel completeEast, JLabel nomColonne) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        grid.add(nomColonne, c);

        c.gridx = 0;
        c.gridy = 1;
        grid.add(completeEast, c);

        c.gridx = 1;
        c.gridy = 1;
        grid.add(scroll, c);

        return grid;
    }

    /**
     * \fn JPanel complete(JPanel completeCenter)
     * \brief Fonction qui renvoie un JPanel
     * \param [in] completeCenter JPanel (Type JPanel)
     * \return Retourne un JPanel
     */
    public JPanel complete(JPanel completeCenter) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        grid.add(titre, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        grid.add(completeCenter, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        grid.add(retour, c);

        return grid;
    }

    /**
     * \fn ArrayList<JButton> nbButtonCourse(ArrayList<Seance_course> sc)
     * \brief Fonction qui renvoie une ArrayList de JButton en fonction de taille de l'arraylist passé en paramètre
     * \param [in] sc ArrayList<Seance_course> (Type ArrayList<Seance_course>)
     * \return Retourne une ArrayList de JButton
     */
    public ArrayList<JButton> nbButtonCourse(ArrayList<Seance_course> sc) {
        for(int  i = 0; i<sc.size(); i++) {
            buttons.add((new JButton(sc.get(i).getId_seance_course() + "  " + sc.get(i).getDistance() + "  " + sc.get(i).getTemps() + "  " + sc.get(i).getDate() )));
        }
        return buttons;
    }

    /**
     * \fn ArrayList<JButton> nbButtonNatation(ArrayList<Seance_natation> sn)
     * \brief Fonction qui renvoie une ArrayList de JButton en fonction de taille de l'arraylist passé en paramètre
     * \param [in] sn ArrayList<Seance_natation> (Type ArrayList<Seance_natation>)
     * \return Retourne une ArrayList de JButton
     */
    public ArrayList<JButton> nbButtonNatation(ArrayList<Seance_natation> sn) {
        for(int  i = 0; i<sn.size(); i++) {
            buttons.add((new JButton(sn.get(i).getId_seance_natation() + "  " + sn.get(i).getNb_longueur() + "  " + sn.get(i).getType_nage() + "  " + sn.get(i).getTemps_total() + "  " + sn.get(i).getDate())));
        }
        return buttons;
    }

    /**
     * \fn ArrayList<JButton> nbButtonsTennis(ArrayList<Seance_tennis> st)
     * \brief Fonction qui renvoie une ArrayList de JButton en fonction de taille de l'arraylist passé en paramètre
     * \param [in] st ArrayList<Seance_tennis> (Type ArrayList<Seance_tennis>)
     * \return Retourne une ArrayList de JButton
     */
    public ArrayList<JButton> nbButtonsTennis(ArrayList<Seance_tennis> st) {
        for(int  i = 0; i<st.size(); i++) {
            buttons.add((new JButton(st.get(i).getId_seance_tennis() + "  " + st.get(i).getPremier_service() + "  " + st.get(i).getDeuxieme_service() + "  " + st.get(i).getTroisieme_service() + "  " + st.get(i).getNb_set() + "  " + st.get(i).getIssue_match())));
        }
        return buttons;
    }

    /**
     * \fn ArrayList<JButton> nbButtonsCyclisme(ArrayList<Seance_cyclisme> seance_cyclisme)
     * \brief Fonction qui renvoie une ArrayList de JButton en fonction de taille de l'arraylist passé en paramètre
     * \param [in] seance_cyclisme ArrayList<Seance_cyclisme> (Type ArrayList<Seance_cyclisme>)
     * \return Retourne une ArrayList de JButton
     */
    public ArrayList<JButton> nbButtonsCyclisme(ArrayList<Seance_cyclisme> seance_cyclisme) {
        for(int  i = 0; i<seance_cyclisme.size(); i++) {
            buttons.add((new JButton(seance_cyclisme.get(i).getId_seance_cyclisme() + "  " + seance_cyclisme.get(i).getObjectif_seance() + "  " + seance_cyclisme.get(i).getNiveau_activite_physique() + "  " + seance_cyclisme.get(i).getPoids())));
        }
        return buttons;
    }

    /**
     * \fn ArrayList<JButton> nbButtonsEchec(ArrayList<Partie_echec> partie_echec)
     * \brief Fonction qui renvoie une ArrayList de JButton en fonction de taille de l'arraylist passé en paramètre
     * \param [in] partie_echec ArrayList<Partie_echec> (Type ArrayList<Partie_echec>)
     * \return Retourne une ArrayList de JButton
     */
    public ArrayList<JButton> nbButtonsEchec(ArrayList<Partie_echec> partie_echec) {
        for(int  i = 0; i<partie_echec.size(); i++) {
            buttons.add((new JButton(partie_echec.get(i).getId_partie_echec() + "  " + partie_echec.get(i).getElo_adversaire() + "  " + partie_echec.get(i).getDuree() + "  " + partie_echec.get(i).getIssue_partie())));
        }
        return buttons;
    }

    /**
     * \fn void actionPerformed(ActionEvent e)
     * \brief Fonction qui donne des actions aux boutons
     * \param [in] a ActionEvent (Type ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        ArrayList<Seance_course> sc = CourseManager.seanceCourseIndividu(AuthentificationManager.personne);
        ArrayList<Seance_natation> sn = NatationManager.seanceNatationIndividu(AuthentificationManager.personne);
        ArrayList<Seance_tennis> st = TennisManager.seanceTennisIndividu(AuthentificationManager.personne);
        ArrayList<Seance_cyclisme> seance_cyclisme = CyclismeManager.seanceCyclismeIndividu((AuthentificationManager.personne));
        ArrayList<Partie_echec> partie_echec = EchecManager.seanceEchecIndividu(AuthentificationManager.personne);

        if(Button == retour) {
            if(selection == 1) {
                new Course();
                dispose();
            }
            else if(selection == 2) {
                new Natation();
                dispose();
            }
            else if(selection == 3) {
                new Tennis();
                dispose();
            }
            else if(selection == 4) {
                new Cyclisme();
                dispose();
            }
            else if(selection == 5) {
                new Echecs();
                dispose();
            }
        }

        for (int i = 0; i < buttons.size(); i++) {
            if (selection == 1) {
                if (Button == buttons.get(i)) {
                    if(!Course.dist.getText().isBlank() && !Course.time.getText().isBlank()) {
                        if (RobustesseManager.StringToFloat(Course.dist.getText()) == 0 && RobustesseManager.StringToInteger(Course.time.getText()) == 0) {
                            int heure = Integer.parseInt(Course.time.getText()) / 60;
                            int min = Integer.parseInt(Course.time.getText()) % 60;
                            final Time TimeSQL = new Time(heure, min, 00);
                            CourseManager.updateCourse(sc.get(i), Float.parseFloat(Course.dist.getText()), TimeSQL);
                        } else {
                            if (RobustesseManager.StringToFloat(Course.dist.getText()) != 0) {
                                RobustesseManager.erreur(RobustesseManager.StringToFloat(Course.dist.getText()), Course.distance);
                            }
                            if (RobustesseManager.StringToInteger(Course.time.getText()) != 0) {
                                RobustesseManager.erreur(RobustesseManager.StringToInteger(Course.time.getText()), Course.temps);
                            }
                        }
                    }
                    else {
                        RobustesseManager.erreur(4, null);
                    }
                }
            }

            if (selection == 2) {
                if (Button == buttons.get(i) ) {
                    if(!Natation.longueur.getText().isBlank() && !Natation.time.getText().isBlank()) {
                        if(RobustesseManager.StringToInteger(Natation.longueur.getText()) == 0 && RobustesseManager.StringToInteger(Natation.time.getText())==0) {
                            int heure = Integer.parseInt(Natation.time.getText()) / 60;
                            int min = Integer.parseInt(Natation.time.getText()) % 60;
                            final Time TimeSQL = new Time(heure, min, 00);
                            NatationManager.updateNatation(sn.get(i), Integer.parseInt(Natation.longueur.getText()), TimeSQL, (String) Natation.nage.getSelectedItem());
                        }
                        else {
                            if(RobustesseManager.StringToInteger(Natation.longueur.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Natation.longueur.getText()), Natation.nb_longueur); }
                            if(RobustesseManager.StringToInteger(Natation.time.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Natation.time.getText()), Natation.temps); }
                        }
                    }
                    else {
                        RobustesseManager.erreur(4, null);
                    }
                }
            }
            if (selection == 3) {
                if (Button == buttons.get(i)) {
                    if(!Tennis.PS.getText().isBlank() && !Tennis.DS.getText().isBlank() && !Tennis.TS.getText().isBlank() && !Tennis.set.getText().isBlank()) {
                        if(RobustesseManager.StringToFloat(Tennis.PS.getText()) ==0 && RobustesseManager.StringToFloat(Tennis.DS.getText()) ==0 && RobustesseManager.StringToFloat(Tennis.TS.getText()) ==0 && RobustesseManager.StringToInteger(Tennis.set.getText())==0) {
                            TennisManager.updateTennis(st.get(i), Float.parseFloat(Tennis.PS.getText()), Float.parseFloat(Tennis.DS.getText()), Float.parseFloat(Tennis.TS.getText()), Integer.parseInt(Tennis.set.getText()), TennisManager.issueMatch((String) Tennis.issue.getSelectedItem()));
                        }
                        else{
                            if(RobustesseManager.StringToFloat(Tennis.PS.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.PS.getText()), Tennis.premierService); }
                            if(RobustesseManager.StringToFloat(Tennis.DS.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.DS.getText()), Tennis.deuxiemeService); }
                            if(RobustesseManager.StringToFloat(Tennis.TS.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(Tennis.TS.getText()), Tennis.troisiemeService); }
                            if(RobustesseManager.StringToInteger(Tennis.set.getText())!=0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Tennis.set.getText()), Tennis.nbSet); }
                        }
                    }
                    else {
                        RobustesseManager.erreur(4, null);
                    }
                }

            }

            if (selection == 4) {
                if (Button == buttons.get(i)) {
                    if (!Cyclisme.weight.getText().isBlank()) {
                        if(RobustesseManager.StringToFloat(Cyclisme.weight.getText()) == 0) {
                            CyclismeManager.updateCyclisme(seance_cyclisme.get(i), Float.parseFloat(Cyclisme.weight.getText()), (String) Cyclisme.objectif.getSelectedItem(), (String) Cyclisme.niveau.getSelectedItem());
                        } else {
                            if(RobustesseManager.StringToFloat(Cyclisme.weight.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(Cyclisme.weight.getText()), Cyclisme.poids); }
                        }
                    } else {
                        RobustesseManager.erreur(4, null);
                    }
                }
            }

            if (selection == 5) {
                if(Button == buttons.get(i)) {
                    if(!Echecs.adversaire.getText().isBlank() && !Echecs.time.getText().isBlank()) {
                        if(RobustesseManager.StringToInteger(Echecs.adversaire.getText()) == 0 && RobustesseManager.StringToInteger(Echecs.time.getText()) == 0) {
                            int heure = Integer.parseInt(Echecs.time.getText()) / 60;
                            int min = Integer.parseInt(Echecs.time.getText()) % 60;
                            final Time TimeSQL = new Time(heure, min, 00);
                            char issuePartie = EchecManager.issue((String) Echecs.issue.getSelectedItem());
                            int newElo = EchecManager.newElo(AuthentificationManager.personne, Integer.parseInt(Echecs.adversaire.getText()), (String) Echecs.issue.getSelectedItem());

                            EchecManager.updateEchec(partie_echec.get(i), Integer.parseInt(Echecs.adversaire.getText()), TimeSQL, issuePartie, newElo);
                        }
                        else {
                            if(RobustesseManager.StringToInteger(Echecs.adversaire.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Echecs.adversaire.getText()), Echecs.eloAdv); }
                            if(RobustesseManager.StringToInteger(Echecs.time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(Echecs.time.getText()), Echecs.temps); }
                        }
                    }
                    else {
                        RobustesseManager.erreur(4, null);
                    }
                }
            }
        }
    }
}
