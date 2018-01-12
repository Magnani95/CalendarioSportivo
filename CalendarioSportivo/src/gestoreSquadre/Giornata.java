package gestoreSquadre;

import java.util.Iterator;
import java.util.Vector;
public class Giornata {

	//--------Parametri
	private Vector<Incontro> giornata;
	
	//--------Metodi
	
	public Giornata() {
		this.giornata = new Vector<Incontro>(); 
	
	}
	
	public Incontro getIncontro(int numIncontro){
		return giornata.elementAt(numIncontro);
	}
	
	public void aggiungiIncontro (Incontro incontro) {
		giornata.add(incontro);
	}

	public Iterator<Incontro> getIterator() {
		return giornata.iterator();
	}
}

