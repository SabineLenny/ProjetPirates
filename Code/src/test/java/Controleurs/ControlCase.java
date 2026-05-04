package Controleurs;

public class ControlCase {
	//Lenny
	enum TypesCases {
		  NORMAL,BOMBE,EMPOISONNEMENT,SOIN,ECHANGE,DEPART,ARRIVEE
	}
	//TypesCases Case;
	
	void select_case (TypesCases Case) {
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
