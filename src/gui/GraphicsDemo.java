package gui;

import javax.swing.*;
import java.awt.*;

public class GraphicsDemo extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawOval(60, 60, 50, 50);
		
		g2d.drawOval(60, 120, 50, 50);
		
		g2d.drawOval(60, 180, 50, 50);
		
		g2d.drawOval(60, 240, 50, 50);
		
		g2d.drawOval(60, 300, 50, 50);
		
		
	}
}
