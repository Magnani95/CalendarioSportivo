package gestoreSquadre;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 * Classe che estende squadra e implementa la squadra dummy da usare per rendere pari il
 * numero di squadre del calendario
 * @author Andrea Magnani
 * @see SquadraDummy
 */
public class SquadraDummy extends Squadra {

	/**
	 * Costruttore della classe
	 */
	public SquadraDummy()
	{
		super("Dummy Club", "Dummy City", caricaImmagine());
		
	}
	/**
	 * Metodo che carica il logo della squadra dummy
	 * @return BufferedImmage caricata
	 */
	private static BufferedImage caricaImmagine()
	{
		BufferedImage img=null; 
		try{
			img = ImageIO.read(new File("media/dummyLogo.png"));
		
		}catch(IOException e) {
			System.err.println("Errore caricamento logo squadra dummy");

		}
		
		return img;
	}
}
