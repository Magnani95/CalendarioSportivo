package grafica;

import gestoreSquadre.*;

import java.util.Iterator;

import javax.swing.table.AbstractTableModel;



public class ModelloTabella extends AbstractTableModel {
	
	private CalendarioSportivo calendario;
	private int nSquadre;
	private boolean calendarioPronto;
	
	private String modalita;
	private String squadraSelezionata;
	private int giornataSelezionata;
	
	private String[] nomeColonne = {"Giornata","Logo Ospiti","Casa","Punti Casa","Punti Ospiti","Ospiti", "Logo Ospiti" };
	
	public ModelloTabella(CalendarioSportivo calendario)
	{
		this.calendario=calendario;
		nSquadre= calendario.getSquadre().size();
		calendarioPronto= false;
		
		modalita="nessuna";
		squadraSelezionata=null;
		giornataSelezionata=-1;
	}

	
	public int getColumnCount() {
		return 7;
	}

	
	public int getRowCount()
	{ 
		switch(modalita) {
		case "tutto": 
			return (nSquadre*(nSquadre - 1)) 			>0?	(nSquadre*(nSquadre - 1)) : 1 ;
		case "giornata":
			return (calendario.getSquadre().size()/2) 	>0?	(calendario.getSquadre().size()/2) : 1;
		case "squadra": 
			return (2*calendario.getCalendario().size())>0?	(2*calendario.getCalendario().size()) : 1;
		
		default: return 1;
		}
		
	}

	public Object getValueAt(int riga, int colonna) 
	{
		
		if(riga==0) {
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
		int nSquadre = calendario.getSquadre().size();
		//int totGiornate = calendario.getCalendario().size();
		//int totIncontri = nSquadre*(nSquadre-1);
		
		int nGiornata = riga/(nSquadre/2);
		int nIncontro = riga%(nSquadre/2);
		
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
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getCasa().getLogo();
		case 2:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getCasa().getNome();
		case 3:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getPunteggioCasa();
		case 4:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getPunteggioOspite();
		case 5:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getOspite().getNome();
		case 6:
			return calendario.getCalendario().get(giornataSelezionata).getIncontro(riga).getOspite().getLogo();
		
		default: return "!!ERRORE 01 !! ";
		}
	}

	private Object casoSquadra(int riga, int colonna)
	{
		Incontro attuale = trovaIncontro(squadraSelezionata, riga-1);
		
		switch(colonna) {
		case 0: 
			return giornataSelezionata+1;
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
	
	private Incontro trovaIncontro(String squadra, int nGiornata)
	{
		Giornata gAttuale = calendario.getCalendario().get(nGiornata);
		Iterator<Incontro> it = gAttuale.getIterator();
		
		for(Incontro iAttuale= it.next(); it.hasNext(); iAttuale=it.next() ) {
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
	
	public void aggiornaTabella(String nuova)
	{
		switch(nuova) {
		case "nessuna":
			modalita="nessuna"; break;
		case "tutto":
			modalita="tutto"; break;
		default:
			squadraSelezionata=nuova;
			modalita="squadra";
		}
	}
	
	public void aggiornaTabella(int nGiornata)
	{
		if (nGiornata <= 0 || nGiornata >= calendario.getCalendario().size()*2) {
			System.err.println("Errore in aggiornaTabella giornata");
			System.exit(-2);
		}
		giornataSelezionata=nGiornata;
		modalita="giornata";
	}
	
}
