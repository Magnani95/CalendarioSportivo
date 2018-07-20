package gestoreSquadre;

import java.io.*;
import java.text.FieldPosition;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import grafica.PannelloCarica;

public class IOputter {

	private PannelloCarica pannello;
	private CalendarioSportivo calendario;
	private String nomeFile;
	
	
	public IOputter(CalendarioSportivo c, String nome)
	{
		calendario=c;
		nomeFile=nome;
		pannello = null;
	}
	
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
	
	public void setPanel(PannelloCarica p) {
		pannello=p;
	}
}
