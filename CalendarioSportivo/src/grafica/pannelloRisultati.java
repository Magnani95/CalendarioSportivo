package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Incontro;

public class pannelloRisultati extends JPanel implements ActionListener {
	
	private JFrame frameContenitore;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	
	private JLabel giornata;
	private JComboBox listaGiornate;
	
	private JLabel incontro;
	private JComboBox listaIncontri;
	
	private JTextField squadraCasa;
	private JTextField squadraOspite;
	
	private JLabel punteggioCasa;
	private JSpinner spinnerCasa;
	
	private JLabel punteggioOspiti;
	private JSpinner spinnerOspiti;
	
	private JButton salva;
	private JButton nonGiocata;
	
	
	public pannelloRisultati(JFrame f, CalendarioSportivo c, FramePrincipale fPrincipale)
	{
		super();
		this.frameContenitore=f;
		this.calendario=c;
		this.framePrincipale=framePrincipale;
		

		// parte punteggio
		squadraCasa = new JTextField(20);
		squadraCasa.setEditable(false);
		
		spinnerCasa= new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
		
		squadraOspite= new JTextField(20);
		squadraOspite.setEditable(false);
		
		spinnerOspiti= new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

		salva= new JButton("Salva");
		salva.addActionListener(this);
		
		nonGiocata= new JButton("Non Giocata");
		nonGiocata.addActionListener(this);
		
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
		add(giornata);
		add(listaGiornate);
		
		add(incontro);
		add(listaIncontri);
		
		add(squadraCasa);
		add(spinnerCasa);
		
		add(squadraOspite);
		add(spinnerOspiti);
		
		add(salva);
		add(nonGiocata);
	}
	
	
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
				
				JOptionPane.showMessageDialog(framePrincipale,
					    "Risultato aggiornato con successo",
					    "Successo",
					    JOptionPane.PLAIN_MESSAGE);
				
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
				
			default: break;
			
		}
		
	}
	
	private void aggiornaValori()
	{
		Incontro selezionato= recuperaIncontro();
		squadraCasa.setText(selezionato.getCasa().getNome());
		squadraOspite.setText(selezionato.getOspite().getNome());
		
		spinnerCasa.setValue(selezionato.getPunteggioCasa());
		spinnerOspiti.setValue(selezionato.getPunteggioOspite());
		
		
	}
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
