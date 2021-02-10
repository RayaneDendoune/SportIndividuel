package gui;

import manager.AuthentificationManager;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {
	
	//GraphicsDemo graphic = new GraphicsDemo();

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

	
	public Accueil() {
		build();
	}
	
	public void build() {
		this.setSize(550, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false); //Taille non changeable

		JPanel grid = imgButtons(); //Partie avec les boutons

		JPanel panel = complete(grid);
		pan.add(panel, BorderLayout.CENTER);
		this.setContentPane(pan);

		this.setVisible(true);
	}

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

	public JPanel complete(JPanel grid) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		panel.add(bienvenue, c);

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
}
