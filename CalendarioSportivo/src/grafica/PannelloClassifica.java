package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gestoreSquadre.CalendarioSportivo;

public class PannelloClassifica extends JPanel implements ActionListener{

	private CalendarioSportivo calendario;
	
	private ButtonGroup gruppoClassifica;
	private JRadioButton calcio;
	private JRadioButton basket;
	private JRadioButton scacchi;
	
	private  ModelloTabellaClassifica modelloTabella;
	private JTable tabella;
	
	public PannelloClassifica(CalendarioSportivo c)
	{
		calendario=c;
		
		gruppoClassifica = new ButtonGroup();
		
		calcio = new JRadioButton("Calcio");
		calcio.addActionListener(this);
		
		basket = new JRadioButton("Basket");
		basket.addActionListener(this);
		
		scacchi = new JRadioButton("Scacchi");
		scacchi.addActionListener(this);
		
		gruppoClassifica.add(calcio);
		gruppoClassifica.add(basket);
		gruppoClassifica.add(scacchi);
		
		modelloTabella= new ModelloTabellaClassifica(calendario);
		tabella= new JTable(modelloTabella);
		
		
		//aggiunte al panel
		JPanel panRadioButton = new JPanel();
		panRadioButton.add(calcio);
		panRadioButton.add(basket);
		panRadioButton.add(scacchi);
		
		add(panRadioButton);
		
		add(tabella);
	}
	
	public void actionPerformed(ActionEvent e) {

		System.err.println("Raccolto evento:\t"+e.getActionCommand());
		switch(e.getActionCommand()) {
		case "Calcio":
			break;
		case "Basket":
			break;
		case "Scacchi":
			break;
		default: return;
		}
	}

}
