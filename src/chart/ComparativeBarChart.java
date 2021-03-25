package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Classe qui permet de faire des graphique en barres pour comparer deux utilisateurs
public class ComparativeBarChart  extends JFrame {

    private static final long serialVersionUID = 1L;

    //Comparative Bar Chart avec deux arraylist de integer
    public ComparativeBarChart(ArrayList<Integer> user, ArrayList<Integer> friend, String titleFrame, String titleGraph, String serie1, String serie2, String xLabel, String yLabel) {
        super(titleFrame);
        CategoryDataset dataset = createBarDataset(user, friend, serie1, serie2);
        JFreeChart chart = createChart(dataset, titleGraph, xLabel, yLabel);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Renvoyer les valeur grâce a deux arraylists de integer
    private CategoryDataset createBarDataset(ArrayList<Integer> user, ArrayList<Integer> friend, String serie1, String serie2) {
        String series1 = serie1;
        String series2 = serie2;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<user.size(); i++) {
            String category1 = "" + (i+1);
            dataset.addValue(user.get(i), series1, category1);
        }

        for(int i = 0; i<friend.size(); i++) {
            String category2 = "" + (i+1);
            dataset.addValue(friend.get(i), series2, category2);
        }

        return dataset;
    }

    //Retourne le graphique avec les valeurs correspondante
    private JFreeChart createChart(CategoryDataset dataset, String title, String xLabel, String yLabel) {
        JFreeChart chart = ChartFactory.createBarChart(title, xLabel, yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
        return chart;
    }
}
