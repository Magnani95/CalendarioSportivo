package grafica;

import gestoreSquadre.*;

import java.util.Iterator;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;


/**
 * Classe che estende <code>AbstractTableModel</code> e gestisce cosa deve essere mostrato
 * nella JTable sul pannello principale. Implementa le varie viste permesse: totale, giornata, squadra.
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see PannelloPrincipale
 */
public class ModelloTabella extends AbstractTableModel{
	/**CalendarioSportivo dal quale recuperare i dati da mostrare */
	private CalendarioSportivo calendario;
	/**Numero di squadre che partecipano al calendario; evita la chiamata su calendario */
	private int nSquadre;
	/**Indica se il calendario è pronto o meno, per evitare accessi errati al calendario */
	private boolean calendarioPronto;
	/**Indica in che modalità va fornito l'output grafico. I valori ammessi sono "nessuna", "tutto", "giornata" e "squadra" */
	private String modalita;
	/**Il nome della squadra da fornire in output per la vista "squadra"*/
	private String squadraSelezionata;
	/**Il numero della giornata da fornire in output per la vista "giornata" */
	private int giornataSelezionata;
	/**Nome delle colonne da mostrare nella prima riga*/
	private String[] nomeColonne = {"Giornata","Logo Casa","Casa","Punti Casa","Punti Ospiti","Ospiti", "Logo Ospiti" };
	
	/**
	 * Costruttore che inizializza l'oggetto in modalità "nessuna".
	 * @param calendario CalendarioSportivo da cui recuperare i dati
	 */
	public ModelloTabella(CalendarioSportivo calendario)
	{
		this.calendario=calendario;
		nSquadre= calendario.getNSquadreCalendario();
		calendarioPronto= false;
		
		modalita="nessuna";
		squadraSelezionata=null;
		giornataSelezionata=0;
	}

	/**
	 * Metodo che ritorna il numero di colonne da mostrare nella tabella.
	 */
	public int getColumnCount() {
		return 7;
	}

	/**
	 * Metodo che calcola e ritorna quante righe sono da mostrare nella tabella.
	 */
	public int getRowCount()
	{ 
		//System.err.println("Check num righe:\t"+ String.valueOf(calendarioPronto));
		nSquadre= calendario.getNSquadreCalendario();
		
		if(calendarioPronto == false)
			return 1;
			
		int nRighe;
		//System.err.println("Check modalità:\t"+ modalita);
		switch(modalita) {
		
		case "tutto": 
			nRighe= (nSquadre*(nSquadre - 1)) 	>0?	(nSquadre*(nSquadre - 1))+1 : 1;
			break;
		
		case "giornata":
			nRighe= (nSquadre/2) 	>0?	(nSquadre/2)+1 : 1;
			break;
		
		case "squadra": 
			nRighe= (calendario.getCalendario().size())>0?	(calendario.getCalendario().size())+1 : 1;
			break;
		
		default: return 1;
		}
		//System.err.println("nRighe:\t"+ String.valueOf(nRighe));
		return nRighe;
	}
	/**
	 * Metodo che ritorna la classe dell'oggetto mostrato nella colonna.
	 */
	public Class getColumnClass(int colonna)
	{
		return getValueAt(0, colonna).getClass();
	}
	/**
	 * Metodo che ritorna il valore/oggetto da mostrare alle coordinate passate.
	 */
	public Object getValueAt(int riga, int colonna) 
	{
		if(riga !=0 &&calendarioPronto== false)
			return null;
		
		if(riga==0) {
			if(colonna==1 || colonna == 6)
				return calendario.getLogo();
			return nomeColonne[colonna];
		}
		
		switch(modalita) {
		case "tutto": 	return casoTutto(riga, colonna);
		case "giornata":return casoGiornata(riga, colonna);
		case "squadra": return casoSquadra(riga, colonna);
			
		case "nessuna": return null;
		default: return "!!ERRORE!!";
		}
		
	}
	/**
	 * Metodo interno che recupera i dati da ritornare alla tabella nel caso la modalità sia 
	 * impostata su "Tutto", ovvero tutti gli incontri.
	 * @param riga Coordinata riga 
	 * @param colonna Coordinata colonna
	 * @return l'oggetto da mostrare a quelle coordinate
	 */
	private Object casoTutto(int riga, int colonna)
	{
		//System.err.println("casoTutto:\t"+String.valueOf(riga)+"-"+String.valueOf(colonna));
		
		int nSquadre = calendario.getNSquadreCalendario();
		int totGiornate = calendario.getCalendario().size();
		int totIncontri = nSquadre*(nSquadre-1);
		
		int nGiornata = (riga-1)/(nSquadre/2) ;
		//System.err.println("nGiornata:\t"+String.valueOf(nGiornata));
		
		int nIncontro = (riga-1)%(nSquadre/2);
		//System.err.println("nIncontro:\t"+String.valueOf(nIncontro));
		//System.err.println("Coordinate\t"+riga+"-"+colonna);
		
		switch(colonna) {
		case 0:	// num giornata
			return 1+nGiornata;
		case 1: // logo cassa
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getCasa().getLogo();
		case 2:	//nome squadra casa
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getCasa().getNome();
		case 3: // pnt casa
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getPunteggioCasa();
		case 4: // pnt ospiti
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getPunteggioOspite();
		case 5: // nome ospiti
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getOspite().getNome();
		case 6: //logo ospiti
			return calendario.getCalendario().elementAt(nGiornata).getIncontro(nIncontro).getOspite().getLogo();
		
		default: return "!!ERRORE 00 !!";
		}
	}
	/**
	 * Metodo interno che recupera i dati da ritornare alla tabella nel caso la modalità sia 
	 * impostato su "Giornata", ovvero solo i dati della giornata in giornataSelezionata.
	 * @param riga Coordinata riga
	 * @param colonna Coordinata colonna
	 * @return valore da mostrare alle coordinate passate
	 */
	private Object casoGiornata(int riga, int colonna)
	{
		switch(colonna) {
		case 0: 
			return giornataSelezionata+1;
		case 1:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getCasa().getLogo();
		case 2:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getCasa().getNome();
		case 3:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getPunteggioCasa();
		case 4:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getPunteggioOspite();
		case 5:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getOspite().getNome();
		case 6:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga-1).getOspite().getLogo();
		
		default: return "!!ERRORE 01 !! ";
		}
	}
	/**
	 * Metodo interno che recupera i dati da ritornare alla tabella nel caso la modalità sia 
	 * impostato su "Squadra", ovvero solo i dati della squadra in squadraSelezionata.
	 * @param riga Coordinata riga
	 * @param colonna Coordinata colonna
	 * @return valore da mostrare alle coordinate passate
	 */
	private Object casoSquadra(int riga, int colonna)
	{
		//System.err.println("casoSquadra\t"+riga+"-"+colonna);
		if(colonna == 0)
			return riga;
		
		Incontro attuale = trovaIncontro(riga-1);
		
		switch(colonna) {
	 
		case 1:
			return attuale.getCasa().getLogo();
		case 2:
			return attuale.getCasa().getNome();
		case 3:
			return attuale.getPunteggioCasa();
		case 4:
			return attuale.getPunteggioOspite();
		case 5:
			return attuale.getOspite().getNome();
		case 6:
			return attuale.getOspite().getLogo();
		
		default: return "!!ERRORE 02 !! ";
		}
		
	}
	/**
	 * Metodo che trova l'incontro della squadra in squadraSelezionata nella giornata passata.
	 * @param nGiornata Numero della giornata cui si vuole ottenere l'incontro con la squadra in squadraSelezionata
	 * @return Incontro della squadra in squadraSelezionata
	 */
	private Incontro trovaIncontro(int nGiornata)
	{
		Giornata gAttuale = calendario.getCalendario().get(nGiornata);
		Iterator<Incontro> it = gAttuale.getIterator();
		
		while(it.hasNext()) {
			Incontro iAttuale = it.next();
			if(iAttuale.getCasa().getNome() == squadraSelezionata 
				|| iAttuale.getOspite().getNome() == squadraSelezionata) {
				return iAttuale;
				
			}
		}
		System.err.println("Errore in ModelloTabella trovaIncontro()");
		System.exit(-3);
		return null;
		
	}
	/**
	 * Setter per impostare calendarioPronto
	 * @param b Booleano da impostare
	 */
	public void setCalendarioPronto(boolean b) {
		calendarioPronto=b;
	}
	
	public void aggiornaSquadra(String nuova)
	{
		squadraSelezionata=nuova;
		modalita="squadra";
	}
	/**
	 * Metodo che aggiorna il valore in giornataSelezionata e imposta la modalita' su "giornata".
	 * @param nGiornata Numero giornata che si vuole mostrare in output
	 */
	public void aggiornaGiornata(int nGiornata)
	{
		System.err.println("nGiornata\t"+nGiornata);
		
		if(calendarioPronto==false)
			return;
		
		if (nGiornata < 0 || nGiornata >= calendario.getCalendario().size()) {
			System.err.println("Errore in aggiornaTabella giornata");
			System.exit(-2);
		}
		giornataSelezionata=nGiornata;
		modalita="giornata";
	}
	/**
	 * Metodo che imposta la modalita' su "tutto", ovvero mostrare tutti gli incontri
	 */
	public void aggiornaTutto()
	{	
		modalita="tutto";
	}
	/**
	 * Metodo che imposta la modalita' su "nessuna", ovvero mostrare solo i titoli delle colonne
	 */
	public void reset() {
		modalita="nessuna";
	}
	
	
}
