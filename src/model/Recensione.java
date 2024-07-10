package model;

public class Recensione {
	private String Email;
	private int Votazione;
	private String Commento;
	private String Data_Recensione;
	
	public Recensione(String email, int votazione, String commento, String data_Recensione) {
		Email = email;
		Votazione = votazione;
		Commento = commento;
		Data_Recensione = data_Recensione;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getVotazione() {
		return Votazione;
	}

	public void setVotazione(int votazione) {
		Votazione = votazione;
	}

	public String getCommento() {
		return Commento;
	}

	public void setCommento(String commento) {
		Commento = commento;
	}

	public String getData_Recensione() {
		return Data_Recensione;
	}

	public void setData_Recensione(String data_Recensione) {
		Data_Recensione = data_Recensione;
	}
	
}