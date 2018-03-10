package Classifica;

import java.util.Iterator;

import javax.swing.JFrame;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;

public class ClassificaCalcio extends Classifica {
	
	private static final int puntiVittoria = 3;
	private static final int puntiPareggio = 1;
	private static final int puntiSconfitta = 0;
	
	
	public ClassificaCalcio(CalendarioSportivo c, JFrame f)
	{
		super(c, f);
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
