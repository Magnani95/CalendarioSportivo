package gestoreSquadre;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Squadra {

	//---------Parametri
	private String 	nome;
	private String 	citta;
	private ImageIcon logo;
	
	//---------Inizio metodi
	
	public Squadra(String nome, String citta, BufferedImage immagine)
	{
		this.nome=nome;
		this.citta=citta;
		this.logo= new ImageIcon(immagine);
	}
	
	public Squadra(String nome, String citta)
	{
		System.err.println("Generazione Squadra "+nome+" - "+citta);
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
	public ImageIcon getLogo() {
		return this.logo;
	}
	public void setNome(String s) {
		nome=s;
	}
	public void setCitta(String s) {
		citta=s;
	}
	public void setLogo(ImageIcon img) {
		logo = img;
	}
}
