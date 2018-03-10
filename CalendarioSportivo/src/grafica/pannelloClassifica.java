package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gestoreSquadre.CalendarioSportivo;

public class pannelloClassifica extends JPanel implements ActionListener{

	private CalendarioSportivo calendario;
	
	private ButtonGroup pulsantiClassifica;
	private JRadioButton calcio;
	private JRadioButton basket;
	private JRadioButton scacchi;
	
	private  ModelloTabellaClassifica modelloTabella;
	private JTable tabella;
	
	public pannelloClassifica(CalendarioSportivo c)
	{
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
