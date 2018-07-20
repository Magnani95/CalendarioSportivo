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
public class CalendarioSportivo implements Serializable {

	//---------Parametri
	private Vector<Giornata> calendario;
	private Vector<Squadra> squadre;
	private ImageIcon logoStandard;
	
	private int nSquadreCalendario;
	private ModelloTabella modelloTabella;
	
	//---------Metodi
	public static void main(String[] args) {
		
		CalendarioSportivo sessione = new CalendarioSportivo();
		FramePrincipale f= new FramePrincipale("Calendario Sportivo", sessione);
		f.setVisible(true);
	}

	public CalendarioSportivo()
	{
		caricaLogo();
		this.calendario = new Vector<Giornata>();
		this.squadre = new Vector<Squadra>();
		this.nSquadreCalendario=0;

	}
	
	//Algoritmo di Berger
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
	
	public void setCarica(Vector<Squadra> sq, Vector<Giornata> g, int nSq) {
		squadre=sq;
		calendario=g;
		nSquadreCalendario=nSq;
		
		if(calendario!=null)
			modelloTabella.setCalendarioPronto(true);
	}
	public void eliminaCalendario()
	{
		calendario.clear();
		nSquadreCalendario=0;
		modelloTabella.setCalendarioPronto(false);
	}
	
	public void setModelloTabella(ModelloTabella m) {
		this.modelloTabella=m;
	}
	
	public void aggiungiSquadra(Squadra nuova){
		if(nuova.getLogo() == null)
			nuova.setLogo(logoStandard);
		squadre.add(nuova);
	}

	public Vector<Giornata> getCalendario() {
		return calendario;
	}

	public Vector<Squadra> getSquadre() {
		return squadre;
	}
	
	public ImageIcon getLogo(){
		return logoStandard;
	}
	public int getNSquadreCalendario() {
		return nSquadreCalendario;
	}
}