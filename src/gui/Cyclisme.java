package gui;

import manager.AuthentificationManager;
import manager.CyclismeManager;
import model.Seance_cyclisme;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Cyclisme extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Cyclisme");

    private JLabel niveauActivitePhysique = new JLabel("Niveau activité physique ");
    private JComboBox niveau = new JComboBox();

    private JLabel poids = new JLabel("Poids ");
    private JTextField weight = new JTextField(5);

    private JLabel objSeance = new JLabel("Objectif de la séance");
    private JComboBox objectif  = new JComboBox();

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");

    private JButton proteine = new JButton("Besoin en protéïne");
    //private JButton energie = new JButton("Dépense énergétique");

    public Cyclisme() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        objectif.addItem("Prise de masse");
        objectif.addItem("Maigrir");
        objectif.addItem("Inventer"); //Inventez des niveaux !!!!!!!!
        objectif.addItem("Jsp");

        niveau.addItem("Sédentaire");
        niveau.addItem("Actif");
        niveau.addItem("Sportif");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        proteine.addActionListener(this);
        //energie.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JPanel grid = donnee();
        JPanel buttons = buttons();
        JPanel graphique = graphiques();
        JPanel complete = complete(grid, buttons, graphique);

        pan.add(complete, BorderLayout.CENTER);

        this.setContentPane(pan);

        this.setVisible(true);

    }

    public JPanel donnee() {
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

        /*c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(energie, c);*/

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
            int nrj = CyclismeManager.depenseNRJ(AuthentificationManager.personne, (String)niveau.getSelectedItem());

            CyclismeManager.ajouterCyclisme(CyclismeManager.idSeance(AuthentificationManager.personne), (String)niveau.getSelectedItem(), Float.parseFloat(weight.getText()), (String)objectif.getSelectedItem(), nrj, 270, AuthentificationManager.personne);
        }

        if(Button == proteine) {
            ArrayList<Integer> proteine = CyclismeManager.proteine(AuthentificationManager.personne);
            ArrayList<Integer> energie = CyclismeManager.energie(AuthentificationManager.personne);

            LineChart lcl = new LineChart(proteine, energie, "Temps moyen par longueur en fonction de la séance", "Besoin en proteine", "Dépense énergétique" , "Numéro de la seance", "Temps moyen (en secondes)");
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }

        /*if(Button == energie) {

        }*/
    }

    /*public static void main(String[] args) {
        new Cyclisme();
    }*/
}
