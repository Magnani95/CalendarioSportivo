package gestoreSquadre;

import java.io.*;
import java.text.FieldPosition;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import grafica.FramePrincipale;
import grafica.PannelloCarica;
/**
 * Classe per gestire l'input e output su file. Le operazioni riguardano il calendario, le squadre 
 * e il numero di squadre al momento della generazione degli incontri. Queste informazioni vengono
 * salvate nel file indicato al costruttore.
 * @author Andrea Magnani
 * @see CalendarioSportivo
 * @see FramePrincipale
 */
public class IOputter {
	/**Pannello cui comunicare l'avanzamento del caricamento */
	private PannelloCarica pannello;
	/**Il CalendarioSportivo i cui dati riguardano le operazioni*/
	private CalendarioSportivo calendario;
	/**Stringa contenente il nome del file da salvare e generare */
	private String nomeFile;
	
	/**
	 * Costruttore della classe
	 * @param c calendario del quale interessa compiere l'input e output
	 * @param nome Stringa col nome del file per le operazioni
	 */
	public IOputter(CalendarioSportivo c, String nome)
	{
		calendario=c;
		nomeFile=nome;
		pannello = null;
	}
	/**
	 * Metodo per salvare su file i dati.
	 * @return boolean riguardante l'esito dell'operazione
	 */
	public boolean salva() {
		
		FileOutputStream of= null;
		try {
			of = new FileOutputStream(nomeFile);
		}catch(IOException e) {
			System.err.println("Errore nell'apertura dello stream in Output");
			return false;
		}
		
		ObjectOutputStream os=null;
		try {
			os= new ObjectOutputStream(of);
			os.writeObject(calendario.getSquadre());
			os.writeObject(calendario.getCalendario());
			os.writeInt(calendario.getNSquadreCalendario());
		}catch(IOException e) {
			System.err.println("Errore nell'apertura dello ObjStream in Output");
			return false;
		}
		
		try {
			os.close();
			of.close();
		}catch(IOException e) {
			System.err.println("Errore nella chiusura degli stream in Output");
		}
		return true;
		
	}
	/**
	 * Metodo per caricare i dati dal file. Comunica con il panel associato per 
	 * gestire l'avanzamento della JProgressBar.
	 * @return boolean contenente l'esito dell'operazione
	 */
	public boolean carica() {
		
		Vector<Squadra> squadre;
		Vector<Giornata> giornate;
		int nSquadre;
		
		FileInputStream ifs = null;
		try {
			ifs = new FileInputStream(nomeFile);
		}catch(IOException e) {
			System.err.println("Errore nell'apertura dello stream in Input");
			return false;
		}
		pannello.setValoreBarra(1);
		ObjectInputStream iss = null;
		try {
			iss = new ObjectInputStream(ifs);
			squadre= (Vector<Squadra>) iss.readObject();
			giornate= (Vector<Giornata>) iss.readObject();
			nSquadre= iss.readInt();
		}catch(IOException | ClassNotFoundException e) {
			System.err.println("Errore nella lettura dello ObjectStream in Input");
			return false;
		}
		pannello.setValoreBarra(2);
		try {
			iss.close();
			ifs.close();
		}catch(IOException e) {
			System.err.println("Errore nella chiusura degli stream in Input");
		}
		pannello.setValoreBarra(3);
		calendario.setCarica(squadre, giornate, nSquadre);
		return true;
		
	}
	/**Setter per impostare il pannello associato */
	public void setPanel(PannelloCarica p) {
		pannello=p;
	}
}
