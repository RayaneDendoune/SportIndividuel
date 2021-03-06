package gui;

import chart.BarChart;
import chart.LineChart;
import manager.AuthentificationManager;
import manager.NatationManager;
import manager.RobustesseManager;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * \file Natation.java
 * \brief Classe qui permet de créer l'interface de la page de sport Natation
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface de la page de sport Natation.
 *
 */
//Classe qui représente la page du sport natation en interface graphique
public class Natation extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Natation");

    public static JLabel nb_longueur = new JLabel("Nombre de longueur total ");
    public static JTextField longueur = new JTextField(5);

    public static JLabel temps = new JLabel("Temps (en minutes) ");
    public static JTextField time = new JTextField(5);

    public static JLabel typeNage = new JLabel("Type de nage");
    public static JComboBox nage  = new JComboBox();

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");
    private JButton modifier = new JButton("Modifier");

    private JButton calorie = new JButton("Calories brulées");
    private JButton tempsMoy = new JButton("Temps Moyen/Longueur");


    public Natation() {
        build();
    }

    /**
     * \fn void build()
     * \brief Fonction qui permet la construction de la fenêtre de la page de sport Natation
     */
    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        nage.addItem("Crawl");
        nage.addItem("Dos-Crawle");
        nage.addItem("Brasse");
        nage.addItem("Papillon");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        modifier.addActionListener(this);
        calorie.addActionListener(this);
        tempsMoy.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JPanel grid = donnee();
        JPanel buttons = buttons();
        JPanel graphique = graphiques();

        JPanel complete = complete(grid, buttons, graphique);

        pan.add(complete, BorderLayout.CENTER);

        this.setContentPane(pan);


        this.setVisible(true);
    }

    /**
     * \fn JPanel donnee()
     * \brief Fonction qui retourne un JPanel sur lequel l'utilisateur pourra entrer ses données
     * \return Retourne un JPanel
     */
    public static JPanel donnee() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        /*nage.addItem("Crawl");
        nage.addItem("Dos-Crawlé");
        nage.addItem("Brasse");
        nage.addItem("Papillon");*/

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(nb_longueur, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(longueur, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        grid.add(temps, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        grid.add(time, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        grid.add(typeNage, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        grid.add(nage, c);

        return grid;
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
        grid.add(enregistrer, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        grid.add(modifier, c);

        return grid;
    }

    /**
     * \fn JPanel graphiques()
     * \brief Fonction qui retourne un JPanel pour les graphiques
     * \return Retourne un JPanel avec les graphiques
     */
    public JPanel graphiques() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(calorie, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(tempsMoy, c);

        return grid;
    }

    /**
     * \fn JPanel complete(JPanel grid, JPanel panel, JPanel graphique)
     * \brief Fonction qui retourne un panel qui assemble tous les JPanel
     * \param [in] grid JPanel (Type JPanel)
     * \param [in] panel JPanel (Type JPanel)
     * \param [in] graphique JPanel (Type JPanel)
     * \return Retourne un JPanel
     */
    public JPanel complete(JPanel grid, JPanel panel, JPanel graphique) {
        JPanel complete = new JPanel();
        complete.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        complete.add(sport, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        complete.add(grid, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        complete.add(panel, c);

        c.insets = new Insets(50,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
        complete.add(graphique, c);

        return complete;
    }

    /**
     * \fn void actionPerformed(ActionEvent e)
     * \brief Fonction qui donne des actions aux boutons
     * \param [in] a ActionEvent (Type ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        if(Button == enregistrer) {
            /*System.out.println(longueur.getText());
            System.out.println(time.getText());
            System.out.println(nage.getSelectedItem());*/

            final java.sql.Date dateSQL = new java.sql.Date(new Date().getTime()) ;

            if(!longueur.getText().isBlank() && !time.getText().isBlank()) {
                if(RobustesseManager.StringToFloat(longueur.getText()) == 0 && RobustesseManager.StringToInteger(time.getText()) == 0) {
                    int hour = Integer.parseInt(time.getText()) / 60;
                    int min = Integer.parseInt(time.getText()) % 60;

                    final Time timeTotal = new Time(hour, min, 00);

                    int calorie = (int) NatationManager.calories(AuthentificationManager.personne, (String) nage.getSelectedItem(), Integer.parseInt(time.getText()));

                    float tempsMoy = NatationManager.tempsMoyLongueur(Integer.parseInt(longueur.getText()), Integer.parseInt(time.getText()));
                    int heure = (int) tempsMoy / 3600;
                    int tempsT = (int) tempsMoy - (heure * 3600);

                    int minutes = tempsT / 60;
                    int secondes = tempsT % 60;

                    final Time tempsLongueur = new Time(heure, minutes, secondes);

                    NatationManager.ajouterNatation(NatationManager.idSeance(AuthentificationManager.personne), Integer.parseInt(longueur.getText()), timeTotal, (String) nage.getSelectedItem(), calorie, tempsLongueur, dateSQL, AuthentificationManager.personne);
                }
                else {
                    if(RobustesseManager.StringToFloat(longueur.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(longueur.getText()), nb_longueur);  }
                    if(RobustesseManager.StringToInteger(time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(time.getText()), temps); }
                }
            }
            else {
                RobustesseManager.erreur(4, null);
            }
        }

        if(Button == modifier) {
            new ModifSport(2);
            dispose();
        }

        if(Button == tempsMoy){
            ArrayList<Time> tpsMoy = NatationManager.tpsMoyLongueur(AuthentificationManager.personne);
            LineChart lcl = new LineChart(tpsMoy, "Natation - Temps moyen par longueur", "Temps moyen par longueur en fonction de la séance" , "Temps moyen", "Numéro de la seance", "Temps moyen (en secondes)");
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }
        if(Button == calorie) {
            ArrayList<Integer> calorie = NatationManager.nbCaloriesPerdues(AuthentificationManager.personne);
            BarChart bc = new BarChart("Natation - Nombre de calories perdues", "Nombre de calories perdues par séance", calorie, "Calories perdues", "Numéro séance", "Nombre de calories perdues (en kcal)");
            bc.pack();
            RefineryUtilities.centerFrameOnScreen(bc);
            bc.setVisible(true);
        }
    }

    /*public static void main(String[] args) {
        new Natation();
    }*/
}
