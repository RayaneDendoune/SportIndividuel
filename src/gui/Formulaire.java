package gui;

import manager.IndividuManager;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Formulaire extends JFrame implements ActionListener {
	
	private JPanel pan  = new JPanel();

	private int eloC;

	private JLabel titre = new JLabel("Bienvenue à ...");
	private JLabel sousTitre = new JLabel("Veuillez remplir ce formulaire");
	
	private JLabel nom = new JLabel("Nom ");
	private JTextField lastName = new JTextField(5);
	
	private JLabel prenom = new JLabel("Prenom ");
	private JTextField firstName = new JTextField(5);
	
	private JLabel id = new JLabel("Identifiant ");
	private JTextField identifiant = new JTextField(5);
	
	private JLabel mdp = new JLabel("Mot de passe ");
	private JPasswordField password = new JPasswordField(5);
	
	private JLabel sexe = new JLabel("Sexe ");
	JComboBox gender = new JComboBox();
	
	private JLabel age = new JLabel("Age ");
	JComboBox old = new JComboBox();
	
	private JLabel poids = new JLabel("Poids (en kg)");
	JComboBox weight = new JComboBox();
	
	private JLabel taille = new JLabel("Taille (en mètre) ");
	JComboBox tall = new JComboBox();
	
	private JLabel joueurechec = new JLabel("Pour les joueurs d'échecs");
	private JLabel classement = new JLabel("Classement ELO ");
	private JTextField elo = new JTextField(5);

	private JLabel frequence = new JLabel("Frequence de jeu ");
	private JComboBox frequency = new JComboBox();
	
	private JButton retour = new JButton("Retour");
	private JButton enregistrer = new JButton("Enregistrer");

	private JLabel error = new JLabel("L'un des attributs n'a pas été rempli.");
	
	public Formulaire() {
		build();
	}
	
	public void build() {
		this.setTitle("Formulaire d'inscription"); //Création de la fenetre
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.setSize(600, 400); //Taille de la fenêtre
		setResizable(false); //Taille non changeable
		setLocationRelativeTo(null);
		
		retour.addActionListener(this);
		enregistrer.addActionListener(this);
		
		gender.addItem('M');
		gender.addItem('F');
		
		for(int i=18; i<=100; i++) {
			old.addItem(i);
		}
		
		for(float i=40f; i<140f; i++) {
			weight.addItem(i);
		}
		
		for(float i = 1.40f; i<2.2f; i+=0.01f) {
			float d = (float) Math.round(i * 100) / 100;
			tall.addItem(d);
		}
		
		frequency.addItem("Débutant");
		frequency.addItem("Joueur occasionnel");
		frequency.addItem("Bon joueur de club");
		frequency.addItem("Très bon joueur de club");
		frequency.addItem("Niveau national");
		frequency.addItem("Candidat maître");
		frequency.addItem("Maître de la Fédération internationale des échecs");
		frequency.addItem("Maître international");
		frequency.addItem("Grand maître international");
		
		pan.setLayout(new BorderLayout());
		
		JPanel grid = grid();
		
		JPanel echec = completeEchec();
		
		JPanel button = buttons();
		
		JPanel complete = completeGrid(grid, echec, button);
		
		pan.add(complete, BorderLayout.CENTER);
		
		this.setContentPane(pan);
		
		setVisible(true) ;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource();
		if (Button == retour) {
			new Authentification();
			dispose();
		}
		
		if(Button == enregistrer) {

			if(!lastName.getText().isBlank() && !firstName.getText().isBlank() && !identifiant.getText().isBlank() && !password.getText().isBlank()) {

				if (elo.getText().isBlank()) {
					eloC = 0;
				} else {
					eloC = Integer.parseInt(elo.getText());
				}

				IndividuManager fm = new IndividuManager();
				fm.ajouterIndividu(identifiant.getText(), lastName.getText(), firstName.getText(), password.getText(), (char)gender.getSelectedItem(), (int) old.getSelectedItem(), (float) weight.getSelectedItem(), (float) tall.getSelectedItem(), eloC, (String)frequency.getSelectedItem());

				if (elo.getText().isBlank()) {
					System.out.println("Classement ELO : 0");
				} else {
					System.out.println("Classement ELO : " + elo.getText());
				}

				System.out.println("Frequence de jeu : " + frequency.getSelectedItem());
			}
			else {
				erreur();
			}
		}
	}
	
	public JPanel grid() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		grid.add(nom, c);
		
		c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		grid.add(lastName, c);
		
		c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		grid.add(prenom, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		grid.add(firstName, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		grid.add(id, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		grid.add(identifiant, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		grid.add(mdp, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		grid.add(password, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		grid.add(sexe, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		grid.add(gender, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		grid.add(age, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		grid.add(old, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		grid.add(poids, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 6;
		grid.add(weight, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		grid.add(taille, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 7;
		grid.add(tall, c);
		
		return grid;
	}

	public JPanel echec() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		/*c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		grid.add(echec, c);*/
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		grid.add(classement, c);
		
		c.ipadx = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		grid.add(elo, c);
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		grid.add(frequence, c);
		
		c.ipadx = 80;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		grid.add(frequency, c);
		
		return grid;
	}
	
	public JPanel completeEchec() {
		JPanel grid = echec();
		
		JPanel echec = new JPanel();
		echec.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		echec.add(joueurechec, c);
		
		c.gridx = 0;
		c.gridy = 1;
		echec.add(grid, c);
		
		return echec;
	}
	
	public JPanel buttons() {
		JPanel button = new JPanel();
		button.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		button.add(retour, c);
		
		c.insets = new Insets(0,40,0,0);
		c.gridx = 3;
		c.gridy = 0;
		button.add(enregistrer, c);
		
		return button;
	}
	
	public JPanel completeGrid(JPanel grid, JPanel echec, JPanel button) {
		JPanel complete = new JPanel();
		complete.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		complete.add(titre, c);
		
		c.gridx = 0;
		c.gridy = 1;
		complete.add(sousTitre, c);
		
		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 3;
		complete.add(grid, c);
		
		c.gridx = 0;
		c.gridy = 4;
		complete.add(echec, c);
		
		c.gridx = 0;
		c.gridy = 5;
		complete.add(button, c);
		
		return complete;
	}

	void erreur() {
		JFrame jf = new JFrame();

		JPanel container = new JPanel();
		container.add(error);

		jf.add(container);
		jf.setSize(400, 80) ;
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setLocationRelativeTo(null);

	}

}
