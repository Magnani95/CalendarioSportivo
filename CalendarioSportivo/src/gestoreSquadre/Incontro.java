package gestoreSquadre;

import java.io.Serializable;
/**
 * Classe che rappresenta un incontro tra due squadre, che salva i rispettivi punteggi e il risultato.
 * @author Andrea Magnani
 * @see Giornata
 * @see Squadra
 * @see Risultato
 */
public class Incontro implements Serializable{

	/**
	 *long di versione usato per permettere la serializzazione 
	 */
	private static final long serialVersionUID = 537182288236741033L;
	//------Parametri
	/**Squadra che gioca in casa */
	private Squadra		casa;
	/**Squadra che gioca ospite */
	private Squadra 	ospite;
	/**Punteggio della squadra in casa */
	private int 		punteggioCasa;
	/**Punteggio della squadra ospite */
	private int			punteggioOspite;
	/** Enum che rappresenta il risultato, per evitare ogni volta il calcolo dei punteggi*/
	private Risultato 	risultato;
	
	//-------Metodi
	/**
	 * Costruttore dell'Incontro
	 * @param casa Squadra che gioca in casa
	 * @param ospite Squadra ceh gioca ospite
	 */
	public Incontro(Squadra casa, Squadra ospite)
	{
		this.casa=casa;
		this.ospite=ospite;
		this.punteggioCasa=-1;
		this.punteggioOspite=-1;
		this.risultato=Risultato.nonGiocata;
	}
	/**
	 * Metodo per impostare il risultato dell'incontro. Imposta automaticamente il risultato
	 * @param puntiCasa punteggio realizzato dalla squadra in casa
	 * @param puntiOspite punteggio realizzato dalla squadra ospite
	 */
	public void setRisultato(int puntiCasa, int puntiOspite)
	{
		if(puntiCasa <0 || puntiOspite<0) {
			setNonGiocata();
			puntiCasa=-1;
			puntiOspite=-1;
			return;
		}
		
		this.punteggioCasa=puntiCasa;
		this.punteggioOspite=puntiOspite;
		updateRisultato(puntiCasa, puntiOspite);
	}
	/**
	 * Metodo che imposta il Risultato in base al punteggio.
	 * @param casa punteggio realizzato dalla squadra in casa
	 * @param ospite punteggio realizzato dalla squadra ospite
	 */
	private void updateRisultato(int casa, int ospite)
	{
		if(casa>ospite)
			this.risultato=Risultato.casa;
		else if(casa<ospite)
			this.risultato=Risultato.ospite;
		else //Pareggio
			this.risultato=Risultato.pareggio;	
	}
	/**
	 * Metodo per impostare il risultato e il punteggio come "Non Giocato"
	 */
	public void setNonGiocata()
	{
		this.risultato= Risultato.nonGiocata;
		this.punteggioCasa=-1;
		this.punteggioOspite=-1;
	}
	/**Getter per ottenere la Squadra che gioca in casa
	 * @return la Squadra in casa
	 * */
	public Squadra getCasa() {
		return casa;
	}
	/**
	 * Getter per ottenere la Squadra ospite
	 * @return Squadra ospite
	 */
	public Squadra getOspite() {
		return ospite;
	}
	/**
	 * Getter per ottenere il punteggio realizzato dalla squadra in casa
	 * @return punteggio della squadra in casa
	 */
	public int getPunteggioCasa() {
		return punteggioCasa;
	}
	/**
	 * Getter per ottenere il punteggio realizzato dalla squadra ospite
	 * @return punteggio della squadra ospite
	 */
	public int getPunteggioOspite() {
		return punteggioOspite;
	}
	/**
	 * Getter per ottenere il risultato
	 * @return enum Risultato dell'incontro
	 */
	public Risultato getRisultato() {
		return risultato;
	}

	
}
