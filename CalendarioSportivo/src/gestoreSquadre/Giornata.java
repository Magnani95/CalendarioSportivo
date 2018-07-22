package gestoreSquadre;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
/**
 * Classe che rappresenta una giornata sportiva, ovvero un Vector di Incontri.
 * @author Andrea Magnani
 *@see CalendarioSportivo
 *@see Incontro
 */
public class Giornata implements Serializable{

	/**Long di versione per la serializzazione
	 */
	private static final long serialVersionUID = -7915867314974987869L;
	//--------Parametri
	/**Vector contenente i singoli incontri.
	 */
	private Vector<Incontro> giornata;
	
	//--------Metodi
	/**
	 * Costruttore che inizializza il Vector di Incontri
	 */
	public Giornata() {
		this.giornata = new Vector<Incontro>(); 
	
	}
	/**
	 * Getter per ottenere un certo incontro.
	 * @param numIncontro numero incontro che si vuole recuperare
	 * @return incontro richiedo dall'input
	 */
	public Incontro getIncontro(int numIncontro){
		return giornata.elementAt(numIncontro);
	}
	/**
	 * Metodo per aggiungere un incontro al Vector
	 * @param incontro che si desidera aggiungere
	 */
	public void aggiungiIncontro (Incontro incontro) {
		giornata.add(incontro);
	}
	/**
	 * Getter che restituisce l'Iteratore degli incontri
	 * @return Iterator del Vector di Incontri
	 */
	public Iterator<Incontro> getIterator() {
		return giornata.iterator();
	}
}

