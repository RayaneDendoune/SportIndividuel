package gui;

import javax.swing.JFrame;
import java.awt.*;

public class Accueil extends JFrame {
	
	GraphicsDemo graphic = new GraphicsDemo();
	
	public Accueil() {
		build();
	}
	
	public void build() {
		this.setSize(550, 510);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false); //Taille non changeable
		
		this.add(graphic);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Accueil();
	}
}
