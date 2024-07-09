package model;

public class Accessorio extends Prodotto {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Accessorio [ID_Accessorio=" + ID_Accessorio + "]";
	}
	private final int ID_Accessorio;
	public Accessorio() {
		this.ID_Accessorio = -1;
	}
	
	public Accessorio(String nome) {
		super(nome);
		this.ID_Accessorio = -1;
	}
	
	public Accessorio (String ID, double Prezzo, String Descrizione, int Giacenza, int img1, int img2, int img3, int ID_Accessorio) {
		super(ID, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Accessorio = ID_Accessorio;
	}
	public int getID_Accessorio() {
		return ID_Accessorio;
	}
}