package krtice;
import java.awt.*;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Basta extends Panel implements Runnable{

	private static final Color boja=Color.GREEN;
	private int povrce=100;
	private int cekanje;
	private int koraci;
	private int kolona,vrsta;
	private Label lpovrce; 
	
	private Thread nit /*= new Thread(this)*/; 
	private Random rand/*=new Random(System.nanoTime())*/;
	private Rupa[][] rupice=new Rupa[vrsta][kolona]; 

	public Basta(int kolona, int vrsta) { 
		rupice=new Rupa[vrsta][kolona];  
		setLayout(new GridLayout(vrsta,kolona,15,15)); 
		this.setBackground(boja);
		this.kolona=kolona;
		this.vrsta=vrsta;
		rand=new Random(System.nanoTime());
		for (int i = 0; i < vrsta; i++)  
			for (int j = 0; j < kolona; j++)  {
			rupice[i][j] = new Rupa(this);
			rupice[i][j].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Rupa r=(Rupa)e.getComponent();
					r.udariZivotinju();
				}
			});
			add(rupice[i][j]);
			}
			
	}  

	public void rupaSlobodna() {
		
		//notifyAll();
	}
	public void posKorake(int cnt) {
		koraci=cnt;
		for (int i = 0; i < vrsta; i++)  
			for (int j = 0; j < kolona; j++)  
			rupice[i][j].posBrKoraka(koraci);	
	}
	public int dohvKorake() {return koraci;}
	public void pojediPovrce() {
		povrce--;	
	}
	public void posInterval(int cekanje) {this.cekanje=cekanje;}
	
	public void stvoriNit() {
		nit=new Thread(this);
		if(nit!=null) 
		nit.start();
	}
	
	public synchronized void pokreni() {
		povrce=100;
		//if(nit!=null) 
			//nit.start();
		
		azurirajLabelu();
		stvoriNit();
		//repaint();
	}
	public void smanjiInterval() {
		cekanje=(int)(cekanje*0.99);
	}

	@Override
	public void run() {
	try {
		while(!Thread.interrupted()) {
			Thread.sleep(cekanje);
				smanjiInterval();
				int v=0,k=0;
				while(rupice[v][k].jePokrenuta())
				  {
				   v=rand.nextInt(vrsta);
				   k=rand.nextInt(kolona);
				 }
				  azurirajLabelu();
				  rupice[v][k].postaviZiv(new Krtica(rupice[v][k]));
				  rupice[v][k].posBrKoraka(koraci);
				  rupice[v][k].stvori();
				  rupice[v][k].pokreni();
				        }
	}
	catch (InterruptedException e) {}
	}
	public void postaviLabelu(Label poeni) {
		lpovrce=poeni;
	}
	private void azurirajLabelu() {   
		if(lpovrce==null) return;  
		if(povrce==0) {lpovrce.setText("GAME OVER");this.zaustavi(); repaint();}
		else lpovrce.setText("Povrce: "+povrce);   
		
	}  
	public synchronized void zaustavi() {
		if(!nit.isInterrupted())
		{nit.interrupt();
		nit=null;
		repaint();}
	}
	
}
