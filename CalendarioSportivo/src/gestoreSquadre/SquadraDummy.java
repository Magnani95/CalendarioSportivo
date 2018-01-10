package gestoreSquadre;

import java.awt.Image;
import java.awt.Toolkit;

public class SquadraDummy extends Squadra {

	public SquadraDummy()
	{
		super("Dummy Club", "Dummy City", caricaImmagine());
		
	}
	
	private static Image caricaImmagine()
	{
		
		Toolkit t= Toolkit.getDefaultToolkit();
		return t.getImage("/CalendarioSportivo/media/dummyLogo.png");
		
	}
}
