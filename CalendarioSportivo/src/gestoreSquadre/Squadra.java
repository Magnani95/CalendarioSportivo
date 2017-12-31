package gestoreSquadre;

import java.awt.image.BufferedImage;

public class Squadra {

	//---------Parametri
	private String 			nome;
	private String 			citta;
	private BufferedImage 	logo;
	
	//---------Inizio metodi
	
	public Squadra(String nome, String citta, BufferedImage immagine)
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
	public BufferedImage getLogo() {
		return this.logo;
	}
}
