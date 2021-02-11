package gui;

import manager.AuthentificationManager;
import manager.CourseManager;
import manager.NatationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Date;

public class Natation extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Natation");

    private JLabel nb_longueur = new JLabel("Nombre de longueur total ");
    private JTextField longueur = new JTextField(5);

    private JLabel temps = new JLabel("Temps (en minutes) ");
    private JTextField time = new JTextField(5);

    private JLabel typeNage = new JLabel("Type de nage");
    private JComboBox nage  = new JComboBox();

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");


    public Natation() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        nage.addItem("Crawl");
        nage.addItem("Dos-Crawlé");
        nage.addItem("Brasse");
        nage.addItem("Papillon");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JPanel grid = donnee();
        JPanel buttons = buttons();
        JPanel complete = complete(grid, buttons);

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

    public JPanel complete(JPanel grid, JPanel panel) {
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
            /*System.out.println(longueur.getText());
            System.out.println(time.getText());
            System.out.println(nage.getSelectedItem());*/

            final java.sql.Date dateSQL = new java.sql.Date(new Date().getTime()) ;

            int hour = Integer.parseInt(time.getText())/60;
            int min = Integer.parseInt(time.getText())%60;

            final Time timeTotal = new Time(hour, min,00);

            int calorie = (int)NatationManager.calories(AuthentificationManager.personne, (String)nage.getSelectedItem(), Integer.parseInt(time.getText()));

            float tempsMoy = NatationManager.tempsMoyLongueur(Integer.parseInt(longueur.getText()), Integer.parseInt(time.getText()));
            int heure = (int)tempsMoy/3600;
            int tempsT = (int)tempsMoy - (heure*3600);

            int minutes = tempsT/60;
            int secondes = tempsT%60;

            final Time tempsLongueur = new Time(heure, minutes, secondes);

            NatationManager.ajouterNatation(NatationManager.idSeance(AuthentificationManager.personne), Integer.parseInt(longueur.getText()), timeTotal, (String)nage.getSelectedItem(), calorie, tempsLongueur, dateSQL, AuthentificationManager.personne);
        }
    }

    /*public static void main(String[] args) {
        new Natation();
    }*/
}