package Entite;


public class Pirate {
	//Elouan
	private int vie;
	private int position;
	private int duréeEmpoisonnement;
        private String nom;
	
	public Pirate(String nom) {
		vie=5;
		position=0;
		duréeEmpoisonnement=0;
                this.nom=nom;
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
        
        public String getNom() {
            return nom;
        }
        
        public void setNom(String nom) {
            this.nom = nom;
        }
        
	public void soigner() {
		if(vie<5) {
			vie++;
		}
	}

	public void prendreDegats(int degats) {
		this.vie -= degats;
	}
	//
}
