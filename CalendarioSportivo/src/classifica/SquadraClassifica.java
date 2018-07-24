package classifica;

import gestoreSquadre.Squadra;
/**
 * Classe implementa una squadra all'interno di una classifica, ovvero ne riporta i punti ottenuti
 * @author Andrea Magnani
 * @see Squadra
 * @see Classifica
 */
public class SquadraClassifica implements Comparable<SquadraClassifica>{

	/**Squadra*/
	private Squadra squadra;
	/**Punti guadagnati dalla squadra*/
	private int punti;
	
	/**
	 *Costruttore che inizializza l'oggetto associandolo alla squadra passata.
	 * @param sq Squadra da associare all'oggetto
	 */
	public SquadraClassifica(Squadra sq)
	{
		squadra = sq;
		punti = -1;
	}
	/**
	 * Getter per ottenere i punti della squadra
	 * @return i punti ottenuti
	 */
	public int getPunti() {
		return punti;
	}
	/**
	 * Metodo per aggiungere punti alla squadra. Un punteggio negativo, comporta l'interruzione del programma.
	 * @param p Punti da aggiungere
	 */
	public void aggiungiPunti(int p) {
		
		if(p < 0) {
			System.err.println("Errore in SqudraClassifica/aggiungiPunti");
			System.exit(-1);
		}
		if(punti<=0) 
			punti=0;
		
		punti+=p;
	}
	/**
	 * Metodo usato per consentire l'ordinamento del vector
	 */
	public int compareTo(SquadraClassifica altra)
	{
		if(this.punti < altra.getPunti())
			return -1;
		else if(this.punti > altra.getPunti())
			return 1;
		else { // (this.punti == altra.getPunti()
			int cmp = this.squadra.getNome().compareTo(altra.getSquadra().getNome());
			if(cmp<0)
				return-1;
			else if(cmp>0)
				return 1;
			else { //(cmp == 0)
				System.err.println("SquadraClassifica: compareTo stessoNome");
				return 0;
			}
		}
	}
	/**
	 * Getter per ottenere la squadra dell'oggetto.
	 * @return La Squadra dell'oggetto
	 */
	public Squadra getSquadra() {
		return squadra;
	}
}
