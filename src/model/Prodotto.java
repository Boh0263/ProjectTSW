package model;
import java.io.*;

public abstract class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected final String Nome;
	protected double Prezzo;
	protected double IVA;
	protected int Giacenza = 0;
	protected String Descrizione = "";
	protected String img1 = "";
	protected String img2 = "";
	protected String img3 = "";
	
	public Prodotto() {
		this.Nome = "";
		this.Prezzo = 0;
		this.IVA = 0;
	}
	
	public Prodotto(String Nome, double Prezzo, String Descrizione, int Giacenza, String img1, String img2, String img3) {
		this.Nome = Nome;
		this.Prezzo = Prezzo;
		this.Descrizione = Descrizione;
		this.Giacenza = Giacenza;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
	}
	
	public String getNome() {
		return Nome;
		}

		public double getPrezzo() {
		return Prezzo;
		}

		public void setPrezzo(double Prezzo) {
		this.Prezzo = Prezzo;
		}

		public double getIVA() {
		return IVA;
		}

		public void setIVA(double IVA) {
		this.IVA = IVA;
		}

		public int getGiacenza() {
		return Giacenza;
		}

		public void setGiacenza(int Giacenza) {
		this.Giacenza = Giacenza;
		}

		public String getDescrizione() {
		return Descrizione;
		}

		public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
		}

		public String getImg1() {
		return img1;
		}

		public void setImg1(String img1) {
		this.img1 = img1;
		}

		public String getImg2() {
		return img2;
		}

		public void setImg2(String img2) {
		this.img2 = img2;
		}

		public String getImg3() {
		return img3;
		}

		public void setImg3(String img3) {
		this.img3 = img3;
		}
	
	
	
	
	
	
}