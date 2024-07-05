package model;

public class Armatura extends Prodotto {
	private static final long serialVersionUID = 1L;
	
	private final int ID_Armatura;
	private String Materiale;
	@Override
	public String toString() {
		return "Armatura [ID_Armatura=" + ID_Armatura + ", Materiale=" + Materiale + ", Pezzo=" + Pezzo + "]";
	}
	private String Pezzo; //da fare l'enum
	
	public Armatura() {
	//bisogna invocare super con parametri invalidanti.
		this.ID_Armatura = -1;
		this.Materiale = "";
		this.Pezzo = "";
	}
	public Armatura(String ID, double Prezzo, String Descrizione, int Giacenza, int img1, int img2, int img3, int ID_Armatura, String Materiale, String Pezzo) {
		super(ID, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Armatura = ID_Armatura;
		this.Materiale = Materiale;
		this.Pezzo = Pezzo;
	}
	
	public int getID_Armatura() {
		return ID_Armatura;
	}
	
	public void setMateriale(String Materiale) {
		this.Materiale = Materiale;
	}
	public String getMateriale() {
		return Materiale;
	}
	public void setPezzo(String Pezzo) {
		this.Pezzo = Pezzo;
	}
	public String getPezzo() {
		return Pezzo;
	}
	// da fare il toString()
}
	
	