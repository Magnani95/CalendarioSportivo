package gestoreSquadre;

import java.util.Vector;

import grafica.FramePrincipale;
public class CalendarioSportivo {

	//---------Parametri
	private Vector<Giornata> calendario;
	private Vector<Squadra> squadre;
	
	//---------Metodi
	public static void main(String[] args) {
		
		CalendarioSportivo sessione = new CalendarioSportivo();
		FramePrincipale f= new FramePrincipale("Calendario Sportivo", sessione);
		f.setVisible(true);
	}

	public CalendarioSportivo()
	{
		this.calendario = new Vector<Giornata>();
		this.squadre = new Vector<Squadra>();
	}
	
	//Algoritmo di Berger
	public boolean generaCalendario()
	{
		Squadra dummy;
		int nSquadre = this.squadre.size();
		
		if(nSquadre<1) 
			return false;
		
		if(nSquadre%2 != 0) {
			dummy=new SquadraDummy();
			this.aggiungiSquadra(dummy);
		}
		
		int nGiornate= nSquadre - 1;
		
		Vector<Squadra> casa=		new Vector<Squadra>();
		Vector<Squadra> trasferta= 	new Vector<Squadra>();
		
		for(int i=0; i<nSquadre/2; i++) {
			casa.add(squadre.elementAt(i));
			trasferta.add(squadre.elementAt(nSquadre-1-i));
		}
	    
	    for (int i = 0; i < nGiornate /2; i++) {
	    	
			Giornata attuale =  new Giornata();

	    	if(i%2 == 0)
	    		for(int j=0; j<nSquadre/2; i++)
	    			attuale.aggiungiIncontro(new Incontro(trasferta.elementAt(j), casa.elementAt(j)) );
	    	else 
	    		for(int j=0; j<nSquadre/2; i++) 
	    			attuale.aggiungiIncontro(new Incontro( casa.elementAt(j), trasferta.elementAt(j)) );
	    	
	    	calendario.add(attuale);
	    	
	    	Squadra riporto = trasferta.lastElement();
	    	trasferta.removeElementAt(trasferta.size()-1);
	    	trasferta.add(0, casa.elementAt(1));
	    	casa.add(riporto);
	    	casa.removeElementAt(1);
	    	
	    }
	    return true;
	}
	
	public void aggiungiSquadra(Squadra nuova){
		squadre.add(nuova);
	}

	public Vector<Giornata> getCalendario() {
		return calendario;
	}

	public Vector<Squadra> getSquadre() {
		return squadre;
	}
}