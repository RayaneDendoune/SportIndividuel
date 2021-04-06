package chart;

import model.Individu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;

/**
 * \file PieChart.java
 * \brief Classe construisant les diagrammes circulaires grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes circulaires.
 *
 */

//Classe qui permet de faire des graphique circulaire pour un seul utilisateur
public class PieChart extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * \fn PieChart(String titleFrame, ArrayList<Character> issue, String titleGraph)
     * \brief Constructeur grâce auquel un diagramme de type circulaire est construit grâce aux données de l'ArrayList de Character passé en paramètre
     *
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param issue Données de l'utilisateur (Type ArrayList<Float>)
     * \param titleGraph Titre du graphique (Type String)
     *
     */
    //Pie Chart avec une arraylist de Character
    public PieChart(String titleFrame, ArrayList<Character> issue, String titleGraph) {
        super(titleFrame);
        setContentPane(createDemoPanel(issue, titleGraph));
    }

    /**
     * \fn PieDataset createIssueDataset(ArrayList<Character> issue)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList de Character passé en paramètre
     *
     * \param [in] issue ArrayList de Character (Type ArrayList<Character>)
     * \return Retourne un PieDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeurs grâce a une arraylist de Character
    private PieDataset createIssueDataset(ArrayList<Character> issue) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int victoire=0;
        int defaite=0;

        for(int i = 0; i<issue.size(); i++) {
            if(issue.get(i).equals('V')) {
                victoire++;
            }
            else if(issue.get(i).equals('D')) {
                defaite++;
            }
        }

        dataset.setValue("Victoire", victoire);
        dataset.setValue("Defaite", defaite);

        return dataset;
    }

    /**
     * \fn JFreeChart createChart(PieDataset dataset, String title)
     * \brief Fonction qui retourne le graphique en fonctions des données qui lui sont passé en paramètres
     *
     * \param [in] dataset Données pour le graphique (Type PieDataset)
     * \param [in] title Titre du graphique (Type String)
     * \return Retourne un JFreeChart qui est le graphique obtenu grâce aux données passé en paramètre
     */
    //Retourne le graphique avec les valeurs correspondante
    private JFreeChart createChart(PieDataset dataset, String title) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    /**
     * \fn JPanel createDemoPanel(ArrayList<Character> issue, String title)
     * \brief Fonction qui retourne le graphique dans un JPanel
     *
     * \param [in] issue ArrayList de Character (Type ArrayList<Character>)
     * \param [in] title Titre du graphique (Type String)
     * \return Retourne le graphique dans un JPanel
     */
    //Retourne le graphique dans un JPanel
    public JPanel createDemoPanel(ArrayList<Character> issue, String title) {
        JFreeChart chart = createChart(createIssueDataset(issue), title);
        return new ChartPanel(chart);
    }

}
