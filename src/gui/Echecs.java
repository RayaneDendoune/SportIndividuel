package gui;

import chart.LineChart;
import manager.AuthentificationManager;
import manager.EchecManager;
import manager.RobustesseManager;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

//Classe qui représente la page du sport echecs en interface graphique
public class Echecs extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Echecs");

    public static String competence = "Votre Compétence Mental est au niveau " + EchecManager.competenceMentale(AuthentificationManager.personne);
    public static JLabel competenceMentale = new JLabel(competence);

    public static JLabel eloAdv = new JLabel("Elo de l'adversaire ");
    public static JTextField adversaire = new JTextField(5);

    public static JLabel temps = new JLabel("Temps (en minutes) ");
    public static JTextField time = new JTextField(5);

    public static JLabel issueMatch = new JLabel("Issue du match ");
    public static JComboBox issue = new JComboBox();

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");
    private JButton modifier = new JButton("Modifier");

    private JButton elo = new JButton("Classement ELO");
    private JButton nvConcentration = new JButton("Niveau de concentration");

    public Echecs() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        issue.addItem("Victoire");
        issue.addItem("Nul");
        issue.addItem("Défaite");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        modifier.addActionListener(this);
        elo.addActionListener(this);
        nvConcentration.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JPanel grid = donnee();
        JPanel buttons = buttons();
        JPanel graphique = graphiques();
        JPanel complete = complete(grid, buttons, graphique);

        pan.add(complete, BorderLayout.CENTER);

        this.setContentPane(pan);


        this.setVisible(true);
    }

    public static JPanel donnee() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(eloAdv, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(adversaire, c);

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
        grid.add(issueMatch, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        grid.add(issue, c);

        return grid;
    }

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

    public JPanel graphiques() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(elo, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(nvConcentration, c);

        return grid;
    }


    public JPanel complete(JPanel grid, JPanel panel, JPanel graphique) {
        JPanel complete = new JPanel();
        complete.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        complete.add(sport, c);

        c.insets = new Insets(5,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        complete.add(competenceMentale, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        complete.add(grid, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
        complete.add(panel, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 4;
        complete.add(graphique, c);

        return complete;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        if(Button == enregistrer) {
            /*System.out.println(adversaire.getText());
            System.out.println(time.getText());
            System.out.println(issue.getSelectedItem());*/
            if(!adversaire.getText().isBlank() && !time.getText().isBlank()) {
                if(RobustesseManager.StringToFloat(adversaire.getText()) == 0 && RobustesseManager.StringToInteger(time.getText()) == 0) {
                    int heure = Integer.parseInt(time.getText()) / 60;
                    int min = Integer.parseInt(time.getText()) % 60;

                    final Time timeSQL = new Time(heure, min, 00);

                    char issuePartie = EchecManager.issue((String) issue.getSelectedItem());
                    String competence = EchecManager.competenceMentale(AuthentificationManager.personne);
                    int niveauConcentration = EchecManager.niveauConcentration(AuthentificationManager.personne, issuePartie, competence);

                    int newElo = EchecManager.newElo(AuthentificationManager.personne, Integer.parseInt(adversaire.getText()), (String) issue.getSelectedItem());
                    //EchecManager.updateValue(AuthentificationManager.personne, 1204);
                    EchecManager.ajouterEchec(EchecManager.idSeance(AuthentificationManager.personne), Integer.parseInt(adversaire.getText()), newElo, timeSQL, competence, issuePartie, niveauConcentration, AuthentificationManager.personne);
                    //System.out.println("Votre elo actuel est : " + AuthentificationManager.personne.getElo() + "    Futur Elo : " + EchecManager.newElo(AuthentificationManager.personne, Integer.parseInt(adversaire.getText()), (String)issue.getSelectedItem()));

                    EchecManager.updateValue(AuthentificationManager.personne, newElo);
                }
                else {
                    if(RobustesseManager.StringToInteger(adversaire.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(adversaire.getText()), eloAdv);  }
                    if(RobustesseManager.StringToInteger(time.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(time.getText()), temps); }
                }
            }
            else {
                RobustesseManager.erreur(4, null);
            }
        }
        if(Button==modifier){
            new ModifSport(5);
            dispose();
        }

        if(Button == elo){
            ArrayList<Integer> eloArray = EchecManager.elo(AuthentificationManager.personne);

            LineChart lcl = new LineChart("Echec - Elo","Evolution de l'elo", "Elo" , "Numéro de la seance", "Elo", eloArray);
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }

        if(Button == nvConcentration) {
            ArrayList<Integer> niveau = EchecManager.concentration(AuthentificationManager.personne);

            LineChart lcl = new LineChart("Echec - Niveau de concentration","Niveau de concentration", "Niveau de concentration" , "Numéro de la seance", "Niveau de concentration", niveau);
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Echecs();
    }
}
