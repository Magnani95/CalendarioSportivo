package grafica;
 
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import gestoreSquadre.*;

public class FramePrincipale extends JFrame implements ActionListener{
	
	//----------Parametri
	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	
	private MenuBar barra;
	
	private Menu file;
	private MenuItem salva;
	private MenuItem carica;
	
	private PannelloPrincipale pannello;
	
	//---------Metodi
	public FramePrincipale(String titolo, CalendarioSportivo calendario)
	{
		super(titolo);
		this.calendario=calendario;
		
		MenuItem salva= new MenuItem("Salva");
		MenuItem carica= new MenuItem("Carica");
		Menu file= new Menu("File");
		
		file.add(salva);
		file.add(carica);
		MenuBar barra= new MenuBar();
		barra.add(file);
		this.setMenuBar(barra);
		
		pannello= new PannelloPrincipale(calendario, this);
		add(pannello);
		
		pack();
		//setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
