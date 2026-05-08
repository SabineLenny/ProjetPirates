package Controleurs;

import Entite.TypeCase;

public class ControlCase {
	//Lenny
	
	//TypesCases Case;
	
	void select_case (TypeCase Case) {
		switch (Case) {
		case NORMAL: {
			
			break;
		}
		case BOMBE: {
			break;
		}
		case EMPOISONNEMENT:{
			break;
		}
		case SOIN:{
			break;
		}
		case ECHANGE:{
			break;
		}
		case DEPART:{
			break;
		}
		case ARRIVEE:{
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + Case);
		}
	}
	
	
	
	
	
}
