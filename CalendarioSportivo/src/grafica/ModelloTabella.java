package grafica;

import javax.swing.table.AbstractTableModel;
import gestoreSquadre.*;


public class ModelloTabella extends AbstractTableModel {
	
	private CalendarioSportivo calendario;
	private int nSquadre;
	private boolean calendarioPronto;
	
	private String[] nomeColonne = {"Giornata","logo","Casa","Punti Casa","Punti Ospiti","logo", "Ospiti" };
	
	public ModelloTabella(CalendarioSportivo calendario)
	{
		this.calendario=calendario;
		nSquadre= calendario.getSquadre().size();
		calendarioPronto= false;
	}

	
	public int getColumnCount() {
		return 7;
	}

	
	public int getRowCount() {
		return 2*nSquadre*(nSquadre - 1);
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCalendarioPronto(boolean b) {
		calendarioPronto=b;
	}
}
