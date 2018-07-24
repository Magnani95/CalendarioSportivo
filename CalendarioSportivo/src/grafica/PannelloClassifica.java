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

/**
 * Classe che implementa il pannello per mostrare e scegliere il tipo di classifica.
 * @author Andrea Magnani
 * @see Classifica
 * @see ModelloTabellaClassifica
 */
public class PannelloClassifica extends JPanel implements ActionListener{

	/**CalendarioSportivo associato al pannello */
	private CalendarioSportivo calendario;
	/**Classifica da mostrare in output */
	private Classifica classifica;
	/**Frame contenitore del pannello */
	private JFrame frame;
	
	/**Button group per gestire la terna di JRadioButton */
	private ButtonGroup gruppoClassifica;
	/**Bottone per scegliere la classifica calcio */
	private JRadioButton calcio;
	/**Bottone per scegliere la classifica basket */
	private JRadioButton basket;
	/**Bottone per scegliere la classifica scacchi */
	private JRadioButton scacchi;
	
	/**Modello della tabella per gestire l'output grafico */
	private  ModelloTabellaClassifica modelloTabella;
	/**Tabella per mostrare la classifica*/
	private JTable tabella;
	
	/**
	 * Costruttore della classe che inizializza le componenti
	 * @param c CalendarioSportivo associato
	 * @param j JFrame contenitore del pannello
	 */
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
	/**
	 * Metodo per l'ascolto e la gestione degli eventi associati al pannello
	 */
	public void actionPerformed(ActionEvent e) {

		System.err.println("Raccolto evento:\t"+e.getActionCommand());
		switch(e.getActionCommand()) {
		
		case "Calcio":
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaCalcio(calendario);
			break;
			
		case "Basket":
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaBasket(calendario, frame);
			break;
			
		case "Scacchi":
			modelloTabella.setSelezioneAttiva(true);
			classifica = new ClassificaScacchi(calendario);
			break;
		default: return;
		}
		classifica.calcolaClassifica();
		modelloTabella.setClassifica(classifica);
		modelloTabella.fireTableDataChanged();
		frame.pack();
	}

}
