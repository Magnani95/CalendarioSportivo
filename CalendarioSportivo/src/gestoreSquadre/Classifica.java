package gestoreSquadre;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

public class Classifica {
	
	protected CalendarioSportivo calendario;
	protected Vector<SquadraClassifica> elencoSquadre;
	protected JFrame frame;
	
	public Classifica (CalendarioSportivo c, JFrame f) 
	{
		calendario=c;
		frame=f;
		elencoSquadre= new Vector<SquadraClassifica>();
		Iterator<Squadra> it=calendario.getSquadre().iterator();
		
		while(it.hasNext() )
			elencoSquadre.add(new SquadraClassifica(it.next()));
		
	}
	
	public boolean calcolaClassifica()
	{
		System.err.println("!!Chiamata su Classifica!!!");
		return false;
	}
	
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
