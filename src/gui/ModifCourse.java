package gui;

import manager.AuthentificationManager;
import manager.CourseManager;
import model.Seance_course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

public class ModifCourse extends JFrame implements ActionListener {

    private JPanel pan  = new JPanel();
    private int selection;
    private JLabel disposition = new JLabel();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private static JLabel distance = new JLabel("Distance (en km)");
    private static JTextField dist = new JTextField(5);

    private static JLabel temps = new JLabel("Temps (en minutes) ");
    private static JTextField time = new JTextField(5);

    public ModifCourse(int selection) {
        this.selection = selection;
        build(selection);
    }

    public void build(int selection) {
        this.setSize(650, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //Taille non changeable

        ArrayList<JButton> nbButtons = nbButton(CourseManager.seanceCourseIndividu(AuthentificationManager.personne));
        for(int i = 0; i<nbButtons.size(); i++) {
            nbButtons.get(i).addActionListener(this);
        }
        pan.setLayout(new BorderLayout());

        JScrollPane grid = buttons(nbButtons);
        JPanel donneeEntree = donnee();
        JPanel completeEast = completeEast(donneeEntree);
        JPanel complete = complete(grid, completeEast);

        pan.add(complete, BorderLayout.WEST);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    public JScrollPane buttons(ArrayList<JButton> but) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for(int i = 0; i<but.size(); i++) {
            c.insets = new Insets(0,20,0,0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = i;
            grid.add(but.get(i), c);
        }

        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setPreferredSize(new Dimension(300, 110));
        return scrollPane;
    }

    public JPanel completeEast(JPanel donnee) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        grid.add(donnee, c);

        return grid;
    }

    public JPanel complete(JScrollPane scroll, JPanel completeEast) {
        JPanel grid = new JPanel();
        grid.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        grid.add(scroll, c);

        c.gridx = 1;
        c.gridy = 0;
        grid.add(completeEast, c);

        return grid;
    }
    public static JPanel donnee() {
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


    public ArrayList<JButton> nbButton(ArrayList<Seance_course> sc) {


        for(int  i = 0; i<sc.size(); i++) {
            buttons.add((new JButton(sc.get(i).getId_seance_course() + "  " + sc.get(i).getDistance() + "  " + sc.get(i).getTemps() + "  " + sc.get(i).getDate() )));
        }

        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Button = e.getSource();
        ArrayList<Seance_course> sc = CourseManager.seanceCourseIndividu(AuthentificationManager.personne);

        for(int  i = 0; i<buttons.size(); i++) {
            if(Button ==buttons.get(i)&& !dist.getText().isBlank() && !time.getText().isBlank()){
                int heure = Integer.parseInt(time.getText()) / 60;
                int min = Integer.parseInt(time.getText()) % 60;
                final Time TimeSQL = new Time(heure, min, 00);
                CourseManager.updateCourse(sc.get(i),  Float.parseFloat(dist.getText()),TimeSQL);
            }
        }
    }
}
