package krtice;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

public class Igra extends Frame {
	private static Igra igra;
	private Basta basta;
	private Label povrce= new Label("Povrce: 100");
	private Button kreni;
	private CheckboxGroup  nivo= new CheckboxGroup();
	Panel odaberi= new Panel(new GridLayout(3,1));
	private boolean uToku=false;

	public Igra() {
		super("Igra");
		basta= new Basta(4,4);
		setSize(500,400);
		dodajMeni();
		add(basta, BorderLayout.CENTER);
		add(side(), BorderLayout.EAST);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(uToku)
				    basta.zaustavi();
				dispose();
			}
		});
		setVisible(true);
	}

	public void promeniDugme() {
		kreni.setLabel("Kreni");
		//basta.zaustavi();
		uToku = false;
	}

	private Panel side() {
		Panel p = new Panel(new GridLayout(8,1,10,10));

		Label nivo = new Label("Tezina:");
		nivo.setAlignment(Label.CENTER);
		kreni = new Button("Kreni");

		p.add(nivo);
		CheckboxGroup nivoi = new CheckboxGroup();
		Checkbox lako = new Checkbox("Lako", nivoi, true);
		p.add(lako);
		Checkbox srednje = new Checkbox("Srednje", nivoi, false);
		p.add(srednje);
		Checkbox tesko = new Checkbox("Tesko", nivoi, false);
		p.add(tesko);
		basta.postaviLabelu(povrce);
		//p.add(basta.getLabela());
		p.add(kreni);
		Font font1=new Font(null,Font.BOLD,20);
		povrce.setFont(font1);
		povrce.setAlignment(Label.CENTER);
		p.add(povrce);
		kreni.addActionListener(e -> {
			if(!uToku) {
				if(nivoi.getSelectedCheckbox()!=null) {
					if(nivoi.getSelectedCheckbox()==lako)
						{
							basta.posInterval(1000);
							basta.posKorake(10);
							basta.pokreni();
						}
					if(nivoi.getSelectedCheckbox()==srednje)
						{
							basta.posInterval(750);
							basta.posKorake(8);
							basta.pokreni();
						}
					if(nivoi.getSelectedCheckbox()==tesko)
						{
							basta.posInterval(500);
							basta.posKorake(6);
							basta.pokreni();
						}
					kreni.setLabel("Stani");
					uToku=true;
				}
			} else {
				basta.zaustavi();
				kreni.setLabel("Kreni");
				uToku=false;
			}
		});
		return p;
	}

	private void dodajMeni() {   
		MenuBar bar = new MenuBar();   
		Menu menu = new Menu("Akcija");   
		setMenuBar(bar); 
		bar.add(menu);   
		//MenuItem novaIgra = new MenuItem( "Nova igra", new MenuShortcut('N'));   
		//menu.add(novaIgra);   
		//novaIgra.addActionListener( e->{basta.pokreni();
		///*azuriraj(true); */ });   
		//menu.addSeparator();   
		MenuItem zatvori = new MenuItem( "Zatvori",new MenuShortcut('Z'));   
		menu.add(zatvori);   
		zatvori.addActionListener( e->{/*basta.zaustavi();*/ dispose();}); 
	}
	public static Igra getInstance() {
		if(igra==null) igra= new Igra();
		return igra;
	}

	public static void main(String[] args) {
		getInstance();
	}
}
