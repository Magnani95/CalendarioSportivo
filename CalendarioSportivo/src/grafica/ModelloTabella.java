package grafica;

import gestoreSquadre.*;

import java.util.Iterator;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;



public class ModelloTabella extends AbstractTableModel{
	
	private CalendarioSportivo calendario;
	private int nSquadre;
	private boolean calendarioPronto;
	
	private String modalita;
	private String squadraSelezionata;
	private int giornataSelezionata;
	
	private String[] nomeColonne = {"Giornata","Logo Casa","Casa","Punti Casa","Punti Ospiti","Ospiti", "Logo Ospiti" };
	
	
	public ModelloTabella(CalendarioSportivo calendario)
	{
		this.calendario=calendario;
		nSquadre= calendario.getNSquadreCalendario();
		calendarioPronto= false;
		
		modalita="nessuna";
		squadraSelezionata=null;
		giornataSelezionata=0;
	}

	
	public int getColumnCount() {
		return 7;
	}

	
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
	
	public Class getColumnClass(int colonna)
	{
		return getValueAt(0, colonna).getClass();
	}

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
	
	public void setCalendarioPronto(boolean b) {
		calendarioPronto=b;
	}
	
	public void aggiornaSquadra(String nuova)
	{
		squadraSelezionata=nuova;
		modalita="squadra";
	}
	
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
	
	public void aggiornaTutto()
	{	
		modalita="tutto";
	}
	
	public void reset() {
		modalita="nessuna";
	}
	
	
}
