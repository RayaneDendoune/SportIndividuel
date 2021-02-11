package gui;
import manager.AuthentificationManager;
import model.Individu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import util.HibernateUtil;

import java.util.ArrayList;

public class LineChartCourse extends ApplicationFrame {


        private static final long serialVersionUID = 1L;

        public LineChartCourse(String title, ArrayList<Float> vitesse) {
            super(title);
            XYDataset dataset = createDataset(vitesse);
            JFreeChart chart = createChart(dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            setContentPane(chartPanel);

        }

        private XYDataset createDataset(ArrayList<Float> vitesse) {
            XYSeries series1 = new XYSeries("Vitesse Moyenne");
            for(int i=0; i<vitesse.size(); i++) {
                series1.add(i, vitesse.get(i));
            }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);

            return dataset;

        }

        private JFreeChart createChart(XYDataset dataset) {
            return ChartFactory.createXYLineChart("Vitesse Moyenne en fonction du nbre de Seance", "NbSeance", "VitesseMoyenne", dataset, PlotOrientation.VERTICAL, true, true, false);
        }

        public static void main(String[] args) {
            ArrayList<Float> vitesse = new ArrayList<Float>();

            vitesse.add(15f);
            vitesse.add(6.9f);
            vitesse.add(56.8f);
            vitesse.add(22.6f);
            vitesse.add(11.3f);

            LineChartCourse demo = new LineChartCourse("Vitesse Moyenne en fonction du nbre de Seance", vitesse);
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
        }

    }

