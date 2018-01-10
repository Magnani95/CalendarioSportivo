package gestoreSquadre;

import java.awt.*;

public class Squadra {

	//---------Parametri
	private String 			nome;
	private String 			citta;
	private Image 	logo;
	
	//---------Inizio metodi
	
	public Squadra(String nome, String citta, Image immagine)
	{
		this.nome=nome;
		this.citta=citta;
		this.logo=immagine;
	}
	
	public Squadra(String nome, String citta)
	{
		this.nome=nome;
		this.citta=citta;
		this.logo=null;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCitta() {
		return citta;
	}
	public Image getLogo() {
		return this.logo;
	}
}