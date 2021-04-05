package gui;

import manager.AuthentificationManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * \file Authentification.java
 * \brief Classe qui permet de créer l'interface de l'Authentification
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 * Classe contenant toutes les fonctions associées à la création de l'interface Authentification.
 *
 */
//Classe qui représente la page d'authentification en interface graphique
public class Authentification extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -541698616292452515L;
	
	private JPanel pan  = new JPanel();
	private JLabel titre = new JLabel("Bienvenue sur ...");
	private JLabel connect = new JLabel("Veuillez vous connecter");
	private JLabel id = new JLabel("Identifiant ");
	private JLabel mdp = new JLabel("Mot de passe ");
	private JTextField identifiant = new JTextField(5);
	private JPasswordField motdepasse = new JPasswordField(5);
	
	private JButton valider = new JButton("Valider");
	
	private JLabel inscription = new JLabel("Vous n'êtes pas encore inscrit ? Inscrivez vous");
	private JButton sinscrire = new JButton("S'inscrire");
	
	private JLabel error = new JLabel("Votre identifiant ou votre mot de passe est faux.");
	
	
	
	public Authentification() {
		build();
	}

	/**
	 * \fn void build()
	 * \brief Fonction qui permet la construction de la fenêtre d'Authentification
	 */
	public void build() {
		this.setTitle("Authentification"); //Cr�ation de la fenetre
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.setSize(350, 350); //Taille de la fen�tre
		setResizable(false); //Taille non changeable
		setLocationRelativeTo(null);
		
		valider.addActionListener(this);
		sinscrire.addActionListener(this);
		
		
		//Mettre les JLabel au centre
		titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		connect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		pan.setLayout(new BorderLayout());
		
		JPanel grid = grid();
		
		JPanel p= new JPanel(); //Sans �a, le bouton prend toute la taille dans le gridlayout
		p.add(valider);
		
		JPanel grid2 = grid2(grid, p);
		
		pan.add(grid2, BorderLayout.CENTER);
		
		this.setContentPane(pan);
		
		setVisible(true) ;
	}

	/**
	 * \fn void actionPerformed(ActionEvent e)
	 * \brief Fonction qui donne des actions aux boutons
	 * \param [in] a ActionEvent (Type ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource();
		if (Button == sinscrire) {
			new Formulaire();
			dispose();
		}
		
		if(Button == valider) {
			if(!identifiant.getText().isEmpty() && !motdepasse.getText().isBlank()) {
				System.out.println("id : " + identifiant.getText());
				System.out.println("mdp : " + motdepasse.getText());

				if(motdepasse.getText().equals(AuthentificationManager.existPassword(identifiant.getText()))) {
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!!! L'identifiant et le mot de passe correspondent !!!!!!!!!!!!!!!!!!!!!!!!");
					new Accueil();
					dispose();
				}
				else {
					erreur();
				}

			}
			else {
				erreur();
			}
		}
	}

	/**
	 * \fn JPanel grid()
	 * \brief Fonction qui retourne un JPanel
	 * \return Retourne un JPanel
	 */
	public JPanel grid() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		grid.add(id, c);
		
		c.ipadx = 80; //Taille de l'endroit ou on peut r��crire
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		grid.add(identifiant, c);
		
		c.ipadx = 0; //Remettre a 0 parce sinon �a d�cale tout (si pas compris, met en commentaire cette ligne tu verras)
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		grid.add(mdp, c);
		
		c.ipadx = 80; //Remettre la taille en question
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		grid.add(motdepasse, c);
		
		c.ipadx=0;
		return grid;
	}


	/**
	 * \fn JPanel grid2(JPanel grid, JPanel p)
	 * \brief Fonction qui assemble tous les JPanel
	 * \param [in] grid JPanel (Type JPanel)
	 * \param [in] p JPanel (Type JPanel)
	 * \return Retourne un JPanel
	 */
	JPanel grid2(JPanel grid, JPanel p) {
		JPanel grid2 = new JPanel();
		grid2.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		grid2.add(titre, c);
		
		c.gridx = 0;
		c.gridy = 1;
		grid2.add(connect, c);
		
		
		c.insets = new Insets(20,0,0,0);
		c.gridx = 0;
		c.gridy = 2;
		grid2.add(grid, c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 3;
		grid2.add(p, c);
		
		c.insets = new Insets(40,0,0,0);
		c.gridx = 0;
		c.gridy = 4;
		grid2.add(inscription, c);
		
		c.insets = new Insets(10,0,0,0);
		c.gridx = 0;
		c.gridy = 5;
		grid2.add(sinscrire, c);
		
		return grid2;
	}

	/**
	 * \fn void erreur()
	 * \brief Fonction qui ouvre une fenêtre d'erreur
	 */
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
