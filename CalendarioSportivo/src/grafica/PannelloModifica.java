package grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Squadra;

/**
 * Pannello che implementa le funzionalità di modifica o eliminazione di una squadra dal calendario.
 * Nel caso di eliminazione, viene chiesto se proseguire, poichè verrà cancellato anche il calendario.
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see FramePrincipale
 */
public class PannelloModifica extends JPanel implements ActionListener{

	/**CalendarioSportivo associato*/
	private CalendarioSportivo calendario;
	/**Frame contenente il pannello */
	private JFrame fContenitore;
	/**Pannello principale del programma */
	private JFrame fPrincipale;
	/**Vector contenente le stringhe coi nomi delle squadre */
	private Vector<String> nomiSquadre;
	
	/**Label che mostra "seleziona Squadra" */
	private JLabel selSquadra;
	/**Elenco per selezionare tramite i nomi delle squadre */
	private JComboBox listaSquadre;
	
	/**Label che mostra "Nome" */
	private JLabel lNome;
	/**Label che mostra "Citta'" */
	private JLabel lCitta;
	/**Label che mostra il logo della squadra */
	private JLabel img;
	
	/**Campo di testo per il nome della squadra */
	private JTextField tNome;
	/**Campo id testo per la città della squadra */
	private JTextField tCitta;
	
	/**Bottone per salvare le modifiche*/
	private JButton modifica;
	/**Bottone per eliminare la squadra */
	private JButton elimina;
	
	/**
	 * Costruttore della classe che inizializza le componenti
	 * @param f Frame contenente il pannello
	 * @param c Calendario cui modificare le squadre
	 * @param framePrincipale FramePrincipale del programma
	 */
	public PannelloModifica(JFrame f, CalendarioSportivo c, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=c;
		this.fContenitore=f;
		this.fPrincipale=framePrincipale;
		this.nomiSquadre= new Vector<String>();
		setLayout(new BorderLayout(5, 5) );
		
		JPanel alto, centrale, basso;
		alto= new JPanel();
		centrale= new JPanel();
		basso= new JPanel();
		
		nomiSquadre=calendario.getNomiSquadre();
		selSquadra= new JLabel("Seleziona Squadra");
		listaSquadre= new JComboBox(nomiSquadre.toArray());
		modifica= new JButton("Accetta Modifiche");
		modifica.setActionCommand("modifica");
		elimina = new JButton("Elimina");
		
		lNome = new JLabel("Nome:");
		tNome = new JTextField(15);
		lCitta= new JLabel("Citta'");
		tCitta= new JTextField(15);
		
		img= new JLabel(calendario.getLogo());
		
		listaSquadre.addActionListener(this);
		modifica.addActionListener(this);
		elimina.addActionListener(this);
		
		alto.add(selSquadra);
		alto.add(listaSquadre);
		alto.add(lNome);
		alto.add(tNome);
		alto.add(lCitta);
		alto.add(tCitta);
		centrale.add(img);
		basso.add(modifica);
		basso.add(elimina);
		
		add(alto, BorderLayout.NORTH);
		add(centrale, BorderLayout.CENTER);
		add(basso, BorderLayout.SOUTH);
	}
	/**
	 * Metodo per l'ascolto e la gestione degli eventi associati al panel
	 */
	public void actionPerformed(ActionEvent e)
	{
		System.err.println("Evento\t"+ e.getActionCommand());
		int i=listaSquadre.getSelectedIndex();
		Squadra s= calendario.getSquadre().elementAt(i);
		
		switch(e.getActionCommand()) {
		
		case "comboBoxChanged":
			
			tNome.setText(s.getNome());
			tCitta.setText(s.getCitta());
			img.setIcon(s.getLogo());
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
		DefaultComboBoxModel model = new DefaultComboBoxModel( nomiSquadre );
		listaSquadre.setModel(model);
		this.repaint();
		this.fContenitore.pack();
	}
	
	

}
