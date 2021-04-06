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

/**
 * \file ComparativeLineChart.java
 * \brief Classe construisant les diagrammes en courbes à deux entrées grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes en courbes afin de comparer deux personnes.
 *
 */
//Classe qui permet de faire des graphique en courbe pour comparer deux utilisateurs
public class ComparativeLineChart extends JFrame {


    private static final long serialVersionUID = 1L;

    /**
     * \fn ComparativeLineChart(ArrayList<Float> user, ArrayList<Float> friend, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis)
     * \brief Constructeur grâce auquel un diagramme de type courbe à deux entrées est construit grâce aux données de deux ArrayList de Float passés en paramètres
     *
     * \param user Données de l'utilisateur (Type ArrayList<Float>)
     * \param friend Données de l'amis pour comparaison (Type ArrayList<Float>)
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param key1 Nom de la courbe de l'utilisateur (Type String)
     * \param key2 Nom de la courbe de l'ami (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     *
     */
    //Comparative Line Chart avec deux arraylist de float
    public ComparativeLineChart(ArrayList<Float> user, ArrayList<Float> friend, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis) {
        super(titleFrame);
        XYDataset dataset = createLineFloatDataset(user, friend, key1, key2);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }


    /**
     * \fn ComparativeLineChart(String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Integer> user, ArrayList<Integer> friend)
     * \brief Constructeur grâce auquel un diagramme de type courbe à deux entrées est construit grâce aux données de deux ArrayList de Integer passés en paramètres
     *
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param key1 Nom de la courbe de l'utilisateur (Type String)
     * \param key2 Nom de la courbe de l'ami (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     * \param user Données de l'utilisateur (Type ArrayList<Integer>)
     * \param friend Données de l'amis pour comparaison (Type ArrayList<Integer>)
     */
    //Comparative Line Chart avec deux arraylist de integer
    public ComparativeLineChart(String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Integer> user, ArrayList<Integer> friend) {
        super(titleFrame);
        XYDataset dataset = createLineIntegerDataset(key1, key2, user, friend);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * \fn ComparativeLineChart(ArrayList<Time> user, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Time> friend)
     * \brief Constructeur grâce auquel un diagramme de type courbe à deux entrées est construit grâce aux données de deux ArrayList de Time passés en paramètres
     *
     * \param user Données de l'utilisateur (Type ArrayList<Time>)
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param key1 Nom de la courbe de l'utilisateur (Type String)
     * \param key2 Nom de la courbe de l'ami (Type String)
     * \param xAxis Nom de l'axe des abscisses (Type String)
     * \param yAxis Nom de l'axe des ordonnées (Type String)
     * \param friend Données de l'amis pour comparaison (Type ArrayList<Integer>)
     */
    //Comparative Line Chart avec deux arraylist de time
    public ComparativeLineChart(ArrayList<Time> user, String titleFrame, String titleGraph, String key1, String key2, String xAxis, String yAxis, ArrayList<Time> friend) {
        super(titleFrame);
        XYDataset dataset = createLineTimeDataset(user, key1, key2, friend);
        JFreeChart chart = createChart(dataset, titleGraph, xAxis, yAxis);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }


    /**
     * \fn XYDataset createLineFloatDataset(ArrayList<Float> user, ArrayList<Float> friend, String key1, String key2)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données des deux ArrayList de Float passés en paramètres
     *
     * \param [in] user Données de l'utilisateur (Type ArrayList<Float>)
     * \param [in] friend Données de l'amis pour comparaison (Type ArrayList<Float>)
     * \param [in] key1 Nom de la courbe de l'utilisateur (Type String)
     * \param [in] key2 Nom de la courbe de l'ami (Type String)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeur grâce a deux arraylist de float
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

    /**
     * \fn XYDataset createLineIntegerDataset(String key1, String key2, ArrayList<Integer> user, ArrayList<Integer> friend)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données des deux ArrayList d'Integer passés en paramètres
     *
     * \param [in] key1 Nom de la courbe de l'utilisateur (Type String)
     * \param [in] key2 Nom de la courbe de l'ami (Type String)
     * \param [in] user Données de l'utilisateur (Type ArrayList<Integer>)
     * \param [in] friend Données de l'amis pour comparaison (Type ArrayList<Integer>)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeur grâce a deux arraylist de integer
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


    /**
     * \fn XYDataset createLineTimeDataset(ArrayList<Time> user, String key1, String key2, ArrayList<Time> friend)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données des deux ArrayList de Time passés en paramètres
     *
     * \param [in] user Données de l'utilisateur (Type ArrayList<Time>)
     * \param [in] key1 Nom de la courbe de l'utilisateur (Type String)
     * \param [in] key2 Nom de la courbe de l'ami (Type String)
     * \param [in] friend Données de l'amis pour comparaison (Type ArrayList<Time>)
     * \return Retourne un XYDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeur grâce a deux arraylist de time
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

    /**
     * \fn JFreeChart createChart(XYDataset dataset, String title, String xAxis, String yAxis)
     * \brief Fonction qui retourne le graphique en fonctions des données qui lui sont passés en paramètress
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

