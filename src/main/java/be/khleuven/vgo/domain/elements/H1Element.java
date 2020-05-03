package be.khleuven.vgo.domain.elements;

public class H1Element extends Element {

    public H1Element(String inhoud) {
        super(inhoud);
    }

    @Override
    protected void initialize() {
        initializeProperty("open", "<h1 ");
        // initializeProperty("id", " id=\"" + super.getId() + "\" ");
        // initializeProperty("class", " class=\"" + super.getKlasse() + "\" ");
        if (super.getStyle() != null) {
            initializeProperty("style", super.getStyle().generateHTML());
        }
        initializeProperty("close", ">");
        initializeProperty("inhoud", super.getInhoud());
        initializeProperty("end", "</h1>");
    }

}
