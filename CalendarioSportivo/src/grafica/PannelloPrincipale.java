package grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import gestoreSquadre.*;

public class PannelloPrincipale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	
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
		listaSquadre.setEnabled(false);
		listaSquadre.setEditable(false);
		listaSquadre.addActionListener(this);
		
		listaGiornate= new JComboBox<>();
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
			return;
			
		case "Genera Calendario":
			
			if(calendario.getCalendario().size() != 0) {
				int n = JOptionPane.showConfirmDialog(
					    framePrincipale,
					    "Calendario presente. Vuoi continuare?",
					    "Attenzione",
					    JOptionPane.YES_NO_OPTION);
						
			}else {
				if(calendario.generaCalendario() == true) {
					JOptionPane.showMessageDialog(framePrincipale,
						    "Generazione calendario avvenuta con successo",
						    "Calendario generato",
						    JOptionPane.PLAIN_MESSAGE);
					modelloTabella.setCalendarioPronto(true);
					return;
				}else {
					JOptionPane.showMessageDialog(framePrincipale,
						    "Impossibile generare calendario. Controlla di aver inserito almeno una squadra",
						    "Errore",
						    JOptionPane.ERROR_MESSAGE);
				}
					
			}
			break;	
		case "Classifica":
		case "Salva":
		case "Carica":
			
			break;
		
		}
		
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
			modelloTabella.aggiornaGiornata(1);
			break;
		case "singolaSquadra":
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(true);
			modelloTabella.aggiornaSquadra("TODO");
			break;
		}
	}
	
}
	
	

