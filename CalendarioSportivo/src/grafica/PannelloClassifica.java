package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import classifica.Classifica;
import classifica.ClassificaBasket;
import classifica.ClassificaCalcio;
import classifica.ClassificaScacchi;
import gestoreSquadre.CalendarioSportivo;

//commento blablabla
public class PannelloClassifica extends JPanel implements ActionListener{

	private CalendarioSportivo calendario;
	private Classifica classifica;
	private JFrame frame;
	
	private ButtonGroup gruppoClassifica;
	private JRadioButton calcio;
	private JRadioButton basket;
	private JRadioButton scacchi;
	
	private  ModelloTabellaClassifica modelloTabella;
	private JTable tabella;
	
	public PannelloClassifica(CalendarioSportivo c, JFrame j)
	{
		//creazione dell'entita'
		calendario=c;
		frame=j;
		
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
		tabella.getColumnModel().getColumn(1).setPreferredWidth(120);

		
		
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
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaCalcio(calendario, frame);
			break;
			
		case "Basket":
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaBasket(calendario, frame);
			break;
			
		case "Scacchi":
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaScacchi(calendario, frame);
			break;
		default: return;
		}
		classifica.calcolaClassifica();
		modelloTabella.setClassifica(classifica);
		modelloTabella.fireTableDataChanged();
		frame.pack();
	}

}
