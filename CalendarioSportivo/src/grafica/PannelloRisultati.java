package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;
import gestoreSquadre.Risultato;
/**
 * Classe che implementa un pannello per modificare i risultati.
 * @author Andrea Magnani
 * @see PannelloPrincipale
 * @see Incontro
 */
public class PannelloRisultati extends JPanel implements ActionListener {
	
	/**JFrame che contiene il pannello */
	private JFrame frameContenitore;
	/**Calendario associato al frame*/
	private CalendarioSportivo calendario;
	/**Frame principale del programma */
	private FramePrincipale framePrincipale;
	
	/**Label mostrante "Numero Giornata"*/
	private JLabel giornata;
	/** JComboBox con la lista delle giornate*/
	private JComboBox listaGiornate;
	
	/**Label mostrante "Numero Incontro" */
	private JLabel incontro;
	/**JComboBox con la lista degli incontri */
	private JComboBox listaIncontri;
	
	/**Label che mostra il nome della squadra in casa */
	private JTextField squadraCasa;
	/**Label che mostra il nome della squadra ospite */
	private JTextField squadraOspite;
	
	/**Spinner per impostare il punteggio della squadra in casa */
	private JSpinner spinnerCasa;
	/**Spinner per impostare il punteggio della squadra ospite */
	private JSpinner spinnerOspiti;
	
	/**Bottone per salvare il risultato impostato*/
	private JButton salva;
	/**Bottone per impostare la partita come "NonGiocata"*/
	private JButton nonGiocata;
	/**Bottone per resettare tutti i risultati del calendario */
	private JButton reset;
	
	/**
	 * Costutruttore della classe che inizializza le componenti
	 * @param f FrameContenitore del panel
	 * @param c CalendarioSportivo dal quale recuperare i dati
	 * @param fPrincipale FramePrincipale del programma
	 */
	public PannelloRisultati(JFrame f, CalendarioSportivo c, FramePrincipale fPrincipale)
	{
		super();
		this.frameContenitore=f;
		this.calendario=c;
		this.framePrincipale=framePrincipale;
		this.setLayout(new BorderLayout(5, 5) );
		JPanel alto, centrale, basso;
		alto= new JPanel();
		centrale = new JPanel();
		basso= new JPanel();
	

		// parte punteggio
		squadraCasa = new JTextField(20);
		squadraCasa.setEditable(false);
		
		spinnerCasa= new JSpinner(new SpinnerNumberModel(0, -1, 999, 1));
		
		squadraOspite= new JTextField(20);
		squadraOspite.setEditable(false);
		
		spinnerOspiti= new JSpinner(new SpinnerNumberModel(0, -1, 999, 1));

		salva= new JButton("Salva");
		salva.addActionListener(this);
		
		nonGiocata= new JButton("Non Giocata");
		nonGiocata.addActionListener(this);
		
		reset = new JButton("Reset totale");
		reset.addActionListener(this);
		
		//parte di selezione
		//!!!DA LASCIARE PER ULTIMA ALTRIMENTI VA IN nullPointerException!!!
		giornata= new JLabel("Numero Giornata");
		incontro= new JLabel("Numero Incontro");

		listaGiornate= new JComboBox();
		listaIncontri= new JComboBox();

		listaGiornate.addActionListener(this);
		listaIncontri.addActionListener(this);
		
		listaGiornate.setActionCommand("Modifica");
		listaIncontri.setActionCommand("Modifica");
		
		for(int i=0; i!=calendario.getCalendario().size(); i++)
			listaGiornate.addItem(i+1);
	
		for(int i=0; i!=calendario.getNSquadreCalendario()/2; i++)
			listaIncontri.addItem(i+1);
		
		
		//aggiunte al panel
		alto.add(giornata);
		alto.add(listaGiornate);
		
		alto.add(incontro);
		alto.add(listaIncontri);
		
		centrale.add(squadraCasa);
		centrale.add(spinnerCasa);
		
		centrale.add(squadraOspite);
		centrale.add(spinnerOspiti);
		
		basso.add(salva);
		basso.add(nonGiocata);
		basso.add(reset);
		
		add(alto, BorderLayout.NORTH);
		add(centrale, BorderLayout.CENTER);
		add(basso, BorderLayout.SOUTH);
	}
	
	/**
	 * Metodo che raccoglie e gestisce gli eventi
	 */
	public void actionPerformed(ActionEvent e)
	{
		System.err.println("PannelloRisultati, evento:\t"+ e.getActionCommand());
		
		switch(e.getActionCommand()) {
			
			case "Salva":
				Incontro attuale= recuperaIncontro();
				
				try {
					spinnerCasa.commitEdit();
					spinnerOspiti.commitEdit();
				}catch(java.text.ParseException ex) {
					JOptionPane.showMessageDialog(framePrincipale,
						    "Impossibile recuperare il valore impostato",
						    "Errore",
						    JOptionPane.ERROR_MESSAGE);
					return;
					}
				attuale.setRisultato( (int) spinnerCasa.getValue(), (int) spinnerOspiti.getValue());
				if(attuale.getRisultato()==Risultato.nonGiocata) {
					JOptionPane.showMessageDialog(framePrincipale,
						    "Il punteggio non pu� essere -1.\nE' stata impostata come non giocata.",
						    "Attenzione",
						    JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(framePrincipale,
					    "Risultato aggiornato con successo",
					    "Successo",
					    JOptionPane.PLAIN_MESSAGE);
				}
				break;
			case "Modifica":
				aggiornaValori();
				
				break;
				
			case "Non Giocata":
				Incontro selezionato = recuperaIncontro();
				selezionato.setNonGiocata();
				
				JOptionPane.showMessageDialog(framePrincipale,
					    "Partita impostata come Non Giocata",
					    "Successo",
					    JOptionPane.PLAIN_MESSAGE);
				
				aggiornaValori();
				
				break;
			case "Reset totale":
				Iterator<Giornata> itg = calendario.getCalendario().iterator();
				while(itg.hasNext()) {
					Iterator<Incontro> iti = itg.next().getIterator();
					while(iti.hasNext()) 
						iti.next().setNonGiocata();
				}
				aggiornaValori();
				
				JOptionPane.showMessageDialog(framePrincipale,
					    "Reset completo dei risultati",
					    "Successo",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			default: break;
			
		}
		
	}
	/**
	 * Aggiorna i valori mostrati nelle label e negli spinner in base alla giornata e all'incontro selezionato
	 */
	private void aggiornaValori()
	{
		Incontro selezionato= recuperaIncontro();
		squadraCasa.setText(selezionato.getCasa().getNome());
		squadraOspite.setText(selezionato.getOspite().getNome());
		
		spinnerCasa.setValue(selezionato.getPunteggioCasa());
		spinnerOspiti.setValue(selezionato.getPunteggioOspite());
		
		
	}
	/**
	 * Recupera l'incontro corrispondente ai valori mostrati dai due JComboBox
	 * @return Incontro corrispondente ai valori selezionati
	 */
	private Incontro recuperaIncontro()
	{
		int nGiornata= listaGiornate.getSelectedIndex();
		int nIncontro= listaIncontri.getSelectedIndex();
		
		nGiornata= nGiornata<0? 0:nGiornata;
		nIncontro= nIncontro<0? 0:nIncontro;
		
		Incontro attuale= calendario.getCalendario().get(nGiornata).getIncontro(nIncontro);
		
		return attuale;
	}

}
