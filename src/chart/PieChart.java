package chart;

import model.Individu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;

//Classe qui permet de faire des graphique circulaire pour un seul utilisateur
public class PieChart extends JFrame {
    private static final long serialVersionUID = 1L;

    //Pie Chart avec une arraylist de Character
    public PieChart(String titleFrame, ArrayList<Character> issue, String titleGraph) {
        super(titleFrame);
        setContentPane(createDemoPanel(issue, titleGraph));
    }

    //Renvoyer les valeurs grâce a une arraylist de Character
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

    public static void main(String[] args) {
        ArrayList<Character> issue = new ArrayList<Character>();
        issue.add('V');
        issue.add('D');
        issue.add('V');
        issue.add('D');
        issue.add('V');
        issue.add('V');
        issue.add('V');
        issue.add('D');
        issue.add('V');
        issue.add('D');

        /*PieChart demo = new PieChart("Test", issue, "Nb victoire");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);*/
    }
}
