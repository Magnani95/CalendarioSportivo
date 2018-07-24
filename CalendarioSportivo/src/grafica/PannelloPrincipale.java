package grafica;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

import classifica.Classifica;
import gestoreSquadre.*;
/**
 * Pannello incluso nel PannelloPrincipale che permette tramite bottoni le operazioni di generazione del calendario, la visione
 * della classifica e l'inserimento dei risultati. Include anche una tabella con gli incontri e i bottoni per scegliere la vista
 * desiderata.
 * @author Andrea Magnani
 * @see PannelloPrincipale
 * @see PannelloClassifica
 * @see CalendarioSportivo
 * @see ModelloTabella
 * @see PannelloRisultati
 */
public class PannelloPrincipale extends JPanel implements ActionListener {

	/**CalendarioSportivo associato*/
	private CalendarioSportivo calendario;
	/**Frame principale e contenitore del pannello*/
	private FramePrincipale framePrincipale;
	/**Vector coi nomi delle squadre per le JComboBox di selezione della vista tabella*/
	private Vector<String> nomiSquadre;
	
	/**Bottone per generare gli incontri */
	private JButton genera;
	/**Bottone per mostrare la classifica */
	private JButton classifica;
	/**Bottone per impostare i risultati */
	private JButton risultati;
	
	/**Gruppo di bottoni per gestire le viste tabella */
	private ButtonGroup gruppoVisualizza;
	/**Bottone per vedere tutti gli incontri */
	private JRadioButton tutteGiornate;
	/**Bottone per vedere solo una singola giornata nella tabella*/
	private JRadioButton singolaGiornata;
	/**Bottone per vedere una singola squadra nella tabella*/
	private JRadioButton singolaSquadra;
	
	/**Lista delle squadre per la vista tabella*/
	private JComboBox listaSquadre;
	/**Lista delle giornate per la vista tabella*/
	private JComboBox listaGiornate;
	
	/**Modello tabella associato alla JTable */
	private ModelloTabella modelloTabella;
	/**JTable usata per mostrare gli incontri */
	private JTable tabella;
	/**Pannello con scrollbar usata per gestire la tabella */
	private JScrollPane jsp;
	/**
	 * Costruttore della classe che inizializza le componenti.
	 * @param calendario Calendario associato
	 * @param framePrincipale Frame che contiene il pannello
	 */
	public PannelloPrincipale(CalendarioSportivo calendario, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=calendario;
		this.framePrincipale = framePrincipale;
		setLayout(new BorderLayout(5, 5) );
		
		// gruppo azioni
		JPanel panAzioni= new JPanel();
		genera= new JButton("Genera Calendario");
		genera.addActionListener(this);
	
		classifica=new JButton("Classifica");
		classifica.addActionListener(this);
		
		risultati= new JButton("Imposta Risultati");
		risultati.addActionListener(this);
		
		//gruppo visualizza
		JPanel panVisualizza = new JPanel();
		
		gruppoVisualizza= new ButtonGroup();
		
		tutteGiornate= new JRadioButton("Tutte le giornate");
		tutteGiornate.setActionCommand("tutteGiornate");
		tutteGiornate.addActionListener(this);
		
		singolaGiornata= new JRadioButton("Giornata singola");
		singolaGiornata.setActionCommand("singolaGiornata");
		singolaGiornata.addActionListener(this);
		
		singolaSquadra= new JRadioButton("Singola squadra");
		singolaSquadra.setActionCommand("singolaSquadra");
		singolaSquadra.addActionListener(this);		
		
		gruppoVisualizza.add(this.tutteGiornate);
		gruppoVisualizza.add(this.singolaGiornata);
		gruppoVisualizza.add(this.singolaSquadra);
		
		//liste
		listaSquadre= new JComboBox();
		listaSquadre.setActionCommand("selSquadra");
		listaSquadre.setEnabled(false);
		listaSquadre.setEditable(false);
		listaSquadre.addActionListener(this);
		
		listaGiornate= new JComboBox();
		listaGiornate.setActionCommand("selGiornata");
		listaGiornate.setEnabled(false);
		listaGiornate.setEditable(false);
		listaGiornate.addActionListener(this);
		
		// tabella giornate
		modelloTabella= new ModelloTabella(calendario);
		tabella= new JTable(modelloTabella);
		tabella.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabella.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabella.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabella.getColumnModel().getColumn(5).setPreferredWidth(120);
		calendario.setModelloTabella(modelloTabella);
		
		//aggiunte al panel
		
		panAzioni.add(genera);
		panAzioni.add(classifica);
		panAzioni.add(risultati);
	
		panVisualizza.add(tutteGiornate);
		panVisualizza.add(singolaGiornata);
		panVisualizza.add(listaGiornate);
		panVisualizza.add(singolaSquadra);
		panVisualizza.add(listaSquadre);
		
		JPanel panAlto = new JPanel();
		panAlto.setLayout(new BorderLayout());
		panAlto.add(panAzioni, BorderLayout.NORTH);
		panAlto.add(panVisualizza, BorderLayout.SOUTH);
		
		add(panAlto, BorderLayout.NORTH);
		
		tabella.setTableHeader(null);
		jsp= new JScrollPane(tabella);
		jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_NEVER);
		add(jsp, BorderLayout.SOUTH);
		
		 
	
		//add(tabella, BorderLayout.CENTER);
}

	/**
	 * Metodo per ascoltare e gestire gli eventi associati al panel
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		System.err.println("Raccolto evento:\t" + e.getActionCommand());
		switch(e.getActionCommand()) {
		
		case "tutteGiornate":
		case "singolaGiornata":
		case "singolaSquadra":	
			visioneTabella(e);
			modelloTabella.fireTableDataChanged();
			break;
			
		case "Genera Calendario":
			
			gruppoVisualizza.clearSelection();
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(false);
			modelloTabella.reset();
			
			if(calendario.getCalendario().size() != 0) {
				int n = JOptionPane.showConfirmDialog(
							framePrincipale,
							"Calendario presente. Vuoi continuare?",
							"Attenzione",
							JOptionPane.YES_NO_OPTION);
			 	
			 	if (n == JOptionPane.NO_OPTION || n == JOptionPane.CLOSED_OPTION)
			 		return;
			 	else
			 		calendario.getCalendario().clear();
			}
			 
			 
			if(calendario.generaCalendario() == true) {
				JOptionPane.showMessageDialog(framePrincipale,
					    "Generazione calendario avvenuta con successo",
					    "Calendario generato",
					    JOptionPane.PLAIN_MESSAGE);
				//modelloTabella.setCalendarioPronto(true);
				return;
			}else {
				JOptionPane.showMessageDialog(framePrincipale,
					    "Impossibile generare calendario. Controlla di aver inserito almeno una squadra",
					    "Errore",
					    JOptionPane.ERROR_MESSAGE);
				}
					
			
			break;	
		
		case "selSquadra": 
			int n= listaSquadre.getSelectedIndex();
			modelloTabella.aggiornaSquadra(nomiSquadre.get(n<0? 0:n));
			
			break;
		case "selGiornata":
			int m =listaGiornate.getSelectedIndex();
			System.err.println("Idx\t"+m);
			modelloTabella.aggiornaGiornata(m<0? 0:m);
			
			break;
			
		case "Imposta Risultati":
			
			if(calendario.getCalendario().isEmpty()) {
				JOptionPane.showMessageDialog(framePrincipale,
					    "Nessun calendario generato",
					    "Errore",
					    JOptionPane.WARNING_MESSAGE);
						return;
			}
				
			JFrame f=new JFrame("Modifica Risultati");
			f.addWindowListener(new FrameAscoltatore(f, framePrincipale));
			f.setResizable(false);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f.add(new PannelloRisultati(f, calendario, framePrincipale));
			f.pack();
			f.setVisible(true);
			framePrincipale.setEnabled(false);
			
			break;
			
		case "Classifica":
			JFrame fc= new JFrame("Classifica");
			fc.addWindowListener(new FrameAscoltatore(fc, framePrincipale));
			fc.setResizable(false);
			fc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			fc.add(new PannelloClassifica(calendario, fc));
			fc.pack();
			fc.setVisible(true);
			fc.setResizable(true);
			framePrincipale.setEnabled(false);
			
			break;		
			
			default: System.err.println("Errore in PannelloPrincipale-switch"); System.exit(-1);
		}
		
		System.err.println("Repaint FramePrincipale");
		framePrincipale.repaint();
		framePrincipale.pack();
	}
	
	/**
	 * Metodo per gestire a parte gli eventi riguardanti la vista della tabella
	 * @param e ActionEvent da gestire
	 */
	private void visioneTabella(ActionEvent e)
	{
		System.err.println("Inizio visioneTabella");
		switch(e.getActionCommand()) {
		case "tutteGiornate":
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(false);
			modelloTabella.aggiornaTutto();
			break;
			
		case "singolaGiornata":
			listaGiornate.setEnabled(true);
			listaSquadre.setEnabled(false);
			
			listaGiornate.removeAllItems();
			if(calendario.getCalendario().size() == 0)
				return;
			
			for(int i=0; i!=calendario.getCalendario().size(); i++)
				listaGiornate.addItem(i+1);
			
			modelloTabella.aggiornaGiornata(0);
			break;
			
		case "singolaSquadra":
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(true);
			
			listaSquadre.removeAllItems();
			nomiSquadre=calendario.getNomiSquadre();
			
			if(calendario.getNSquadreCalendario() == 0)
				return;
			
			
			//rimuovo le squadre aggiunte dopo la generazione del calendario.
			for(int i=calendario.getNSquadreCalendario(); i!=calendario.getSquadre().size(); i++ ) {
				nomiSquadre.remove(calendario.getNSquadreCalendario());
			}
			Iterator<String> it = nomiSquadre.iterator();
			
			while(it.hasNext()) {
				listaSquadre.addItem(it.next());
			}
			
			modelloTabella.aggiornaSquadra(nomiSquadre.get(0));
			break;
			
		default: System.err.println("Errore in VisioneTabella");
		}
	}
	/**
	 * Getter per ottenere la tabella contenuta nel panel
	 * @return JTable del panel
	 */
	public JTable getTabella() {
		return this.tabella;
	}
}
	
	

