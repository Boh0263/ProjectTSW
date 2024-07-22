package model;

public class Abbigliamento extends Prodotto {
	private static final long serialVersionUID = 1L;
	
	private final int ID_Abbigliamento;
	private String Tipo; 
	private String Genere; 
	
	public Abbigliamento(String Nome, double Prezzo, String Descrizione, int Giacenza, int img1, int img2, int img3, int ID_Abbigliamento, String Tipo, String Genere) {
		super(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3);
		this.ID_Abbigliamento = ID_Abbigliamento;
		this.Tipo = Tipo;
		this.Genere = Genere;
	}
	
	public Abbigliamento() {
		this.ID_Abbigliamento = -1;
	
	}
	
	public Abbigliamento (String nome) {
		super(nome);
		this.ID_Abbigliamento = -1;
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
	public void setGenere(String Materiale) {
		this.Genere = Materiale;
	}
	public String getGenere() {
		return Genere;
	}
}
	
	