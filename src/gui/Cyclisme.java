package gui;

import chart.LineChart;
import manager.AuthentificationManager;
import manager.CyclismeManager;
import manager.RobustesseManager;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Cyclisme extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Cyclisme");

    public static JLabel niveauActivitePhysique = new JLabel("Niveau activité physique ");
    public static JComboBox niveau = new JComboBox();

    public static JLabel poids = new JLabel("Poids (en kg) ");
    public static JTextField weight = new JTextField(5);

    public static JLabel objSeance = new JLabel("Objectif de la séance");
    public static JComboBox objectif  = new JComboBox();

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");
    private JButton modifier = new JButton("Modifier");

    private JButton proteine = new JButton("Besoin en proteine");
    private JButton energie = new JButton("Dépense énergétique");

    public Cyclisme() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        objectif.addItem("Prise de masse");
        objectif.addItem("Force musculaire");
        objectif.addItem("Perte de poids"); //Inventez des niveaux !!!!!!!!
        objectif.addItem("Endurance musculaire");

        niveau.addItem("Sedentaire");
        niveau.addItem("Actif");
        niveau.addItem("Sportif");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        modifier.addActionListener(this);
        proteine.addActionListener(this);
        energie.addActionListener(this);

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
        grid.add(poids, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(weight, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        grid.add(niveauActivitePhysique, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        grid.add(niveau, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        grid.add(objSeance, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        grid.add(objectif, c);

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
        grid.add(proteine, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(energie, c);

        return grid;
    }

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

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
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
            /*System.out.println(niveau.getSelectedItem());
            System.out.println(weight.getText());
            System.out.println(objectif.getSelectedItem());*/
            if(!weight.getText().isBlank()) {
                if(RobustesseManager.StringToFloat(weight.getText()) == 0) {
                    int nrj = CyclismeManager.depenseNRJ(AuthentificationManager.personne, (String) niveau.getSelectedItem());
                    int besoin = CyclismeManager.besoinProteine((int)Float.parseFloat(weight.getText()));

                    CyclismeManager.ajouterCyclisme(CyclismeManager.idSeance(AuthentificationManager.personne), (String) niveau.getSelectedItem(), Float.parseFloat(weight.getText()), (String) objectif.getSelectedItem(), nrj, besoin, AuthentificationManager.personne);
                }
                else {
                    if(RobustesseManager.StringToFloat(weight.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(weight.getText()), poids);  }
                }
            }
            else {
                RobustesseManager.erreur(4, null);
            }
        }

        if(Button==modifier){
            new ModifCourse(4);
            dispose();
        }

        if(Button == proteine) {
            ArrayList<Integer> proteine = CyclismeManager.proteine(AuthentificationManager.personne);

            LineChart lcl = new LineChart("Cyclisme - Besoin en proteine", "Besoin en proteine en fonction de la séance" ,  "Besoin en proteine" , "Numéro de la seance", "Besoin en proteine (en g)", proteine);
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }

        if(Button == energie) {
            ArrayList<Integer> energie = CyclismeManager.energie(AuthentificationManager.personne);

            LineChart lcl = new LineChart("Cyclisme - Dépense énergétique", "Dépense énergétique en fonction de la séance" , "Dépense énergétique" , "Numéro de la seance", "Dépense énergétique (en kcal)", energie);
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }
    }

    /*public static void main(String[] args) {
        new Cyclisme();
    }*/
}
