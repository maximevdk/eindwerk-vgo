package be.khleuven.vgo.util;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public class MijnStijlen {
	String [] fonts;
	public MijnStijlen() {
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	}
	
	public String[] getFonts(){
		return this.fonts;
	}
	public static void main(String[] args) {
		MijnStijlen m = new MijnStijlen();
		for (String string : m.getFonts()) {
			System.out.println(string);
		}
	}
}
