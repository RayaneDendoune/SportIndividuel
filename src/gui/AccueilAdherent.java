package gui;

import javax.swing.*;
import java.awt.*;

public class AccueilAdherent extends JFrame {
    private JPanel pan = new JPanel();
    private JPanel pTitre = new JPanel();
    private JPanel pSport = new JPanel();

    private JLabel titre = new JLabel("Bienvenue Avishka !");
    private JLabel titre2 = new JLabel("Sélectionner l'activité voulue : ");
    private JLabel imgCourse = new JLabel(new ImageIcon("src/images/course.jpg"));
    private JLabel imgNatation = new JLabel(new ImageIcon("src/images/natation.jpg"));
    private JLabel imgCyclisme = new JLabel(new ImageIcon("src/images/cyclisme.jpg"));
    private JLabel imgEchecs = new JLabel(new ImageIcon("src/images/echecs.png"));
    private JLabel imgTennis = new JLabel(new ImageIcon("src/images/tennis.jpg"));
    private JLabel imgRs = new JLabel(new ImageIcon("src/images/rs.jpg"));


    private JButton course = new JButton("Course");
    private JButton cyclisme = new JButton("Cyclisme");
    private JButton natation = new JButton("Natation");
    private JButton tennis = new JButton("Tennis");
    private JButton echecs = new JButton("Echecs");
    private JButton rs = new JButton("Réseau social");

    //GridLayout gl = new GridLayout(2,3);

    public AccueilAdherent() {
        build();
    }

    public void build() {
        this.setTitle("Choix de l'activité");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        pan.setLayout(new BorderLayout());

        pTitre.add(titre);
        pTitre.add(titre2);
        pan.add(pTitre, BorderLayout.NORTH);


        /*pSport.setLayout((new GridLayout(4, 3)));
        pSport.add(img);
        pSport.add(course);
        pSport.add(img);
        pSport.add(cyclisme);
        pSport.add(img);
        pSport.add(natation);
        pSport.add(img);
        pSport.add(tennis);
        pSport.add(img);
        pSport.add(echecs);
        pSport.add(img);
        pSport.add(rs);
     //   pan.add(pSport);*/



        JPanel gridSport = gridSport();
        pSport.add(gridSport());
        pan.add(pSport);

        this.setContentPane(pan);
        setVisible(true);


    }

    public JPanel gridSport() {
        JPanel gridSport = new JPanel();
        gridSport.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 5;
        c.gridx = 0;
        c.gridy = 0;
        gridSport.add(imgCourse, c);

        c.weightx = 5;
        c.gridx = 0;
        c.gridy = 1;
        gridSport.add(course, c);

        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 1;
        c.gridy = 0;
        gridSport.add(imgCyclisme, c);

        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 1;
        c.gridy = 1;
        gridSport.add(cyclisme, c);

        c.ipadx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 2;
        c.gridy = 0;
        gridSport.add(natation, c);

        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 0;
        c.gridy = 1;
        gridSport.add(tennis, c);

        c.ipadx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 1;
        c.gridy = 1;
        gridSport.add(echecs, c);

        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5;
        c.gridx = 2;
        c.gridy = 1;
        gridSport.add(rs, c);

        return gridSport;
}






    public static void main(String[] args) {
        new AccueilAdherent();
    }
}
