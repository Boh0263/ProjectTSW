package model;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected final String Nome;
	protected Double Prezzo;
	protected Double IVA;
	protected Integer Giacenza = 0;
	protected String Descrizione = "";
	protected Integer img1 = 1;
	protected Integer img2 = 1;
	protected Integer img3 =  1;

	
	
	public Prodotto() {
		this.Nome = "";
		this.Prezzo = 0d;
		this.IVA = 0d;
	}
	
	public Prodotto(String nome) {
		this.Nome = nome;
		this.Prezzo = 0d;
		this.IVA = 0d;
	}
	
	
	
	public Prodotto(String Nome, Double Prezzo, String Descrizione, Integer Giacenza, Integer img1, Integer img2, Integer img3) {
		this.Nome = Nome;
		this.Prezzo = BigDecimal.valueOf(Prezzo).setScale(2, RoundingMode.HALF_UP).doubleValue();
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
		return BigDecimal.valueOf(Prezzo).setScale(2, RoundingMode.HALF_UP ).doubleValue();
		}

		public void setPrezzo(Double Prezzo) {
		this.Prezzo = BigDecimal.valueOf(Prezzo).setScale(2, RoundingMode.HALF_UP).doubleValue();
		}

		public Double getIVA() {
		return IVA;
		}

		public void setIVA(Double IVA) {
		this.IVA = IVA;
		}

		public int getGiacenza() {
		return Giacenza;
		}

		public void setGiacenza(Integer Giacenza) {
		this.Giacenza = Giacenza;
		}

		public String getDescrizione() {
		return Descrizione;
		}

		public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
		}
		public int getImg1() {
			return img1;
		}

		public void setImg1(Integer img1) {
			this.img1 = img1;
		}

		public int getImg2() {
			return img2;
		}

		public void setImg2(Integer img2) {
			this.img2 = img2;
		}

		public int getImg3() {
			return img3;
		}

		public void setImg3(Integer img3) {
			this.img3 = img3;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Prodotto prodotto = (Prodotto) obj;
			return Nome.equals(prodotto.Nome);
        }
		
		@Override
		public int hashCode() {
			return Nome.hashCode();
		}
		
		
	
}