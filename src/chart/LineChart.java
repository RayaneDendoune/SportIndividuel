package chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;

public class LineChart extends JFrame {


    private static final long serialVersionUID = 1L;

    //Line Chart avec une arraylist de float
    public LineChart(String titleFrame, String titleGraph, ArrayList<Float> al, String key, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineFloatDataset(al, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    //Line Chart avec une arraylist de time
    public LineChart(ArrayList<Time> tl, String titleFrame, String titleGraph, String key, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineTimeDataset(tl, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Line Chart avec une arraylist de integer
    public LineChart(String titleFrame, String titleGraph, String key, String xAxis, String yAxis, ArrayList<Integer> array) {
        super(titleFrame);
        XYDataset dataset = createLineIntegerDataset(array, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Renvoyer les valeur grâce a une arraylist de integer
    private XYDataset createLineIntegerDataset(ArrayList<Integer> array, String key) {
        XYSeries series1 = new XYSeries(key);
        for(int i=0; i<array.size(); i++) {
            series1.add((i+1), array.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }

    //Renvoyer les valeur grâce a une arraylist de float
    private XYDataset createLineFloatDataset(ArrayList<Float> vitesse, String key) {
        XYSeries series1 = new XYSeries(key);
        for(int i=0; i<vitesse.size(); i++) {
            series1.add((i+1), vitesse.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }

    //Renvoyer les valeur grâce a une arraylist de time
    private XYDataset createLineTimeDataset(ArrayList<Time> temps, String key) {
        XYSeries series1 = new XYSeries(key);
        for(int i=0; i<temps.size(); i++) {
            int min = temps.get(i).getMinutes();
            int sec = temps.get(i).getSeconds();
            int total = min*60 + sec;
            series1.add((i+1), total);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }


    /*private CategoryDataset createBarDataset(ArrayList<Integer> nbPas) {
        String series1 = "Nombre de pas";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<nbPas.size(); i++) {
            String category1 = "" + (i+1);
            dataset.addValue(nbPas.get(i), series1, category1);
        }

        return dataset;
    }*/

    private JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
    }

}

