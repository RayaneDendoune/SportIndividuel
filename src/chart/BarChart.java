package chart;

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

/**
 * \file BarChart.java
 * \brief Classe construisant les diagrammes en bars grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes en bars.
 *
 */
//Classe qui permet de faire des graphique en barres pour un seul utilisateur
public class BarChart extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * \fn BarChart(String titleFrame, String titleGraph, ArrayList<Integer> al, String serie, String xLabel, String yLabel)
     * \brief Constructeur grâce auquel un diagramme de type bar est construit grâce aux données de l'ArrayList d'Integer passé en paramètre
     *
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph Titre du graphique (Type String)
     * \param al Données de l'utilisateur (Type ArrayList<Integer>)
     * \param serie Nom de la bar (Type String)
     * \param xLabel Nom de l'axe des abscisses (Type String)
     * \param yLabel Nom de l'axe des ordonnées (Type String)
     *
     */
    //Bar Chart avec une arraylist de integer
    public BarChart(String titleFrame, String titleGraph, ArrayList<Integer> al, String serie, String xLabel, String yLabel) {

        super(titleFrame);

        CategoryDataset dataset = createBarDataset(al, serie);
        JFreeChart chart = createChart(dataset, titleGraph, xLabel, yLabel);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * \fn CategoryDataset createBarDataset(ArrayList<Integer> nbPas, String serie)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList passé en paramètre
     *
     * \param [in] integers ArrayList d'Integer (Type ArrayList<Integer>)
     * \param [in] serie Nom de la bar (Type String)
     * \return Les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeur grâce a une arraylist de integer
    private CategoryDataset createBarDataset(ArrayList<Integer> integers, String serie) {
        String series1 = serie;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<integers.size(); i++) {
            String category1 = "" + (i+1);
            dataset.addValue(integers.get(i), series1, category1);
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

    /*public static void main(String[] args) {

        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(3578);
        al.add(5436);
        al.add(4374);
        al.add(8970);

        /*BarChart demo = new BarChart("Nombre de pas moyen", al, "Nombre de pas", "Numéro de la séance", "Nb pas");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }*/
}


