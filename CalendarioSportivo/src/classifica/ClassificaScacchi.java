package classifica;

import java.util.Iterator;

import javax.swing.JFrame;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;

/**
 * Classe che estende la classe Classifica e implementa una classifica di scacchi. i punteggi vengono calcolati
 * raddoppiati per continuare ad usare valori int; dovranno poi essere dimezzati al momento d
 * @author Andrea Magnani
 * @see Classifica
 */
public class ClassificaScacchi extends Classifica {
	
	/**Punti raddoppiati guadagnati da una vittoria  */
	private static final int puntiVittoria = 2;		//punteggi duplicati per evitare di usare float
	/**Punti raddoppiati guadagnati da un pareggio  */
	private static final int puntiPareggio = 1;
	/**Punti raddoppiati guadagnati da una sconfitta  */
	private static final int puntiSconfitta = 0;
	
	/**
	 * Costurttore della classe
	 * @param c Calendario dal quale recuperare squadre e incontri
	 */
	public ClassificaScacchi(CalendarioSportivo c)
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
