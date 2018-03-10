package Classifica;

import gestoreSquadre.Squadra;

public class SquadraClassifica implements Comparable<SquadraClassifica>{

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
	public int compareTo(SquadraClassifica altra)
	{
		if(this.punti < altra.getPunti())
			return -1;
		else if(this.punti > altra.getPunti())
			return 1;
		else { // (this.punti == altra.getPunti()
			int cmp = this.squadra.getNome().compareTo(altra.getSquadra().getNome());
			if(cmp<0)
				return-1;
			else if(cmp>0)
				return 1;
			else { //(cmp == 0)
				System.err.println("SquadraClassifica: compareTo stessoNome");
				return 0;
			}
		}
	}
	
	public Squadra getSquadra() {
		return squadra;
	}
}
