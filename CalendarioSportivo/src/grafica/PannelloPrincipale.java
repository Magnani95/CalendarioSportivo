package grafica;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import gestoreSquadre.*;

public class PannelloPrincipale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	private Vector<String> nomiSquadre;
	
	private JButton genera;
	private JButton classifica;
	
	private ButtonGroup gruppoVisualizza;
	private JRadioButton tutteGiornate;
	private JRadioButton singolaGiornata;
	private JRadioButton singolaSquadra;
	
	private JComboBox listaSquadre;
	private JComboBox listaGiornate;
	
	private ModelloTabella modelloTabella;
	private JTable tabella;
	
	public PannelloPrincipale(CalendarioSportivo calendario, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=calendario;
		this.framePrincipale = framePrincipale;
		
		// gruppo azioni
		
		genera= new JButton("Genera Calendario");
		genera.addActionListener(this);
		
		classifica=new JButton("Classifica");
		classifica.addActionListener(this);

		//gruppo visualizza
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
		tabella.getSelectionModel().addListSelectionListener(modelloTabella);;
		
		//aggiunte al panel
		add(genera);
		add(classifica);
		
		add(tutteGiornate);
		add(singolaGiornata);
		add(listaGiornate);
		add(singolaSquadra);
		add(listaSquadre);
		
		add(tabella);

		
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		
		System.err.println("Raccolto evento:\t" + e.getActionCommand());
		switch(e.getActionCommand()) {
		
		case "tutteGiornate":
		case "singolaGiornata":
		case "singolaSquadra":	
			visioneTabella(e);
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
		case "Classifica":
		case "Salva":
		case "Carica":
			
			break;
		
		}
		
		framePrincipale.repaint();
		framePrincipale.pack();
	}
	
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
	
}
	
	

