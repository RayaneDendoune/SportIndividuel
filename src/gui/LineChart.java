package gui;
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

    public LineChart(String title, ArrayList<Float> al, String key, String xAxis, String yAxis) {
        super(title);
        XYDataset dataset = createLineFloatDataset(al, key);
        JFreeChart chart = createChart(dataset, title, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    public LineChart(ArrayList<Time> tl, String title, String key, String xAxis, String yAxis) {
        super(title);
        XYDataset dataset = createLineTimeDataset(tl, key);
        JFreeChart chart = createChart(dataset, title, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public LineChart(ArrayList<Integer> proteine, ArrayList<Integer> energie, String title, String key1, String key2, String xAxis, String yAxis) {
        super(title);
        XYDataset dataset = createLineIntegerDataset(proteine, energie, key1, key2);
        JFreeChart chart = createChart(dataset, title, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private XYDataset createLineIntegerDataset(ArrayList<Integer> proteine, ArrayList<Integer> energie, String key1, String key2) {
        XYSeries series1 = new XYSeries(key1);
        for(int i=0; i<proteine.size(); i++) {
            series1.add((i+1), proteine.get(i));
        }

        XYSeries series2 = new XYSeries(key2);
        for(int i=0; i<energie.size(); i++) {
            series2.add((i+1), energie.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private XYDataset createLineFloatDataset(ArrayList<Float> vitesse, String key) {
        XYSeries series1 = new XYSeries(key);
        for(int i=0; i<vitesse.size(); i++) {
            series1.add((i+1), vitesse.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }

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


    private CategoryDataset createBarDataset(ArrayList<Integer> nbPas) {
        String series1 = "Nombre de pas";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<nbPas.size(); i++) {
            String category1 = "" + (i+1);
            dataset.addValue(nbPas.get(i), series1, category1);
        }

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    public static void main(String[] args) {
        ArrayList<Float> vitesse = new ArrayList<Float>();

        vitesse.add(15f);
        vitesse.add(6.9f);
        vitesse.add(56.8f);
        vitesse.add(22.6f);
        vitesse.add(11.3f);

        LineChart demo = new LineChart("Vitesse Moyenne en fonction de la séance", vitesse, "Vitesse Moyenne", "Numéro de la seance", "Vitesse Moyenne");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

