package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BarChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public BarChart(String titleFrame, String titleGraph, ArrayList<Integer> al, String serie, String xLabel, String yLabel) {

        super(titleFrame);

        CategoryDataset dataset = createBarDataset(al, serie);
        JFreeChart chart = createChart(dataset, titleGraph, xLabel, yLabel);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private CategoryDataset createBarDataset(ArrayList<Integer> nbPas, String serie) {
        String series1 = serie;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<nbPas.size(); i++) {
            String category1 = "" + (i+1);
            dataset.addValue(nbPas.get(i), series1, category1);
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset, String title, String xLabel, String yLabel) {

        JFreeChart chart = ChartFactory.createBarChart(title, xLabel, yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);

        return chart;

    }

    public static void main(String[] args) {

        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(3578);
        al.add(5436);
        al.add(4374);
        al.add(8970);

        /*BarChart demo = new BarChart("Nombre de pas moyen", al, "Nombre de pas", "Numéro de la séance", "Nb pas");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);*/
    }

}


