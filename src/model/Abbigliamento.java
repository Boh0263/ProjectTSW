package model;

public class Abbigliamento extends Prodotto {
	private static final long serialVersionUID = 1L;
	
	private final int ID_Abbigliamento;
	private String Tipo; //da fare l' enum
	private String Genere; //da fare l'enum; 
	
	public Abbigliamento(String Nome, double Prezzo, String Descrizione, int Giacenza, String img1, String img2, String img3, int ID_Abbigliamento, String Tipo, String Genere) {
		super(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Abbigliamento = ID_Abbigliamento;
		this.Tipo = Tipo;
		this.Genere = Genere;
	}
	
	@Override
	public String toString() {
		return "Abbigliamento [ID_Abbigliamento=" + ID_Abbigliamento + ", Tipo=" + Tipo + ", Genere=" + Genere + "]";
	}

	public int getID_Abbigliamento() {
		return ID_Abbigliamento;
	}
	
	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setMateriale(String Materiale) {
		this.Genere = Materiale;
	}
	public String getMateriale() {
		return Genere;
	}
}
	
	