package be.khleuven.vgo.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import be.khleuven.vgo.db.DatabaseException;
import be.khleuven.vgo.db.HTMLReadAndWriter;
import be.khleuven.vgo.domain.elements.Element;
import be.khleuven.vgo.domain.elements.H1Element;
import be.khleuven.vgo.ui.utilities.DisplayHTMLPage;

public class Page {
	private String name;
	private String path;
	private List<String> inhoud;
	private HTMLReadAndWriter rw = new HTMLReadAndWriter();

	public Page(String pathname) {
		inhoud = new LinkedList<String>();
		init(null);
		this.setName("untitled");
		this.setPath(pathname);
		try {
			writePage();
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public Page(String pathname, String pagename, String title) {
		inhoud = new LinkedList<String>();
		init(title);
		this.setName(pagename);
		this.setPath(pathname);
		try {
			writePage();
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void init(String title) {
		Element e;
		inhoud.add(0, "<!DOCTYPE html>");
		inhoud.add(1, "<html>");
		inhoud.add(2, "<head>");

		if (title != null) {
			inhoud.add(3, "<title>" + title + "</title>");
		} else {
			inhoud.add(3, "<title>NO-TITLE</title>");
		}

		inhoud.add(4, "</head>");
		inhoud.add(5, "<body>");

		if (title != null) {
			e = new H1Element(title);
			inhoud.add(6, e.generateHTML());
		} else {
			e = new H1Element("You have to find a great title for this page!");
			inhoud.add(6, e.generateHTML());
		}

		inhoud.add(7,
				"<p>You just created a page. Now you can start editting it!</p>");

		inhoud.add(8, "</body>");
		inhoud.add(9, "</html>");
	}

	public void updateTitle(String newtitle) {
		inhoud.set(3, "<title>" + newtitle + "</title>");
	}

	public void display() throws DomainException, DatabaseException {
		writePage();
		File file = new File("Projects", this.getPathname() + "/pages/"
				+ this.getName() + ".html");
		DisplayHTMLPage disp = new DisplayHTMLPage(file);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPath(String pathname) {
		this.path = pathname;
	}

	public String getPathname() {
		return this.path;
	}

	public List<String> getInhoud() {
		return this.inhoud;
	}

	public void setInhoud(List<String> list) {
		this.inhoud = list;
	}

	public void writePage() throws DatabaseException {
		rw.writeToFile(getInhoud(), "Projects/" + this.getPathname()
				+ "/pages/" + this.getName());
	}

	public void addElement(int plaats, Element element) { // voegt 1 element toe
		if (plaats > 5 && plaats < inhoud.size() - 2) {
			inhoud.add(plaats, element.generateHTML());
		}else{
			inhoud.add(inhoud.size()-2, element.generateHTML());
		}
	}

	public void addElements(int plaats, List<Element> elementen) { // voegt verschillende elementen toe
		List<String> tmp = new ArrayList<String>();
		for (Element e : elementen) {
			tmp.add(e.generateHTML());
		}
		
		if (plaats > 5 && plaats < inhoud.size() - 2) {
			inhoud.addAll(plaats, tmp);
		} else{
			inhoud.addAll(inhoud.size()-2, tmp);
		}
	}
	

	public void removeElement(int plaats) {
		if (plaats > 5 && plaats < inhoud.size() - 2) {
			inhoud.remove(plaats);
		}
	}
	
	public String getTitleOutOfPage() {
		return inhoud.get(3).substring(7, inhoud.get(3).length() - 8);
	}

	@Override
	public boolean equals(Object o){
		boolean result = false;
		Page p = null;
		if(o != null && o instanceof Page){
			p = (Page)o;
		}
		if(this.getName().equals(p.getName()) && this.getPathname().equals(p.getPathname())){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + path.hashCode();
		hash = hash * 31 + name.hashCode();
		return hash;
	}


//	public static void main(String[] args) throws DatabaseException {
//		Page p = new Page("tmp", "tmp", "title");
//		PElement e = new PElement("Dit is nog een andere Paragraaf");
//		H1Element e1 = new H1Element(("Dit is een header baby"));
//		Style s = new Style();
//		Style s1 = new Style();
//		
//		s.setBackgroundColor(Color.BROWN);
//		s.setTextColor(Color.WHITE);
//		s.setFontSize("30em");
//		s.setFontStyle(FontStyle.NORMAL);
//		
//		//System.out.println(s.generateHTML());
//		s1.setTextColor(Color.YELLOW);
//		e.setStyle(s1);
//		e1.setStyle(s);
//		
//		
//		p.addElement(40, e1);
//		p.addElement(40, e);
//		
//		p.writePage();
//		try {
//			p.display();
//		} catch (DomainException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//	}
}
