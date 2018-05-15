package grafica;

import javax.swing.table.AbstractTableModel;

import classifica.Classifica;
import gestoreSquadre.CalendarioSportivo;

public class ModelloTabellaClassifica extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private Classifica classifica;
	private boolean selezioneAttiva;
	
	private String[] nomeColonne = {"Posizione", "Squadra", "Punti"};
	
	public ModelloTabellaClassifica(CalendarioSportivo cld)
	{
		calendario=cld;
		classifica=null;
		selezioneAttiva=false;
	}
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return calendario.getSquadre().size() >0? calendario.getSquadre().size() : 1 ;
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
	public void setSelezioneAttiva(boolean sel) {
		selezioneAttiva=sel;
	}
	public void setClassifica(Classifica cls) {
		classifica=cls;
	}
	
}
