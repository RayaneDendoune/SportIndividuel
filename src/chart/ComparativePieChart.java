package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * \file ComparativePieChart.java
 * \brief Classe construisant les diagrammes circulaire à deux entrées grâce à JFreeChart
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées aux diagrammes circulaires afin de comparer deux personnes.
 *
 */
//Classe qui permet de faire des graphique circulaire pour comparer deux utilisateurs
public class ComparativePieChart extends JFrame {
    private static final long serialVersionUID = 1L;
    JPanel pan = new JPanel();


    /**
     * \fn ComparativePieChart(ArrayList<Character> issue1, ArrayList<Character> issue2, String titleFrame, String titleGraph1, String titleGraph2)
     * \brief Constructeur grâce auquel un diagramme de type circulaire est construit grâce aux données de deux ArrayList de Character passés en paramètres
     *
     * \param issus1 Données de l'utilisateur (Type ArrayList<Character>)
     * \param issus2 Données de l'amis pour comparaison (Type ArrayList<Character>)
     * \param titleFrame Titre de la fenêtre (Type String)
     * \param titleGraph1 Titre du graphique de l'utilisateur (Type String)
     * \param titleGraph2 Titre du graphique de l'ami (Type String)
     *
     */
    //Comparative Pie Chart avec deux arraylist de Character
    public ComparativePieChart(ArrayList<Character> issue1, ArrayList<Character> issue2, String titleFrame, String titleGraph1, String titleGraph2) {
        super(titleFrame);

        JPanel cp1 = createDemoPanel(issue1, titleGraph1);
        cp1.setPreferredSize(new java.awt.Dimension(320, 270));

        JPanel cp2 = createDemoPanel(issue2, titleGraph2);
        cp2.setPreferredSize(new java.awt.Dimension(320, 270));

        pan.setLayout(new BorderLayout());
        pan.add(cp1, BorderLayout.WEST);
        pan.add(cp2, BorderLayout.EAST);
        setContentPane(pan);
    }

    /**
     * \fn PieDataset createIssueDataset(ArrayList<Character> issue)
     * \brief Fonction qui retourne les données pour le graphique en fonction des données de l'ArrayList de Character passé en paramètre
     *
     * \param [in] issue ArrayList de Character (Type ArrayList<Character>)
     * \return Retourne un PieDataset qui sont les données qui ont été configurés pour le graphique
     */
    //Renvoyer les valeurs grâce a deux arraylist de Character
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
     * \brief Fonction qui retourne le graphique en fonctions des données qui lui sont passés en paramètres
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
    public JPanel createDemoPanel(ArrayList<Character> issue, String title) {
        JFreeChart chart = createChart(createIssueDataset(issue), title);
        return new ChartPanel(chart);
    }
}
