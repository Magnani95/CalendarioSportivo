package gestoreSquadre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import grafica.FramePrincipale;
import grafica.ModelloTabella;

/**
 * Classe contenente il main del programma, nel quale vengono creati il frame principale
 * i vettori contenenti le squadre e le giornate. E' il fulcro del programma che fa comunicare gli oggetti
 * del programma e ne gestisce gli aspetti strutturali.
 * 
 * @author Andrea Magnani
 * @version 1.0
 * @see grafica.FramePrincipale
 * @see Giornata
 * @see Squadra
 */
public class CalendarioSportivo implements Serializable {

	//---------Parametri
	/**Vettore contenente le giornate del campionato*/
	private Vector<Giornata> calendario;
	/**Vettore contenente le squadre inserite*/
	private Vector<Squadra> squadre;
	/**Immagine contenente il logo standard */
	private ImageIcon logoStandard;
	/**Intero rappresentante il numero di squadre presenti al momento della generazione
	 * del calendario. Serve per fermare l'accesso a squadre aggunte dopo la generazione. */
	private int nSquadreCalendario;
	/**Modello tabella salvato per garantire una comunicazione diretta tra i due oggetti. */
	private ModelloTabella modelloTabella;
	
	//---------Metodi
	/**Main del programma che inizializza un CalendarioSportivo e il JFrame principale */
	public static void main(String[] args) {
		
		CalendarioSportivo sessione = new CalendarioSportivo();
		FramePrincipale f= new FramePrincipale("Calendario Sportivo", sessione);
		f.setVisible(true);
	}
	
	/**
	 * Costruttore dell'oggetto. Vengono inizializzati gli attributi
	 */
	public CalendarioSportivo()
	{
		caricaLogo();
		this.calendario = new Vector<Giornata>();
		this.squadre = new Vector<Squadra>();
		this.nSquadreCalendario=0;

	}
	
	/**
	 * Metodo che genera gli incontri secondo l'algoritmo di Berger. Viene generata andata e ritorno.
	 * Se le squadre sono dispari, viene aggiunta la squadra "Dymmy club".
	 * @see SquadraDummy
	 * */
	public boolean generaCalendario()
	{
		Squadra dummy;
		int nSquadre = this.squadre.size();
		
		if(nSquadre<1) 
			return false;
		
		if(nSquadre%2 != 0) {
			dummy=new SquadraDummy();
			this.aggiungiSquadra(dummy);
			++nSquadre;
		}
		nSquadreCalendario= nSquadre;
		int nGiornate= nSquadre - 1;
		
		Vector<Squadra> casa=		new Vector<Squadra>();
		Vector<Squadra> trasferta= 	new Vector<Squadra>();
		
		for(int i=0; i<nSquadre/2; i++) {
			casa.add(squadre.elementAt(i));
			trasferta.add(squadre.elementAt(nSquadre-1-i));
		}
	    
	    for (int i = 0; i < nGiornate; i++) {
	    	System.err.println("Giornata\t"+String.valueOf(i));
			Giornata attuale =  new Giornata();

	    	if(i%2 == 0) {
	    		for(int j=0; j<nSquadre/2; j++)
	    			attuale.aggiungiIncontro(new Incontro(trasferta.elementAt(j), casa.elementAt(j)) );
	    	}else { 
	    		for(int j=0; j<nSquadre/2; j++) 
	    			attuale.aggiungiIncontro(new Incontro( casa.elementAt(j), trasferta.elementAt(j)) );
	    	}
	    	calendario.add(attuale);
	    
	    	if(nGiornate-i==1) 
	    		break;
	    	
	    	Squadra riporto = trasferta.lastElement();
	    	trasferta.removeElementAt(trasferta.size()-1);
	    	trasferta.add(0, casa.elementAt(1));
	    	casa.add(riporto);
	    	casa.removeElementAt(1);
	    	
	    }
	    System.err.println("nIncontri\t"+String.valueOf(calendario.size()));
	    generaRitorno();
	    modelloTabella.setCalendarioPronto(true);
	    return true;
	}
	/**
	 * Metodo che genera il ritorno in modo speculare all'andata
	 */
	private void generaRitorno()
	{
		Incontro andata=null, ritorno=null;
		Giornata giornataRitorno= null,
				giornataAndata=null;
		int nGiornateAndata= calendario.size();
		int incontriGiornata=squadre.size()/2;
				
		for(int i=0; i!=nGiornateAndata; i++) {
			giornataRitorno= new Giornata();
			giornataAndata=calendario.get(i);
			
			for(int j=0; j!=incontriGiornata; j++) {
				andata= giornataAndata.getIncontro(j);
				ritorno = new Incontro(andata.getOspite(), andata.getCasa());
				giornataRitorno.aggiungiIncontro(ritorno);
			}
			
			calendario.add(giornataRitorno);
		}
	}
	/**
	 * Metodo che carica il logo standard all'interno dell'oggetto
	 */
	private void caricaLogo()
	{
		BufferedImage img=null; 
		try{
			img = ImageIO.read(new File("media/standard.png"));
		
		}catch(IOException e) {
			System.err.println("Errore caricamento logo Standard");
		}
		this.logoStandard= new ImageIcon(img);
	}
	/**
	 * Getter per ottenere i nomi delle squadre.
	 * @return un Vector di stringhe coi nomi delle squadre.
	 */
	public Vector<String> getNomiSquadre()
	{
		Vector<String> nomiSquadre= new Vector<String>();
		
		if(squadre.size() == 0) 
			return nomiSquadre;
		
		Iterator<Squadra> it =squadre.iterator();
		Squadra attuale;
		
		while(it.hasNext() )
			nomiSquadre.add( it.next().getNome() );
		return nomiSquadre;
	}
	/**
	 * Metodo usato per il caricamento. Imposta le squadre, il calendario e il numero squadre 
	 * dell'oggetto con quelle passate al metodo.
	 * 
	 * @param sq Vector di squadre da impostare
	 * @param g Vector di giornate da impostare
	 * @param nSq int di numeroSquadreCalendario da impostare
	 */
	public void setCarica(Vector<Squadra> sq, Vector<Giornata> g, int nSq) {
		squadre=sq;
		calendario=g;
		nSquadreCalendario=nSq;
		
		if(calendario!=null)
			modelloTabella.setCalendarioPronto(true);
	}
	/**
	 * Metodo per eliminare completamente il calendario. Imposta anche il modelloTabella su "Nonpronto"
	 * per evitare un output grafico errato.
	 */
	public void eliminaCalendario()
	{
		calendario.clear();
		nSquadreCalendario=0;
		modelloTabella.setCalendarioPronto(false);
	}
	/**Metodo per salvare il modelloTabella usato nella grafica nell'oggetto. */
	public void setModelloTabella(ModelloTabella m) {
		this.modelloTabella=m;
	}
	/**
	 * Metodo usato per aggiungere una squadra al Vector. Nel caso la squadra sia senza logo, viene 
	 * impostato il logo standard
	 * @param nuova squadra da aggiungere
	 */
	public void aggiungiSquadra(Squadra nuova){
		if(nuova.getLogo() == null)
			nuova.setLogo(logoStandard);
		squadre.add(nuova);
	}
	/**
	 * Getter per ottenere il calendario.
	 * @return Vector di giornate
	 */
	public Vector<Giornata> getCalendario() {
		return calendario;
	}
	/**
	 * Getter per ottenere le squadre.
	 * @return Vector di squadre
	 */
	public Vector<Squadra> getSquadre() {
		return squadre;
	}
	/**
	 * Getter per ottenere il logo standard.
	 * @return ImageIcon contenente il logo standard
	 */
	public ImageIcon getLogo(){
		return logoStandard;
	}
	/**
	 * Getteer per ottenere il numero di squadre al momento della generazione
	 * del calendario.
	 * @return int con nSquadreCalendario
	 */
	public int getNSquadreCalendario() {
		return nSquadreCalendario;
	}
}