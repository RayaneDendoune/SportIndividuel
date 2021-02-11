package gui;

import manager.AuthentificationManager;
import manager.CourseManager;
import model.Individu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Course extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Course");

    private JLabel distance = new JLabel("Distance ");
    private JTextField dist = new JTextField(5);

    private JLabel temps = new JLabel("Temps (en minutes) ");
    private JTextField time = new JTextField(5);

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");

    public Course() {
        build();
    }

    public void build() { //Création de la grille
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        retour.addActionListener(this);
        enregistrer.addActionListener(this);

        pan.setLayout(new BorderLayout());
        JPanel grid = donnee();
        JPanel panel = buttons();

        JPanel complete = complete(grid, panel);

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


    public static void main(String[] args) {
        new Course();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        if(Button == enregistrer) {
            final java.sql.Date dateSQL = new java.sql.Date(new Date().getTime()) ;

            int heure = Integer.parseInt(time.getText())/60;
            int min = Integer.parseInt(time.getText())%60;

            final Time timeSQL = new Time(heure, min,00);
            //System.out.println(dateSQL);

            //System.out.println("Nb occ : " + CourseManager.nbSeanceCourse(AuthentificationManager.personne));

            CourseManager.ajouterCourse(CourseManager.idSeance(AuthentificationManager.personne), Float.parseFloat(dist.getText()), timeSQL, CourseManager.vitesseMoyenne(Float.parseFloat(dist.getText()), Integer.parseInt(time.getText())), CourseManager.nbPas(Float.parseFloat(dist.getText())), dateSQL, AuthentificationManager.personne);
        }
    }
}
