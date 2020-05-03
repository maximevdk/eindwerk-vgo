package be.khleuven.vgo.domain.elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import be.khleuven.vgo.domain.style.Color;
import be.khleuven.vgo.domain.style.FontStyle;
import be.khleuven.vgo.domain.style.Style;

public abstract class Element {
	private Style style;
	// private String id;
	// private String klasse;
	
	private Map<String,String> properties;
	private List<Element> elementen;
	
	private String inhoud;
	
	// constructor
	public Element(String inhoud) {
		// style = new Style();
		properties = new HashMap<String, String>();
		elementen = new LinkedList<Element>();
		this.inhoud = inhoud;
		initialize();
		
	}
	
	

	public String getInhoud() {
		return inhoud;
	}

	public void setInhoud(String inhoud) {
		this.inhoud = inhoud;
		this.initializeProperty("inhoud", this.inhoud);
	}


	// setters and getters
	// public String getId() {
	// return id;
	// }
	//
	// public void setId(String id) {
	// this.id = id;
	// }
	//
	// public String getKlasse() {
	// if(klasse != null){
	// return klasse;
	// } else{
	// return "";
	// }
	// }

	// public void setKlasse(String klasse) {
	// this.klasse = klasse;
	// }

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
		initializeProperty("style", style.generateHTML());
	}

	// set style props
	public void setTextColor(Color color) {
		this.style.setTextColor(color);
	}

	public void setBackgroundColor(Color color) {
		this.style.setBackgroundColor(color);
	}

	public void setFontSize(String size) {
		this.style.setFontSize(size);
	}

	public void setFontStyle(FontStyle style) {
		this.style.setFontStyle(style);
	}
	
	public void addElement(Element element){
		this.elementen.add(element);
	}
	
	public void removeElement(Element e){
		int index = elementen.indexOf(e);
		this.elementen.remove(index);
	}
	
	protected List<Element> getElementen(){
		return this.elementen;
	}

	// init the element vb: <div id="vb"> </div>
	protected abstract void initialize();

	// returns the HTML
	public String generateHTML(){
		StringBuffer html = new StringBuffer();

		html.append(getProperty("open"));
		
		// if (getProperty("id") != null && !getProperty("id").equals("")) {
		// html.append(getProperty("id"));
		// }
//		
//		if(1 != 1){
//			html.append(getProperty("id"));
//		}
		
		// if (getProperty("class") != null && !getProperty("class").equals(""))
		// {
		// html.append(getProperty("class"));
		// }
//		if(1 != 1){
//			html.append(getProperty("class"));
//		}
		
		if (style != null) {
		html.append(getProperty("style"));
		}
		html.append(getProperty("close"));

		if(getProperty("inhoud") != null && !getProperty("inhoud").equals("")){
			html.append(getProperty("inhoud"));
		}
		for (Element e : getElementen()) {
			html.append(e.generateHTML());
		}

		html.append(getProperty("end"));

		return html.toString();
	}
	
	protected void initializeProperty(String property, String value){
		this.properties.put(property, value);
	}
	
	protected String getProperty(String property){
		return properties.get(property);
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof Element) {
			Element e = (Element)o;
			return this.getInhoud().equals(e.getInhoud());
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return this.inhoud;
	}
}
