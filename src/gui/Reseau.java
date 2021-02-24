package gui;

import manager.*;
import model.Individu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Reseau extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel reseau = new JLabel("Réseau Social");

    private JButton testAmis = new JButton("Test amis");
    private JButton retour = new JButton("Retour");


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

        JPanel buttons = buttons();
        JPanel complete = complete(buttons);

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

    public JPanel complete(JPanel buttons) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(reseau, c);

        c.insets = new Insets(20,0,0,0);
        c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
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
    }
}
