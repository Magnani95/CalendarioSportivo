package gestoreSquadre;

import java.awt.print.*;
import java.awt.*;

public class Stampatore implements Printable{
	
	  public int print(Graphics g, PageFormat pf, int page)
	      throws PrinterException {
	    if (page > 0) {
	         return NO_SUCH_PAGE;
	    }

	    return PAGE_EXISTS;
	  }
	}
}
