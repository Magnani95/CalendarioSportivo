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
	
	private MenuBar menuBar;
	
	private Menu file;
	private MenuItem salva;
	private MenuItem carica;
	
	private PannelloPrincipale pannello;
	
	//---------Metodi
	public FramePrincipale(String titolo, CalendarioSportivo calendario)
	{
		super(titolo);
		this.calendario=calendario;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		salva= new MenuItem("Salva");
		carica= new MenuItem("Carica");
		file = new Menu();
		menuBar= new MenuBar();
		
		salva.addActionListener(this);
		carica.addActionListener(this);
		
		file.add(salva);
		file.add(carica);
		
		menuBar.add(file);
		
		setMenuBar(menuBar);
		pannello= new PannelloPrincipale(calendario, this);
		add(pannello);
		
		pack();
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
