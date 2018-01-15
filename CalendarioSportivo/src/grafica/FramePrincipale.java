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
	
	private Menu gestisciSquadre;
	private MenuItem aggiungi;
	private MenuItem modifica;
	
	private PannelloPrincipale pannello;
	
	//---------Metodi
	public FramePrincipale(String titolo, CalendarioSportivo calendario)
	{
		super(titolo);
		this.calendario=calendario;
		
		MenuItem salva= new MenuItem("Salva");
		salva.addActionListener(this);
		MenuItem carica= new MenuItem("Carica");
		carica.addActionListener(this);
		Menu file= new Menu("File");
		
		MenuItem aggiungi= new MenuItem("Aggiungi");
		aggiungi.addActionListener(this);
		MenuItem modifica= new MenuItem("Modifica");
		modifica.addActionListener(this);
	
		Menu gestisciSquadre= new Menu("Gestisci Squadre");
		
		file.add(salva);
		file.add(carica);
		
		gestisciSquadre.add(aggiungi);
		gestisciSquadre.add(modifica);
		
		MenuBar barra= new MenuBar();
		barra.add(file);
		barra.add(gestisciSquadre);
		this.setMenuBar(barra);
		
		pannello= new PannelloPrincipale(calendario, this);
		add(pannello);
		
		pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		System.err.println(e.getActionCommand());
		
		JFrame f=new JFrame("Calendario Sportivino");
		f.addWindowListener(new FrameAscoltatore(f, this));
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		switch(e.getActionCommand()) {
			case "Salva":
		
			case "Carica":
				
			case "Aggiungi":
				f.add(new PannelloAggiungi(f, calendario, this));
				f.pack();
				f.setVisible(true);
				this.setEnabled(false);
				break;
			case "Modifica":
				f.add(new PannelloModifica(f, calendario, this));
				f.pack();
				f.setVisible(true);
				this.setEnabled(false);
				
				break;
			default: System.err.println("Errore FramePrincipale->actionPerformed"); System.exit(-1);
		}
		
	}
	
}
