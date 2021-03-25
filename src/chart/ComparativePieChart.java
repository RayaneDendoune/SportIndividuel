package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Classe qui permet de faire des graphique circulaire pour comparer deux utilisateurs
public class ComparativePieChart extends JFrame {
    private static final long serialVersionUID = 1L;
    JPanel pan = new JPanel();

    //Comparative Pie Chart avec deux arraylist de Character
    public ComparativePieChart(ArrayList<Character> issue1, ArrayList<Character> issue2, String titleFrame, String titleGraph1, String titleGraph2) {
        super(titleFrame);

        JPanel cp1 = createDemoPanel(issue1, titleGraph1);
        cp1.setPreferredSize(new java.awt.Dimension(320, 270));

        JPanel cp2 = createDemoPanel(issue2, titleGraph2);
        cp2.setPreferredSize(new java.awt.Dimension(320, 270));

        pan.setLayout(new BorderLayout());
        pan.add(cp1, BorderLayout.WEST);
        pan.add(cp2, BorderLayout.EAST);
        setContentPane(pan);
    }

    //Renvoyer les valeurs grâce a deux arraylist de Character
    private PieDataset createIssueDataset(ArrayList<Character> issue) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int victoire=0;
        int defaite=0;

        for(int i = 0; i<issue.size(); i++) {
            if(issue.get(i).equals('V')) {
                victoire++;
            }
            else if(issue.get(i).equals('D')) {
                defaite++;
            }
        }

        dataset.setValue("Victoire", victoire);
        dataset.setValue("Defaite", defaite);

        return dataset;
    }

    //Retourne le graphique avec les valeurs correspondante
    private JFreeChart createChart(PieDataset dataset, String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    public JPanel createDemoPanel(ArrayList<Character> issue, String title) {
        JFreeChart chart = createChart(createIssueDataset(issue), title);
        return new ChartPanel(chart);
    }
}
