package krtice;
import java.awt.*;
import java.awt.event.*;
import java.awt.List.*;
import java.util.*;
import java.util.List;


public abstract class Zivotinja {

	protected Rupa rupa;
	public Zivotinja(Rupa rupa) {this.rupa=rupa;}
	//public abstract boolean udarena(boolean u);
	public abstract void udarenaZivotinja();
	public abstract void pobeglaZivotinja();
	public abstract void crtaj(Rupa t);
	
}
