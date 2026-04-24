package Entite;


public class Pirate {
	//Elouan
	private int vie;
	private int position;
	private int duréeEmpoisonnement;
	
	public Pirate() {
		vie=5;
		position=0;
		duréeEmpoisonnement=0;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getDuréeEmpoisonnement() {
		return duréeEmpoisonnement;
	}
	public void setDuréeEmpoisonnement(int duréeEmpoisonnement) {
		this.duréeEmpoisonnement = duréeEmpoisonnement;
	}
	public int getVie() {
		return vie;
	}
	public void setVie(int vie) {
		this.vie = vie;
	}
	public void soigner() {
		if(vie<5) {
			vie++;
		}
	}
	//
}
