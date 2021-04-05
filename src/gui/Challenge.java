package gui;

import chart.ComparativeBarChart;
import chart.ComparativeLineChart;
import chart.ComparativePieChart;
import chart.LineChart;
import manager.*;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

/**
 * \file Challenge.java
 * \brief Classe qui permet de créer l'interface de Challenge
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface Challenge.
 *
 */
//Classe qui représente la page de challenge entre amis en interface graphique
public class Challenge extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel challenge = new JLabel("Challenge");
    private JLabel vosAmis = new JLabel("Vos Amis");

    private ArrayList<JComboBox> sportAmis = new ArrayList<JComboBox>();

    private ArrayList<String> amisAvecIndividu = AmisManager.dejaAmis(AuthentificationManager.personne);
    private ArrayList<JButton> selectionAmis = new ArrayList<JButton>();
    //private ArrayList<JRadioButton> selectionAmis = new ArrayList<JRadioButton>();
    //ButtonGroup groupRadioButtons = new ButtonGroup();

    private JButton valider = new JButton("Valider");
    private JButton retour = new JButton("Retour");

    public Challenge() {
        build();
    }

    /**
     * \fn void build()
     * \brief Fonction qui permet la construction de la fenêtre de Challenge
     */
    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        valider.addActionListener(this);
        retour.addActionListener(this);

        for(int i = 0; i<amisAvecIndividu.size(); i++) {
            JButton button = new JButton(amisAvecIndividu.get(i));
            button.setBackground(Color.white);
            selectionAmis.add(button);
            selectionAmis.get(i).addActionListener(this);
            //groupRadioButtons.add(new JRadioButton(amisAvecIndividu.get(i)));
        }

        for(int i = 0; i<amisAvecIndividu.size(); i++) {
            ArrayList<String> sportEnCommun = ChallengeManager.sportEnCommun(AuthentificationManager.personne, IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(i)) );
            JComboBox sports = new JComboBox();
            for(int j = 0; j<sportEnCommun.size(); j++) {
                sports.addItem(sportEnCommun.get(j));
            }
            sportAmis.add(sports);
        }

        JPanel buttons = buttons();
        JScrollPane panelAmis = panelAmis(selectionAmis, sportAmis);
        JPanel complete = complete(buttons, panelAmis);

        pan.add(complete, BorderLayout.CENTER);

        this.setContentPane(pan);
        this.setVisible(true);
    }

    /**
     * \fn JPanel buttons()
     * \brief Fonction qui retourne un JPanel pour les boutons
     * \return Retourne un JPanel avec les boutons
     */
    public JPanel buttons() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(retour, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(valider, c);

        return grid;
    }


    /**
     * \fn JScrollPane panelAmis(ArrayList<JButton> selectionAmis, ArrayList<JComboBox> sportAmis)
     * \brief Fonction qui retourne un JScrollPane avec les amis de l'utilisateur
     * \param [in] selectionAmis Amis de l'utilisateur (Type ArrayList<JButton>)
     * \param [in] sportAmis Sport en commun entre les amis et l'utilisateur
     * \return Retourne un JScrollPane avec les amis de l'utilisateur
     */
    public JScrollPane panelAmis(ArrayList<JButton> selectionAmis, ArrayList<JComboBox> sportAmis) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(vosAmis, c);

        int i = 0;
        while(i<selectionAmis.size()) {
            c.gridwidth = 1;
            c.insets = new Insets(0,0,0,0);
            //JLabel idAmis = new JLabel(suggestion.get(buttonsSuggestion.get(i)));
            //c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = (i+1);
            grid.add(selectionAmis.get(i), c);

            c.gridwidth = 1;
            c.insets = new Insets(0,20,0,0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 1;
            c.gridy = (i+1);
            grid.add(sportAmis.get(i), c);

            i++;
        }
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(450, 110));
        return scrollPane;
    }

    /**
     * \fn JPanel complete(JPanel buttons, JScrollPane panelAmis)
     * \brief Fonction qui retourne un panel qui assemble tous les JPanel & JScrollPane
     * \param [in] buttons JPanel (Type JPanel)
     * \param [in] panelAmis JScrollPane (Type JScrollPane)
     * \return Retourne un JPanel
     */
    public JPanel complete(JPanel buttons, JScrollPane panelAmis) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        grid.add(challenge, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        grid.add(panelAmis, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
        grid.add(buttons, c);

        return grid;
    }

    /**
     * \fn void actionPerformed(ActionEvent e)
     * \brief Fonction qui donne des actions aux boutons
     * \param [in] a ActionEvent (Type ActionEvent)
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        int i = 0;
        while(i < selectionAmis.size()) {
            if(Button == selectionAmis.get(i)) {
                for(int j = 0 ; j<selectionAmis.size(); j++) {
                    selectionAmis.get(j).setBackground(Color.white);
                }

                selectionAmis.get(i).setBackground(Color.GREEN);
                //System.out.println("Vous avez selectionner " + amisAvecIndividu.get(i) + " avec le sport : " + sportAmis.get(i).getSelectedItem());
            }
            i++;
        }

        if(Button == valider) {
            for(int j = 0; j<selectionAmis.size(); j++) {
                if(selectionAmis.get(j).getBackground() == Color.GREEN) {
                    System.out.println("Vous avez selectionner " + amisAvecIndividu.get(j) + " avec le sport : " + sportAmis.get(j).getSelectedItem());

                    if((String)sportAmis.get(j).getSelectedItem() == "Tennis") {
                        ArrayList<Float> vitesse1 = TennisManager.VitesseMoy(AuthentificationManager.personne);
                        ArrayList<Float> vitesse2 = TennisManager.VitesseMoy(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ArrayList<Character> issue1 = TennisManager.nbVictoire(AuthentificationManager.personne);
                        ArrayList<Character> issue2 = TennisManager.nbVictoire(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ComparativeLineChart vitesse = new ComparativeLineChart(vitesse1,vitesse2,"Tennis - Comparaison Vitesse Moyenne par Service", "Comparaison Vitesse Moyenne par service en fonction de la séance", "Vitesse Moyenne "+ AuthentificationManager.personne.getId_individu(), "Vitesse Moyenne " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Vitesse Moyenne");
                        vitesse.pack();
                        RefineryUtilities.positionFrameOnScreen(vitesse, 0.25, 0.5);
                        vitesse.setVisible(true);

                        ComparativePieChart issue = new ComparativePieChart(issue1, issue2, "Tennis - Comparaison Pourcentage de réussite", "Pourcentage de réussite " + AuthentificationManager.personne.getId_individu(), "Pourcentage de réussite " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu());
                        issue.pack();
                        RefineryUtilities.positionFrameOnScreen(issue, 0.9, 0.5);
                        issue.setVisible(true);
                    }

                    if((String)sportAmis.get(j).getSelectedItem() == "Cyclisme") {
                        ArrayList<Integer> proteine1 = CyclismeManager.proteine(AuthentificationManager.personne);
                        ArrayList<Integer> proteine2 = CyclismeManager.proteine(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ArrayList<Integer> energie1 = CyclismeManager.energie(AuthentificationManager.personne);
                        ArrayList<Integer> energie2 = CyclismeManager.energie(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ComparativeLineChart proteine = new ComparativeLineChart("Cyclisme - Comparaison Besoin en proteine", "Besoin en proteine en fonction de la séance", "Besoin en proteine " + AuthentificationManager.personne.getId_individu(), "Besoin en proteine " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Besoin en proteine (en g)", proteine1, proteine2);
                        proteine.pack();
                        RefineryUtilities.positionFrameOnScreen(proteine, 0.3, 0.5);
                        proteine.setVisible(true);

                        ComparativeLineChart energie =  new ComparativeLineChart("Cyclisme - Comparaison Dépense énergétique", "Dépense énergétique en fonction de la séance", "Dépense énergétique " + AuthentificationManager.personne.getId_individu(), "Dépense énergétique " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Dépense énergétique (en kcal)", energie1, energie2);
                        energie.pack();
                        RefineryUtilities.positionFrameOnScreen(energie, 0.8, 0.5);
                        energie.setVisible(true);

                    }


                    if((String)sportAmis.get(j).getSelectedItem() == "Natation") {
                        ArrayList<Time> tmpMoyLongueur1 = NatationManager.tpsMoyLongueur(AuthentificationManager.personne);
                        ArrayList<Time> tmpMoyLongueur2 = NatationManager.tpsMoyLongueur(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ArrayList<Integer> nbCalories1 = NatationManager.nbCaloriesPerdues(AuthentificationManager.personne);
                        ArrayList<Integer> nbCalories2 = NatationManager.nbCaloriesPerdues(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ComparativeLineChart tmpMoyLongueur = new ComparativeLineChart(tmpMoyLongueur1, "Natation - Comparaison Temps moyen par longueur", "Temps moyen par longueur en fonction de la séance", "Temps Moyen " + AuthentificationManager.personne.getId_individu(), "Temps Moyen " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Temps moyen (en secondes)", tmpMoyLongueur2);
                        tmpMoyLongueur.pack();
                        RefineryUtilities.positionFrameOnScreen(tmpMoyLongueur, 0.3, 0.5);
                        tmpMoyLongueur.setVisible(true);

                        ComparativeBarChart nbCalorie = new ComparativeBarChart(nbCalories1, nbCalories2, "Natation - Comparaison Nombre de calories perdues", "Nombre de calories perdues par séance", "Calories perdues " + AuthentificationManager.personne.getId_individu(), "Calories perdues " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro séance", "Nombre de calories perdues (en kcal)");
                        nbCalorie.pack();
                        RefineryUtilities.positionFrameOnScreen(nbCalorie, 0.8, 0.5);
                        nbCalorie.setVisible(true);
                    }

                    if((String)sportAmis.get(j).getSelectedItem() == "Echec") {
                        ArrayList<Integer> elo1 = EchecManager.elo(AuthentificationManager.personne);
                        ArrayList<Integer> elo2 = EchecManager.elo(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ArrayList<Integer> concentration1 = EchecManager.concentration(AuthentificationManager.personne);
                        ArrayList<Integer> concentration2 = EchecManager.concentration(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ComparativeLineChart elo = new ComparativeLineChart("Echec - Comparaison Elo","Evolution de l'elo", "Elo " + AuthentificationManager.personne.getId_individu(), "Elo " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Elo", elo1, elo2);
                        elo.pack();
                        RefineryUtilities.positionFrameOnScreen(elo, 0.3, 0.5);
                        elo.setVisible(true);

                        ComparativeLineChart concentration = new ComparativeLineChart("Echec - Comparaison Niveau de concentration","Niveau de concentration", "Niveau de concentration de " + AuthentificationManager.personne.getId_individu(), "Niveau de concentration de " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Niveau de concentration", concentration1, concentration2);
                        concentration.pack();
                        RefineryUtilities.positionFrameOnScreen(concentration, 0.8, 0.5);
                        concentration.setVisible(true);

                    }

                    if((String)sportAmis.get(j).getSelectedItem() == "Course") {
                        ArrayList<Float> vitesseMoy1 = CourseManager.VitesseMoy(AuthentificationManager.personne);
                        ArrayList<Float> vitesseMoy2 = CourseManager.VitesseMoy(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ArrayList<Integer> nbPas1 = CourseManager.nombrePas(AuthentificationManager.personne);
                        ArrayList<Integer> nbPas2 = CourseManager.nombrePas(IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)));

                        ComparativeLineChart vitesseMoy = new ComparativeLineChart(vitesseMoy1, vitesseMoy2, "Course - Comparaison Vitesse Moyenne", "Vitesse Moyenne en fonction de la séance", "Vitesse Moyenne "  + AuthentificationManager.personne.getId_individu(), "Vitesse Moyenne " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Vitesse Moyenne (en m/s)");
                        vitesseMoy.pack();
                        RefineryUtilities.positionFrameOnScreen(vitesseMoy, 0.3, 0.5);
                        vitesseMoy.setVisible(true);

                        ComparativeBarChart nbPas = new ComparativeBarChart(nbPas1, nbPas2, "Course - Comparaison Nombre de pas moyen", "Nombre de pas moyen en fonction de la séance", "Nombre de pas " + AuthentificationManager.personne.getId_individu(), "Nombre de pas " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la séance", "Nb pas");
                        nbPas.pack();
                        RefineryUtilities.positionFrameOnScreen(nbPas, 0.8, 0.5);
                        nbPas.setVisible(true);
                    }

                }
            }
        }
    }
}
