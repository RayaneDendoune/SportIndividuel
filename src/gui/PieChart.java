package gui;

import model.Individu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;

public class PieChart extends JFrame {
    private static final long serialVersionUID = 1L;

    public PieChart(String title, ArrayList<Character> issue) {
        super(title);
        setContentPane(createDemoPanel(issue));
    }

    /*private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }*/

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

    private JFreeChart createChart(PieDataset dataset) {
        return ChartFactory.createPieChart("Pie Chart Demo 1", dataset, true, true, false);
    }

    public JPanel createDemoPanel(ArrayList<Character> issue) {
        JFreeChart chart = createChart(createIssueDataset(issue));
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

        PieChart demo = new PieChart("Pie Chart Demo", issue);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
