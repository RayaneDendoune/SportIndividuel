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

/**
 * \file LineChart.java
 * \brief Classe construisant les diagrammes en courbes grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes en courbes.
 *
 */

//Classe qui permet de faire des graphique en courbe pour un seul utilisateur
public class LineChart extends JFrame {


    private static final long serialVersionUID = 1L;

    /**
     * \fn LineChart(String titleFrame, String titleGraph, ArrayList<Float> al, String key, String xAxis, String yAxis)
     * \brief Constructeur grâce auquel un diagramme de type courbe est construit grâce aux données de l'ArrayList de Float passé en paramètre
     *
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param al Données de l'utilisateur (Type ArrayList<Float>)
     * \param key Nom de la courbe (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     *
     */
    //Line Chart avec une arraylist de float
    public LineChart(String titleFrame, String titleGraph, ArrayList<Float> al, String key, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineFloatDataset(al, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }


    /**
     * \fn LineChart(ArrayList<Time> tl, String titleFrame, String titleGraph, String key, String xAxis, String yAxis)
     * \brief Constructeur grâce auquel un diagramme de type courbe est construit grâce aux données de l'ArrayList de Time passé en paramètre
     *
     * \param tl Données de l'utilisateur (Type ArrayList<Time>)
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param key Nom de la courbe (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     */
    //Line Chart avec une arraylist de time
    public LineChart(ArrayList<Time> tl, String titleFrame, String titleGraph, String key, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineTimeDataset(tl, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * \fn LineChart(String titleFrame, String titleGraph, String key, String xAxis, String yAxis, ArrayList<Integer> array)
     * \brief Constructeur grâce auquel un diagramme de type courbe est construit grâce aux données de l'ArrayList de Integer passé en paramètre
     *
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param key Nom de la courbe (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     * \param array Données de l'utilisateur (Type ArrayList<Integer>)
     */
    //Line Chart avec une arraylist de integer
    public LineChart(String titleFrame, String titleGraph, String key, String xAxis, String yAxis, ArrayList<Integer> array) {
        super(titleFrame);
        XYDataset dataset = createLineIntegerDataset(array, key);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * \fn XYDataset createLineIntegerDataset(ArrayList<Integer> array, String key)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList d'Integer passé en paramètre
     *
     * \param [in] array ArrayList d'Integer (Type ArrayList<Integer>)
     * \param [in] key Nom de la courbe (Type String)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
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

    /**
     * \fn XYDataset createLineFloatDataset(ArrayList<Float> vitesse, String key)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList de Float passé en paramètre
     *
     * \param [in] floats ArrayList de Float (Type ArrayList<Float>)
     * \param [in] key Nom de la courbe (Type String)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeur grâce a une arraylist de float
    private XYDataset createLineFloatDataset(ArrayList<Float> floats, String key) {
        XYSeries series1 = new XYSeries(key);
        for(int i=0; i<floats.size(); i++) {
            series1.add((i+1), floats.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }

    /**
     * \fn XYDataset createLineTimeDataset(ArrayList<Time> temps, String key)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList de Time passé en paramètre
     *
     * \param [in] temps ArrayList de Time (Type ArrayList<Time>)
     * \param [in] key Nom de la courbe (Type String)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
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

    /**
     * \fn JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis)
     * \brief Fonction qui retourne le graphique en fonctions des données qui lui sont passé en paramètres
     *
     * \param [in] dataset Données pour le graphique (Type XYDataset)
     * \param [in] title Titre du graphique (Type String)
     * \param [in] xAxis Nom de l'axe des abscisses (Type String)
     * \param [in] yAxis Nom de l'axe des ordonnées (Type String)
     * \return Retourne un JFreeChart qui est le graphique obtenu grâce aux données passé en paramètre
     */
    //Retourne le graphique avec les valeurs correspondante
    private JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis) {
        return ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
    }

}

