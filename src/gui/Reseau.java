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

/**
 * \file Reseau.java
 * \brief Classe qui permet de créer l'interface de la page de Reseau Social
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface de la page de Reseau Social.
 *
 */
//Classe qui représente la page de réseau social en interface graphique
public class Reseau extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();

    private JLabel reseau = new JLabel("Réseau Social");
    private JLabel titreSuggestion = new JLabel("Suggestion d'amis");
    private JLabel titreDemande = new JLabel("Demande d'amis");

    private JButton testAmis = new JButton("Mes amis");
    private JButton retour = new JButton("Retour");

    ArrayList<Individu> amis = ReseauManager.sportJouer(AuthentificationManager.personne);
    ArrayList<String> idDemandeEnvoyer = DemandeManager.idDemandeEnvoyer(AuthentificationManager.personne);
    ArrayList<String> dejaAmis = AmisManager.dejaAmis(AuthentificationManager.personne);

    ArrayList<String> invitation = AmisManager.invitation(AuthentificationManager.personne);

    ArrayList<Button> buttonsSuggestion = new ArrayList<Button>();
    ArrayList<Button> buttonsAccepter = new ArrayList<Button>();
    ArrayList<Button> buttonsDecliner = new ArrayList<Button>();

    HashMap<Button, String> suggestion = new HashMap<Button, String>();


    public Reseau() {
        build();
    }

    /**
     * \fn void build()
     * \brief Fonction qui permet la construction de la fenêtre de la page de Reseau Social
     */
    public void build() {
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        testAmis.addActionListener(this);
        retour.addActionListener(this);

        //Si on a trop de ligne, on met ce for dans une fonction dans DemandeManager
        for(int j = 0; j<idDemandeEnvoyer.size(); j++) {
            for(int i = 0; i<amis.size(); i++) {
                if(amis.get(i).getId_individu().equals(idDemandeEnvoyer.get(j))) {
                    amis.remove(amis.get(i));
                }
            }
        }
        for(int j = 0; j<invitation.size(); j++) {
            for(int i = 0; i<amis.size(); i++) {
                if(amis.get(i).getId_individu().equals(invitation.get(j))) {
                    amis.remove(amis.get(i));
                }
            }
        }
        for(int j = 0; j<dejaAmis.size(); j++) {
            for(int i = 0; i<amis.size(); i++) {
                if(amis.get(i).getId_individu().equals(dejaAmis.get(j))) {
                    amis.remove(amis.get(i));
                }
            }
        }


        for(int i = 0; i<amis.size(); i++) {
            buttonsSuggestion.add(new Button("Demander en amis"));
            buttonsSuggestion.get(i).addActionListener(this);
        }

        for(int i = 0; i<amis.size(); i++) {
            suggestion.put(buttonsSuggestion.get(i), amis.get(i).getId_individu());
        }

        for(int i = 0; i<invitation.size(); i++) {
            buttonsAccepter.add(new Button("Accepter"));
            buttonsAccepter.get(i).addActionListener(this);
            buttonsDecliner.add(new Button("Decliner"));
            buttonsDecliner.get(i).addActionListener(this);
        }

        JPanel buttons = buttons();
        JScrollPane panelSuggestion = panelSuggestion();
        JScrollPane panelDemande = panelDemande();
        JPanel complete = complete(buttons, panelSuggestion, panelDemande);


        pan.add(complete, BorderLayout.CENTER);

        this.setContentPane(pan);
        this.setVisible(true);
    }

    /**
     * \fn JPanel buttons()
     * \brief Fonction qui retourne un JPanel pour les boutons
     * \return Retourne un JPanel avec les boutons
     */
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

    /**
     * \fn JScrollPane panelSuggestion()
     * \brief Fonction qui retourne un JScollPane avec les suggestions d'Amis
     * \return Retourne un JScollPane
     */
    public JScrollPane panelSuggestion() {
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
            JLabel idAmis = new JLabel(suggestion.get(buttonsSuggestion.get(i)));
            //c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
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
            grid.add(buttonsSuggestion.get(i), c);

            i++;
        }
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(450, 110));
        return scrollPane;
    }

    /**
     * \fn JScrollPane panelDemande()
     * \brief Fonction qui retourne un JScollPane avec les demandes d'amis
     * \return Retourne un JScollPane
     */
    public JScrollPane panelDemande() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        grid.add(titreDemande, c);

        int i = 0;
        while(i<invitation.size()) {
            c.gridwidth = 1;
            c.insets = new Insets(0,0,0,0);
            JLabel idAmis = new JLabel(invitation.get(i));
            //c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
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
            grid.add(buttonsAccepter.get(i), c);

            c.gridwidth = 1;
            c.insets = new Insets(0,20,0,0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 2;
            c.gridy = (i+1);
            grid.add(buttonsDecliner.get(i), c);

            i++;
        }
        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(450, 110));
        return scrollPane;
    }

    /**
     * \fn JPanel complete(JPanel buttons, JScrollPane panelSuggestion, JScrollPane panelDemande)
     * \brief Fonction qui retourne un panel qui assemble tous les JPanel & JScrollPane
     * \param [in] buttons JPanel (Type JPanel)
     * \param [in] panelSuggestion JScrollPane (Type JScrollPane)
     * \param [in] panelDemande JScrollPane (Type JScrollPane)
     * \return Retourne un JPanel
     */
    public JPanel complete(JPanel buttons, JScrollPane panelSuggestion, JScrollPane panelDemande) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        grid.add(reseau, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        grid.add(panelSuggestion, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 3;
        grid.add(panelDemande, c);

        c.insets = new Insets(20,0,0,0);
        c.gridx = 0;
        c.gridy = 4;
        grid.add(buttons, c);

        return grid;
    }

    /**
     * \fn void actionPerformed(ActionEvent e)
     * \brief Fonction qui donne des actions aux boutons
     * \param [in] a ActionEvent (Type ActionEvent)
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        if (Button == retour) {
            new Accueil();
            dispose();
        }

        if (Button == testAmis) {
            //ReseauManager.sportJouer(AuthentificationManager.personne);
            /*ArrayList<Individu> individus = ReseauManager.sportJouer(AuthentificationManager.personne);
            for(int i = 0; i<individus.size(); i++) {
                System.out.println(individus.get(i).getPrenom() + " " + individus.get(i).getNom());
            }*/

            new Challenge();
            dispose();
        }

        int i = 0;
        while(i<buttonsSuggestion.size()) {
            if(Button == buttonsSuggestion.get(i)) {
                System.out.println("Vous avez appuyer sur " + suggestion.get(buttonsSuggestion.get(i)));
                buttonsSuggestion.get(i).setLabel("Demande envoyée");
                buttonsSuggestion.get(i).setBackground(Color.GREEN);

                if(buttonsSuggestion.get(i).isEnabled()) {
                    DemandeManager.ajout(suggestion.get(buttonsSuggestion.get(i)), AuthentificationManager.personne.getId_individu());
                    buttonsSuggestion.get(i).setEnabled(false);
                }

            }
            i++;
        }

        int j = 0;
        while(j<buttonsAccepter.size()) {
            if(Button == buttonsAccepter.get(j)) {
                System.out.println("Vous avez accepter la demande");
                buttonsAccepter.get(j).setLabel("Demande accepté"); //
                buttonsAccepter.get(j).setEnabled(false);
                buttonsDecliner.get(j).setEnabled(false);
                buttonsAccepter.get(j).setBackground(Color.GREEN);

                AmisManager.ajout(invitation.get(j) , AuthentificationManager.personne.getId_individu());
                DemandeManager.deleteValue(DemandeManager.suppDemande(AuthentificationManager.personne.getId_individu(), invitation.get(j)));
            }
            if(Button == buttonsDecliner.get(j)) {
                System.out.println("Vous avez decliner la demande");
                buttonsDecliner.get(j).setLabel("Demande déclinée"); //
                buttonsDecliner.get(j).setEnabled(false);
                buttonsAccepter.get(j).setEnabled(false);
                buttonsDecliner.get(j).setBackground(Color.RED);

                DemandeManager.deleteValue(DemandeManager.suppDemande(AuthentificationManager.personne.getId_individu(), invitation.get(j)));
            }
            j++;
        }
    }
}
