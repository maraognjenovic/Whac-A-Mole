package krtice;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja{

	public Krtica(Rupa rupa) {
			super(rupa);	
	}
	public void udarenaZivotinja() {
		rupa.postaviZiv(null);	
	}
	public void pobeglaZivotinja() {
		rupa.basta.pojediPovrce();
		if(rupa.dohvZivotinju()!=null)
		rupa.postaviZiv(null);
		//rupa.basta.pojediPovrce();
	}
	@Override
	public void crtaj(Rupa t) {
		Graphics g = t.getGraphics();  
		g.setColor(Color.DARK_GRAY); 
		double proc=(double)t.dohvTrBrKoraka()/t.dohvBrKoraka();
		int x=t.getWidth()/2;
		int y=t.getHeight()/2;
		int sirina=(int)(t.getWidth()*proc);
		int visina=(int)(t.getHeight()*proc);
		
		g.fillOval(x-sirina/2,y-visina/2,sirina,visina);
	}

}
