package gestoreSquadre;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SquadraDummy extends Squadra {

	public SquadraDummy()
	{
		super("Dummy Club", "Dummy City", caricaImmagine());
		
	}
	
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
