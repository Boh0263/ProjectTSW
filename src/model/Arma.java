package model;

public class Arma extends Prodotto {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Arma [ID_Arma=" + ID_Arma + ", Materiale=" + Materiale + ", Tipo=" + Tipo + ", Utilizzo=" + Utilizzo
				+ "]";
	}

	private final int ID_Arma;
	private String Materiale;
	private String Tipo; //da fare l' enum
	private String Utilizzo; //da fare l'enum
	
	public Arma() {
		this.ID_Arma = -1;
		this.Materiale = "";
		this.Tipo = "";
		this.Utilizzo = "";
	}
	
	public Arma(String ID, double Prezzo, String Descrizione, int Giacenza, String img1, String img2, String img3, int ID_Arma, String Materiale, String Tipo, String Utilizzo) {
		super(ID, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Arma = ID_Arma;
		this.Materiale = Materiale;
		this.Tipo = Tipo;
		this.Utilizzo = Utilizzo;
	}
	
	public int getID_Arma() {
		return ID_Arma;
	}
	
	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setUtilizzo(String Utilizzo) {
		this.Utilizzo = Utilizzo;
	}
	public String getUtilizzo() {
		return Utilizzo;
	}
	
	public void setMateriale(String Materiale) {
	this.Materiale = Materiale;
	}
	
	public String getMateriale() {
		return Materiale;
	}
	// da fare il toString()
	
}