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
		gruppoClassifica = new ButtonGroup();
		
		calcio = new JRadioButton("Calcio");
		calcio.addActionListener(this);
		
		basket = new JRadioButton("Basket");
		basket.addActionListener(this);
		
		scacchi = new JRadioButton("Scacchi");
		scacchi.addActionListener(this);
		
		gruppoClassifica.add(calcio);
		gruppoClassifica.add(bascket);
		gruppoClassifica.add(scacchi);
		
		
		//aggiunte al panel
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
