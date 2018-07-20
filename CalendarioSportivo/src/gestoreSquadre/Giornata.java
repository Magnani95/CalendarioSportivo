package gestoreSquadre;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
public class Giornata implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7915867314974987869L;
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

