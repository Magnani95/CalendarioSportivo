package gestoreSquadre;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;
/**
 * Classe che implementa una squadra salvandone nome, citta' e logo.
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see Incontro
 */
public class Squadra implements Serializable{

	/**
	 * long di versione per permettere la serializzazione
	 */
	private static final long serialVersionUID = 2411061205671187889L;
	//---------Parametri
	/**Stringa contenente il nome della squadra */
	private String 	nome;
	/**Stringa contenente il nome della citta' d'origine */
	private String 	citta;
	/**Logo della squadra */
	private ImageIcon logo;
	
	//---------Inizio metodi
	/**
	 * Costruttore della classe con logo
	 * @param nome nome della squadra
	 * @param citta citta' della squadra
	 * @param immagine logo della squadra
	 */
	public Squadra(String nome, String citta, BufferedImage immagine)
	{
		this.nome=nome;
		this.citta=citta;
		this.logo= new ImageIcon(immagine);
	}
	/**
	 * Costruttore della classe senza logo
	 * @param nome nome della squadra
	 * @param citta citta' della squadra
	 */
	public Squadra(String nome, String citta)
	{
		System.err.println("Generazione Squadra "+nome+" - "+citta);
		this.nome=nome;
		this.citta=citta;
		this.logo=null;
	}
	/**
	 * Getter per ottenere il nome della squadra
	 * @return String con nome della squadra
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Getter per ottenere la citta' della squadra
	 * @return String con nome della citta'
	 */
	public String getCitta() {
		return citta;
	}
	/**
	 * Getter per ottenere il logo della Squadra
	 * @return ImageIcon del logo
	 */
	public ImageIcon getLogo() {
		return this.logo;
	}
	/**
	 * Setter per modificare il nome
	 * @param s nuovo nome da impostare
	 */
	public void setNome(String s) {
		nome=s;
	}
	/**
	 * Setter per modificare la citta'
	 * @param s nuova citta' da impostare
	 */
	public void setCitta(String s) {
		citta=s;
	}
	/**
	 * Setter per modificare il logo
	 * @param img nuovo logo da impostare
	 */
	public void setLogo(ImageIcon img) {
		logo = img;
	}
}
