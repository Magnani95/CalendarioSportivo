package gestoreSquadre;

public class Incontro {

	//------Parametri
	private Squadra		casa;
	private Squadra 	ospite;
	private int 		punteggioCasa;
	private int			punteggioOspite;
	private Risultato 	risultato;
	
	//-------Metodi
	
	public Incontro(Squadra casa, Squadra ospite)
	{
		this.casa=casa;
		this.ospite=ospite;
		this.punteggioCasa=0;
		this.punteggioOspite=0;
		this.risultato=Risultato.nonGiocata;
	}
	
	public void setRisultato(int puntiCasa, int puntiOspite)
	{
		this.punteggioCasa=puntiCasa;
		this.punteggioOspite=puntiOspite;
		updateRisultato(puntiCasa, puntiOspite);
	}
	
	private void updateRisultato(int casa, int ospite)
	{
		if(casa>ospite)
			this.risultato=Risultato.casa;
		else if(casa<ospite)
			this.risultato=Risultato.ospite;
		else //Pareggio
			this.risultato=Risultato.pareggio;	
	}
	
	public void setNonGiocata()
	{
		this.risultato= Risultato.nonGiocata;
		this.punteggioCasa=0;
		this.punteggioOspite=0;
	}

	public Squadra getCasa() {
		return casa;
	}

	public Squadra getOspite() {
		return ospite;
	}

	public int getPunteggioCasa() {
		return punteggioCasa;
	}

	public int getPunteggioOspite() {
		return punteggioOspite;
	}

	public Risultato getRisultato() {
		return risultato;
	}

	
}
