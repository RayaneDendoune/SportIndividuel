package gui;

import manager.AuthentificationManager;
import manager.ReseauManager;
import model.Individu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;


public class SuggestionPanel extends JPanel implements ActionListener {

    ArrayList<Individu> amis = ReseauManager.sportJouer(AuthentificationManager.personne);
    ArrayList<Button> buttons = new ArrayList<Button>();
    HashMap<Button, String> suggestion = new HashMap<Button, String>();
    private JPanel pan  = new JPanel();

    public SuggestionPanel() {
        build();
    }

    public void build() {
        for(int i = 0; i<amis.size(); i++) {
            buttons.add(new Button("b" + i));
            buttons.get(i).addActionListener(this);
        }

        for(int i = 0; i<amis.size(); i++) {
            suggestion.put(buttons.get(i), amis.get(i).getId_individu());
        }
        pan.setLayout(new BorderLayout());

        JPanel grid = disposition();

        pan.add(grid);
    }

    public JPanel disposition() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int i = 0;
        while(i<amis.size()) {
            JLabel idAmis = new JLabel(suggestion.get(i));
            c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = i;
            grid.add(idAmis, c);

            c.ipadx = 80; //Remettre la taille en question
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 1;
            c.gridy = i;
            grid.add(buttons.get(i), c);

            i++;
        }

        return grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        int i = 0;
        while(i<buttons.size()) {
            if(Button == buttons.get(i)) {
                System.out.println("Vous avez appuyer sur " + suggestion.get(buttons.get(i)));
            }
            i++;
        }
    }
}
