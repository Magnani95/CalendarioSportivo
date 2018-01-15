package grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Squadra;

public class PannelloModifica extends JPanel implements ActionListener{

	private CalendarioSportivo calendario;
	private JFrame fContenitore;
	private JFrame fPrincipale;
	private Vector<String> nomiSquadre;
	
	private JLabel selSquadra;
	private JComboBox listaSquadre;
	
	private JLabel lNome;
	private JLabel lCitta;
	private JLabel lLogo;
	
	private JTextField tNome;
	private JTextField tCitta;
	private ImageIcon logo;
	
	private JButton modifica;
	private JButton elimina;
	
	public PannelloModifica(JFrame f, CalendarioSportivo c, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=c;
		this.fContenitore=f;
		this.fPrincipale=framePrincipale;
		this.nomiSquadre= new Vector<String>();
		
		nomiSquadre=calendario.getNomiSquadre();
		selSquadra= new JLabel("Seleziona Squadra");
		listaSquadre= new JComboBox(nomiSquadre.toArray());
		modifica= new JButton("Accetta Modifiche");
		modifica.setActionCommand("modifica");
		elimina = new JButton("Elimina");
		
		lNome = new JLabel("Nome:");
		tNome = new JTextField(20);
		lCitta= new JLabel("Citta'");
		tCitta= new JTextField(20);
		
		listaSquadre.addActionListener(this);
		modifica.addActionListener(this);
		elimina.addActionListener(this);
		
		add(selSquadra);
		add(listaSquadre);
		add(lNome);
		add(tNome);
		add(lCitta);
		add(tCitta);
		add(modifica);
		add(elimina);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.err.println("Evento\t"+ e.getActionCommand());
		int i=listaSquadre.getSelectedIndex();
		Squadra s= calendario.getSquadre().elementAt(i);
		
		switch(e.getActionCommand()) {
		
		case "comboBoxChanged":
			
			tNome.setText(s.getNome());
			tCitta.setText(s.getCitta());
			break;
		
		case "modifica":
			
			s.setNome(tNome.getText());
			s.setCitta(tCitta.getText());
			
			JOptionPane.showMessageDialog(fContenitore,
				    "Squadra modificata con successo",
				    "Evvai",
				    JOptionPane.PLAIN_MESSAGE);
			break;
			
		case "Elimina":
			if(calendario.getCalendario().size() != 0) {
				int n = JOptionPane.showConfirmDialog(
					fContenitore,
					"Eliminare la squadra comporterà la cancellazione del calendario. Continuare?",
					"Attenzione",
					JOptionPane.YES_NO_OPTION);
				if( n==JOptionPane.NO_OPTION || n==JOptionPane.CLOSED_OPTION)
					break;
			}
			
			calendario.getSquadre().remove(i);
			calendario.eliminaCalendario();
			
			JOptionPane.showMessageDialog(fContenitore,
				    "Squadra eliminata con successo",
				    "TUtto ok",
				    JOptionPane.PLAIN_MESSAGE);
			
		default: break;
		}
		
		nomiSquadre= calendario.getNomiSquadre();
	}
	
	

}
