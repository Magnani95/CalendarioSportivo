package grafica;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PannelloCarica extends JPanel{
	private JProgressBar barra;
	
	
	public PannelloCarica()
	{
		barra= new JProgressBar(0,3);
		barra.setStringPainted(true);
		barra.setValue(0);
		
		add(barra);
	}
	
	public void setValoreBarra(int n) 
	{
		if(n>= 0 && n<=3)
			barra.setValue(n);
	}
	
	
}
