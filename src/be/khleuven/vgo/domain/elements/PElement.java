package be.khleuven.vgo.domain.elements;

public class PElement extends Element {
	public PElement(String inhoud) {
		super(inhoud);
	}

	@Override
	protected void initialize() {
		initializeProperty("open", "<p ");
		// initializeProperty("id", "id=\"" + this.getId() + "\" ");
		// initializeProperty("class", "class=\"" + this.getKlasse() + "\" ");
		if (super.getStyle() != null) {
		initializeProperty("style", this.getStyle().generateHTML());
		}
		initializeProperty("close", ">");

		initializeProperty("inhoud", super.getInhoud());
		
		initializeProperty("end", "</p>");
	}

}
