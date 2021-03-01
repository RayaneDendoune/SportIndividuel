/*package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;

public class ComparativeLineChart extends JFrame {


    private static final long serialVersionUID = 1L;

    //Line Chart avec une arraylist de float
    public ComparativeLineChart(ArrayList<Float> user, ArrayList<Float> friend, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineFloatDataset(user, friend, key1, key2);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Renvoyer les valeur grâce a une arraylist de float
    private XYDataset createLineFloatDataset(ArrayList<Float> user, ArrayList<Float> friend, String key1, String key2) {
        XYSeries series1 = new XYSeries(key1);
        XYSeries series2 = new XYSeries(key2);

        for(int i=0; i<user.size(); i++) {
            series1.add((i+1), user.get(i));
        }

        for(int i=0; i<friend.size(); i++) {
            series2.add((i+1), friend.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
    }
}*/




package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;

public class ComparativeLineChart extends JFrame {


    private static final long serialVersionUID = 1L;

    //Line Chart avec une arraylist de float
    public ComparativeLineChart(ArrayList<Float> user, ArrayList<Float> friend, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineFloatDataset(user, friend, key1, key2);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Line Chart avec une arraylist de integer
    public ComparativeLineChart(String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Integer> user, ArrayList<Integer> friend) {
        super(titleFrame);
        XYDataset dataset = createLineIntegerDataset(key1, key2, user, friend);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    //Line Chart avec une arraylist de time
    public ComparativeLineChart(ArrayList<Time> user, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Time> friend) {
        super(titleFrame);
        XYDataset dataset = createLineTimeDataset(user, key1, key2, friend);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }



    //Renvoyer les valeur grâce a une arraylist de float
    private XYDataset createLineFloatDataset(ArrayList<Float> user, ArrayList<Float> friend, String key1, String key2) {
        XYSeries series1 = new XYSeries(key1);
        XYSeries series2 = new XYSeries(key2);

        for(int i=0; i<user.size(); i++) {
            series1.add((i+1), user.get(i));
        }

        for(int i=0; i<friend.size(); i++) {
            series2.add((i+1), friend.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    //Renvoyer les valeur grâce a une arraylist de integer
    private XYDataset createLineIntegerDataset(String key1, String key2, ArrayList<Integer> user, ArrayList<Integer> friend) {
        XYSeries series1 = new XYSeries(key1);
        XYSeries series2 = new XYSeries(key2);

        for(int i=0; i<user.size(); i++) {
            series1.add((i+1), user.get(i));
        }

        for(int i=0; i<friend.size(); i++) {
            series2.add((i+1), friend.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }


    //Renvoyer les valeur grâce a une arraylist de time
    private XYDataset createLineTimeDataset(ArrayList<Time> user, String key1, String key2, ArrayList<Time> friend) {
        XYSeries series1 = new XYSeries(key1);
        XYSeries series2 = new XYSeries(key2);

        for(int i=0; i<user.size(); i++) {
            int min = user.get(i).getMinutes();
            int sec = user.get(i).getSeconds();
            int total = min*60 + sec;
            series1.add((i+1), total);
        }

        for(int j=0; j<friend.size(); j++) {
            int min = friend.get(j).getMinutes();
            int sec = friend.get(j).getSeconds();
            int total = min*60 + sec;
            series2.add((j+1), total);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
    }
}

