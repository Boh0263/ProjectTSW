package model;

public class Accessorio extends Prodotto {
	@Override
	public String toString() {
		return "Accessorio [ID_Accessorio=" + ID_Accessorio + "]";
	}
	private final int ID_Accessorio;
	public Accessorio() {
		//bisogna chiamare super con valori invalidanti.
		this.ID_Accessorio = -1;
	}
	public Accessorio (String ID, double Prezzo, String Descrizione, int Giacenza, String img1, String img2, String img3, int ID_Accessorio) {
		super(ID, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Accessorio = ID_Accessorio;
	}
	public int getID_Accessorio() {
		return ID_Accessorio;
	}
	// da fare il toString()
}