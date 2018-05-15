package classifica;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Squadra;

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
	
	public SquadraClassifica getPosizione(int pos)
	{
		return elencoSquadre.get(pos);
	}
	
	protected void ordinaClassifica() {
		Collections.sort(elencoSquadre);
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
