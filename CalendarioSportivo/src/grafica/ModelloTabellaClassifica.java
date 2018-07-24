package grafica;

import javax.swing.table.AbstractTableModel;

import classifica.Classifica;
import gestoreSquadre.CalendarioSportivo;
/**
 * Classe che implementa tramite <code>AbstractTableModel</code> la vista di una classifica. 
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see PannelloClassifica
 * @see Classifica
 */
public class ModelloTabellaClassifica extends AbstractTableModel {
	
	/**CalendarioSportivo associato */
	private CalendarioSportivo calendario;
	/**Classifica dalla quale recuperare i dati da mostrare*/
	private Classifica classifica;
	/**Indica se è stata compiuta una selezione e bisogna quindi mostrare dei dati in output */
	private boolean selezioneAttiva;
	/**Nomi delle colonne da mostrare nella prima riga */
	private String[] nomeColonne = {"Posizione", "Squadra", "Punti"};
	
	/**
	 * Costruttore per inizializzare l'oggetto
	 * @param cld Il calendario associato all'oggetto
	 */
	public ModelloTabellaClassifica(CalendarioSportivo cld)
	{
		calendario=cld;
		classifica=null;
		selezioneAttiva=false;
	}
	/**
	 * Metodo che ritorna il numero di colonne da mostrare in output grafico
	 */
	public int getColumnCount() {
		return 3;
	}
	/**
	 * Metodo che ritorna il numero di righe da mostrare in output grafico
	 */
	public int getRowCount() {
		
		if(selezioneAttiva==false)
			return 1;
		
		//System.err.println("ClassificaTabella - row :\t"+calendario.getSquadre().size());
		return calendario.getSquadre().size() >0? calendario.getSquadre().size()+1 : 1 ;
	}
	/**
	 * Metodo che ritorna l'oggetto/valore da mostrare alle coordinate passate.
	 * I dati di una classificaScacchi vengono dimezzati.
	 */
	public Object getValueAt(int riga, int colonna) 
	{
		if(riga==0) 
			return nomeColonne[colonna];
		
		switch(colonna) {
		case 0: return riga;
		case 1: return classifica.getPosizione(riga-1).getSquadra().getNome();
		case 2: 
			
			if(classifica.getClass().getSimpleName().equals("ClassificaScacchi")) {
				double ris = ((double)classifica.getPosizione(riga-1).getPunti() )/2;
				if(ris<0)
					return -1;
				else
					return ris;
			}else
				return classifica.getPosizione(riga-1).getPunti();
		default : System.err.println("Errore ModelloTabellaClassifica-switch"); System.exit(-1); return -1;
		}
	}
	/**
	 * Metodo per impostare la selezioneAttiva
	 * @param sel Valore booleano da impostare
	 */
	public void setSelezioneAttiva(boolean sel) {
		selezioneAttiva=sel;
	}
	/**
	 * Metodo per impostare la classifica cui recuperare i dati da mostrare
	 * @param cls Classifica da impostare
	 */
	public void setClassifica(Classifica cls) {
		classifica=cls;
	}
	
}
