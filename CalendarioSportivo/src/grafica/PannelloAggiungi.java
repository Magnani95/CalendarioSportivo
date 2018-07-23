package grafica;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import gestoreSquadre.*;
/**
 * Classe che estende un <code>JPanel</code> e gestisce le operazioni per aggiungere
 * una squadra al CalendarioSportivo
 * @author Andrea Magnani
 * @see PannelloPrincipale
 * @see CalendarioSportivo
 */
public class PannelloAggiungi extends JPanel implements ActionListener{

	/**Frame che contiene il pannello */
	private JFrame fContenitore;
	/**Calendario associato al pannello */
	private CalendarioSportivo calendario;
	/**Frame principale del programma */
	private FramePrincipale fPrincipale;
	
	/**Label che mostra "Nome" */
	private JLabel lNome;
	/**Campo testo per inserire il nome della squadra */
	private JTextField nome;
	/**Stringa in cui salvare il nome della squadra */
	private String stNome;
	/**Label che mostra "Citta'" */
	private JLabel lCitta;
	/**Campo testo per inserire la citta' */
	private JTextField citta;
	/**Stringa in cui salvare la citta' */
	private String stCitta;
	/**Bottone per scegliere il logo */
	private JButton logo;
	/**Stringa in cui salvare il path del logo */
	private String path;
	
	/**Campo testo in cui viene mostrato il path del logo selezionato */	
	private JTextField pathLogo;
	/**Bottone che attiva il caricamento del logo e la creazione di una squadra */
	private JButton conferma;
	
	/**
	 *Costruttore della classe che inizializza le componenti
	 * @param f Frame contenitore del pannello
	 * @param c CalendarioSportivo cui salvare la squadra
	 * @param framePrincipale FramePrincipale del programma
	 */
	public PannelloAggiungi(JFrame f, CalendarioSportivo c, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=c;
		this.fContenitore=f;
		this.fPrincipale=framePrincipale;
		this.path=null;
		this.setLayout(new BorderLayout(5,5));
		
		JPanel alto, centrale, basso;
		alto = new JPanel();
		centrale = new JPanel();
		basso = new JPanel();
		
		lNome= new JLabel("Nome Squadra");
		alto.add(lNome);
		nome= new JTextField(15);
		alto.add(nome);
		
		lCitta= new JLabel("Citta'");
		alto.add(lCitta);
		citta = new JTextField(15);
		alto.add(citta);
		
		logo = new JButton("Scegli logo");
		logo.addActionListener(this);
		centrale.add(logo);
		
		pathLogo= new JTextField(35);
		pathLogo.setEditable(false);
		centrale.add(pathLogo);
		
		conferma= new JButton("Conferma");
		conferma.addActionListener(this);
		basso.add(conferma);
		
		add(alto, BorderLayout.NORTH);
		add(centrale, BorderLayout.CENTER);
		add(basso, BorderLayout.SOUTH);
	}

	/**
	 * Metodo per l'ascolto e la gestione degli eventi associati al pannello
	 */
	public void actionPerformed(ActionEvent e) 
	{
		System.err.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		case "Scegli logo":
			 JFileChooser chooser = new JFileChooser();
             if (chooser.showOpenDialog(fContenitore) == JFileChooser.APPROVE_OPTION) {
            	 path = chooser.getSelectedFile().getAbsolutePath();
            	 pathLogo.setText(path);
             }

			break;
			
		case "Conferma":
			System.err.println("Inizio ciclo Conferma");
			
			stNome=nome.getText();
			if (stNome.isEmpty()) {
				JOptionPane.showMessageDialog(fContenitore,
				    "Il nome non puo' essere vuoto",
				    "Errore",
				    JOptionPane.WARNING_MESSAGE);
					return;
				}
				
			System.err.println("Inizio controllo citta");
			stCitta=citta.getText();
			if(stCitta.isEmpty()) {
				JOptionPane.showMessageDialog(fContenitore,
				    "La città non può essere vuota",
				    "Errore",
				    JOptionPane.WARNING_MESSAGE);
				return;
				}
				
			path=pathLogo.getText();
				
			if(path.isEmpty()) {
				System.err.println("Generazione Squadra senza logo");
				calendario.aggiungiSquadra(new Squadra(stNome, stCitta));
			}else {
				calendario.aggiungiSquadra(new Squadra (stNome, stCitta, caricaImmagine(path)) );
			}
			
			JOptionPane.showMessageDialog(fContenitore,
				    "Squadra aggiunta con successo",
				    "Evvai",
				    JOptionPane.PLAIN_MESSAGE);
			
			return;
			
		default: System.err.println("Errore PannelloAggiungi"); return;
		}
	}
	/**
	 * Metodo che carica un'immagine al path passato.
	 * @param percorso Percorso cui è l'immagine
	 * @return BufferedImage caricata
	 */
	private BufferedImage caricaImmagine(String percorso)
	{
		BufferedImage img=null; 
		try{
			img = ImageIO.read(new File(percorso));
		
		}catch(IOException e) {
			JOptionPane.showMessageDialog(fContenitore,
				    "Verrà caricato un logo standard.",
				    "Errore Caricamento",
				    JOptionPane.WARNING_MESSAGE);
		}
		
		return img;
	}
	
}
