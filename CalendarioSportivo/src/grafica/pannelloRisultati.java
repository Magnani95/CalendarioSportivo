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
		
		//parte di selezione
		//!!!DA LASCIARE PER ULTIMA ALTRIMENTI VA IN nullPointerException!!!
		giornata= new JLabel("Numero Giornata");
		incontro= new JLabel("Numero Incontro");

		listaGiornate= new JComboBox();
		listaIncontri= new JComboBox();

		listaGiornate.addActionListener(this);
		listaIncontri.addActionListener(this);
		
		//listaIncontri.setSelectedItem(1);
		//listaGiornate.setSelectedItem(1);
		
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
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		System.err.println("PannelloRisultati, evento:\t"+ e.getActionCommand());
		
		switch(e.getActionCommand()) {
		
		case "Salva":
		
			break;
		case "Modifica":
			aggiornaValori();
			
			break;
		default:// aggiornaValori(); break;
		
		}
		
	}
	
	private void aggiornaValori()
	{
		int nGiornata= listaGiornate.getSelectedIndex();
		int nIncontro= listaIncontri.getSelectedIndex();
		
		nGiornata= nGiornata<0? 0:nGiornata;
		nIncontro= nIncontro<0? 0:nIncontro;
		System.err.println("aggiornaValori:\t"+ nGiornata +"-"+ nIncontro);
		
		Incontro selezionato= calendario.getCalendario().get(nGiornata).getIncontro(nIncontro);
		squadraCasa.setText(selezionato.getCasa().getNome());
		squadraOspite.setText(selezionato.getOspite().getNome());
		
		spinnerCasa.setValue(selezionato.getPunteggioCasa());
		spinnerOspiti.setValue(selezionato.getPunteggioOspite());
		
		
	}

}
