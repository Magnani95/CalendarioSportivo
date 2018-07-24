package classifica;

import java.util.Iterator;

import javax.swing.JFrame;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;
/**
 * Classe che estende Classifica e implementa una classifica di calcio.
 * @author Andrea Magnani
 * @see Classifica
 */
public class ClassificaCalcio extends Classifica {
	
	/**Punti guadagnati per la vittoria*/
	private static final int puntiVittoria = 3;
	/**Punti guadagnati per il pareggio*/
	private static final int puntiPareggio = 1;
	/**Punti guadagnati per la sconfitta*/
	private static final int puntiSconfitta = 0;
	
	/**
	 * Costuttore della classe che inizializza le componenti
	 * @param c Calendario dal quale recuperare le squadre e i risultati
	 */
	public ClassificaCalcio(CalendarioSportivo c)
	{
		super(c);
	}

	public boolean calcolaClassifica() 
	{
		Iterator<Giornata> itGiornata=calendario.getCalendario().iterator();
		Iterator<Incontro> itIncontro;
		
		while(itGiornata.hasNext()) {
			itIncontro=itGiornata.next().getIterator();
			while(itIncontro.hasNext()) {
				Incontro attuale= itIncontro.next();
				switch(attuale.getRisultato()) {
					case casa:
						super.assegnaPunti(attuale.getCasa(), puntiVittoria);
						super.assegnaPunti(attuale.getOspite(), puntiSconfitta);
						break;
					case ospite:
						super.assegnaPunti(attuale.getCasa(), puntiSconfitta);
						super.assegnaPunti(attuale.getOspite(), puntiVittoria);
						break;
					case pareggio:
						super.assegnaPunti(attuale.getCasa(), puntiPareggio);
						super.assegnaPunti(attuale.getOspite(), puntiPareggio);
						break;
					case nonGiocata:
						break;
					
					default: System.err.println("Errore in switch calcolaClassifica"); System.exit(-1);
				}
			}
		}
		super.ordinaClassifica();
		return true;
	}
	
}
