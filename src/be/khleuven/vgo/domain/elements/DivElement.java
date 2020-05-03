package be.khleuven.vgo.domain.elements;


public class DivElement extends Element {
	
	public DivElement(String inhoud) {
		super(inhoud);
	}

	@Override
	protected void initialize() {
		initializeProperty("open", "<div ");
		// initializeProperty("id", "id=\"" + super.getId() + "\" ");
		// initializeProperty("class", "class=\"" + super.getKlasse() + "\" ");
		if (super.getStyle() != null) {
		initializeProperty("style", super.getStyle().generateHTML());
		}
		initializeProperty("close", ">");

		initializeProperty("inhoud", super.getInhoud());
		
		initializeProperty("end", "</div>");
	}
}
