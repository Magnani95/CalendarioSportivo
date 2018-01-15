package grafica;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import gestoreSquadre.*;

public class PannelloAggiungi extends JPanel implements ActionListener{

	private JFrame fContenitore;
	private CalendarioSportivo calendario;
	private FramePrincipale fPrincipale;
	
	private JLabel lNome;
	private JTextField nome;
	private String stNome;
	private JLabel lCitta;
	private JTextField citta;
	private String stCitta;
	private JButton logo;
	private String path;
	
	private JTextField pathLogo;
	private JButton conferma;
	
	public PannelloAggiungi(JFrame f, CalendarioSportivo c, FramePrincipale framePrincipale)
	{
		super();
		this.calendario=c;
		this.fContenitore=f;
		this.fPrincipale=framePrincipale;
		this.path=null;
		
		lNome= new JLabel("Nome Squadra");
		add(lNome);
		nome= new JTextField(15);
		add(nome);
		
		lCitta= new JLabel("Città");
		add(lCitta);
		citta = new JTextField(15);
		add(citta);
		
		logo = new JButton("Scegli logo");
		logo.addActionListener(this);
		add(logo);
		
		pathLogo= new JTextField(35);
		pathLogo.setEditable(false);
		add(pathLogo);
		
		conferma= new JButton("Conferma");
		conferma.addActionListener(this);
		add(conferma);
	}

	@Override
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
				    "Il nome non può essere vuoto",
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
