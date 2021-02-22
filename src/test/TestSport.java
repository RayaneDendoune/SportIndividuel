package test;

import gui.Authentification;
import util.DataInit;


public class TestSport {
	public static void main(String[] args) {
		DataInit.createTables();
		new Authentification();
	}
}
