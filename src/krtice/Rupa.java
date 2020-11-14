package krtice;
import java.awt.*;
import java.awt.event.*;
import java.awt.List.*;
import java.util.*;
import java.util.List;


public class Rupa extends Canvas implements Runnable {

	private static final Color boja= new Color(51, 0, 0);
	private Thread nit ; 
	protected Basta basta;
	private int cntMAX;
	private int cntCurr;
	private boolean radi; 
	private Zivotinja zivuljka;
	private boolean udarena=false;
	public Rupa(Basta b) {   
		basta = b; 
		 
	}
	public void postaviZiv(Zivotinja z) {
		zivuljka=z;
	}
	public Zivotinja dohvZivotinju() {
		return zivuljka;
	}
	public void udariZivotinju() {
		zivuljka.udarenaZivotinja();
		radi=false;
		udarena=true;
	}
	public void pokreni() {  
		if(nit!=null) {
			cntCurr=0;
			nit.start();
			radi=true;}
	}
	@Override
	public void run() {   
		try {    
			
			while(!Thread.interrupted()) {     
				synchronized (this){         
				Thread.sleep(100); 
				cntCurr++;
				if(cntCurr==cntMAX) {
					
					Thread.sleep(2000);
					if(udarena==false)((Krtica)zivuljka).pobeglaZivotinja();
					//this.basta.pojediPovrce();
					zaustavi();
				}
				repaint();
				
				}   
			}}catch (InterruptedException e) {}  
		} 
	public void posBrKoraka(int cnt) {cntMAX=cnt;}
	public int dohvBrKoraka() {return cntMAX;}
	public int dohvTrBrKoraka() {return cntCurr;}
	public synchronized void stvori() {
		if(nit!=null)
			zaustavi();
		nit=new Thread(this);
	}
	public synchronized void zaustavi() {
		nit.interrupt();
		nit=null;
		radi=false;
		if(zivuljka!=null /*&& udarena==false*/) 
				zivuljka.pobeglaZivotinja();
		repaint();
	}
	
	public synchronized boolean radi() {return radi;}
	public boolean jePokrenuta() {return radi;/* Thread.IsAllive, isinteruppted !+null;*/}

	@Override
	public void paint(Graphics g) {
		
		//super.paint(g);
		g.setColor(boja);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(zivuljka!=null)
		zivuljka.crtaj(this);
	}
	
}
