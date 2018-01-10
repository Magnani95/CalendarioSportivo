package grafica;

import javax.swing.table.AbstractTableModel;
import gestoreSquadre.*;


public class ModelloTabella extends AbstractTableModel {
	
	private CalendarioSportivo calendario;
	private String[] nomeColonne = {"Giornata","Casa","Punti Casa","Punti Ospiti", "Ospiti" };
	
	public ModelloTabella(CalendarioSportivo calendario)
	{
		this.calendario=calendario;
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
