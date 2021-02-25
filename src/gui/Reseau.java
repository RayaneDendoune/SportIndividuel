package gui;

import manager.*;
import model.Demande;
import model.Individu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Reseau extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel reseau = new JLabel("Réseau Social");
    private JLabel titreSuggestion = new JLabel("Suggestion d'amis");

    private JButton testAmis = new JButton("Test amis");
    private JButton retour = new JButton("Retour");

    ArrayList<Individu> amis = ReseauManager.sportJouer(AuthentificationManager.personne);
    ArrayList<Button> buttons = new ArrayList<Button>();
    HashMap<Button, String> suggestion = new HashMap<Button, String>();


    public Reseau() {
        build();
    }

    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        testAmis.addActionListener(this);
        retour.addActionListener(this);

        for(int i = 0; i<amis.size(); i++) {
            buttons.add(new Button("Demander en amis"));
            buttons.get(i).addActionListener(this);
        }

        for(int i = 0; i<amis.size(); i++) {
            suggestion.put(buttons.get(i), amis.get(i).getId_individu());
        }

        JPanel buttons = buttons();
        JScrollPane disposition = disposition();
        JPanel complete = complete(buttons, disposition);


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
        grid.add(testAmis, c);

        return grid;
    }

    public JScrollPane disposition() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(titreSuggestion, c);

        int i = 0;
        while(i<amis.size()) {
            c.gridwidth = 1;
            c.insets = new Insets(0,0,0,0);
            JLabel idAmis = new JLabel(suggestion.get(buttons.get(i)));
            c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = (i+1);
            grid.add(idAmis, c);

            c.gridwidth = 1;
            c.insets = new Insets(0,20,0,0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 1;
            c.gridy = (i+1);
            grid.add(buttons.get(i), c);

            i++;
        }
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(450, 110));
        return scrollPane;
    }

    public JPanel complete(JPanel buttons, JScrollPane disposition) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        grid.add(reseau, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        grid.add(disposition, c);

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

        if (Button == testAmis) {
            //ReseauManager.sportJouer(AuthentificationManager.personne);
            ArrayList<Individu> individus = ReseauManager.sportJouer(AuthentificationManager.personne);
            for(int i = 0; i<individus.size(); i++) {
                System.out.println(individus.get(i).getPrenom() + " " + individus.get(i).getNom());
            }
        }

        int i = 0;
        while(i<buttons.size()) {
            if(Button == buttons.get(i)) {
                System.out.println("Vous avez appuyer sur " + suggestion.get(buttons.get(i)));
                buttons.get(i).setLabel("Demande envoyée");
                buttons.get(i).setBackground(Color.GREEN);

                if(buttons.get(i).isEnabled()) {
                    DemandeManager.ajout(suggestion.get(buttons.get(i)), AuthentificationManager.personne.getId_individu());
                    buttons.get(i).setEnabled(false);
                }

            }
            i++;
        }
    }
}
