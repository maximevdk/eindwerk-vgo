package be.khleuven.vgo.domain.elements;


public class ElementFactory {
	private static Element e;
	
	public static Element createElement(String type, String inhoud){
		if(type.equals("div")){
			e = new DivElement(inhoud);
		} else if(type.equals("p")){
			e = new PElement(inhoud);
		} else if(type.equals("h1")){
			e = new H1Element(inhoud);
		}
		return e;
	}

	public static Element createLinkElement(String type, String inhoud,
			String linkedTo) {
		if (type.equals("a")) {
			e = new AElement(inhoud, linkedTo);
		}
		return e;
	}
}
