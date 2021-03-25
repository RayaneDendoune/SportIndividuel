package gui;

import chart.LineChart;
import chart.PieChart;
import manager.AuthentificationManager;
import manager.RobustesseManager;
import manager.TennisManager;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Classe qui représente la page du sport tennis en interface graphique
public class Tennis extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel sport = new JLabel("Tennis");

    public static JLabel premierService = new JLabel("Premier Service (en km/h)");
    public static JTextField PS = new JTextField(5);

    public static JLabel deuxiemeService = new JLabel("Deuxieme Service (en km/h) ");
    public static JTextField DS = new JTextField(5);

    public static JLabel troisiemeService = new JLabel("Troisieme Service (en km/h) ");
    public static JTextField TS = new JTextField(5);

    public static JLabel issueMatch = new JLabel("Issue du match ");
    public static JComboBox issue = new JComboBox();

    public static JLabel nbSet = new JLabel("Nombre de set ");
    public static JTextField set = new JTextField(5);

    private JButton enregistrer = new JButton("Enregistrer");
    private JButton retour = new JButton("Retour");
    private JButton modifier = new JButton("Modifier");

    private JButton vitesse = new JButton("Vitesse moyenne");
    private JButton pourcentageReussite = new JButton("Pourcentage de réussite");

    public Tennis() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        issue.addItem("Victoire");
        issue.addItem("Défaite");

        retour.addActionListener(this);
        enregistrer.addActionListener(this);
        modifier.addActionListener(this);
        vitesse.addActionListener(this);
        pourcentageReussite.addActionListener(this);

        pan.setLayout(new BorderLayout());

        JPanel grid = donnee();
        JPanel buttons = buttons();
        JPanel graphique = graphiques();
        JPanel complete = complete(grid, buttons,graphique);

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
        grid.add(premierService, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(PS, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        grid.add(deuxiemeService, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        grid.add(DS, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        grid.add(troisiemeService, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        grid.add(TS, c);

        //Ici
        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        grid.add(nbSet, c);

        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        grid.add(set, c);

        c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        grid.add(issueMatch, c);

        c.ipadx = 80; //Remettre la taille en question
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
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
        grid.add(vitesse, c);

        c.insets = new Insets(0,20,0,0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid.add(pourcentageReussite, c);

        return grid;
    }

    public JPanel complete(JPanel grid, JPanel panel,JPanel graphique) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        if(Button == enregistrer) {
           /* System.out.println(PS.getText());
            System.out.println(DS.getText());
            System.out.println(TS.getText());
            System.out.println(set.getText());
            System.out.println(issue.getSelectedItem());*/
            if(!PS.getText().isBlank() && !DS.getText().isBlank() && !TS.getText().isBlank() && !set.getText().isBlank()) {
                if(RobustesseManager.StringToFloat(PS.getText()) == 0 && RobustesseManager.StringToFloat(DS.getText()) == 0 && RobustesseManager.StringToFloat(TS.getText()) == 0 && RobustesseManager.StringToInteger(set.getText()) == 0) {
                    float vitesse = TennisManager.vitesseService(Float.parseFloat(PS.getText()), Float.parseFloat(DS.getText()), Float.parseFloat(TS.getText()));
                    TennisManager.ajouterTennis(TennisManager.idSeance(AuthentificationManager.personne), Float.parseFloat(PS.getText()), Float.parseFloat(DS.getText()), Float.parseFloat(TS.getText()), Integer.parseInt(set.getText()), TennisManager.issueMatch((String) issue.getSelectedItem()), vitesse, AuthentificationManager.personne);
                }

                else {
                    if(RobustesseManager.StringToFloat(PS.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(PS.getText()), premierService);  }
                    if(RobustesseManager.StringToFloat(DS.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(DS.getText()), deuxiemeService); }
                    if(RobustesseManager.StringToFloat(TS.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToFloat(TS.getText()), troisiemeService); }
                    if(RobustesseManager.StringToInteger(set.getText()) != 0) { RobustesseManager.erreur(RobustesseManager.StringToInteger(set.getText()), nbSet); }
                }
            }
            else {
                RobustesseManager.erreur(4, null);
            }
        }

        if(Button==modifier){
            new ModifSport(3);
            dispose();
        }
        if(Button==vitesse){
            ArrayList<Float> vitesse = TennisManager.VitesseMoy(AuthentificationManager.personne);
            LineChart lcl = new LineChart("Tennis - Vitesse Moyenne par Service", "Vitesse Moyenne par service en fonction de la séance", vitesse, "Vitesse Moyenne", "Numéro de la seance", "Vitesse Moyenne");
            lcl.pack();
            RefineryUtilities.centerFrameOnScreen(lcl);
            lcl.setVisible(true);
        }


        if(Button==pourcentageReussite) {
            ArrayList<Character> issue = TennisManager.nbVictoire(AuthentificationManager.personne);
            PieChart pc = new PieChart("Tennis - Pourcentage de réussite", issue, "Pourcentage de réussite");
            pc.pack();
            RefineryUtilities.centerFrameOnScreen(pc);
            pc.setVisible(true);
        }

    }

    /*public static void main(String[] args) {
        new Tennis();
    }*/
}
