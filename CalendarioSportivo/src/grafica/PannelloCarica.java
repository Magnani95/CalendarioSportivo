package grafica;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import gestoreSquadre.IOputter;
/**
 * Pannello che mostra una <code>JProgressBar</code> utilizzata per mostrare 
 * l'avanzamento del caricamento da file
 * @author Andrea Magnani
 * @see IOputter
 * @see FramePrincipale
 */
public class PannelloCarica extends JPanel{
	
	/**Barra di avanzamento */
	private JProgressBar barra;
	
	/**
	 * Costruttore della classe. Inizializza la classe a 4 valori possibili.  
	 */
	public PannelloCarica()
	{
		barra= new JProgressBar(0,3);
		barra.setStringPainted(true);
		barra.setValue(0);
		
		add(barra);
	}
	/**
	 * Setter per impostare l'avanzamento della barra. I valori devono essere tra 0 e 3 inclusi.
	 * @param n Valore d'avanzamento da impostare [0,3]
	 */
	public void setValoreBarra(int n) 
	{
		if(n>= 0 && n<=3)
			barra.setValue(n);
	}
	
	
}
