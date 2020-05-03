package be.khleuven.vgo.domain.elements;

public class AElement extends Element {
	private String linkedTo;

	public AElement(String inhoud, String linkedTo) {
		super(inhoud);
		this.linkedTo = linkedTo;
	}

	@Override
	protected void initialize() {
		initializeProperty("open", "<a ");
		// initializeProperty("id", "id=\"" + this.getId() + "\" ");
		// initializeProperty("class", "class=\"" + this.getKlasse() + "\" ");
		if (super.getStyle() != null) {
			initializeProperty("style", this.getStyle().generateHTML());
		}
		initializeProperty("linkedTo", " href=\"" + this.linkedTo + "\" ");
		initializeProperty("close", ">");

		initializeProperty("inhoud", super.getInhoud());

		initializeProperty("end", "</a>");
	}

	@Override
	public String generateHTML() {
		StringBuffer html = new StringBuffer();

		html.append(getProperty("open"));


		html.append(" href=\"" + this.linkedTo + "\" ");

		if (super.getStyle() != null) {
			html.append(getProperty("style"));
		}
		html.append(getProperty("close"));

		if (getProperty("inhoud") != null && !getProperty("inhoud").equals("")) {
			html.append(getProperty("inhoud"));
		}
		for (Element e : getElementen()) {
			html.append(e.generateHTML());
		}

		html.append(getProperty("end"));

		return html.toString();
	}

}
