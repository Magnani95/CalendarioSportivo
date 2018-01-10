package grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import gestoreSquadre.*;

public class PannelloPrincipale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	
	private ButtonGroup gruppoAzioni;
	private JButton aggiungi;
	private JButton genera;
	private JButton classifica;
	
	private ButtonGroup gruppoVisualizza;
	private JRadioButton tutteGiornate;
	private JRadioButton singolaGiornata;
	private JRadioButton singolaSquadra;
	
	private JComboBox listaSquadre;
	
	private ModelloTabella modelloTabella;
	private JTable tabella;
	
	public PannelloPrincipale(CalendarioSportivo calendario, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=calendario;
		this.framePrincipale = framePrincipale;
		
		// gruppo azioni
		this.gruppoAzioni= new ButtonGroup();
		this.aggiungi= new JButton("Aggiungi");
		this.genera= new JButton("Genera");
		this.classifica=new JButton("Classifica");
		
		this.gruppoAzioni.add(this.aggiungi);
		this.gruppoAzioni.add(this.genera);
		this.gruppoAzioni.add(this.classifica);

		//gruppo visualizza
		this.gruppoVisualizza= new ButtonGroup();
		this.tutteGiornate= new JRadioButton("Tutte le giornate");
		this.singolaGiornata= new JRadioButton("Giornata singola");
		this.singolaSquadra= new JRadioButton("Singola squadra");
				
		gruppoVisualizza.add(this.tutteGiornate);
		gruppoVisualizza.add(this.singolaGiornata);
		gruppoVisualizza.add(this.singolaSquadra);
		
		//singola squadra
		this.listaSquadre= new JComboBox();
		this.listaSquadre.setEnabled(false);
		this.listaSquadre.setEditable(false);
		
		
		this.modelloTabella= new ModelloTabella(calendario);
		this.tabella= new JTable(modelloTabella);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
