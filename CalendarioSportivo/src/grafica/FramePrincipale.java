package grafica;
 
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

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
	private MenuItem stampa;
	
	private Menu gestisciSquadre;
	private MenuItem aggiungi;
	private MenuItem modifica;
	
	private PannelloPrincipale pannello;
	
	//---------Metodi
	public FramePrincipale(String titolo, CalendarioSportivo calendario)
	{
		super(titolo);
		this.calendario=calendario;
		
		salva= new MenuItem("Salva");
		salva.addActionListener(this);
		carica= new MenuItem("Carica");
		carica.addActionListener(this);
		stampa= new MenuItem("Stampa");
		stampa.addActionListener(this);
		
		file= new Menu("File");
		
		aggiungi= new MenuItem("Aggiungi");
		aggiungi.addActionListener(this);
		modifica= new MenuItem("Modifica");
		modifica.addActionListener(this);
	
		gestisciSquadre= new Menu("Gestisci Squadre");
		
		file.add(salva);
		file.add(carica);
		file.add(stampa);
		
		gestisciSquadre.add(aggiungi);
		gestisciSquadre.add(modifica);
		
		barra= new MenuBar();
		barra.add(file);
		barra.add(gestisciSquadre);
		this.setMenuBar(barra);
		
		pannello= new PannelloPrincipale(calendario, this);
		add(pannello);
		
		setResizable(false);
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
		
		IOputter io=new IOputter(calendario, "dati.dat");
		
		switch(e.getActionCommand()) {
			case "Carica":
				PannelloCarica p = new PannelloCarica();
				io.setPanel(p);
				f.add(p);
				f.pack();
				f.setVisible(true);
				if(!io.carica())
					JOptionPane.showMessageDialog(this,
						    "Impossibile caricare i dati!",
						    "Errore",
						    JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,
						    "Caricamento riuscito!",
						    "Successo",
						    JOptionPane.DEFAULT_OPTION);
				f.dispose();
				break;
				
			case "Salva":
				if(!io.salva())
					JOptionPane.showMessageDialog(this,
						    "Impossibile salvare i dati!",
						    "Errore",
						    JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,
						    "Salvataggio riuscito!",
						    "Successo",
						    JOptionPane.DEFAULT_OPTION);
				break;
			
			case "Stampa":
				boolean stato=false;
				try {
					stato=pannello.getTabella().print();
					
					if(stato)
						JOptionPane.showMessageDialog(this,
							    "Stampa in corso!",
							    "Successo",
							    JOptionPane.DEFAULT_OPTION);
					else
						JOptionPane.showMessageDialog(this,
							    "Impossibile stampare!",
							    "Errore",
							    JOptionPane.ERROR_MESSAGE);
				}catch(PrinterException ep){
					JOptionPane.showMessageDialog(this,
						    "Impossibile stampare!",
						    "Errore",
						    JOptionPane.ERROR_MESSAGE);
					System.err.println("Errore di stampa, eccezione\t"+ep.getMessage());
					}
				break;
				
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
