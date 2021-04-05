package gui;

import chart.BarChart;
import chart.LineChart;
import manager.AuthentificationManager;
import manager.CourseManager;
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
 * \file Course.java
 * \brief Classe qui permet de créer l'interface de la page de sport Course
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface de la page de sport Course.
 *
 */
//Classe qui représente la page du sport course en interface graphique
public class Course extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Course");

    public static JLabel distance = new JLabel("Distance (en km)");
    public static JTextField dist = new JTextField(5);

    public static JLabel temps = new JLabel("Temps (en minutes) ");
    public static JTextField time = new JTextField(5);

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");
    private JButton modifier = new JButton("Modifier");

    private JButton vitesse = new JButton("Vitesse moyenne");
    private JButton nbPas = new JButton("Nombre de pas");

    public Course() {
        build();
    }

    /**
     * \fn void build()
     * \brief Fonction qui permet la construction de la fenêtre de la page de sport Course
     */
    public void build() { //Création de la grille
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        modifier.addActionListener(this);
        vitesse.addActionListener(this);
        nbPas.addActionListener(this);

        pan.setLayout(new BorderLayout());
        JPanel grid = donnee();
        JPanel panel = buttons();
        JPanel graphique = graphiques();

        JPanel complete = complete(grid, panel, graphique);

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

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(distance, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(dist, c);

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
        grid.add(vitesse, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(nbPas, c);

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


    public static void main(String[] args) {
        new Course();
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
            final java.sql.Date dateSQL = new java.sql.Date(new Date().getTime()) ;
            if(!dist.getText().isBlank() && !time.getText().isBlank()){

                if(RobustesseManager.StringToFloat(dist.getText()) == 0 && RobustesseManager.StringToInteger(time.getText()) == 0) {
                    int heure = Integer.parseInt(time.getText()) / 60;
                    int min = Integer.parseInt(time.getText()) % 60;
                    final Time timeSQL = new Time(heure, min, 00);

                    CourseManager.ajouterCourse(CourseManager.idSeance(AuthentificationManager.personne), Float.parseFloat(dist.getText()), timeSQL, CourseManager.vitesseMoyenne(Float.parseFloat(dist.getText()), Integer.parseInt(time.getText())), CourseManager.nbPas(Float.parseFloat(dist.getText())), dateSQL, AuthentificationManager.personne);
                }
                else {
                    if(RobustesseManager.StringToFloat(dist.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(dist.getText()), distance);  }
                    if(RobustesseManager.StringToInteger(time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(time.getText()), temps); }
                }
            }
            else {
                RobustesseManager.erreur(4, null);
            }

        }

        if(Button == modifier) {
            new ModifSport(1);
            dispose();
        }

        if(Button == vitesse) {
            ArrayList<Float> vitesse = CourseManager.VitesseMoy(AuthentificationManager.personne);
            LineChart lcl = new LineChart("Course - Vitesse Moyenne", "Vitesse Moyenne en fonction de la séance" , vitesse, "Vitesse Moyenne", "Numéro de la seance", "Vitesse Moyenne (en m/s)");
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }

        if(Button == nbPas) {
            ArrayList<Integer> nombrePas = CourseManager.nombrePas(AuthentificationManager.personne);
            BarChart bc = new BarChart("Course - Nombre de pas moyen", "Nombre de pas moyen en fonction de la séance",nombrePas, "Nombre de pas", "Numéro de la séance", "Nb pas");
            bc.pack();
            RefineryUtilities.centerFrameOnScreen(bc);
            bc.setVisible(true);
        }
    }
}
