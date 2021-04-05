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


/**
 * \file ComparativeBarChart.java
 * \brief Classe construisant les diagrammes en bars à deux entrée grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes en bars afin de comparer deux personnes.
 *
 */
//Classe qui permet de faire des graphique en barres pour comparer deux utilisateurs
public class ComparativeBarChart  extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * \fn ComparativeBarChart(ArrayList<Integer> user, ArrayList<Integer> friend, String titleFrame, String titleGraph, String serie1, String serie2, String xLabel, String yLabel)
     * \brief Constructeur grâce auquel un diagramme de type bar à deux entrée est construit grâce aux données des deux ArrayList d'Integer passé en paramètre
     *
     * \param user Données de l'utilisateur (Type ArrayList<Integer>)
     * \param friend Données de l'amis pour comparaison (Type ArrayList<Integer>)
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param serie1 Nom de la bar de l'utilisateur (Type String)
     * \param serie2 Nom de la bar de l'ami (Type String)
     * \param xLabel Nom de l'axe des abscisses (Type String)
     * \param yLabel Nom de l'axe des ordonnées (Type String)
     *
     */
    //Comparative Bar Chart avec deux arraylist de integer
    public ComparativeBarChart(ArrayList<Integer> user, ArrayList<Integer> friend, String titleFrame, String titleGraph, String serie1, String serie2, String xLabel, String yLabel) {
        super(titleFrame);
        CategoryDataset dataset = createBarDataset(user, friend, serie1, serie2);
        JFreeChart chart = createChart(dataset, titleGraph, xLabel, yLabel);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * \fn CategoryDataset createBarDataset(ArrayList<Integer> user, ArrayList<Integer> friend, String serie1, String serie2)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données des deux ArrayList passé en paramètre
     *
     * \param [in] user Données de l'utilisateur (Type ArrayList<Integer>)
     * \param [in] friend Données de l'amis pour comparaison (Type ArrayList<Integer>)
     * \param [in] serie1 Nom de la bar de l'utilisateur (Type String)
     * \param [in] serie2 Nom de la bar de l'ami (Type String)
     * \return Les données qui ont été configurés pour le graphique
     */
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

    /**
     * \fn JFreeChart createChart(CategoryDataset dataset, String title, String xLabel, String yLabel)
     * \brief Fonction qui retourne le graphique en fonctions des données qui lui sont passé en paramètres
     *
     * \param [in] dataset Données pour le graphique (Type CategoryDataset)
     * \param [in] title Titre du graphique (Type String)
     * \param [in] xLabel Nom de l'axe des abscisses (Type String)
     * \param [in] yLabel Nom de l'axe des ordonnées (Type String)
     * \return Le graphique grâce aux données passé en paramètre
     */
    //Retourne le graphique avec les valeurs correspondante
    private JFreeChart createChart(CategoryDataset dataset, String title, String xLabel, String yLabel) {
        JFreeChart chart = ChartFactory.createBarChart(title, xLabel, yLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
        return chart;
    }
}
