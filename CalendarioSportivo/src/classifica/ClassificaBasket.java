package classifica;

import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gestoreSquadre.CalendarioSportivo;
import gestoreSquadre.Giornata;
import gestoreSquadre.Incontro;
/**
 * Estensione della classe Classifica che implementa una classifica di Basket. Nel venga trovato un pareggio, mostra a schermo un
 * messaggio che notifica che la partita verrà contata come non giocata.
 * @author Andrea Magnani
 * @see Classifica
 */
public class ClassificaBasket extends Classifica {
	
	/**Punti guadagnati per la vittoria */
	private static final int puntiVittoria = 2;
	/**Punti assegnati per il pareggio (impossibile nel basket) */
	private static final int puntiPareggio = 0;
	/**Punti assegnati per la sconfitta */
	private static final int puntiSconfitta = 0;
	/**Booleano per ricordare se l'avviso di pareggio è già stato mostrato o meno */
	private boolean avviso;
	/**Frame cui associare i messaggi generati dagli oggetti */
	protected JFrame frame;
	
	/**
	 * Costruttore della classe che inizializza i 
	 * @param c Calendario da cui si vogliono recuperare le squadre e risultati
	 * @param f Frame cui associare i messaggi mostrati a schermo
	 */
	public ClassificaBasket(CalendarioSportivo c, JFrame f)
	{
		super(c);
		avviso= false; 
	}
	/**
	 * Metodo che aggiona la classifica andando a controllare i risultati dal calendario. Mostra a schermo un messaggio nel 
	 * caso venga trovato un pareggio, risultato impossibile nel basket
	 * @return Ritorna true se la chiamata è fatta su una sottoclasse, false se la chiamata è fatta su un'istanza di Classifica
	 */
	public boolean calcolaClassifica() 
	{
		Iterator<Giornata> itGiornata=calendario.getCalendario().iterator();
		Iterator<Incontro> itIncontro;
		
		while(itGiornata.hasNext()) {
			itIncontro=itGiornata.next().getIterator();
			while(itIncontro.hasNext()) {
				Incontro attuale= itIncontro.next();
				switch(attuale.getRisultato()) {
					case casa:
						super.assegnaPunti(attuale.getCasa(), puntiVittoria);
						super.assegnaPunti(attuale.getOspite(), puntiSconfitta);
						break;
					case ospite:
						super.assegnaPunti(attuale.getCasa(), puntiSconfitta);
						super.assegnaPunti(attuale.getOspite(), puntiVittoria);
						break;
					case pareggio:
						if(avviso==false) {
							JOptionPane.showMessageDialog(frame,
								    "E' stato trovato un pareggio, risultato impossibile nel Basket. Verra' contata come partita non giocata",
								    "Attenzione",
								    JOptionPane.INFORMATION_MESSAGE);
							avviso=true;	
						}
						super.assegnaPunti(attuale.getCasa(), puntiPareggio);
						super.assegnaPunti(attuale.getOspite(), puntiPareggio);
						break;
					case nonGiocata:
						break;
					
					default: System.err.println("Errore in switch calcolaClassifica"); System.exit(-1);
				}
			}
		}
		super.ordinaClassifica();
		return true;
	}
	
}
