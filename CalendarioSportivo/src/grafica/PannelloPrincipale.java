package grafica;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PannelloPrincipale extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CalendarioSportivo calendario;
	private FramePrincipale framePrincipale;
	public PannelloPrincipale(CalendarioSportivo calendario, JFrame framePrincipale)
	{
		super();
		this.calendario=calendario;
		this.framePrincipale = framePrincipale;
	}
}
