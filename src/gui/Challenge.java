package gui;

import chart.ComparativeLineChart;
import chart.LineChart;
import manager.*;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                        ComparativeLineChart comparativeLineChart = new ComparativeLineChart(vitesse1,vitesse2,"Tennis - Comparaison Vitesse Moyenne par Service", "Comparaison Vitesse Moyenne par service en fonction de la séance", "Vitesse Moyenne "+ AuthentificationManager.personne.getId_individu(), "Vitesse Moyenne " + IndividuManager.rechercheIndividuParId(amisAvecIndividu.get(j)).getId_individu(), "Numéro de la seance", "Vitesse Moyenne");
                        comparativeLineChart.pack();
                        RefineryUtilities.positionFrameOnScreen(comparativeLineChart, 50, 50);
                        //RefineryUtilities.centerFrameOnScreen(comparativeLineChart);
                        comparativeLineChart.setVisible(true);

                    }
                }
            }
        }
    }
}
