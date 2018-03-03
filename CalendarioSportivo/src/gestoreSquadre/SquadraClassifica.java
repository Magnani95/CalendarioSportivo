package gestoreSquadre;

public class SquadraClassifica {

	private Squadra squadra;
	private int punti;
	
	public SquadraClassifica(Squadra sq)
	{
		squadra = sq;
		punti = -1;
	}
	
	public int getPunti() {
		return punti;
	}
	
	public void aggiungiPunti(int p) {
		
		if(p < 0) {
			System.err.println("Errore in SqudraClassifica/aggiungiPunti");
			System.exit(-1);
		}
		if(punti<=0) 
			punti=0;
		
		punti+=p;
	}
	public Squadra getSquadra() {
		return squadra;
	}
}
