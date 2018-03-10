package grafica;

import javax.swing.table.AbstractTableModel;

import Classifica.Classifica;
import gestoreSquadre.CalendarioSportivo;

public class ModelloTabellaClassifica extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private Classifica classifica;
	
	private String[] nomeColonne = {"Posizione", "Squadra", "Punti"};
	
	public ModelloTabellaClassifica(CalendarioSportivo cld, Classifica cls)
	{
		calendario=cld;
		classifica=cls;
	}
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return calendario.getSquadre().size();
	}
	public Object getValueAt(int riga, int colonna) 
	{
		if(riga==0) 
			return nomeColonne[colonna];
		
		switch(colonna) {
		case 0: return riga;
		case 1: return classifica.getPosizione(riga-1).getSquadra().getNome();
		case 2: 
			if(classifica.getClass().getSimpleName() == "ClassificaScacchi")
				return (double)classifica.getPosizione(riga-1).getPunti() /2;
			else
				return classifica.getPosizione(riga-1).getPunti();
		default : System.err.println("Errore ModelloTabellaClassifica-switch"); System.exit(-1); return -1;
		}
	}
}
