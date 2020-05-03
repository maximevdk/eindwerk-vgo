package be.khleuven.vgo.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HTMLReadAndWriter implements Reader, Writer {

	@Override
	public void writeToFile(List<String> sourcecode, String filename) throws DatabaseException {
		File htmlpage = new File(filename+".html");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(htmlpage));
			for (String string : sourcecode) {
				bw.write(string);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			throw new DatabaseException("De file kan niet worden beschreven",e);
		}
		
	}

	@Override
	public List<String> readFromFile(File htmlpage) throws DatabaseException {
		List<String> sourcecode = new LinkedList<String>();
		try {
			//BufferedReader br = new BufferedReader(new FileReader(htmlpage+".html"));
			BufferedReader br = new BufferedReader(new FileReader(htmlpage));
			String line;

			while ((line = br.readLine()) != null) {
				sourcecode.add(line);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			throw new DatabaseException(
					"Het te lezen bestand is niet gevonden", e);
		} catch (IOException e) {
			throw new DatabaseException("Er is iets mis met de IO", e);
		}
		return sourcecode;
	}

}
