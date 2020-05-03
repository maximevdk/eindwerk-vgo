package be.khleuven.vgo.ui.launcher;

import java.io.File;

import be.khleuven.vgo.ui.WelcomeScreen;

public class GraphicalWebsiteBuilder {	
	public static void main(String[] args) {
		WelcomeScreen w = new WelcomeScreen();
		
		File file = new File("Projects");
		boolean exists = file.exists();
		if(!exists){
			file.mkdir();
		}
	}
}
