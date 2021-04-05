package gui;

import manager.AuthentificationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * \file Accueil.java
 * \brief Classe qui permet de créer l'interface de l'Accueil
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface Accueil.
 *
 */
//Classe qui représente l'accueil en interface graphique
public class Accueil extends JFrame implements ActionListener {

	private JPanel pan  = new JPanel();

	private JLabel bienvenue = new JLabel("Bienvenue " + AuthentificationManager.nom + " " + AuthentificationManager.prenom + " !");
	private JLabel titre = new JLabel("Sélectionner l'activité voulue : ");

	private ImageIcon iconCourse = new ImageIcon("src/images/course.png");
	private ImageIcon iconCourse2 = new ImageIcon(iconCourse.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgCourse = new JButton(iconCourse2);

	private ImageIcon iconNatation = new ImageIcon("src/images/natation.png");
	private ImageIcon iconNatation2 = new ImageIcon(iconNatation.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgNatation = new JButton(iconNatation2);

	private ImageIcon iconCyclisme = new ImageIcon("src/images/cyclisme.png");
	private ImageIcon iconCyclisme2 = new ImageIcon(iconCyclisme.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgCyclisme = new JButton(iconCyclisme2);

	private ImageIcon iconEchecs = new ImageIcon("src/images/echecs.png");
	private ImageIcon iconEchecs2 = new ImageIcon(iconEchecs.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgEchecs = new JButton(iconEchecs2);

	private ImageIcon iconTennis = new ImageIcon("src/images/tennis.png");
	private ImageIcon iconTennis2 = new ImageIcon(iconTennis.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgTennis = new JButton(iconTennis2);

	private ImageIcon iconRs = new ImageIcon("src/images/rs.png");
	private ImageIcon iconRs2 = new ImageIcon(iconRs.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	private JButton imgRs = new JButton(iconRs2);

	private ImageIcon deco = new ImageIcon("src/images/deconnexion.png");
	private ImageIcon deco2 = new ImageIcon(deco.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
	private JButton deconnexion = new JButton(deco2);

	
	public Accueil() {
		build();
	}

	/**
	 * \fn void build()
	 * \brief Fonction qui permet la construction de la fenêtre d'Accueil
	 */
	public void build() {
		this.setSize(550, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false); //Taille non changeable

		imgCourse.addActionListener(this);
		imgNatation.addActionListener(this);
		imgCyclisme.addActionListener(this);
		imgTennis.addActionListener(this);
		imgEchecs.addActionListener(this);
		imgRs.addActionListener(this);
		deconnexion.addActionListener(this);

		JPanel grid = imgButtons(); //Partie avec les boutons
		JPanel header = header();

		JPanel panel = complete(grid, header);
		pan.add(panel, BorderLayout.CENTER);
		this.setContentPane(pan);

		this.setVisible(true);
	}

	/**
	 * \fn JPanel imgButtons()
	 * \brief Fonction qui retourne un JPanel pour les boutons
	 * \return Retourne un JPanel avec les boutons
	 */
	public JPanel imgButtons() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(20,20,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		grid.add(imgCourse, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		grid.add(imgNatation, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		grid.add(imgCyclisme, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		grid.add(imgTennis, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		grid.add(imgEchecs, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		grid.add(imgRs, c);

		return grid;
	}

	/**
	 * \fn JPanel header()
	 * \brief Fonction qui retourne un JPanel pour le haut de la fenêtre
	 * \return Retourne un JPanel
	 */
	public JPanel header() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 3;
		c.gridy = 0;
		panel.add(bienvenue, c);

		c.insets = new Insets(0,20,0,0);
		c.gridx = 5;
		c.gridy = 0;
		panel.add(deconnexion, c);

		return panel;
	}

	/**
	 * \fn JPanel complete(JPanel grid, JPanel header)
	 * \brief Fonction qui retourne un panel qui assemble tous les JPanel
	 * \param [in] grid JPanel (Type JPanel)
	 * \param [in] header JPanel (Type JPanel)
	 * \return Retourne un JPanel
	 */
	public JPanel complete(JPanel grid, JPanel header) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		panel.add(header, c);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(titre, c);

		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(grid, c);

		return panel;
	}
	
	public static void main(String[] args) {
		new Accueil();
	}

	/**
	 * \fn void actionPerformed(ActionEvent e)
	 * \brief Fonction qui donne des actions aux boutons
	 * \param [in] a ActionEvent (Type ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource();
		if(Button==imgCourse){
			new Course();
			dispose();
		}

		if(Button==imgNatation) {
			new Natation();
			dispose();
		}

		if(Button==imgCyclisme) {
			new Cyclisme();
			dispose();
		}

		if(Button==imgTennis) {
			new Tennis();
			dispose();
		}

		if(Button==imgEchecs) {
			new Echecs();
			dispose();
		}

		if(Button==imgRs) {
			new Reseau();
			dispose();
		}

		if(Button == deconnexion) {
			new Authentification();
			dispose();
		}
	}
}
