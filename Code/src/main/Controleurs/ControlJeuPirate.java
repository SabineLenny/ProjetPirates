package Controleurs;

import Entite.Pirate;

public class ControleurPirate{
	//Elouan
	public static Pirate creationPirate(String nom) {
		Pirate pirate= new Pirate(nom);
		return pirate;
	}
	//
}