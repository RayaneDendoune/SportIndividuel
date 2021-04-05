package test;

import gui.Authentification;
import util.DataInit;

/**
 * \file TestSport.java
 * \brief Classe qui permet de tester
 * \author OBEYESEKARA Avishka, CERINI Enzo, DENDOUNE Rayane
 * \version 1.0
 * \date 29/03/2021
 *
 */
public class TestSport {
	public static void main(String[] args) {
		DataInit.createTables();
		new Authentification();
	}
}
