package classifica;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;

public class ClassificaBasket extends Classifica {
	
	private static final int puntiVittoria = 2;
	private static final int puntiPareggio = 0;
	private static final int puntiSconfitta = 0;
	private boolean avviso;
	
	public ClassificaBasket(CalendarioSportivo c, JFrame f)
	{
		super(c, f);
		avviso= false; 
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
						if(avviso==false) {
							JOptionPane.showMessageDialog(frame,
								    "E' stato trovato un pareggio, risultato impossibile nel Basket. Verra' contata come partita non giocata",
								    "Attenzione",
								    JOptionPane.INFORMATION_MESSAGE);
							avviso=true;	
						}
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
