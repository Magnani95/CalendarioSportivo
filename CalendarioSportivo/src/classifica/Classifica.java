package classifica;

import java.awt.Frame;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Squadra;
import grafica.PannelloClassifica;
/**
 * Classe che implementa e calcola una classifica tramite un Vector di SquadraClassifica. 
 * Generalmente si sfrutta il polimorfismo e si assegna a una variabile Classifica una classe figlia.
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see PannelloClassifica
 * @see SquadraClassifica
 * @see ClassificaBasket
 * @see ClassificaCalcio
 * @see ClassificaScacchi
 */
public class Classifica {
	
	/**CalendarioSportivo associato alla classifica*/
	protected CalendarioSportivo calendario;
	/**Vector di SquadraClassifica che implementa la classifica coi punteggi*/
	protected Vector<SquadraClassifica> elencoSquadre;

	
	/**
	 * Costuttore della classe che inizializza gli elementi
	 * @param c CalendarioSportivo da cui recuperare i dati
	 */
	public Classifica (CalendarioSportivo c) 
	{
		calendario=c;
		
		elencoSquadre= new Vector<SquadraClassifica>();
		Iterator<Squadra> it=calendario.getSquadre().iterator();
		
		while(it.hasNext() )
			elencoSquadre.add(new SquadraClassifica(it.next()));
		
	}
	/**
	 * Metodo che aggiona la classifica andando a recuperare le squadre e a controllare i risultati dal calendario.
	 * Al termine la classifica viene ordinata. Se la chiamata è su un'istanza di Classifica, non viene fatto nulla.
	 * @return Ritorna true se la chiamata è fatta su una sottoclasse, false se la chiamata è fatta su un'istanza di Classifica
	 */
	public boolean calcolaClassifica()
	{
		System.err.println("!!Chiamata su Classifica!!!");
		return false;
	}
	/**
	 * Ritorna la squadra alla posizione passata
	 * @param pos Posizione della quale si vuole sapere la squadra
	 * @return Squadra alla posizione passata
	 */
	public SquadraClassifica getPosizione(int pos)
	{
		return elencoSquadre.get(pos);
	}
	/**
	 * Metodo che ordina il Vector di squadre secondo il loro punteggio in ordine decrescente
	 */
	protected void ordinaClassifica() {
		Collections.sort(elencoSquadre, Collections.reverseOrder());
	}
	/**
	 * Assegna i punti passati alla squadra passata sommandoli agli attuali.
	 * @param sq Squadra a cui si vogliono assegnare i punti
	 * @param punti Punti che si vogliono assegnare
	 */
	protected void assegnaPunti(Squadra sq, int punti)
	{
		Iterator<SquadraClassifica> it = elencoSquadre.iterator();
		
		while(it.hasNext()) {
			SquadraClassifica attuale = it.next();
			if(attuale.getSquadra()==sq) {
				attuale.aggiungiPunti(punti);
				break;
			}
		}
	}
}
