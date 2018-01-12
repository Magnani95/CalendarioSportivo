package grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import gestoreSquadre.*;

public class PannelloPrincipale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	
	private JButton gestione;
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
		gestione= new JButton("Gestisci Squadre");
		genera= new JButton("Genera");
		classifica=new JButton("Classifica");


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
		listaGiornate= new JComboBox<>();
		listaGiornate.setEnabled(false);
		listaGiornate.setEditable(false);
		
		// tabella giornate
		modelloTabella= new ModelloTabella(calendario);
		tabella= new JTable(modelloTabella);
		
		
		//aggiunte al panel
		this.add(gestione);
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
		System.out.println("Evento Raccolto!");
		switch(e.getActionCommand()) {
		case "tutteGiornate":
		case "singolaGiornata":
		case "singolaSquadra":	
			visioneTabella(e);
			break;
		
		}
	}
	
	private void visioneTabella(ActionEvent e)
	{
		switch(e.getActionCommand()) {
		case "tutteGiornate":
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(false);
			modelloTabella.aggiornaTabella("tutto");
			break;
		case "singolaGiornata":
			listaGiornate.setEnabled(true);
			listaSquadre.setEnabled(false);
			modelloTabella.aggiornaTabella("giornata");
			break;
		case "singolaSquadra":
			System.out.println("punto RAggiunto!");
			listaGiornate.setEnabled(false);
			listaSquadre.setEnabled(true);
			modelloTabella.aggiornaTabella("squadra");
			break;
		}
	}
	
}
	
	

