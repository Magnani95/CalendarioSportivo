package grafica;

import javax.swing.table.AbstractTableModel;
import gestoreSquadre.*;


public class ModelloTabella extends AbstractTableModel {
	
	private CalendarioSportivo calendario;
	private int nSquadre;
	private boolean calendarioPronto;
	
	private String modalita;
	private String squadraSelezionata;
	private int giornataSelezionata;
	
	private String[] nomeColonne = {"Giornata","logo","Casa","Punti Casa","Punti Ospiti","logo", "Ospiti" };
	
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
		case "tutto": return (2*nSquadre*(nSquadre - 1));
		case "giornata": return (calendario.getSquadre().size()/2);
		case "squadra": return (2*calendario.getCalendario().size());
		default: return 1;
		}
		
	}

	public Object getValueAt(int arg0, int arg1) {
		if(modalita=="nessuna")
			return null;
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
